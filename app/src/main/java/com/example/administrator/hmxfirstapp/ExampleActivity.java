package com.example.administrator.hmxfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.hmxfirstapp.Example.ItemExample;
import com.example.administrator.hmxfirstapp.Menu.HomeActivity;

/**
 * Created by Administrator on 2015/10/6.
 */
public  class ExampleActivity extends Activity{
    private ListView ListViewExample;
    private ArrayAdapter<ItemExample> ListViewExampleAdapter;
    private ItemExample[] ArrayExampleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutexample);

        ListViewExample= (ListView) findViewById(R.id.ListViewExample);

        ArrayExampleList=new ItemExample[]{
                new ItemExample(this,"读取raw目录下文件",new Intent(this, HomeActivity.class)),
                new ItemExample(this,"读取assets目录下文件",new Intent(this, HomeActivity.class)),
                new ItemExample(this,"待添加",new Intent(this, HomeActivity.class)),
                new ItemExample(this,"待添加",new Intent(this, HomeActivity.class))
        };

        ListViewExampleAdapter=new ArrayAdapter<ItemExample>(this,android.R.layout.simple_list_item_1);
        ListViewExampleAdapter.addAll(ArrayExampleList);
        ListViewExample.setAdapter(ListViewExampleAdapter);

        ListViewExample.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemExample item= (ItemExample) adapterView.getItemAtPosition(i);
                item.StartActivity();

            }
        });

    }
}
