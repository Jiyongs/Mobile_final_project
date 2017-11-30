package com.example.jy.goodhabit_201520012;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jy.goodhabit_201520012.Helpers.myDBHelper;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;

    ///DB관련 변수
    myDBHelper myHelper; //DB와 테이블을 이용하는 것을 도와주는 변수
    EditText edtHNameResult, edtHTargetResult, edtIColorResult, edtDoDayResult, edtHCountResult;      //각종 EditText 변수들
    Button btnSelect;       //각종 Button 변수들
    SQLiteDatabase sqlDB;       //SQLiteDatabase형 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtHNameResult = (EditText)findViewById(R.id.edtHNameResult);
        edtHTargetResult = (EditText)findViewById(R.id.edtHTargetResult);
        edtIColorResult = (EditText)findViewById(R.id.edtIColorResult);
        edtDoDayResult = (EditText)findViewById(R.id.edtDoDayResult);
        edtHCountResult = (EditText)findViewById(R.id.edtHCountResult);

        btnSelect = (Button)findViewById(R.id.btnSelect);

        myHelper = new myDBHelper(this); //미리 정의한 DB파일을 생성

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        /*
        //////////리스트뷰 스크롤 시, 플로팅 액션버튼 보이기와 숨기기 설정//////////
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && floatingActionButton.isShown()) {
                    floatingActionButton.hide();
                } else if (dy < 0 && !floatingActionButton.isShown()) {
                    floatingActionButton.show();
                }
            }
        });
        */

        //////////////////////////////////각 버튼에 대한 동작 정의////////////////////////////////

        ///플로팅 버튼 이벤트
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sethabit_intent = new Intent(getApplicationContext(),ActivitySetHabit.class);
                startActivity(sethabit_intent);
                //startActivityForResult(sethabit_intent,0);
                Toast.makeText(getApplicationContext(),"버튼 클릭.",Toast.LENGTH_LONG).show();
            }
        });

        //조회 버튼 이벤트
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase(); //DB를 읽기전용으로 오픈
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM HabitTBL;", null);

                String strHName = "습관 이름" + "\r\n";
                String strHTarget = "목표 횟수" + "\r\n";
                String strIColor = "아이콘 색상" + "\r\n";
                String strDoDay = "실행 날짜" + "\r\n";
                String strHCount = "실행 횟수" + "\r\n";

                //모든 데이터를 가리키게 됨
                while (cursor.moveToNext()){
                    strHName += cursor.getString(0)+"\r\n"; //HabitTBL DB 테이블의 0열을 가리킴
                    strHTarget += cursor.getString(1)+"\r\n";
                    strIColor += cursor.getString(2)+"\r\n";
                    strDoDay += cursor.getString(3)+"\r\n";
                    strHCount  += cursor.getString(4)+"\r\n";
                }

                //모든 데이터를 출력
                edtHNameResult.setText(strHName);
                edtHTargetResult.setText(strHTarget);
                edtIColorResult.setText(strIColor);
                edtDoDayResult.setText(strDoDay);
                edtHCountResult.setText(strHCount);

                cursor.close(); //커서를 닫음
                sqlDB.close(); //DB를 닫음
            }
        });
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case RESULT_OK:
                break;
            default:
                break;
        }
    }
    */
}
