package com.dawn.finalhomework;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ZhucActivity extends AppCompatActivity {
    EditText zcnname;
    EditText zcnpassword;
    String zcname;
    String zcpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuc);
        zcnname = findViewById(R.id.zhuce_name);
        zcnpassword = findViewById(R.id.zhuce_password);

    }
    public void zhuce(View btn){
        zcname = zcnname.getText().toString();
        zcpassword = zcnpassword.getText().toString();
        Log.i("新建","获取到"+zcname);
        SharedPreferences sharedPreferences = getSharedPreferences("zhuce",Activity.MODE_PRIVATE);
        if (sharedPreferences.getString("zcname","")==""){
            Intent intent = new Intent(this,MainActivity.class);
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.remove("zcname");
            editor.remove("zcpassword");
            editor.commit();
            editor.putString("zcname",zcname);
            editor.putString("zcpassword",zcpassword);
            editor.commit();
            startActivity(intent);
        }else {
            Toast.makeText(this,"用户已注册，请直接登录",Toast.LENGTH_SHORT).show();
        }
    }
    public void zcopenMain(View btn){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
