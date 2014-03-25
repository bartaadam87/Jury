package com.fimuni.jury;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
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

		sendButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				messageToSend = writeJuryName().toJSONString()
						+ System.getProperty("line.separator");

				if (clientSender != null) {
					System.out.println(clientSender.getStatus());
				}
				clientSender = new ClientSender(context);
				clientSender.execute(messageToSend);

				dbj.addJury(new Jury(juryName.getText().toString()));
			}
		});

	}

	public JSONObject writeJuryName() {
		JSONObject object = new JSONObject();
		object.put("name", juryName.getText().toString());
		object.put("mac", getMacAddress(context));
		return object;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

//	@Override
//	protected void onStop() {
//		super.onStop();
//		try {
//			socket.close();
//			System.out.println("Socket closed!");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
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

	public class ClientSender extends AsyncTask<String, Void, Socket> {
		private String answer;
		private Context context;
		private BufferedWriter out;
		private BufferedReader in;
		// private static final String SERVER_IP = "192.168.1.102";
		private final String SERVER_IP = IPText.getText().toString();

		// public static final String ID = "id";

		// String id = UUID.randomUUID().toString();
		// private String id=String.valueOf(Math.random());
		// public void setId(String id){this.id=id;}
		// public String getId(){return this.id;}

		public ClientSender(Context context) {
			this.context = context;
			out = null;
			in = null;
		}

		@Override
		protected Socket doInBackground(String... params) {
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

				// out.write(getMacAddress(this.context));
				// System.out.println(id);

				out.write(params[0]);
				out.flush();
				answer = in.readLine() + System.getProperty("line.separator");
			} catch (IOException e) {
				e.printStackTrace();
			}

			return socket;
		}

		protected void onPostExecute(Socket socket) {
			if (socket != null) {
				JSONParser parser = new JSONParser();
				Object obj = null;
				try {
					obj = parser.parse(answer);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				JSONObject cat = (JSONObject) obj;

				//ODKOMENTOVAT PRO PRIJIMANI ZPRAV!
//				String value = cat.get("value").toString();
//
//				// System.out.println(no + " " + breed + " " + ems + " " +
//				// cclass + " " + sex + " " + born);
//
//				if (value.equals("sql")) {
//					String no = cat.get("no").toString();
//					String breed = cat.get("breed").toString();
//					String ems = cat.get("ems").toString();
//					String cclass = cat.get("class").toString();
//					String sex = cat.get("sex").toString();
//					String born = cat.get("born").toString();
//					String empty = "";
//					
//					db.addReport(new Report(no, breed, ems, cclass, sex, born,
//							empty, empty, empty, empty, empty, empty, empty,
//							empty, empty));
//				}
//
//				if (value.equals("msg")) {
//					String msg = cat.get("msg").toString();
//					Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
//				}

			} else {
				Toast.makeText(context, "Can't connect to server!",
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

	public void startJuryClicked(View button) {
		this.finish();
		startJuryActivity();
	}

	protected void startJuryActivity() {
		Intent intent = new Intent(this, JuryActivity.class);
		startActivity(intent);
	}
}