package cn.ucai.fulishop.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/1/17 0017.
 */

public class DBOpenHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERISON = 1;
    private static final String FULISHOP_USER_TABLE_CREATE =
            "CREATE TABLE " + UserDao.USER_TABLE_NAME + " ("
                    + UserDao.USER_COLUMN_NAME + " TEXT PRIMARY KEY, "
                    + UserDao.USER_COLUMN_NICK + " TEXT,"
                    + UserDao.USER_COLUMN_AVATAR + " INTEGER,"
                    + UserDao.USER_COLUMN_AVATAR_PATH + " TEXT,"
                    + UserDao.USER_COLUMN_AVATAR_TYPE + " INTEGER,"
                    + UserDao.USER_COLUMN_AVATAR_SUFFIX + " TEXT,"
                    + UserDao.USER_COLUMN_AVATAR_UPDATE_TIME + " TEXT);";

    private static DBOpenHelper instance;

    public DBOpenHelper(Context context) {
        super(context, getUserDataBaseName(), null, DATABASE_VERISON);
    }

    public static DBOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBOpenHelper(context);
        }
        return instance;
    }

    private static  String getUserDataBaseName(){
        return "cn.ucai.fulishop.db";
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(FULISHOP_USER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    private void closeDB(){
        if (instance != null) {
            SQLiteDatabase db = instance.getWritableDatabase();
            db.close();
            instance = null;
        }
    }
}
