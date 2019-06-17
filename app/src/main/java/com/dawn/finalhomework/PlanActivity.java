package com.dawn.finalhomework;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class PlanActivity extends AppCompatActivity {
    Handler handler;
    EditText planN;
    String plan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        planN = findViewById(R.id.planN);
    }
    public void openTwo(View btn){
        plan = planN.getText().toString();
        PlanItem Nplan = new PlanItem(plan);
        PlanManager manager = new PlanManager(this);
        manager.add(Nplan);
        Intent intent = new Intent(this,FrameActivity.class);
        startActivity(intent);
        finish();
    }
}

