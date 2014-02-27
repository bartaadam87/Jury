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
	public static final String INFO = "info";
	public static final String JURY_NAME = "juryName";

	public static final String EXTRA_TYPE = "type";
	public static final String EXTRA_HEAD = "head";

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

		Spinner caption = (Spinner) findViewById(R.id.caption);
		ArrayAdapter<CharSequence> captionadapter = ArrayAdapter
				.createFromResource(this, selectCatClass(),
						R.layout.spinner_layout);
		captionadapter.setDropDownViewResource(R.layout.spinner_layout);
		caption.setAdapter(captionadapter);

		Intent i = getIntent();
		String info = i.getStringExtra(INFO);
		String juryName = i.getStringExtra(JURY_NAME);

		String[] parts = info.split(" / ");
		String catNo = parts[0];
		String catBreed = parts[1];
		String catCode = parts[2];
		String catClass = parts[3];
		String catSex = parts[4];
		String catBorn = parts[5];

		TextView catNoTv = (TextView) findViewById(R.id.catNr);
		TextView catBreedTv = (TextView) findViewById(R.id.catBreed);
		TextView catCodeTv = (TextView) findViewById(R.id.catCode);
		TextView catClassTv = (TextView) findViewById(R.id.catClass);
		TextView catSexTv = (TextView) findViewById(R.id.catSex);
		TextView catBornTv = (TextView) findViewById(R.id.catBorn);
		TextView juryNameTv = (TextView) findViewById(R.id.title_right);

		int No = Integer.parseInt(catNo);

		displayNr(No, catNoTv);
		displayBreed(catBreed, catBreedTv);
		displayCode(catCode, catCodeTv);
		displayClass(catClass, catClassTv);
		displaySex(catSex, catSexTv);
		displayBorn(catBorn, catBornTv);
		displayJuryName(juryName, juryNameTv);

	}

	protected void displayNr(int nr, TextView tv) {
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

	protected void displayJuryName(String jn, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.jury_name, jn);
		tv.setText(text);
	}

	private int catClass = 2;

	private int selectCatClass() {
		if (catClass == 1)
			return R.array.caption_arrays_class1;

		if (catClass == 2)
			return R.array.caption_arrays_class2;

		return catClass;
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

	public void backButtonClicked(View button) {
		Toast.makeText(this, R.string.backToast, Toast.LENGTH_LONG).show();
		this.finish();
	}

	public void buttonClicked(View button) {
		System.out.println(((EditText) this.findViewById(R.id.type)).getText()
				.toString());
		System.out.println(((EditText) this.findViewById(R.id.head)).getText()
				.toString());
		Toast.makeText(this, R.string.wellcome_toast, Toast.LENGTH_LONG).show();
		this.finish();
	}

}
