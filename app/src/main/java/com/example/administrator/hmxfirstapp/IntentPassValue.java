package com.example.administrator.hmxfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/8/1.
 */
public class IntentPassValue extends Activity {
    private static final int SHOW_SUBACTIVITY = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intentpassvalue);
        final EditText editText=(EditText)findViewById(R.id.EditTextIntent);
        Button btn=(Button)findViewById(R.id.btn_IntentPassValue);


        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentPassValue.this, IntentPassValue2.class);
                startActivityForResult(intent, SHOW_SUBACTIVITY);
        }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        String result =data.getExtras().getString("result");
        Toast.makeText(IntentPassValue.this, result, Toast.LENGTH_SHORT).show();
    }
}
