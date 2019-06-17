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
public class SecondFragment extends Fragment {
    Button bj;
    TextView second_show;
    String rjshow;


    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savadInstanceState){
        super.onActivityCreated(savadInstanceState);
        bj = getActivity().findViewById(R.id.bjrj);
        second_show = getActivity().findViewById(R.id.second_show);
        rjshow = second_show.getText().toString();

        //获取SP里保存的数据
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myrj",Activity.MODE_PRIVATE);
        rjshow = sharedPreferences.getString("fin_rj"," ");
        second_show.setText(rjshow);

        bj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),RjActivity.class);
                intent.putExtra("second_rj",rjshow);
                startActivityForResult(intent,98);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==98&&resultCode==97){
            Bundle bundle = data.getExtras();
            rjshow = bundle.getString("new_rj");
            //将新设置的汇率写到SP里
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myrj",Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("fin_rj",rjshow);
            editor.commit();
            second_show.setText(rjshow);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
