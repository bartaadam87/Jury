package com.example.jury;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Report {

	//prace s databazi	
	
	protected static final String DATABASE_NAME = "juryDB";
	protected static final int DATABASE_VERSION = 1;

	protected static final String TB_NAME = "reports";
	
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_HEAD = "head";
	
	protected static final String ORDER_BY = COLUMN_ID + " DESC";
	
	public static final String[] columns = { COLUMN_ID, COLUMN_TYPE, COLUMN_HEAD };
	
	private SQLiteOpenHelper openHelper;
	

	static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + TB_NAME + " (" + COLUMN_ID
					+ " INTEGER PRIMARY KEY," + COLUMN_TYPE
					+ " TEXT NOT NULL," + COLUMN_HEAD + " TEXT NOT NULL" + ");");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS notes");
			onCreate(db);
		}
	}

	public Report(Context ctx) {
		openHelper = new DatabaseHelper(ctx);
	}

	public Cursor getReports() {
		SQLiteDatabase db = openHelper.getReadableDatabase();
		return db.query(TB_NAME, columns, null, null, null, null, ORDER_BY);
	}

	public Cursor getReport(long id) {
		SQLiteDatabase db = openHelper.getReadableDatabase();
		String[] selectionArgs = { String.valueOf(id) };
		return db.query(TB_NAME, columns, COLUMN_ID + "= ?", selectionArgs,
				null, null, ORDER_BY);
	}

	public boolean deleteReport(long id) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		String[] selectionArgs = { String.valueOf(id) };

		int deletedCount = db.delete(TB_NAME, COLUMN_ID + "= ?", selectionArgs);
		db.close();
		return deletedCount > 0;
	}

	public long insertReport(String title, String text) {
		SQLiteDatabase db = openHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_TYPE, title);
		values.put(COLUMN_HEAD, text);

		long id = db.insert(TB_NAME, null, values);
		db.close();
		return id;
	}

	public void close() {
		openHelper.close();
	}	
		
	
}
