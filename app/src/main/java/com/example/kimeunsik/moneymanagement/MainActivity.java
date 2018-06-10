package com.example.kimeunsik.moneymanagement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    //텍스트뷰
    TextView inputTxt;
    TextView ouputTxt;
    TextView totalTxt;
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
    boolean[] ioCheck=new boolean[10];
    int aTSum=0; //합계금액
    int aISum=0; //소비합계
    int aOSum=0; //지출합계
    ArrayList<String> AaFact=null; // 수입지출
    ArrayList<String> AaWhat=null; // 뭐에썼니?
    ArrayList<String> manage=null; // 전체관리
    int[] AaValue=new int[10];
    int[] AaYear=new int[10];
    int[] AaMonth=new int[10];
    int[] AaDay=new int[10];
    int count=0;// 갯수관리
    ListView listview;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //데이터관리 배열생성
        AaFact = new ArrayList<String>();
        AaWhat = new ArrayList<String>();
        manage = new ArrayList<String>();

        //텍스트뷰 생성
        inputTxt = (TextView) findViewById(R.id.inputValue);
        ouputTxt = (TextView) findViewById(R.id.outputValue);
        totalTxt = (TextView) findViewById(R.id.sumValue);


        //관리버튼
        Button manageMoneyBtn = (Button)findViewById(R.id.manageMoney);
        //관리버튼 누르면 관리액티비티시행
        manageMoneyBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ManageActivity.class);
                startActivityForResult(intent,1);
            }
        });



        //리스트뷰,어댑터생성
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,manage);
        listview = (ListView)findViewById(R.id.list);




        //수입지출합계 버튼
        Button inputMoneyBtn = (Button)findViewById(R.id.inputMoney);
        inputMoneyBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                adapter.clear();
                for(int a=0; a<count; a++){
                    if(ioCheck[a]==false) {
                        manage.add(AaYear[a]+"년 "+AaMonth[a]+"월 "+AaDay[a]+"일 \n"+AaWhat.get(a)+"\n"+"금액  "+AaValue[a]+"원");
                    }
                }
                listview.setAdapter(adapter);
                inputTxt.setText(Integer.toString(aISum));
                ouputTxt.setText(Integer.toString(0));
                totalTxt.setText(Integer.toString(aISum));
            }
        });

        Button outputMoneyBtn = (Button)findViewById(R.id.outputMoney);
        outputMoneyBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                adapter.clear();
                for(int a=0; a<count; a++){
                    if(ioCheck[a]==true) {
                        manage.add(AaYear[a]+"년 "+AaMonth[a]+"월 "+AaDay[a]+"일 \n"+AaWhat.get(a)+"\n"+"금액  "+AaValue[a]+"원");
                    }
                }
                listview.setAdapter(adapter);
                ouputTxt.setText(Integer.toString(aOSum));
                inputTxt.setText(Integer.toString(0));
                totalTxt.setText(Integer.toString(-aOSum));
            }
        });

        Button sumMoneyBtn = (Button)findViewById(R.id.sumMoney);
        sumMoneyBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                adapter.clear();
                for(int a=0; a<count; a++) {
                        manage.add(AaYear[a] + "년 " + AaMonth[a] + "월 " + AaDay[a] + "일 \n" + AaFact.get(a) + "(" + AaWhat.get(a) + ")" + "\n" + "금액  " + AaValue[a] + "원 ");
                    }
                listview.setAdapter(adapter);
                inputTxt.setText(Integer.toString(aISum));
                ouputTxt.setText(Integer.toString(aOSum));
                totalTxt.setText(Integer.toString(aTSum));
                }

        });




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
    }//onCreate 끝



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode==1){
            if(resultCode==RESULT_OK){
                    adapter.clear();
                for(int a=0; a<count; a++) {
                    manage.add(AaYear[a] + "년 " + AaMonth[a] + "월 " + AaDay[a] + "일 \n" + AaFact.get(a) + "(" + AaWhat.get(a) + ")" + "\n" + "금액  " + AaValue[a] + "원 ");
                }
                listview.setAdapter(adapter);
                    ioCheck[count]=data.getBooleanExtra("체크",true);
                    AaFact.add(count,data.getStringExtra("팩트"));
                    AaWhat.add(count,data.getStringExtra("내역"));
                    AaYear[count]=data.getIntExtra("날짜년",0);
                    AaMonth[count]=data.getIntExtra("날짜월",0);
                    AaDay[count]=data.getIntExtra("날짜일",0);
                    AaValue[count]=data.getIntExtra("금액",0);

                //리스트
                if(ioCheck[count]==false){ //소득
                    aTSum=aTSum+AaValue[count];
                    manage.add(AaYear[count]+"년 "+AaMonth[count]+"월 "+AaDay[count]+"일 \n"+AaFact.get(count)+"("+AaWhat.get(count)+")"+"\n"+"금액  "+AaValue[count]+"원 ");
                    aISum=aISum+AaValue[count];
                    inputTxt.setText(Integer.toString(aISum));
                    totalTxt.setText(Integer.toString(aTSum));
                    count++;
                    listview.setAdapter(adapter);

                }

                else{ //지출
                    aTSum=aTSum-AaValue[count];
                    manage.add(AaYear[count]+"년 "+AaMonth[count]+"월 "+AaDay[count]+"일 \n"+AaFact.get(count)+"("+AaWhat.get(count)+")"+"\n"+"금액  "+AaValue[count]+"원 ");
                    aOSum=aOSum+AaValue[count];
                    ouputTxt.setText(Integer.toString(aOSum));
                    totalTxt.setText(Integer.toString(aTSum));
                    count++;
                    listview.setAdapter(adapter);

                }
            }
        }
    }



}
