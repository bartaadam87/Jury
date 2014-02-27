package com.example.jury;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "juryDatabase";

	// Contacts table name
	private static final String TABLE_REPORTS = "reports";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
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
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_TYPE + " TEXT,"
				+ KEY_HEAD + " TEXT" + KEY_EYES + " TEXT" + KEY_EARS + " TEXT"
				+ KEY_COAT + " TEXT" + KEY_TAIL + " TEXT" + KEY_CONDITION
				+ " TEXT" + KEY_IMPRESS + " TEXT" + KEY_COMMENT + " TEXT" + ")";
		db.execSQL(CREATE_REPORTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORTS);

		// Create tables again
		onCreate(db);

	}

	// Adding new contact
	void addReport(Report report) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TYPE, report.getType());
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
		db.close(); // Closing database connection
	}

	// Getting single contact
	Report getReport(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_REPORTS, new String[] { KEY_ID,
				KEY_TYPE, KEY_HEAD, KEY_EYES, KEY_EARS, KEY_COAT, KEY_TAIL,
				KEY_CONDITION, KEY_IMPRESS, KEY_COMMENT }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Report report = new Report(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5), cursor.getString(6),
				cursor.getString(7), cursor.getString(8), cursor.getString(9));
		// return contact
		return report;
	}

	// Getting All Contacts
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
				report.setType(cursor.getString(1));
				report.setHead(cursor.getString(2));
				report.setEyes(cursor.getString(3));
				report.setEars(cursor.getString(4));
				report.setCoat(cursor.getString(5));
				report.setTail(cursor.getString(6));
				report.setCondition(cursor.getString(7));
				report.setImpress(cursor.getString(8));
				report.setComment(cursor.getString(9));
				// Adding contact to list
				reportList.add(report);
			} while (cursor.moveToNext());
		}

		// return contact list
		return reportList;
	}

	// Updating single contact
	public int updateReport(Report report) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TYPE, report.getType());
		values.put(KEY_HEAD, report.getHead());
		values.put(KEY_EYES, report.getEyes());
		values.put(KEY_EARS, report.getEars());
		values.put(KEY_COAT, report.getCoat());
		values.put(KEY_TAIL, report.getTail());
		values.put(KEY_CONDITION, report.getCondition());
		values.put(KEY_IMPRESS, report.getImpress());
		values.put(KEY_COMMENT, report.getComment());
		
		// updating row
		return db.update(TABLE_REPORTS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(report.getID()) });
	}

	// Deleting single contact
	public void deleteReport(Report report) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_REPORTS, KEY_ID + " = ?",
				new String[] { String.valueOf(report.getID()) });
		db.close();
	}

}