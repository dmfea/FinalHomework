package com.dawn.finalhomework;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FrameActivity extends FragmentActivity implements Runnable, AdapterView.OnItemClickListener{
    private Fragment mFragments[];
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RadioButton rbtHome,rbtThought,rbtPlan;
    Handler handler;
    private String updateDate = "";
    String todayStn;
    TextView jz;
    ArrayAdapter adapter;
    List<String> plan = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        jz = findViewById(R.id.homeTextView1);


        mFragments = new Fragment[3];
        fragmentManager = getSupportFragmentManager();
        mFragments[0] = fragmentManager.findFragmentById(R.id.fragment_home);
        mFragments[1] = fragmentManager.findFragmentById(R.id.fragment_thought);
        mFragments[2] = fragmentManager.findFragmentById(R.id.fragment_plan);
        fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
        fragmentTransaction.show(mFragments[0]).commit();

        rbtHome = findViewById(R.id.radioHome);
        rbtThought = findViewById(R.id.radioThought);
        rbtPlan = findViewById(R.id.radioPlan);
        rbtHome.setBackgroundResource(R.drawable.shape2);
        rbtThought.setBackgroundResource(R.drawable.shape3);
        rbtPlan.setBackgroundResource(R.drawable.shape3);
        radioGroup = findViewById(R.id.bottomGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("radioGroup","ceckedId="+checkedId);
                fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
                rbtHome.setBackgroundResource(R.drawable.shape3);
                rbtThought.setBackgroundResource(R.drawable.shape3);
                rbtPlan.setBackgroundResource(R.drawable.shape3);
                switch (checkedId){
                    case R.id.radioHome:
                        fragmentTransaction.show(mFragments[0]).commit();
                        rbtHome.setBackgroundResource(R.drawable.shape2);
                        break;
                    case R.id.radioThought:
                        fragmentTransaction.show(mFragments[1]).commit();
                        rbtThought.setBackgroundResource(R.drawable.shape2);
                        break;
                    case R.id.radioPlan:
                        fragmentTransaction.show(mFragments[2]).commit();
                        rbtPlan.setBackgroundResource(R.drawable.shape2);
                        break;
                    default:
                        break;
                }
            }
        });
        //获取当前系统时间
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        final String todayStr = sdf.format(today);
        Log.i("第一个页面","今日系统时间"+todayStr);
        //获取保存的时间与句子
        SharedPreferences sharedPreferences = getSharedPreferences("mystn",Activity.MODE_PRIVATE);
        updateDate = sharedPreferences.getString("Supdate","");
        todayStn = sharedPreferences.getString("Stodaystn","");

        Log.i("第一个页面","保存后更新时间"+updateDate);

        Thread t = new Thread(this);
        t.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (!todayStr.equals(updateDate)){
                    Log.i("第一个页面","需要更新");
                    if (msg.what==5){
                        Bundle bdl = (Bundle) msg.obj;
                        todayStn = bdl.getString("sentence");
                        Log.i("第一个页面","带回今日句子"+todayStn);
                        //保存更新日期、今日句子
                        SharedPreferences sharedPreferences = getSharedPreferences("mystn",Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Supdate",todayStr);
                        editor.putString("Stodaystn",todayStn);
                        editor.apply();
                        Log.i("第一个页面","已保存日期和句子"+todayStn);
                        jz.setText(todayStn);
                        Toast.makeText(FrameActivity.this,"句子已更新",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Log.i("第一个页面","不需要更新"+"今日句子是"+todayStn);
                    jz.setText(todayStn);
                }

                super.handleMessage(msg);
            }
        };
        ListView listView = findViewById(R.id.myPlanlist);
        PlanManager manager = new PlanManager(this);
        for (PlanItem item :manager.listAll()){
            plan.add(item.getCurPlan());
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,plan);
        listView.setAdapter(adapter);
        listView.setEmptyView(findViewById(R.id.noPlan));
        listView.setOnItemClickListener(this);
    }
    @Override
    public void run() {
        Bundle bundle = getFromNET();
        //获取msg对象，用于返回主线程
        Message msg = handler.obtainMessage(5);
        msg.obj = bundle;
        handler.sendMessage(msg);
    }
    private Bundle getFromNET() {
        Bundle bundle = new Bundle();
        Document doc = null;
        try{
            java.util.Random r = new java.util.Random();
            int i = r.nextInt(70)+1;
            doc = Jsoup.connect("http://www.buhuiwan.com/juzi/4508.html").get();
            Log.i("第一个页面","run:"+doc.title());
            Elements ps = doc.getElementsByTag("p");
            Element p=ps.get(i);
            String pt = p.text();
            bundle.putString("sentence",pt);
            Log.i("第一个页面","获取到今日句子"+pt);

        }catch (IOException e){
            e.printStackTrace();
        }
        return bundle;
    }

    @Override
    public void onItemClick(AdapterView<?> listv, View view, int position, long id) {
        Log.i("第三个页面","onItemClick:position"+position);
        Log.i("第三个页面","onItemClick:parent"+listv);
        adapter.remove(listv.getItemAtPosition(position));
    }
    public void openThree(View btn){
        Intent intent = new Intent(this,PlanActivity.class);
        startActivity(intent);
        finish();

    }


}
