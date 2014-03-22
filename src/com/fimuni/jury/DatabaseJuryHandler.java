package com.fimuni.jury;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseJuryHandler extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "juryNameDatabase.db";
	private static final String TABLE_NAMES = "names";

	// Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";

	public DatabaseJuryHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_NAMES_TABLE = "CREATE TABLE " + TABLE_NAMES + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";
		db.execSQL(CREATE_NAMES_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMES);
		onCreate(db);

	}

	// Add jury
	void addJury(Jury jury) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMES);
		String CREATE_NAMES_TABLE = "CREATE TABLE " + TABLE_NAMES + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";
		db.execSQL(CREATE_NAMES_TABLE);
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, jury.getName());

		// Inserting Row
		db.insert(TABLE_NAMES, null, values);
		db.close();
	}

	// Get jury
	Jury getJury(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_NAMES, new String[] { KEY_ID, KEY_NAME },
				KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null,
				null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Jury jury = new Jury(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1));
		return jury;
	}
}
