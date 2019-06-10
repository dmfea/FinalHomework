package com.dawn.finalhomework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FrameActivity extends FragmentActivity{
    private Fragment mFragments[];
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RadioButton rbtHome,rbtThought,rbtPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

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
    }
}
