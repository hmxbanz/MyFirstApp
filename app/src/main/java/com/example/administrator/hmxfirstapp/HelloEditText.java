package com.example.administrator.hmxfirstapp;

/**
 * Created by Administrator on 2015/8/1.
 */

        import android.app.ActionBar;
        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.NavUtils;
        import android.support.v4.app.TaskStackBuilder;
        import android.text.Editable;
        import android.text.Selection;
        import android.view.KeyEvent;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.widget.TextView.OnEditorActionListener;
/**
 * EditText演示
 * @author 飞雪无情
 * @since 2010-11-29
 */
public class HelloEditText extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helloedittext);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);




        final EditText editText=(EditText)findViewById(R.id.edit_text);
        //监听回车键
        editText.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(HelloEditText.this, String.valueOf(actionId), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //获取EditText文本
        Button getValue=(Button)findViewById(R.id.btn_get_value);
        getValue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HelloEditText.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        //让EditText全选
        Button all=(Button)findViewById(R.id.btn_all);
        all.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.selectAll();
            }
        });
        //从第2个字符开始选择EditText文本
        Button select=(Button)findViewById(R.id.btn_select);
        select.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable editable=editText.getText();
                Selection.setSelection(editable, 1,editable.length());
            }
        });
        //获取选中的文本
        Button getSelect=(Button)findViewById(R.id.btn_get_select);
        getSelect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int start=editText.getSelectionStart();
                int end=editText.getSelectionEnd();
                CharSequence selectText=editText.getText().subSequence(start, end);
                Toast.makeText(HelloEditText.this, selectText, Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**
     * 交换两个索引
     * @param start 开始索引
     * @param end 结束索引
     */
    protected void switchIndex(int start, int end) {
        int temp=start;
        start=end;
        end=temp;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflate = getMenuInflater();
        menuInflate.inflate(R.menu.menu_main, menu);
        //return true;
        // menu.add(1, 1, 1, "个人后台");
        // menu.add(1, 3, 3, "个人后台2");
        // menu.add(0, 2, 2, "登录系统");

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                } else {
                    upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;

        }
        return true;
    }
}