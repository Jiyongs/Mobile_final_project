package com.example.jy.goodhabit_201520012;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class ActivityChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        //액션바 타이틀 변경하기
        getSupportActionBar().setTitle("          Monthly Chart");
        //액션바 색상 지정
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFFc9aee5));
        //액션바에 홈버튼 표시
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    //액션바 뒤로가기 버튼 활성화
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
