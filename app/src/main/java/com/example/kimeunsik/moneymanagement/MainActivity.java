package com.example.kimeunsik.moneymanagement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //텍스트뷰
    TextView inputTxt;
    TextView ouputTxt;
    TextView totalTxt;
    //시작날짜.. 끝날짜
    Button startDateBtn;
    Button endDateBtn;
    int Syear=0;
    int Smonth=0;
    int Sday=0;
    int Eyear=0;
    int Emonth=0;
    int Eday=0;
    //날짜임시저장
    int Scyear;
    int Scmonth;
    int Scday;
    int Ecyear;
    int Ecmonth;
    int Ecday;
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
    int bCk=2; //버튼 무엇이 눌렸는지 확인(뭐가눌려져있나 구분위해서)
    //날짜 선택 관련 변수
    String s1;
    Date d1;
    String s2;
    Date d2;
    String s3;
    Date d3;
    int compareS;
    int compareE;
    SimpleDateFormat simpleDateFormat1;
    SimpleDateFormat simpleDateFormat2;
    SimpleDateFormat simpleDateFormat3;
    int p=0;
    int[] tempP=new int[10];

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



        //리스트뷰 아이템 클릭리스너
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                   /* manage.remove(position);
                    listview.clearChoices();
                    adapter.notifyDataSetChanged(); */
                   if((count==0)||(count==1)){
                       adapter.clear();
                       inputTxt.setText(Integer.toString(0));
                       ouputTxt.setText(Integer.toString(0));
                       totalTxt.setText(Integer.toString(0));
                   }
                   else{
                        //갯수 2이상일때
                       manage.remove(position);
                       tempP[p]=position;
                       p++;
                       if(bCk==0){
                            aISum=aISum-AaValue[position];
                            inputTxt.setText(Integer.toString(aISum));
                            totalTxt.setText(Integer.toString(aISum));
                       }
                       else if(bCk==1){
                           aOSum=aOSum-AaValue[position];
                           ouputTxt.setText(Integer.toString(aOSum));
                           totalTxt.setText(Integer.toString(-aOSum));
                       }
                       if(bCk==2){
                           if(ioCheck[position]==false) {
                               aISum=aISum-AaValue[position];
                               aTSum = aTSum - AaValue[position];
                           }
                           else{
                               aOSum=aOSum-AaValue[position];
                               aTSum = aTSum + AaValue[position];
                           }
                           inputTxt.setText(Integer.toString(aISum));
                           ouputTxt.setText(Integer.toString(aOSum));
                           totalTxt.setText(Integer.toString(aTSum));
                       }

                       listview.clearChoices();
                       adapter.notifyDataSetChanged();
                   }

            }
        });

        //수입지출합계 버튼
        Button inputMoneyBtn = (Button)findViewById(R.id.inputMoney);
        inputMoneyBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                bCk=0;
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
                bCk=1;
                adapter.clear();
                for(int a=0; a<count; a++){

                        if (ioCheck[a] == true) {
                            manage.add(AaYear[a] + "년 " + AaMonth[a] + "월 " + AaDay[a] + "일 \n" + AaWhat.get(a) + "\n" + "금액  " + AaValue[a] + "원");
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
                bCk=2;
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


        //여기서부터 날짜 선택 관련
        simpleDateFormat1=new SimpleDateFormat("yyyy-M-d");
        simpleDateFormat2=new SimpleDateFormat("yyyy-M-d");
        simpleDateFormat3=new SimpleDateFormat("yyyy-M-d");
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
                        s1 = (Integer.toString(Syear)+"-"+Integer.toString(Smonth)+"-"+Integer.toString(Sday));

                        try {
                            d1 = simpleDateFormat1.parse(s1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                        //눌린 버튼에따라서!! 나타내기
                        if(((Eday!=0)&&(Eyear!=0)&&(Emonth!=0))&&bCk==0){
                            adapter.clear();
                            for(int a=0; a<count; a++) {
                                s2 = (Integer.toString(AaYear[a])+"-"+Integer.toString(AaMonth[a])+"-"+Integer.toString(AaDay[a]));

                                try {
                                    d2 = simpleDateFormat2.parse(s2);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                compareS=d1.compareTo(d2);

                                if ((compareS<=0) && (compareE>0)) {
                                    if(ioCheck[a]==false) {
                                        manage.add(AaYear[a] + "년 " + AaMonth[a] + "월 " + AaDay[a] + "일 \n" + AaWhat.get(a) + "\n" + "금액  " + AaValue[a] + "원");
                                    }
                                }
                            }
                            listview.setAdapter(adapter);

                        }

                        else if((Eday!=0)&&(Eyear!=0)&&(Emonth!=0)&&bCk==1){
                            adapter.clear();
                            for(int a=0; a<count; a++) {

                                s2 = (Integer.toString(AaYear[a])+"-"+Integer.toString(AaMonth[a])+"-"+Integer.toString(AaDay[a]));
                                try {
                                    d2 = simpleDateFormat2.parse(s2);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                compareS=d1.compareTo(d2);

                                if ((compareS<=0) && (compareE>0)) {
                                    if(ioCheck[a]==true) {
                                        manage.add(AaYear[a] + "년 " + AaMonth[a] + "월 " + AaDay[a] + "일 \n" + AaWhat.get(a) + "\n" + "금액  " + AaValue[a] + "원");
                                    }
                                }
                            }
                            listview.setAdapter(adapter);
                        }

                        else if((Eday!=0)&&(Eyear!=0)&&(Emonth!=0)&&bCk==2){
                            adapter.clear();
                            for(int a=0; a<count; a++) {

                                s2 = (Integer.toString(AaYear[a])+"-"+Integer.toString(AaMonth[a])+"-"+Integer.toString(AaDay[a]));
                                try {
                                    d2 = simpleDateFormat2.parse(s2);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                compareS=d1.compareTo(d2);
                                if ((compareS<=0) && (compareE>0)) {
                                    manage.add(AaYear[a] + "년 " + AaMonth[a] + "월 " + AaDay[a] + "일 \n" + AaFact.get(a) + "(" + AaWhat.get(a) + ")" + "\n" + "금액  " + AaValue[a] + "원 ");
                                }
                            }
                            listview.setAdapter(adapter);
                        }
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

                        s3 = (Integer.toString(Eyear)+"-"+Integer.toString(Emonth)+"-"+Integer.toString(Eday));

                        try {
                            d3 = simpleDateFormat3.parse(s3);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                        //눌린 버튼에따라서!! 나타내기
                        if(((Sday!=0)&&(Syear!=0)&&(Smonth!=0))&&bCk==0){
                            adapter.clear();

                            for(int a=0; a<count; a++) {

                                s2 = (Integer.toString(AaYear[a])+"-"+Integer.toString(AaMonth[a])+"-"+Integer.toString(AaDay[a]));
                                try {
                                    d2 = simpleDateFormat2.parse(s2);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                compareE=d3.compareTo(d2);


                                if ((compareS<=0) && (compareE>0)) {
                                    if(ioCheck[a]==false) {
                                        manage.add(AaYear[a] + "년 " + AaMonth[a] + "월 " + AaDay[a] + "일 \n" + AaWhat.get(a) + "\n" + "금액  " + AaValue[a] + "원");

                                    }
                                }
                            }

                            listview.setAdapter(adapter);

                        }

                        else if((Sday!=0)&&(Syear!=0)&&(Smonth!=0)&&bCk==1){
                            adapter.clear();
                            for(int a=0; a<count; a++) {


                                s2 = (Integer.toString(AaYear[a])+"-"+Integer.toString(AaMonth[a])+"-"+Integer.toString(AaDay[a]));
                                try {
                                    d2 = simpleDateFormat2.parse(s2);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                compareE=d3.compareTo(d2);



                                if ((compareS<=0) && (compareE>0)) {
                                    if(ioCheck[a]==true) {
                                        manage.add(AaYear[a] + "년 " + AaMonth[a] + "월 " + AaDay[a] + "일 \n" + AaWhat.get(a) + "\n" + "금액  " + AaValue[a] + "원");
                                    }
                                }
                            }
                            listview.setAdapter(adapter);
                        }

                        else if((Sday!=0)&&(Syear!=0)&&(Smonth!=0)&&bCk==2){
                            adapter.clear();

                            for(int a=0; a<count; a++) {


                                s2 = (Integer.toString(AaYear[a])+"-"+Integer.toString(AaMonth[a])+"-"+Integer.toString(AaDay[a]));
                                try {
                                    d2 = simpleDateFormat2.parse(s2);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                compareE=d3.compareTo(d2);

                                if ((compareS<=0) && (compareE>0)  ) {
                                    manage.add(AaYear[a] + "년 " + AaMonth[a] + "월 " + AaDay[a] + "일 \n" + AaFact.get(a) + "(" + AaWhat.get(a) + ")" + "\n" + "금액  " + AaValue[a] + "원 ");
                                }
                            }
                            listview.setAdapter(adapter);
                        }
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
                bCk=2;
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
