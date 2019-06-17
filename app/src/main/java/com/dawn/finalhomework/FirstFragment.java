package com.dawn.finalhomework;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    Button bj;
    TextView first_show;
    String bjshow;


    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);

    }
    @Override
    public void onActivityCreated(Bundle savadInstanceState){
        super.onActivityCreated(savadInstanceState);
        bj = getActivity().findViewById(R.id.bjbj);
        first_show = getActivity().findViewById(R.id.first_show);
        bjshow = first_show.getText().toString();
        //获取SP里保存的数据
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mybj",Activity.MODE_PRIVATE);
        bjshow = sharedPreferences.getString("fin_bj"," ");
        first_show.setText(bjshow);


        bj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),BjActivity.class);
                intent.putExtra("first_bj",bjshow);
                startActivityForResult(intent,100);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==100&&resultCode==99){
            Bundle bundle = data.getExtras();
            bjshow = bundle.getString("new_bj");
            //将新设置的汇率写到SP里
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mybj",Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("fin_bj",bjshow);
            editor.commit();
            first_show.setText(bjshow);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
