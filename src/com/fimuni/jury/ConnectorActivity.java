package com.fimuni.jury;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConnectorActivity extends Activity {

	// private static final String TAG = "MainActivity";

	private Button sendButton;
	private EditText juryName;
	private String messageToSend;
	private Context context;
	private Socket socket = null;
	ClientSender clientSender;
	// private TextView text;
	private static TextView IPText;

	// private static String SERVER_IP = "192.168.1.102";

	DatabaseHandler db = new DatabaseHandler(this);
	DatabaseJuryHandler dbj = new DatabaseJuryHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connector_layout);

		IPText = (EditText) findViewById(R.id.editTextIP);

		juryName = (EditText) findViewById(R.id.editTextName);
		sendButton = (Button) findViewById(R.id.sendButton);
		context = this.getApplicationContext();

		Jury jury = dbj.getJury(1);
		String savedName = jury.getName();
		String savedIp = jury.getIp();

		juryName.setText(savedName);
		IPText.setText(savedIp);

		sendButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				messageToSend = writeJuryName().toJSONString()
						+ System.getProperty("line.separator");

				if (clientSender != null) {
					System.out.println(clientSender.getStatus());
				}
				clientSender = new ClientSender(context);
				clientSender.execute(messageToSend);

				// kolo
				try {
					Boolean result = clientSender.get();
					if (result) {
						finish();
						startJuryActivity();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				// toc kolem
				// zrusis kolo

				String name = juryName.getText().toString();
				String ip = IPText.getText().toString();
				dbj.addJury(new Jury(name, ip));
			}
		});

	}

	public JSONObject writeJuryName() {
		JSONObject object = new JSONObject();
		object.put("name", juryName.getText().toString());
		object.put("mac", getMacAddress(context));
		object.put("value", "initialConnection");
		return object;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// @Override
	// protected void onStop() {
	// super.onStop();
	// try {
	// socket.close();
	// System.out.println("Socket closed!");
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	// public JSONObject writeJSON() throws JSONException {
	// JSONObject object = new JSONObject();
	// object.put("no", "1");
	// object.put("breed", messageText.getText().toString());
	// object.put("ems", "MCO");
	// object.put("class", 1);
	// object.put("sex", 01);
	// object.put("born", "23.1.2011");
	// return object;
	// }

	public class ClientSender extends AsyncTask<String, Void, Boolean> {
		private String answer;
		private Context context;
		private BufferedWriter out;
		private BufferedReader in;
		private final String SERVER_IP = IPText.getText().toString();

		public ClientSender(Context context) {
			this.context = context;
			out = null;
			in = null;
		}

		@Override
		protected Boolean doInBackground(String... params) {
			try {
				if (socket == null) {
					socket = new Socket(SERVER_IP, 3344);

				}
				System.out.println("IP connected");
				System.out.println("port connected");

				out = new BufferedWriter(new OutputStreamWriter(
						socket.getOutputStream()));
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				out.write(params[0]);
				out.flush();
				answer = in.readLine() + System.getProperty("line.separator");

				String name = juryName.getText().toString();

				JSONParser parser = new JSONParser();
				Object obj = null;
				try {
					obj = parser.parse(answer);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				JSONObject cats = (JSONObject) obj;
				
				if (cats.containsKey(name)) {
					System.out.println("Jury name found");
					System.out.println("answer: " + answer);

					db.cleanTable();
					
					// String str = (String) cats.get("Adam Barta").toString();
					// String str2 = str.replace("[", "");
					// String str3 = str2.replace("]", "");
					// System.out.println("str: " + str3);

					JSONArray list = (JSONArray) cats.get(name);
					System.out.println("arr: " + list.toJSONString());

					for (int i = 0; i < list.size(); i++) {
						JSONObject cat = (JSONObject) list.get(i);
//						System.out.println("object " + i + ": " + cat);

						String no = cat.get("no").toString();
						String breed = cat.get("breed").toString();
						String ems = cat.get("ems").toString();
						String cclass = cat.get("class").toString();
						String sex = cat.get("sex").toString();
						String born = cat.get("born").toString();
						String empty = "";

						db.addReport(new Report(no, breed, ems, cclass, sex,
								born, empty, empty, empty, empty, empty, empty,
								empty, empty, empty, empty, empty, "false",
								"false", empty, empty, empty));
					}

				} else {
					System.out.println("Jury name not found!");
//					socket.close();
					return false;
				}

				// JSONParser parser = new JSONParser();
				// Object obj = null;
				// try {
				// obj = parser.parse(answer);
				// } catch (ParseException e) {
				// e.printStackTrace();
				// }
				// JSONObject cat = (JSONObject) obj;
				//
				// String value = cat.get("value").toString();
				//
				// // PRESUNOIT DO doInBackground - zustane tu jen vykreslovani
				// // toustu
				// if (value.equals("sql")) {
				// String no = cat.get("no").toString();
				// String breed = cat.get("breed").toString();
				// String ems = cat.get("ems").toString();
				// String cclass = cat.get("class").toString();
				// String sex = cat.get("sex").toString();
				// String born = cat.get("born").toString();
				// String empty = "";
				//
				// db.addReport(new Report(no, breed, ems, cclass, sex, born,
				// empty, empty, empty, empty, empty, empty, empty,
				// empty, empty, empty, empty, "false", "false",
				// empty, empty, empty));
				// }

				// if (value.equals("msg")) {
				// String msg = cat.get("msg").toString();
				// Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
				// try {
				// socket.close();
				// } catch (IOException e) {
				// e.printStackTrace();
				// }
				// }
				System.out
						.println("I'm cleaning my stuff...closing socket NOW!");
				socket.close();
			} catch (IOException e) {
				System.out
						.println("Something wrong - Error in *Do in background*");
				return false;
			}
			return true;
		}

		protected void onPostExecute(Boolean result) {
			if (result) {
				Toast.makeText(context, "Success - Starting!", Toast.LENGTH_LONG)
						.show();
			} else {
				Toast.makeText(context, "Wrong IP address or Jury name!",
						Toast.LENGTH_LONG).show();
			}
		}

	}

	public String getMacAddress(Context context) {
		WifiManager wimanager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		String macAddress = wimanager.getConnectionInfo().getMacAddress();
		if (macAddress == null) {
			macAddress = "Device don't have mac address or wi-fi is disabled";
		}
		return macAddress;
	}

	// public void startJuryClicked(View button) {
	// this.finish();
	// startJuryActivity();
	// }

	protected void startJuryActivity() {
		Intent intent = new Intent(this, JuryActivity.class);
		startActivity(intent);
	}
}