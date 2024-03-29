package com.dawn.finalhomework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class BjActivity extends AppCompatActivity {
    EditText first_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bj);

        Intent intent = getIntent();
        String bj = intent.getStringExtra("first_bj");

        first_input = findViewById(R.id.first_input);

        first_input.setText(bj);
    }
    public void save(View btn){
        //获取新的值
        String newbj = first_input.getText().toString();

        //保存值到Bundle或放入到Extra
        Intent intent = getIntent();
        Bundle bdl = new Bundle();
        bdl.putString("new_bj",newbj);
        intent.putExtras(bdl);
        setResult(99,intent);

        //返回原来页面
        finish();
    }
}
