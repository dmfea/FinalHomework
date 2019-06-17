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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String nname = intent.getStringExtra("n_name");
        String npassword = intent.getStringExtra("n_password");
        nameT = findViewById(R.id.name);
        passwordT = findViewById(R.id.password);
        nameT.setText(nname);

        SharedPreferences sharedPreferences = getSharedPreferences("renzheng",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",nname);
        editor.putString("password",npassword);
        editor.commit();
        Log.i("进入","已保存");
    }
    public void openOne(View btn){
        //打开其他页面的代码
        Log.i("进入","openOne");
        name = nameT.getText().toString();
        password = passwordT.getText().toString();
        Log.i("sf",name+password);
        SharedPreferences sp = getSharedPreferences("renzheng",Activity.MODE_PRIVATE);
        if (name.equals(sp.getString("name","100"))&&password.equals(sp.getString("password","lll"))){
            Intent intent = new Intent(this,FrameActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(MainActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
        }
    }
    public void openNew(View btn){
        Intent intent = new Intent(this,NewInActivity.class);
        startActivity(intent);
    }
}
