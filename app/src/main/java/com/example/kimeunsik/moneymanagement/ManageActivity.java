package com.example.kimeunsik.moneymanagement;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;


public class ManageActivity extends AppCompatActivity {

        //수입인지 지출인지 확인변수들
        String inoutFact; //수입지출
        String strColor; //수입지출 색상
        //내역
        String what;
        //금액
        int Value;
        //날짜
        int mYear;
        int mMonth;
        int mDay;
        Button dateBtn;
        int cYear;
        int cMonth;
        int cDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        //수입지출 버튼
        final Button factBtn = (Button)findViewById(R.id.factBtn);
        factBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(factBtn.getText()=="수입"){
                    inoutFact="지출";
                    factBtn.setText(inoutFact);
                    strColor="#FF0000";
                    factBtn.setTextColor(Color.parseColor(strColor));
                }
                else {
                    inoutFact="수입";
                    factBtn.setText(inoutFact);
                    strColor="#0000FF";
                    factBtn.setTextColor(Color.parseColor(strColor));
                }
            }
        });


        //내역 에딧텍스트 값받기
        EditText strWhat = (EditText) findViewById(R.id.what);
        what = strWhat.getText().toString();

        //금액 에딧텍스트 값받기
        EditText strValue = (EditText) findViewById(R.id.money);
        Value = Integer.parseInt(strValue.getText().toString());

        //날짜 버튼 datepicker 써보자!
        dateBtn = (Button)findViewById(R.id.dateBtn);
        dateBtn.setOnClickListener(new View.OnClickListener() //dateBtn을 누르면.. 작동
        {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ManageActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateBtn.setText(year+"년 "+(month+1)+"월 "+dayOfMonth+"일");
                        cYear=year;
                        cMonth=month+1;
                        cDay=dayOfMonth;
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

    }





    }













