package com.example.administrator.hmxfirstapp.Menu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.administrator.hmxfirstapp.Menu.MenuContent;
import com.example.administrator.hmxfirstapp.Menu.MenuFragment;
import com.example.administrator.hmxfirstapp.R;

import java.lang.Override;

/**
 * Created by Stas Melnychenko on 19.06.2015.
 */
public class BaseActivity extends AppCompatActivity implements MenuFragment.MenuListener {
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
       setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
       getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu: {
                triggerFragmentMenu();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void triggerFragmentMenu() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment menuFragment = fragmentManager.findFragmentById(R.id.fragment_menu_container);
        if (menuFragment == null) {
            MenuFragment fragment = MenuFragment.newInstance();
            fragmentTransaction.replace(R.id.fragment_menu_container, fragment);
        } else {
            fragmentTransaction.remove(menuFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onMenuItemSelected(int menuPosition) {
        removeMenuFragment();
        MenuContent.MenuItem menuItem = MenuContent.ITEMS.get(menuPosition);
        Intent intent = new Intent(this, menuItem.activityClass);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (!removeMenuFragment())
            super.onBackPressed();
    }

    private boolean removeMenuFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment menuFragment = fragmentManager.findFragmentById(R.id.fragment_menu_container);
        if (menuFragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(menuFragment);
            fragmentTransaction.commit();
            return true;
        } else {
            return false;
        }
    }

}
