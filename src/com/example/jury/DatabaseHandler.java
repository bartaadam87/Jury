package com.example.jury;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "juryDatabase.db";
	private static final String TABLE_REPORTS = "reports";

	// Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NO = "no";
	private static final String KEY_BREED = "breed";
	private static final String KEY_CODE = "code";
	private static final String KEY_CCLASS = "cclass";
	private static final String KEY_SEX = "sex";
	private static final String KEY_BORN = "born";
	private static final String KEY_TYPE = "type";
	private static final String KEY_HEAD = "head";
	private static final String KEY_EYES = "eyes";
	private static final String KEY_EARS = "ears";
	private static final String KEY_COAT = "coat";
	private static final String KEY_TAIL = "tail";
	private static final String KEY_CONDITION = "condition";
	private static final String KEY_IMPRESS = "impress";
	private static final String KEY_COMMENT = "comment";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_REPORTS_TABLE = "CREATE TABLE " + TABLE_REPORTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NO + " TEXT,"
				+ KEY_BREED + " TEXT," + KEY_CODE + " TEXT," + KEY_CCLASS
				+ " TEXT," + KEY_SEX + " TEXT," + KEY_BORN + " TEXT,"
				+ KEY_TYPE + " TEXT," + KEY_HEAD + " TEXT," + KEY_EYES
				+ " TEXT," + KEY_EARS + " TEXT," + KEY_COAT + " TEXT,"
				+ KEY_TAIL + " TEXT," + KEY_CONDITION + " TEXT," + KEY_IMPRESS
				+ " TEXT," + KEY_COMMENT + " TEXT" + ")";
		db.execSQL(CREATE_REPORTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORTS);
		onCreate(db);

	}

	// Add report
	void addReport(Report report) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TYPE, report.getType());
		values.put(KEY_NO, report.getNo());
		values.put(KEY_BREED, report.getBreed());
		values.put(KEY_CODE, report.getCode());
		values.put(KEY_CCLASS, report.getCclass());
		values.put(KEY_SEX, report.getSex());
		values.put(KEY_BORN, report.getBorn());
		values.put(KEY_HEAD, report.getHead());
		values.put(KEY_EYES, report.getEyes());
		values.put(KEY_EARS, report.getEars());
		values.put(KEY_COAT, report.getCoat());
		values.put(KEY_TAIL, report.getTail());
		values.put(KEY_CONDITION, report.getCondition());
		values.put(KEY_IMPRESS, report.getImpress());
		values.put(KEY_COMMENT, report.getComment());

		// Inserting Row
		db.insert(TABLE_REPORTS, null, values);
		db.close();
	}

	// Get report
	Report getReport(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_REPORTS, new String[] { KEY_ID, KEY_NO,
				KEY_BREED, KEY_CODE, KEY_CCLASS, KEY_SEX, KEY_BORN, KEY_TYPE,
				KEY_HEAD, KEY_EYES, KEY_EARS, KEY_COAT, KEY_TAIL,
				KEY_CONDITION, KEY_IMPRESS, KEY_COMMENT }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Report report = new Report(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5), cursor.getString(6),
				cursor.getString(7), cursor.getString(8), cursor.getString(9),
				cursor.getString(10), cursor.getString(11),
				cursor.getString(12), cursor.getString(13),
				cursor.getString(14), cursor.getString(15));
		return report;
	}

	// Get report
	public List<Report> getAllReports() {
		List<Report> reportList = new ArrayList<Report>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_REPORTS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Report report = new Report();
				report.setID(Integer.parseInt(cursor.getString(0)));
				report.setNo(cursor.getString(1));
				report.setBreed(cursor.getString(2));
				report.setCode(cursor.getString(3));
				report.setCclass(cursor.getString(4));
				report.setSex(cursor.getString(5));
				report.setBorn(cursor.getString(6));
				report.setType(cursor.getString(7));
				report.setHead(cursor.getString(8));
				report.setEyes(cursor.getString(9));
				report.setEars(cursor.getString(10));
				report.setCoat(cursor.getString(11));
				report.setTail(cursor.getString(12));
				report.setCondition(cursor.getString(13));
				report.setImpress(cursor.getString(14));
				report.setComment(cursor.getString(15));
				reportList.add(report);
			} while (cursor.moveToNext());
		}

		// return contact list
		return reportList;
	}

	// Updating single contact
	public int edit(int id, String type, String head, String eyes, String ears,
			String coat, String tail, String condition, String impress, String comment) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TYPE, type);
		values.put(KEY_HEAD, head);
		values.put(KEY_EYES, eyes);
		values.put(KEY_EARS, ears);
		values.put(KEY_COAT, coat);
		values.put(KEY_TAIL, tail);
		values.put(KEY_CONDITION, condition);
		values.put(KEY_IMPRESS, impress);
		values.put(KEY_COMMENT, comment);

		// updating row
		return db.update(TABLE_REPORTS, values, "id" + "='" + id + "'", null);
	}

	// Deleting single contact
	public void deleteReport(Report report) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_REPORTS, KEY_ID + " = ?",
				new String[] { String.valueOf(report.getID()) });
		db.close();
	}

}