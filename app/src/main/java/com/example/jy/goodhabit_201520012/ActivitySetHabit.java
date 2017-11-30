package com.example.jy.goodhabit_201520012;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jy.goodhabit_201520012.Helpers.myDBHelper;

public class ActivitySetHabit extends AppCompatActivity {

    ///DB관련 변수
    myDBHelper myHelper; //DB와 테이블을 이용하는 것을 도와주는 변수
    EditText edtHName, edtHTarget, edtIColor, edtDoDay; //각종 EditText 변수들
    Button btnInit, btnInsert;
    SQLiteDatabase sqlDB;       //SQLiteDatabase형 변수
    String HCount = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_habit);

        edtHName = (EditText)findViewById(R.id.edtHName);
        edtHTarget = (EditText)findViewById(R.id.edtHTarget);
        edtIColor = (EditText)findViewById(R.id.edtIColor);
        edtDoDay = (EditText)findViewById(R.id.edtDoDay);


        btnInit = (Button)findViewById(R.id.btnInit);
        btnInsert = (Button)findViewById(R.id.btnInsert);

        myHelper = new myDBHelper(this); //미리 정의한 DB파일을 생성

        ///초기화 버튼 이벤트
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase(); //DB를 쓰기전용으로 오픈
                myHelper.onUpgrade(sqlDB,1,2); //1버전을 2버전으로 새로 오픈(초기화)
                sqlDB.close(); //DB를 닫음
            }
        });

        //입력 버튼 이벤트
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO HabitTBL VALUES ('"+edtHName.getText().toString() +"' , "+edtHTarget.getText().toString()+" , "+edtIColor.getText().toString()+" , '"+edtDoDay.getText().toString()+"', '"+HCount+"');"); //CHAR형만 ''로 묶어줘야 함!

                sqlDB.close();
                Toast.makeText(getApplicationContext(), "입력 완료",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}

