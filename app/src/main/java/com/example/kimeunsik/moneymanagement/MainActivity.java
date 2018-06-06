package com.example.kimeunsik.moneymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //받아 올 값
    String inoutFact; //수입 or 지출
    //날짜
    int Myear;
    int Mmonth;
    int Mdate;
    String inoutWhat; // 뭐에썼니?
    int input=0; //수입금액
    int output=0; //지출금액

    int sum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //날짜 버튼
        Button startDateBtn = (Button)findViewById(R.id.startDate);
        Button endDateBtn = (Button)findViewById(R.id.endDate);
        //수입지출합계 버튼
        Button inputMoneyBtn = (Button)findViewById(R.id.inputMoney);
        Button outputMoneyBtn = (Button)findViewById(R.id.outputMoney);
        Button sumMoneyBtn = (Button)findViewById(R.id.sumMoney);

        //관리버튼
        Button manageMoneyBtn = (Button)findViewById(R.id.manageMoney);


        //관리버튼 누르면 관리액티비티시행
        manageMoneyBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //관리액티비티시행 돌아올 때 넘겨받을 값 : 1. 수입인지 지출인지 2. 날짜 3. 내역(뭐에썼니?) 4. 금액
            }
        });





    }



}
