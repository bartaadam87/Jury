package com.fimuni.jury;

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
	private static final String KEY_MARK = "mark";
	private static final String KEY_RANK = "rank";
	private static final String KEY_BIV = "biv";
	private static final String KEY_NOMINATION = "nomination";
	private static final String KEY_NOTE = "note";
	private static final String KEY_TITLE = "title";
	private static final String KEY_REASON = "reason";

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
				+ " TEXT," + KEY_COMMENT + " TEXT," + KEY_MARK + " TEXT,"
				+ KEY_RANK + " TEXT," + KEY_BIV + " TEXT," + KEY_NOMINATION
				+ " TEXT," + KEY_NOTE + " TEXT," + KEY_TITLE + " TEXT,"
				+ KEY_REASON + " TEXT" + ")";
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
		values.put(KEY_MARK, report.getMark());
		values.put(KEY_RANK, report.getRank());
		values.put(KEY_BIV, report.getBiv());
		values.put(KEY_NOMINATION, report.getNomination());
		values.put(KEY_NOTE, report.getNote());
		values.put(KEY_TITLE, report.getTitle());
		values.put(KEY_REASON, report.getReason());

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
				KEY_CONDITION, KEY_IMPRESS, KEY_COMMENT, KEY_MARK, KEY_RANK,
				KEY_BIV, KEY_NOMINATION, KEY_NOTE, KEY_TITLE, KEY_REASON },
				KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null,
				null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Report report = new Report(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5), cursor.getString(6),
				cursor.getString(7), cursor.getString(8), cursor.getString(9),
				cursor.getString(10), cursor.getString(11),
				cursor.getString(12), cursor.getString(13),
				cursor.getString(14), cursor.getString(15),
				cursor.getString(16), cursor.getString(17),
				cursor.getString(18), cursor.getString(19),
				cursor.getString(20), cursor.getString(21),
				cursor.getString(22));
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
				report.setMark(cursor.getString(16));
				report.setRank(cursor.getString(17));
				report.setBiv(cursor.getString(18));
				report.setNomination(cursor.getString(19));
				report.setNote(cursor.getString(20));
				report.setTitle(cursor.getString(21));
				report.setReason(cursor.getString(22));
				reportList.add(report);
			} while (cursor.moveToNext());
		}

		// return report list
		return reportList;
	}

	// Updating single report
	public int edit(int id, String type, String head, String eyes, String ears,
			String coat, String tail, String condition, String impress,
			String comment, String mark, String rank, String biv,
			String nomination, String note, String title, String reason) {
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
		values.put(KEY_MARK, mark);
		values.put(KEY_RANK, rank);
		values.put(KEY_BIV, biv);
		values.put(KEY_NOMINATION, nomination);
		values.put(KEY_NOTE, note);
		values.put(KEY_TITLE, title);
		values.put(KEY_REASON, reason);
		// updating row
		return db.update(TABLE_REPORTS, values, "id" + "='" + id + "'", null);
	}

	// Deleting single report
	public void deleteReport(Report report) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_REPORTS, KEY_ID + " = ?",
				new String[] { String.valueOf(report.getID()) });
		db.close();
	}

}