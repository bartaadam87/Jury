package com.example.jury;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class JuryActivity extends ListActivity {

	// public static final String INFO = "info";
	// public static final String JURY_NAME = "juryName";
	public static final String ID = "id";

	DatabaseHandler db = new DatabaseHandler(this);
	DatabaseJuryHandler dbj = new DatabaseJuryHandler(this);

	ArrayList<String> names = new ArrayList<String>();

	// protected static String[] names2 = new String[] {
	// "1 / EXO / d 03 22 / 9 / 0 1 / 16.8.2012",
	// "10 / PER / f 33 / 9 / 1 0 / 2.4.2012",
	// "17 / MCO / GR  2 (n 09) / 2 / 1 0 / 29.11.2011",
	// "39 / MCO / GR  8 (ds 09 22) / 6 / 0 1 / 27.5.2010",
	// "83 / NFO / GR 4 (n 09 22) / 12 / 0 1 / 31.7.2012",
	// "209 / BUR / b / 4 / 1 0 / 11.2.2012",
	// "294 / SYS / OSH n 03 / 12 / 0 1 / 6.4.2011", };

	// public int lng = names.size();
	//
	// protected Class<?>[] classes = new Class[lng];
	// {
	// for (int i = 0; i < lng; i++) {
	// classes[i] = MainActivity.class;
	// }
	// }

	// static String juryName = "Simon Testovic";

	// Scrollovatelny klikatelny vypis jednotlivych jmen kocek
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.jury_layout);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,

		R.layout.title_layout);

		Jury jury = dbj.getJury(1);
		String juryName = jury.getName();
		TextView nameTv = (TextView) findViewById(R.id.title_right);
		displayName(juryName, nameTv);
		
		// Insert test to database
		// Log.d("Insert: ", "Inserting ..");
		// db.addReport(new Report("2", "MCO", "F22 (e)", "9", "01",
		// "23.9.1987",
		// "Type", "Small and round", "Blue and ugly", "Dont even ask",
		// "Coat nice", "Tail long", "Condition bad", "Impress good",
		// "Comment"));
		// db.addReport(new Report("3", "MCO", "F12", "3", "01", "23.9.1987",
		// "Type", "Small and round", "Blue and ugly", "Dont even ask",
		// "Coat nice", "Tail long", "Condition bad", "Impress good",
		// "Comment"));
		// db.addReport(new Report("7", "MCO", "b", "9", "01", "23.9.1987", "",
		// "", "", "", "", "", "", "", ""));
		// db.addReport(new Report("10", "PER", "ABC", "9", "01", "23.9.1987",
		// "",
		// "", "", "", "", "", "", "", ""));
		// db.addReport(new Report("11", "EXO", "G(11)", "9", "01", "23.9.1987",
		// "Type", "Small and round", "Blue and ugly", "Dont even ask",
		// "Coat nice", "Tail long", "Condition bad", "Impress good",
		// "Comment"));
		// db.addReport(new Report("15", "EXO", "ABC", "6", "01", "23.9.1987",
		// "Type", "Small and round", "Blue and ugly", "Dont even ask",
		// "Coat nice", "Tail long", "Condition bad", "Impress good",
		// "Comment"));

		// Reading from Log
		Log.d("Reading: ", "Reading stuff..");
		List<Report> reports = db.getAllReports();

		for (Report rep : reports) {
			String log = "Id: " + rep.getID() + " ,Cat No: " + rep.getNo()
					+ " ,Breed: " + rep.getBreed() + " ,Code: " + rep.getCode()
					+ " ,Class: " + rep.getCclass() + " ,Sex: " + rep.getSex()
					+ " ,Born: " + rep.getBorn() + " ,Type: " + rep.getType()
					+ " ,Head : " + rep.getHead() + " ,Eyes: " + rep.getEyes()
					+ " ,Ears :" + rep.getEars() + " ,Coat: " + rep.getCoat()
					+ " ,Tail: " + rep.getTail() + " ,Condition: "
					+ rep.getCondition() + " , Impress: " + rep.getImpress()
					+ " ,Comment: " + rep.getComment();
			Log.d("Report: ", log);
		}

		for (Report rep : reports) {
			String log = "No: " + rep.getNo() + " / " + "Breed: "
					+ rep.getBreed() + " / " + "Code: " + rep.getCode() + " / "
					+ "Class: " + rep.getCclass() + " / " + "Sex: "
					+ rep.getSex() + " / " + "Born: " + rep.getBorn();
			names.add(log);
		}
		//
		// Log.d("Insert: ", "Inserting ..");
		// dbj.addJury(new Jury("Simon Testikov"));

		// TextView juryNameTv = (TextView) getWindow().findViewById(
		// R.id.title_right);

		// Resources res = getResources();
		// String text = res.getString(R.string.jury_name, "Test");
		// juryNameTv.setText(text);

		// Intent i = getIntent();
		// String juryName = i.getStringExtra(JURY_NAME);
		// displayJuryName(juryName, juryNameTv);

		setListAdapter(new MyAdapater(this, R.layout.list_layout, names));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TextView juryNameTv = (TextView)
		// getWindow().findViewById(R.id.title_right);
		startMainActivity(position);
		this.finish();
	}

	// public String info = null;

	// Give MainActivity cat No.

	protected void startMainActivity(int position) {
		// String catInfo = names.get(position);
		Intent intent = new Intent(this, MainActivity.class);
		// intent.putExtra(MainActivity.INFO, catInfo);
		// intent.putExtra(MainActivity.JURY_NAME, juryName);
		int pos = position + 1;
		String post = Integer.toString(pos);
		intent.putExtra(MainActivity.ID, post);
		startActivity(intent);
	}

	// test nove tridy ktera bude mit obarvenou polozku
	class MyAdapater extends ArrayAdapter<String> {

		Context context;
		LayoutInflater mInflater;
		int resource;

		public MyAdapater(Context context, int resource, ArrayList<String> names) {
			super(context, resource, names);
			mInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.context = context;
			this.resource = resource;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				TextView v = (TextView) mInflater.inflate(resource, parent,
						false);
				Report rep = db.getReport(position + 1);
				v.setText(getItem(position));
				if (isFilled(rep)) {
					v.setBackgroundColor(getResources().getColor(R.color.green));
					v.setTextColor(Color.BLACK);
				}
				return v;
			}
			// return convertView;

			return super.getView(position, convertView, parent);
		}

		public boolean isFilled(Report rep) {
			if (rep.getType().isEmpty() || rep.getHead().isEmpty()
					|| rep.getEyes().isEmpty() || rep.getEars().isEmpty()
					|| rep.getCoat().isEmpty() || rep.getTail().isEmpty()
					|| rep.getCondition().isEmpty()
					|| rep.getImpress().isEmpty() || rep.getComment().isEmpty()) {
				return false;
			}
			return true;
		}

		// public View getView(View convertView, int position, ViewGroup parent)
		// {
		//
		// if (position == 0) {
		// getListView().setBackgroundColor(Color.GREEN);
		// } else {
		// }
		//
		// return convertView;
		// }
		//
		// @Override
		// public int getCount() {
		// // TODO Auto-generated method stub
		// return 0;
		// }
		//
		// @Override
		// public String getItem(int position) {
		// // TODO Auto-generated method stub
		// return null;
		// }
		//
		// @Override
		// public long getItemId(int position) {
		// // TODO Auto-generated method stub
		// return 0;
		// }
	}

	protected void displayName(String jn, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.jury_name, jn);
		tv.setText(text);
	}
}