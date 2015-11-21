package com.mirhoseini.diabetes.reminder.ui;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.mirhoseini.diabetes.reminder.R;

import java.util.Timer;
import java.util.TimerTask;

public abstract class ParentActionBarMainActivity extends
        ParentActionBarActivity implements ListView.OnItemClickListener {

    protected ActionBarDrawerToggle actionBarDrawerToggle;
    protected ActionBar actionBar;
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        drawerInit();
    }

    @Override
    protected void onResume() {
        doubleBackToExitPressedOnce = false;
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish();
            return;
        }
        doubleBackToExitPressedOnce = true;

        String str = // Farsi.Convert((
                getResources().getString(R.string.msg_exit)
                // ))
                ;
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

        Timer t = new Timer();
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2500);

    }

    protected abstract void drawerInit();

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (actionBarDrawerToggle != null)
            actionBarDrawerToggle.syncState();
    }
}
