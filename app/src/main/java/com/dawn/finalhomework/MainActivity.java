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

public class MainActivity extends AppCompatActivity {
    EditText nameT;
    EditText passwordT;
    String name;
    String password;
    String lsname;
    String lspassworrd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameT = findViewById(R.id.name);
        passwordT = findViewById(R.id.password);

        Intent intent = getIntent();
        lsname = intent.getStringExtra("n_name");
        lspassworrd = intent.getStringExtra("n_password");
        SharedPreferences sharedPreferences = getSharedPreferences("zhuce",Activity.MODE_PRIVATE);
        if (lsname!=null){
            nameT.setText(lsname);
        }else if (sharedPreferences.getString("zcname","") != ""){
            nameT.setText(sharedPreferences.getString("zcname",""));
        }

    }
    public void openOne(View btn){
        Log.i("进入","openOne");
        name = nameT.getText().toString();
        password = passwordT.getText().toString();
        Log.i("sf",name+password);
        SharedPreferences sp = getSharedPreferences("zhuce",Activity.MODE_PRIVATE);
        if (name.equals(sp.getString("zcname","100"))&&password.equals(sp.getString("zcpassword","lyj"))){
            Intent intent = new Intent(this,FrameActivity.class);
            startActivity(intent);
            finish();
        }else if (name.equals(lsname)&&password.equals(lspassworrd)){
            Intent intent = new Intent(this,FrameActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(MainActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
        }
    }
    public void openNew(View btn){
        Intent intent = new Intent(this,NewInActivity.class);
        startActivity(intent);
    }
}
