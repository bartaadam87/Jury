package com.fimuni.jury;

import org.json.simple.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fimuni.jury.ConnectorActivity.ClientSender;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.jury.MESSAGE";
	// public static final String INFO = "info";
	// public static final String JURY_NAME = "juryName";
	//
	// public static final String EXTRA_TYPE = "type";
	// public static final String EXTRA_HEAD = "head";
	public static final String ID = "id";

	ConnectorActivity connect = new ConnectorActivity();
	ClientSender clientSender;

	DatabaseHandler db = new DatabaseHandler(this);
	DatabaseJuryHandler dbj = new DatabaseJuryHandler(this);
	DatabasePointsHandler dbp = new DatabasePointsHandler(this);

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

		Spinner rank = (Spinner) findViewById(R.id.rank);
		ArrayAdapter<CharSequence> rankadapter = ArrayAdapter
				.createFromResource(this, R.array.rank_arrays,
						R.layout.spinner_layout);
		rankadapter.setDropDownViewResource(R.layout.spinner_layout);
		rank.setAdapter(rankadapter);

		Jury jury = dbj.getJury(1);
		String juryName = jury.getName();
		TextView nameTv = (TextView) findViewById(R.id.title_right);
		displayName(juryName, nameTv);

		nameTv.setTextColor(getResources().getColor(R.color.white));
		TextView titleLeft = (TextView) findViewById(R.id.title_left);
		titleLeft.setTextColor(getResources().getColor(R.color.white));

		// int catId = Integer.parseInt(id);
		// String info = i.getStringExtra(INFO);
		// String juryName = i.getStringExtra(JURY_NAME);
		//
		// String[] parts = info.split(" / ");
		// DatabaseHandler db = new DatabaseHandler(this);

		Intent i = getIntent();
		String id = i.getStringExtra(ID);

		// Get stuff to fill form header
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
		String catMark = rep.getMark();
		String catRank = rep.getRank();
		String catBiv = rep.getBiv();
		String catNomination = rep.getNomination();
		String catNote = rep.getNote();
		String catTitle = rep.getTitle();
		String catReason = rep.getReason();
		
		// points for each breed
		Standard standard = dbp.getStandard(catBreed);
		String headPoints = standard.getHead();
		String eyesPoints = standard.getEyes();
		String earsPoints = standard.getEars();
		String bodyPoints = standard.getBody();
		String coatPoints = standard.getCoat();
		String tailPoints = standard.getTail();
		String conditionPoints = standard.getCondition();

		TextView catNoTv = (TextView) findViewById(R.id.catNr);
		TextView catBreedTv = (TextView) findViewById(R.id.catBreed);
		TextView catCodeTv = (TextView) findViewById(R.id.catCode);
		TextView catClassTv = (TextView) findViewById(R.id.catClass);
		TextView catSexTv = (TextView) findViewById(R.id.catSex);
		TextView catBornTv = (TextView) findViewById(R.id.catBorn);
		// TextView juryNameTv = (TextView) findViewById(R.id.title_right);
		TextView headPointsTv = (TextView) findViewById(R.id.headPoints);
		TextView eyesPointsTv = (TextView) findViewById(R.id.eyesPoints);
		TextView earsPointsTv = (TextView) findViewById(R.id.earsPoints);
		TextView bodyPointsTv = (TextView) findViewById(R.id.bodyPoints);
		TextView coatPointsTv = (TextView) findViewById(R.id.coatPoints);
		TextView tailPointsTv = (TextView) findViewById(R.id.tailPoints);
		TextView conditionPointsTv = (TextView) findViewById(R.id.conditionPoints);

		EditText type = (EditText) findViewById(R.id.type);
		EditText head = (EditText) findViewById(R.id.head);
		EditText eyes = (EditText) findViewById(R.id.eyes);
		EditText ears = (EditText) findViewById(R.id.ears);
		EditText coat = (EditText) findViewById(R.id.coat);
		EditText tail = (EditText) findViewById(R.id.tail);
		EditText condition = (EditText) findViewById(R.id.condition);
		EditText impress = (EditText) findViewById(R.id.impress);
		EditText comment = (EditText) findViewById(R.id.comment);
		EditText reason = (EditText) findViewById(R.id.reason);
		
		setMark(catMark);
		setRank(catRank);
		
		checkBiv(catBiv);
		checkNomination(catNomination);
		
		EditText note = (EditText) findViewById(R.id.note);

		// int No = Integer.parseInt(catNo);
		//
		displayNr(catNo, catNoTv);
		displayBreed(catBreed, catBreedTv);
		displayCode(catCode, catCodeTv);
		displayClass(catClass, catClassTv);
		displaySex(catSex, catSexTv);
		displayBorn(catBorn, catBornTv);
		// displayJuryName(juryName, juryNameTv);
		displayBodyP(bodyPoints, bodyPointsTv);
		displayHeadP(headPoints, headPointsTv);
		displayEyesP(eyesPoints, eyesPointsTv);
		displayEarsP(earsPoints, earsPointsTv);
		displayCoatP(coatPoints, coatPointsTv);
		displayTailP(tailPoints, tailPointsTv);
		displayConditionP(conditionPoints, conditionPointsTv);

		type.setText(catType);
		head.setText(catHead);
		eyes.setText(catEyes);
		ears.setText(catEars);
		coat.setText(catCoat);
		tail.setText(catTail);
		condition.setText(catCondition);
		impress.setText(catImpress);
		comment.setText(catComment);
		note.setText(catNote);
		reason.setText(catReason);

		// Funkcni vypis z databaze!
		// DatabaseHandler db = new DatabaseHandler(this);
		// Report rep2 = db.getReport(1);
		// EditText typeEt = (EditText) findViewById(R.id.type);
		// typeEt.setText(rep2.getType());
		
		findViewById(R.id.reason).setVisibility(View.INVISIBLE);

		Spinner title = (Spinner) findViewById(R.id.title);
		ArrayAdapter<CharSequence> titleadapter = ArrayAdapter
				.createFromResource(this, selectCatClass(catClass),
						R.layout.spinner_layout);
		titleadapter.setDropDownViewResource(R.layout.spinner_layout);
		title.setAdapter(titleadapter);
		
		title.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 0) {
					findViewById(R.id.reason).setVisibility(View.VISIBLE);
				} else {
					findViewById(R.id.reason).setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		setCatTitle(catTitle);
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

	protected void displayBodyP(String bp, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.bodyPoints, bp);
		tv.setText(text);
	}

	protected void displayHeadP(String hp, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.headPoints, hp);
		tv.setText(text);
	}

	protected void displayEyesP(String ep, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.eyesPoints, ep);
		tv.setText(text);
	}

	protected void displayEarsP(String ep, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.earsPoints, ep);
		tv.setText(text);
	}

	protected void displayCoatP(String cp, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.coatPoints, cp);
		tv.setText(text);
	}

	protected void displayTailP(String tp, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.tailPoints, tp);
		tv.setText(text);
	}

	protected void displayConditionP(String cp, TextView tv) {
		Resources res = getResources();
		String text = res.getString(R.string.conditionPoints, cp);
		tv.setText(text);
	}
	
	protected void checkBiv(String str) {
	    if (str.equals("true")) {
	        CheckBox checkBox = (CheckBox) findViewById(R.id.biv);
	        checkBox.setChecked(true);
	    }
	}
	
	protected void checkNomination(String str) {
	    if (str.equals("true")) {
	        CheckBox checkBox = (CheckBox) findViewById(R.id.nomination);
	        checkBox.setChecked(true);
	    }
	}
	
	protected void setMark(String str) {
		Spinner mark = (Spinner) findViewById(R.id.mark);
		if (str.equals("EX")) {
			mark.setSelection(0);
		}
		if (str.equals("VG")) {
			mark.setSelection(1);
		}
		if (str.equals("G")) {
			mark.setSelection(2);
		}
		if (str.equals("HP")) {
			mark.setSelection(3);
		}
		if (str.equals("Disqualified")) {
			mark.setSelection(4);
		}
	}
	
	protected void setRank(String str) {
		Spinner rank = (Spinner) findViewById(R.id.rank);
		if (str.equals("")) {
			rank.setSelection(0);
		}
		if (str.equals("1")) {
			rank.setSelection(1);
		}
		if (str.equals("2")) {
			rank.setSelection(2);
		}
		if (str.equals("3")) {
			rank.setSelection(3);
		}
		if (str.equals("4")) {
			rank.setSelection(4);
		}
	}
	
	protected void setCatTitle(String str) {
		Spinner title = (Spinner) findViewById(R.id.title);
		if (str.equals("")) {
			title.setSelection(0);
		} else {
			title.setSelection(1);
		}
	}

	// DOPLNIT!!
	private int selectCatClass(String cc) {
		if (cc.equals("1") || cc.equals("2") || cc.equals("11")
				|| cc.equals("12") || cc.equals("13a") || cc.equals("13b")
				|| cc.equals("13c") || cc.equals("14")) {
			findViewById(R.id.title).setVisibility(View.GONE);
			return R.array.title_arrays_classNo;
		}

		if (cc.equals("3")) {
			findViewById(R.id.title).setVisibility(View.VISIBLE);
			return R.array.title_arrays_class3;
		}

		if (cc.equals("4")) {
			findViewById(R.id.title).setVisibility(View.VISIBLE);
			return R.array.title_arrays_class4;
		}

		if (cc.equals("5")) {
			findViewById(R.id.title).setVisibility(View.VISIBLE);
			return R.array.title_arrays_class5;
		}

		if (cc.equals("6")) {
			findViewById(R.id.title).setVisibility(View.VISIBLE);
			return R.array.title_arrays_class6;
		}

		if (cc.equals("7")) {
			findViewById(R.id.title).setVisibility(View.VISIBLE);
			return R.array.title_arrays_class7;
		}

		if (cc.equals("8")) {
			findViewById(R.id.title).setVisibility(View.VISIBLE);
			return R.array.title_arrays_class8;
		}

		if (cc.equals("9")) {
			findViewById(R.id.title).setVisibility(View.VISIBLE);
			return R.array.title_arrays_class9;
		}

		if (cc.equals("10")) {
			findViewById(R.id.title).setVisibility(View.VISIBLE);
			return R.array.title_arrays_class10;
		}

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
		TextView catNoTv = (TextView) findViewById(R.id.catNr);
		String no = catNoTv.getText().toString();
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
		Spinner mark = (Spinner) findViewById(R.id.mark);
		String getCatMark = mark.getSelectedItem().toString();
		Spinner rank = (Spinner) findViewById(R.id.rank);
		String getCatRank = rank.getSelectedItem().toString();
		CheckBox biv = (CheckBox) findViewById(R.id.biv);
		String getCatBiv = Boolean.toString(biv.isChecked());
		CheckBox nomination = (CheckBox) findViewById(R.id.nomination);
		String getCatNomination = Boolean.toString(nomination.isChecked());
		EditText noteEt = (EditText) findViewById(R.id.note);
		String getCatNote = noteEt.getText().toString();
		Spinner title = (Spinner) findViewById(R.id.title);
		String getCatTitle = title.getSelectedItem().toString();
		EditText reasonEt = (EditText) findViewById(R.id.reason);
		String getCatReason = reasonEt.getText().toString();

		db.edit(Integer.parseInt(id), getCatType, getCatHead, getCatEyes,
				getCatEars, getCatCoat, getCatTail, getCatCondition,
				getCatImpress, getCatComment, getCatMark, getCatRank,
				getCatBiv, getCatNomination, getCatNote, getCatTitle,
				getCatReason);
		// Send data to server
		String messageToSend = writeForm(no, getCatType, getCatHead,
				getCatEyes, getCatEars, getCatCoat, getCatTail,
				getCatCondition, getCatImpress, getCatComment, getCatMark,
				getCatRank, getCatBiv, getCatNomination, getCatNote,
				getCatTitle, getCatReason)
				+ System.getProperty("line.separator");
		if (clientSender != null) {
			System.out.println(clientSender.getStatus());
		}
		ClientSender clientSender = connect.new ClientSender(
				this.getApplicationContext());
		clientSender.execute(messageToSend.toString());

		startListActivity();
		Toast.makeText(this, R.string.sendToast, Toast.LENGTH_LONG).show();
		this.finish();
	}

	public JSONObject writeForm(String no, String type, String head,
			String eyes, String ears, String coat, String tail,
			String condition, String impress, String comment, String mark,
			String rank, String biv, String nomination, String note,
			String title, String reason) {
		JSONObject object = new JSONObject();
		object.put("no", no);
		object.put("type", type);
		object.put("head", head);
		object.put("eyes", eyes);
		object.put("ears", ears);
		object.put("coat", coat);
		object.put("tail", tail);
		object.put("condition", condition);
		object.put("impress", impress);
		object.put("comment", comment);
		object.put("mark", mark);
		object.put("rank", rank);
		object.put("biv", biv);
		object.put("nomination", nomination);
		object.put("note", note);
		object.put("title", title);
		object.put("reason", reason);
		return object;
	}
}
