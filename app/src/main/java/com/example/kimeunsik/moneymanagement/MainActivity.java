package com.example.kimeunsik.moneymanagement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //시작날짜.. 끝날짜
    Button startDateBtn;
    Button endDateBtn;
    int Syear;
    int Smonth;
    int Sday;
    int Eyear;
    int Emonth;
    int Eday;

    //날짜임시저장
    int Scyear;
    int Scmonth;
    int Scday;
    int Ecyear;
    int Ecmonth;
    int Ecday;

    //배열
    /*넘겨 받아 관리해야 하는 것
                1. 수입/지출 –1)어레이
                2. 날짜 –2)어레이3)시작날 지정하는 변수 – 하나만 있어도ok4)마지막날 지정하는 변수 – 하나만 있어도ok
                3. 5)내역 -어레이
                4. 금액 –어레이(6)수입,7)지출,8)합계)*/
    String aFact; // 수입지출
    String aWhat; // 뭐에썼니?
    int aValue=0;
    int aSum=0; //합계금액
    int aYear;
    int aMonth;
    int aDay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //수입지출합계 버튼
        Button inputMoneyBtn = (Button)findViewById(R.id.inputMoney);
        Button outputMoneyBtn = (Button)findViewById(R.id.outputMoney);
        Button sumMoneyBtn = (Button)findViewById(R.id.sumMoney);
        //관리버튼
        Button manageMoneyBtn = (Button)findViewById(R.id.manageMoney);


        //start 데이트피커
        startDateBtn = (Button)findViewById(R.id.startDate);
        startDateBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                Scyear = c.get(Calendar.YEAR);
                Scmonth = c.get(Calendar.MONTH);
                Scday = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        startDateBtn.setText(year+"년  "+(month+1)+"월  "+dayOfMonth+"일");
                        Syear=year;
                        Smonth=month+1;
                        Sday=dayOfMonth;
                    }
                },Scyear,Scmonth,Scday);
                datePickerDialog.show();
            }
        });

        //end 데이트피커
        endDateBtn = (Button)findViewById(R.id.endDate);
        endDateBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                Ecyear = c.get(Calendar.YEAR);
                Ecmonth = c.get(Calendar.MONTH);
                Ecday = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        endDateBtn.setText(year+"년  "+(month+1)+"월  "+dayOfMonth+"일");
                        Eyear=year;
                        Emonth=month+1;
                        Eday=dayOfMonth;
                    }
                },Ecyear,Ecmonth,Ecday);
                datePickerDialog.show();
            }
        });





        //관리버튼 누르면 관리액티비티시행
        manageMoneyBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ManageActivity.class);
                startActivityForResult(intent,1);
            }
        });




    }//onCreate 끝



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode==1){
            if(resultCode==RESULT_OK){
                      aWhat=data.getStringExtra("내역");
                    aFact=data.getStringExtra("팩트");
                    aYear=data.getIntExtra("날짜년",0);
                    aMonth=data.getIntExtra("날짜월",0);
                    aDay=data.getIntExtra("날짜일",0);
                    aValue=data.getIntExtra("금액",0);
            }
        }

    }



}
