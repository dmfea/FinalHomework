package com.dawn.finalhomework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class RjActivity extends AppCompatActivity {
    EditText second_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rj);

        Intent intent = getIntent();
        String rj = intent.getStringExtra("second_rj");

        second_input = findViewById(R.id.second_input);

        second_input.setText(rj);
    }
    public void save(View btn){
        //获取新的值
        String newbj = second_input.getText().toString();

        //保存值到Bundle或放入到Extra
        Intent intent = getIntent();
        Bundle bdl = new Bundle();
        bdl.putString("new_rj",newbj);
        intent.putExtras(bdl);
        setResult(97,intent);

        //返回原来页面
        finish();
    }
}
