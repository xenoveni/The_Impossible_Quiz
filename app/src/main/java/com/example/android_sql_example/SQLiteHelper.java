package com.example.android_sql_example;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class SQLliteHelper extends SQLiteOpenHelper {

    private static final String TABLE_USERS = ;
    private Context context;
    private static final String DATABASE_NAME = "Question_db.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_QUESTIONS = "Questions";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_ANSWER1 = "answer1";
    public static final String COLUMN_ANSWER2 = "answer2";
    public static final String COLUMN_ANSWER3 = "answer3";
    public static final String COLUMN_ANSWER4 = "answer4";
    public static final String COLUMN_RIGHTANSWER = "right_answer";


    public SQLliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE_USERS+" (" +
                COLUMN_ID+" integer primary key autoincrement, " +
                COLUMN_FNAME+" text not null, " +
                COLUMN_LNAME+" text not null, " +
                COLUMN_EMAIL+" text not null);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}