package com.example.kimeunsik.moneymanagement;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ManageActivity extends AppCompatActivity {

        //수입인지 지출인지 확인
        String inoutFact;
        String strColor;
        //
        String what;
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


        /*여기서 부터 진행 6/6*/
        //내역 에딧텍스트
        EditText strWhat = (EditText) findViewById(R.id.what);
        //내역값 받아놓기
        what = strWhat.getText().toString();




    }




    }
