/**
 * Before continuing it is recommended to go through the documentation Chapter 5 on prerequisites and how to run this program
 * This is source code that is most likely deprecated due to introduction of the new Android OS
 * Android development is rapidly changing hence this code is most likely obsolete in a few months
 * Upgrade to the new IDE (Android Studio) has been developed by the android team as the Main development environment
 * Submitted by Bill Colin Chabari DIT student*/
package com.app.buzzapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;



/*In order to access the database, create a class DatabaseAdapter
 * this is because the BillHelper contains private static final data hence can only be accessed by the Helper class
 * this is a security feature since data can't be manipulated
 * data would be destroyed easily if other classes can access the db leading to destroying the database  */
public class BillDatabaseAdapter  {
	
	BillHelper helper;
	
	public BillDatabaseAdapter(Context context) {
		
		helper=new BillHelper(context);
	}

	public long insertData(String name, String password, String email)
	{
		SQLiteDatabase db=helper.getWritableDatabase();
		ContentValues contentValues=new ContentValues();
		contentValues.put(BillHelper.NAME, name);
		contentValues.put(BillHelper.PASSWORD, password);
		contentValues.put(BillHelper.EMAIL, email);
		long id=db.insert(BillHelper.TABLE_NAME,null,contentValues);
		return id;
	}
	//BillHelper class is accessed by other DatabaseAdapter class
	/**
	 * BillHelper class is static
	 * this grants the class minimum access to perform it's tasks
	 * Therefore if it remained as "class BillHelper extends SQLiteOpenHelper", it could access the BillDatabaseAdapter non static fields
	 * Also the static means it can only access the static fields in the outer class*/
	static class BillHelper extends SQLiteOpenHelper {
		@Override
		public SQLiteDatabase getReadableDatabase() {
			Cursor cursor=getReadableDatabase().rawQuery("select* from BILLTABLE", null);
			// TODO Auto-generated method stub
			return super.getReadableDatabase();
		}

		private static final String DATABASE_NAME = "billdatabase";
		private static final String TABLE_NAME = "BILLTABLE";
		private static final int DATABASE_VERSION = 3;
		private static final String UID ="_id";
		private static final String NAME ="Name";
		private static final String PASSWORD= "Password";
		private static final String EMAIL="Email";
		private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255),"+PASSWORD+","+EMAIL+");";		
		private static final String DROP_TABLE="DROP TABLE IF EXISTS"+TABLE_NAME;		
		public BillHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// CREATE TABLE BILLTABLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(255));
			
			try {
				db.execSQL(CREATE_TABLE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			try {
				db.execSQL(DROP_TABLE);
				onCreate(db);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}

}
