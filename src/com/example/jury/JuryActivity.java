package com.example.jury;

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

	public static final String INFO = "info";
	public static final String JURY_NAME = "juryName";

	// Docasne pole ukazkovych jmen - pozdeji pribude dynamicke cteni jmen
	// napriklad z JSON
	// Idealne nejaka funkce jako GetNames - Vytahne jmena / oznaceni
	// jednotlivych kocek pro daneho rozhodciho z baliku kteyr posle server
	protected static String[] names = new String[] {
			"1 / EXO / d 03 22 / 9 / 0 1 / 16.8.2012",
			"10 / PER / f 33 / 9 / 1 0 / 2.4.2012",
			"17 / MCO / GR  2 (n 09) / 2 / 1 0 / 29.11.2011",
			"39 / MCO / GR  8 (ds 09 22) / 6 / 0 1 / 27.5.2010",
			"83 / NFO / GR 4 (n 09 22) / 12 / 0 1 / 31.7.2012",
			"209 / BUR / b / 4 / 1 0 / 11.2.2012",
			"294 / SYS / OSH n 03 / 12 / 0 1 / 6.4.2011", };

	private static int lng = names.length;

	protected static Class<?>[] classes = new Class[lng];
	{
		for (int i = 0; i < lng; i++) {
			classes[i] = MainActivity.class;
		}
	}

	static String juryName = "Simon Testovic";

	// Scrollovatelny klikatelny vypis jednotlivych jmen kocek
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.jury_layout);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,

		R.layout.title_layout);

		DatabaseHandler db = new DatabaseHandler(this);

		/**
		 * CRUD Operations
		 * */
		// Insert test to database
		Log.d("Insert: ", "Inserting ..");
		db.addReport(new Report("MCO", "Big Head", "Round Eyes", "Big Ears",
				"Coat Wet", "Tail Big", "Condition Good", "", "Comment"));
		db.addReport(new Report("MCO", "Big Head", "Round Eyes", "Big Ears",
				"Coat Wet", "Tail Big", "Condition Good", "", "Comment"));
		db.addReport(new Report("MCO", "Big Head", "Round Eyes", "Big Ears",
				"Coat Wet", "Tail Big", "Condition Good", "", "Comment"));
		
		// Reading from Log
		Log.d("Reading: ", "Reading stuff..");
		List<Report> reports = db.getAllReports();

		for (Report rep : reports) {
			String log = "Id: " + rep.getID() + " ,Type: " + rep.getType()
					+ " ,Head: " + rep.getHead() + " ,Eyes:" + rep.getEyes()
					+ " ,Ears:" + rep.getEars() + " ,Coat:" + rep.getCoat()
					+ " ,Tail:" + rep.getTail() + " ,Condition:"
					+ rep.getCondition() + " ,Impress:" + rep.getImpress()
					+ " ,Comment:" + rep.getComment();
			// Writing Contacts to log
			Log.d("Report: ", log);
		}

		TextView juryNameTv = (TextView) getWindow().findViewById(
				R.id.title_right);

		// Resources res = getResources();
		// String text = res.getString(R.string.jury_name, "Test");
		// juryNameTv.setText(text);

		Intent i = getIntent();
		String juryName = i.getStringExtra(JURY_NAME);
		displayJuryName(juryName, juryNameTv);

		setListAdapter(new MyAdapater(this, R.layout.list_layout, names));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TextView juryNameTv = (TextView)
		// getWindow().findViewById(R.id.title_right);
		startMainActivity(position);
	}

	public String info = null;

	// Give MainActivity cat No.

	protected void startMainActivity(int position) {
		String catInfo = names[position];

		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra(MainActivity.INFO, catInfo);
		intent.putExtra(MainActivity.JURY_NAME, juryName);
		startActivity(intent);
	}

	// test nove tridy ktera bude mit obarvenou polozku
	class MyAdapater extends ArrayAdapter<String> {

		Context context;
		LayoutInflater mInflater;
		int resource;

		public MyAdapater(Context context, int resource, String[] objects) {
			super(context, resource, objects);
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
				v.setText(getItem(position));
				if (position == 3) {
					v.setBackgroundColor(getResources().getColor(R.color.green));
					v.setTextColor(Color.BLACK);
				}
				return v;
			}
			// return convertView;

			return super.getView(position, convertView, parent);
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

	protected void displayJuryName(String jn, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.jury_name, jn);
		tv.setText(text);
	}

}