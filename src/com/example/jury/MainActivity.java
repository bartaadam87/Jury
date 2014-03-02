package com.example.jury;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.jury.MESSAGE";
	// public static final String INFO = "info";
	// public static final String JURY_NAME = "juryName";
	//
	// public static final String EXTRA_TYPE = "type";
	// public static final String EXTRA_HEAD = "head";
	public static final String ID = "id";

	DatabaseHandler db = new DatabaseHandler(this);
	DatabaseJuryHandler dbj = new DatabaseJuryHandler(this);
	
	// prace s formularem

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.title_layout);

		// final TextView titleLeft = (TextView) findViewById(R.id.title_left);

		// final TextView titleRight = (TextView)
		// findViewById(R.id.title_right);
		// titleLeft.setText("@string/app_name");
		// titleRight.setText(juryName);

		Spinner mark = (Spinner) findViewById(R.id.mark);
		ArrayAdapter<CharSequence> markadapter = ArrayAdapter
				.createFromResource(this, R.array.mark_arrays,
						R.layout.spinner_layout);
		markadapter.setDropDownViewResource(R.layout.spinner_layout);
		mark.setAdapter(markadapter);

		Jury jury = dbj.getJury(1);
		String juryName = jury.getName();
		TextView nameTv = (TextView) findViewById(R.id.title_right);
		displayName(juryName, nameTv);
		
		// int catId = Integer.parseInt(id);
		// String info = i.getStringExtra(INFO);
		// String juryName = i.getStringExtra(JURY_NAME);
		//
		// String[] parts = info.split(" / ");
//		DatabaseHandler db = new DatabaseHandler(this);

		Intent i = getIntent();
		String id = i.getStringExtra(ID);

		Report rep = db.getReport(Integer.parseInt(id));
		String catNo = rep.getNo();
		String catBreed = rep.getBreed();
		String catCode = rep.getCode();
		String catClass = rep.getCclass();
		String catSex = rep.getSex();
		String catBorn = rep.getBorn();
		String catType = rep.getType();
		String catHead = rep.getHead();
		String catEyes = rep.getEyes();
		String catEars = rep.getEars();
		String catCoat = rep.getCoat();
		String catTail = rep.getTail();
		String catCondition = rep.getCondition();
		String catImpress = rep.getImpress();
		String catComment = rep.getComment();

		TextView catNoTv = (TextView) findViewById(R.id.catNr);
		TextView catBreedTv = (TextView) findViewById(R.id.catBreed);
		TextView catCodeTv = (TextView) findViewById(R.id.catCode);
		TextView catClassTv = (TextView) findViewById(R.id.catClass);
		TextView catSexTv = (TextView) findViewById(R.id.catSex);
		TextView catBornTv = (TextView) findViewById(R.id.catBorn);
		// TextView juryNameTv = (TextView) findViewById(R.id.title_right);

		EditText type = (EditText) findViewById(R.id.type);
		EditText head = (EditText) findViewById(R.id.head);
		EditText eyes = (EditText) findViewById(R.id.eyes);
		EditText ears = (EditText) findViewById(R.id.ears);
		EditText coat = (EditText) findViewById(R.id.coat);
		EditText tail = (EditText) findViewById(R.id.tail);
		EditText condition = (EditText) findViewById(R.id.condition);
		EditText impress = (EditText) findViewById(R.id.impress);
		EditText comment = (EditText) findViewById(R.id.comment);

		// int No = Integer.parseInt(catNo);
		//
		displayNr(catNo, catNoTv);
		displayBreed(catBreed, catBreedTv);
		displayCode(catCode, catCodeTv);
		displayClass(catClass, catClassTv);
		displaySex(catSex, catSexTv);
		displayBorn(catBorn, catBornTv);
		// displayJuryName(juryName, juryNameTv);

		type.setText(catType);
		head.setText(catHead);
		eyes.setText(catEyes);
		ears.setText(catEars);
		coat.setText(catCoat);
		tail.setText(catTail);
		condition.setText(catCondition);
		impress.setText(catImpress);
		comment.setText(catComment);

		// Funkcni vypis z databaze!
		// DatabaseHandler db = new DatabaseHandler(this);
		// Report rep2 = db.getReport(1);
		// EditText typeEt = (EditText) findViewById(R.id.type);
		// typeEt.setText(rep2.getType());

		Spinner caption = (Spinner) findViewById(R.id.caption);
		ArrayAdapter<CharSequence> captionadapter = ArrayAdapter
				.createFromResource(this, selectCatClass(catClass),
						R.layout.spinner_layout);
		captionadapter.setDropDownViewResource(R.layout.spinner_layout);
		caption.setAdapter(captionadapter);
	}

	protected void displayNr(String nr, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.nr, nr);
		tv.setText(text);
	}

	protected void displayBreed(String br, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.br, br);
		tv.setText(text);
	}

	protected void displayCode(String co, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.co, co);
		tv.setText(text);
	}

	protected void displayClass(String cl, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.cl, cl);
		tv.setText(text);
	}

	protected void displaySex(String se, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.se, se);
		tv.setText(text);
	}

	protected void displayBorn(String bo, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.bo, bo);
		tv.setText(text);
	}

	protected void displayName(String jn, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.jury_name, jn);
		tv.setText(text);
	}

	// DOPLNIT!!
	private int selectCatClass(String cc) {
		if (cc.equals("6"))
			return R.array.caption_arrays_class1;

		if (cc.equals("9"))
			return R.array.caption_arrays_class2;

		return 0;
	}

	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.activity_main);
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	protected void startListActivity() {
		Intent intent = new Intent(this, JuryActivity.class);
		startActivity(intent);
	}
	
	public void backButtonClicked(View button) {
		Toast.makeText(this, R.string.backToast, Toast.LENGTH_LONG).show();
		this.finish();
		startListActivity();
	}

	public void buttonClicked(View button) {
		Intent i = getIntent();
		String id = i.getStringExtra(ID);
		EditText typeEt = (EditText) findViewById(R.id.type);
		String getCatType = typeEt.getText().toString();
		EditText headEt = (EditText) findViewById(R.id.head);
		String getCatHead = headEt.getText().toString();
		EditText eyesEt = (EditText) findViewById(R.id.eyes);
		String getCatEyes = eyesEt.getText().toString();
		EditText earsEt = (EditText) findViewById(R.id.ears);
		String getCatEars = earsEt.getText().toString();
		EditText coatEt = (EditText) findViewById(R.id.coat);
		String getCatCoat = coatEt.getText().toString();
		EditText tailEt = (EditText) findViewById(R.id.tail);
		String getCatTail = tailEt.getText().toString();
		EditText conditionEt = (EditText) findViewById(R.id.condition);
		String getCatCondition = conditionEt.getText().toString();
		EditText impressEt = (EditText) findViewById(R.id.impress);
		String getCatImpress = impressEt.getText().toString();
		EditText commentEt = (EditText) findViewById(R.id.comment);
		String getCatComment = commentEt.getText().toString();
		db.edit(Integer.parseInt(id), getCatType, getCatHead, getCatEyes,
				getCatEars, getCatCoat, getCatTail, getCatCondition,
				getCatImpress, getCatComment);
		startListActivity();
		Toast.makeText(this, R.string.sendToast, Toast.LENGTH_LONG).show();
		this.finish();
	}

}
