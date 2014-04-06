//package com.fimuni.jury;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.Socket;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import android.content.Context;
//import android.os.AsyncTask;
//import android.widget.Toast;
//
//public class ReportSender extends AsyncTask<String, Void, Boolean> {
//		private String answer;
//		private Context context;
//		private BufferedWriter out;
//		private BufferedReader in;
//
//		public ReportSender(Context context) {
//			this.context = context;
//			out = null;
//			in = null;
//		}
//
//		@Override
//		protected Boolean doInBackground(String... params) {
//			ConnectorActivity conn = (ConnectorActivity) new ConnectorActivity();
//			Socket socket = (Socket) socket;
//			String SERVER_IP = (String) SERVER_IP;
//			try {
//				if (socket == null) {
//					socket = new Socket(SERVER_IP, 3344);
//
//				}
//				System.out.println("IP connected");
//				System.out.println("port connected");
//
//				out = new BufferedWriter(new OutputStreamWriter(
//						socket.getOutputStream()));
//				in = new BufferedReader(new InputStreamReader(
//						socket.getInputStream()));
//
//				out.write(params[0]);
//				out.flush();
//				answer = in.readLine() + System.getProperty("line.separator");
//
//				String name = juryName.getText().toString();
//
//				if (answer.contains(name)) {
//					System.out.println("Jury name found");
//					System.out.println("answer: " + answer);
//
//					db.cleanTable();
//					
//					JSONParser parser = new JSONParser();
//					Object obj = null;
//					try {
//						obj = parser.parse(answer);
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
//					JSONObject cats = (JSONObject) obj;
//
//					// String str = (String) cats.get("Adam Barta").toString();
//					// String str2 = str.replace("[", "");
//					// String str3 = str2.replace("]", "");
//					// System.out.println("str: " + str3);
//
//					JSONArray list = (JSONArray) cats.get(name);
//					System.out.println("arr: " + list.toJSONString());
//
//					for (int i = 0; i < list.size(); i++) {
//						JSONObject cat = (JSONObject) list.get(i);
////						System.out.println("object " + i + ": " + cat);
//
//						String no = cat.get("no").toString();
//						String breed = cat.get("breed").toString();
//						String ems = cat.get("ems").toString();
//						String cclass = cat.get("class").toString();
//						String sex = cat.get("sex").toString();
//						String born = cat.get("born").toString();
//						String empty = "";
//
//						db.addReport(new Report(no, breed, ems, cclass, sex,
//								born, empty, empty, empty, empty, empty, empty,
//								empty, empty, empty, empty, empty, "false",
//								"false", empty, empty, empty));
//					}
//
//				} else {
//					System.out.println("Jury name not found!");
//				}
//
//				// JSONParser parser = new JSONParser();
//				// Object obj = null;
//				// try {
//				// obj = parser.parse(answer);
//				// } catch (ParseException e) {
//				// e.printStackTrace();
//				// }
//				// JSONObject cat = (JSONObject) obj;
//				//
//				// String value = cat.get("value").toString();
//				//
//				// // PRESUNOIT DO doInBackground - zustane tu jen vykreslovani
//				// // toustu
//				// if (value.equals("sql")) {
//				// String no = cat.get("no").toString();
//				// String breed = cat.get("breed").toString();
//				// String ems = cat.get("ems").toString();
//				// String cclass = cat.get("class").toString();
//				// String sex = cat.get("sex").toString();
//				// String born = cat.get("born").toString();
//				// String empty = "";
//				//
//				// db.addReport(new Report(no, breed, ems, cclass, sex, born,
//				// empty, empty, empty, empty, empty, empty, empty,
//				// empty, empty, empty, empty, "false", "false",
//				// empty, empty, empty));
//				// }
//
//				// if (value.equals("msg")) {
//				// String msg = cat.get("msg").toString();
//				// Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
//				// try {
//				// socket.close();
//				// } catch (IOException e) {
//				// e.printStackTrace();
//				// }
//				// }
//				System.out
//						.println("I'm cleaning my stuff...closing socket NOW!");
//				socket.close();
//			} catch (IOException e) {
//				System.out
//						.println("Something wrong - Error in *Do in background*");
//				return false;
//			}
//			return true;
//		}
//
//		protected void onPostExecute(Boolean result) {
//			if (result) {
//				Toast.makeText(context, "Everything OK!", Toast.LENGTH_LONG)
//						.show();
//			} else {
//				Toast.makeText(context, "Can't connect to server!",
//						Toast.LENGTH_LONG).show();
//			}
//		}
//
//	}