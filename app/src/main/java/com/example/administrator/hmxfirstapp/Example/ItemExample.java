package com.example.administrator.hmxfirstapp.Example;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2015/10/6.
 */
public class ItemExample {
    private String name;
    private Intent intent;
    private Context context;
    public ItemExample(Context context,String name,Intent intent)
    {
        this.context=context;
        this.name=name;
        this.intent=intent;
    }
    public Context getContext()
    {
        return context;
    }

    public void StartActivity()
    {
        this.context.startActivity(this.intent);
    }


    @Override
    public String toString() {
        return this.name;
    }
}
