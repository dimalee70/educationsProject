package com.example.dager2test.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dager2test.di.ApplicationContext;
import com.example.dager2test.di.DatabaseInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DbHelper extends SQLiteOpenHelper
{
    public static final String USER_TABLE_NAME = "users";
    public static final String USER_COLUMN_USER_ID = "id";
    public static final String USER_COLUMN_USER_NAME = "user_name";
    public static final String USER_COLUMN_USER_ADDRESS = "user_address";
    public static final String USER_COLUMN_USER_CREATED_AT = "created_at";
    public static final String USER_COLUMN_USER_UPDATED_AT = "updated_at";

    @Inject
    public DbHelper(@ApplicationContext Context context,
                    @DatabaseInfo String dbName,
                    @DatabaseInfo Integer version)
    {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void tableCreateStatements(SQLiteDatabase db)
    {
        try
        {
            db.execSQL
                    ("create table if not exists "
                            + USER_TABLE_NAME + " ("
                            + USER_COLUMN_USER_ID + " integer primary key autoincrement, "
                            + USER_COLUMN_USER_NAME + " varchar(20), "
                            + USER_COLUMN_USER_ADDRESS + " varchar(50), "
                            + USER_COLUMN_USER_CREATED_AT + " varchar(10) default " + getCurrentTimeStamp() + ","
                            + USER_COLUMN_USER_UPDATED_AT + " varchar(10) default " + getCurrentTimeStamp() + ")"
                    );
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    private String getCurrentTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}
