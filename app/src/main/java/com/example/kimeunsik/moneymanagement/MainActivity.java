package com.example.kimeunsik.moneymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //시작날짜.. 끝날짜
    int Syear;
    int Smonth;
    int Sday;
    int Eyear;
    int Emonth;
    int Eday;

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
                Intent intent = new Intent(MainActivity.this,ManageActivity.class);
                startActivityForResult(intent,1);


                //테스트
                TextView ck=(TextView)findViewById(R.id.check);
                ck.setText(aYear+"년 "+aMonth+"월 "+aDay+"일 "+aValue+"(지출액) "+aWhat+"(내역) "+aFact+" (수입or지출)");

            }
        });





    }

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
