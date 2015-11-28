package com.example.administrator.hmxfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2015/8/1.
 */
public class IntentPassValue2 extends Activity {
    private static final int SHOW_SUBACTIVITY = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intentpassvalue2);
        final EditText editText = (EditText) findViewById(R.id.EditTextIntent2);
        Button btn = (Button) findViewById(R.id.btn_IntentPassValue2);

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                 result.putExtra("result", editText.getText().toString());
                setResult(RESULT_OK, result);
                finish();
            }
        });

    }
}
