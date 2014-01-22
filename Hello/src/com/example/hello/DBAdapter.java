package com.example.hello;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter 
{ 
    //public static final String KEY_ROWID = "_id";
    public static final String KEY_USER = "user";
    //public static final String KEY_TITLE = "title";
    public static final String KEY_PASS = "pass";    
    private static final String TAG = "DBAdapter";
    
    private static final String DATABASE_NAME = "books";
    private static final String DATABASE_TABLE = "titles";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
        "create table titles (user text not null, "
                + "pass text not null);";
        
    private final Context context;  
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
        
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
                              int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                  + " to "
                  + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS titles");
            onCreate(db);
        }
    }    
  //---opens the database---
    public DBAdapter open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    
    //---insert a title into the database---
    public long insertTitle(String user, String pass) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USER, user);
        
        initialValues.put(KEY_PASS, pass);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular title---
    public boolean deleteTitle(long user) 
    {
        return db.delete(DATABASE_TABLE, KEY_USER + 
        		"=" + user, null) > 0;
    }

    //---retrieves all the titles---
    public Cursor getAllTitles() 
    {
        return db.query(DATABASE_TABLE, new String[] {
        		KEY_USER,        		
                KEY_PASS}, 
                null, 
                null, 
                null, 
                null, 
                null);
    }

    //---retrieves a particular title---
    /*public Cursor getTitle(long rowId) throws SQLException 
    
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		KEY_USER,        		
                        KEY_PASS},
                		KEY_USER + "=" + rowId, 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }*/

    //---updates a title---
    /*public boolean updateTitle(long rowId, String isbn, 
    String title, String publisher) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_ISBN, isbn);
        args.put(KEY_TITLE, title);
        args.put(KEY_PUBLISHER, publisher);
        return db.update(DATABASE_TABLE, args, 
                         KEY_ROWID + "=" + rowId, null) > 0;
    }*/
}