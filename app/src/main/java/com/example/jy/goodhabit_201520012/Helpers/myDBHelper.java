package com.example.jy.goodhabit_201520012.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.jy.goodhabit_201520012.HabitInfo;

/**
 * Created by JY on 2017-11-30.
 */

///////SQLiteOpenHelper를 상속받은 클래스 정의
public class myDBHelper extends SQLiteOpenHelper {

    private Context context;

    public myDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,name,factory,version);
        this.context = context;
    }

    //생성자
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE HabitTBL (HName CHAR(20) PRIMARY KEY, HTarget INTEGER, IColor INTEGER, DoDay CHAR(20), HCount INTEGER);"); //테이블 생성(이름, 목표횟수, 아이콘 색상, 실행날짜, 실행횟수의 5개의 열)
        Toast.makeText(context, "Table 생성완료", Toast.LENGTH_SHORT).show();
    }
    //초기화 정의
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "버전이 상승했습니다.", Toast.LENGTH_SHORT).show();
        db.execSQL("DROP TABLE IF EXISTS HabitTBL");
        onCreate(db);
    }

    //테이블에 값 추가하는 함수
    public void addHabit(HabitInfo habitInfo){

        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO HabitTBL ( ");
        sb.append(" HName, HTarget, IColor, DoDay, HCount ) ");
        sb.append(" VALUES ( ?, ?, ?, ?, ?) ");

        db.execSQL(sb.toString(),
                new Object[]{
                        habitInfo.getHName(),
                        Integer.parseInt(habitInfo.getHTarget()),
                        Integer.parseInt(habitInfo.getIColor()),
                        habitInfo.getDoDay(),
                        Integer.parseInt(habitInfo.getHCount())
                });
        Toast.makeText(context, "추가 완료", Toast.LENGTH_SHORT).show();
        //sqlDB.close();
    }

}
