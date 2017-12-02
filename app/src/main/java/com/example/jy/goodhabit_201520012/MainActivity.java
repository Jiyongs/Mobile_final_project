package com.example.jy.goodhabit_201520012;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jy.goodhabit_201520012.Helpers.MyAdapter;
import com.example.jy.goodhabit_201520012.Helpers.myDBHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  static final String TAG = "MainActivity";

    //카드뷰 관련 변수
    private ArrayList<HabitInfo> rowListItem;
    private MyAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView recyclerView;

    //플로팅액션버튼 변수
    FloatingActionButton floatingActionButton;

    ///DB관련 변수
    myDBHelper myHelper; //DB와 테이블을 이용하는 것을 도와주는 변수
    EditText edtHNameResult, edtHTargetResult, edtIColorResult, edtDoDayResult, edtHCountResult;      //각종 EditText 변수들
    Button btnSelect;       //각종 Button 변수들
    SQLiteDatabase sqlDB;//SQLiteDatabase형 변수
    Cursor mCusor;


    //onCreate 시작
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

        myHelper = new myDBHelper(this,"HabitTBL",null,1); //미리 정의한 DB파일을 생성

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        //////////액선바 세팅//////////
        getSupportActionBar().getCustomView();
        //액션바 색상 지정
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFFc9aee5));

        //어뎁터와 레이아웃매니저 세팅
        rowListItem = getAllItemList(); //데이터 배열 준비

        mLinearLayoutManager = new GridLayoutManager(MainActivity.this,2);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new MyAdapter(this, rowListItem);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

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



        //////////////////////////////////각 버튼에 대한 동작 정의////////////////////////////////

        ///플로팅 버튼 이벤트
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sethabit_intent = new Intent(getApplicationContext(),ActivitySetHabit.class);
                //startActivity(sethabit_intent);
                startActivityForResult(sethabit_intent,0);
                Toast.makeText(getApplicationContext(),"버튼 클릭.",Toast.LENGTH_LONG).show();
            }
        });

        //조회 버튼 이벤트
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase(); //DB를 읽기전용으로 오픈
                mCusor = sqlDB.rawQuery("SELECT * FROM HabitTBL;", null);


                String strHName = "습관 이름" + "\r\n";
                String strHTarget = "목표 횟수" + "\r\n";
                String strIColor = "아이콘 색상" + "\r\n";
                String strDoDay = "실행 날짜" + "\r\n";
                String strHCount = "실행 횟수" + "\r\n";

                //모든 데이터를 가리키게 됨
                while (mCusor.moveToNext()){
                    strHName += mCusor.getString(0)+"\r\n"; //HabitTBL DB 테이블의 0열을 가리킴
                    strHTarget += mCusor.getString(1)+"\r\n";
                    strIColor += mCusor.getString(2)+"\r\n";
                    strDoDay += mCusor.getString(3)+"\r\n";
                    strHCount  += mCusor.getString(4)+"\r\n";
                }

                //모든 데이터를 출력
                edtHNameResult.setText(strHName);
                edtHTargetResult.setText(strHTarget);
                edtIColorResult.setText(strIColor);
                edtDoDayResult.setText(strDoDay);
                edtHCountResult.setText(strHCount);

                mCusor.close(); //커서를 닫음
                sqlDB.close(); //DB를 닫음
            }
        });

    }
    //onCreate 끝

    //액션 버튼 메뉴를 액션바에 집어넣기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    //액션버튼을 클릭했을 때 동작 설정
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.one:
                Intent intent1 = new Intent(getApplicationContext(),ActivityCal.class);
                startActivity(intent1);
                Toast.makeText(getApplicationContext(),"캘린더 형식으로 보기 설정이 변경됩니다.",Toast.LENGTH_LONG).show();
                break;
            case R.id.two:
                Intent intent2 = new Intent(getApplicationContext(),ActivityChart.class);
                 startActivity(intent2);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //데이터 배열을 읽어오는 함수
    private ArrayList<HabitInfo> getAllItemList(){
        sqlDB = myHelper.getReadableDatabase(); //DB를 읽기전용으로 오픈
        mCusor = sqlDB.rawQuery("SELECT * FROM HabitTBL;", null);

        ArrayList<HabitInfo> allItems = new ArrayList<HabitInfo>();

        String strHName;
        String strHTarget;
        String strIColor;
        String strDoDay;
        String strHCount;

        while (mCusor.moveToNext()){
            strHName = mCusor.getString(0); //HabitTBL DB 테이블의 0열을 가리킴
            strHTarget = mCusor.getString(1);
            strIColor = mCusor.getString(2);
            strDoDay = mCusor.getString(3);
            strHCount = mCusor.getString(4);
            allItems.add(new HabitInfo(strHName,strHTarget,strIColor,strDoDay,strHCount));
        }
        mCusor.close(); //커서를 닫음
        sqlDB.close(); //DB를 닫음
        return allItems;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case RESULT_OK:
                rowListItem = getAllItemList(); //데이터 배열 준비
                mAdapter = new MyAdapter(this, rowListItem);
                recyclerView.setAdapter(mAdapter);
                break;
            default:
                break;
        }
    }

}
