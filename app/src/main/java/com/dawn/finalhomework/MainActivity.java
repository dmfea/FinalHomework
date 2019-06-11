package com.dawn.finalhomework;

import android.content.Intent;
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
        nameT = findViewById(R.id.name);
        passwordT = findViewById(R.id.password);



    }
    public void openOne(View btn){
        //打开其他页面的代码
        Log.i("进入","openOne");
        name = nameT.getText().toString();
        password = passwordT.getText().toString();
        Log.i("sf",name+password);
        if (name.equals("100")&&password.equals("lyj")){
            Intent intent = new Intent(this,FrameActivity.class);
            startActivityForResult(intent,1);
            finish();
        }else {
            Toast.makeText(MainActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
        }

    }
}
