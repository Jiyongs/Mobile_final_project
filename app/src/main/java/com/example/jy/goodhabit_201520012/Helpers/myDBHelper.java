package com.example.jy.goodhabit_201520012.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JY on 2017-11-30.
 */

///////SQLiteOpenHelper를 상속받은 클래스 정의
public class myDBHelper extends SQLiteOpenHelper {

    public myDBHelper(Context context) {
        super(context, "HabitTBL",null,1);
    }
    //생성자
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE HabitTBL (HName CHAR(20) PRIMARY KEY, HTarget INTEGER, IColor INTEGER, DoDay CHAR(20), HCount INTEGER);"); //테이블 생성(이름, 목표횟수, 아이콘 색상, 실행날짜, 실행횟수의 5개의 열)
    }
    //초기화 정의
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS HabitTBL");
        onCreate(db);
    }
}
