package com.mirhoseini.diabetes.reminder.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.mirhoseini.diabetes.reminder.AppManager;
import com.mirhoseini.diabetes.reminder.MealInsulin.MealType;
import com.mirhoseini.diabetes.reminder.R;

public class MainActivity extends ParentActionBarMainActivity {

    public static final String ACTION_LOG_INSULIN_NOTIFICATION = "com.mirhoseini.diabetes.reminder.prefs.INSULIN_REMINDER_NOTIFICATION";

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void drawerInit() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initForm(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        AppManager.resetAlarms(context);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ScheduleFragment()).commit();

        if (getIntent().getAction().equals(ACTION_LOG_INSULIN_NOTIFICATION)) {
            Intent alarmIntent = new Intent(context, AlarmActivity.class);
            MealType mealType = (MealType) getIntent().getExtras()
                    .getSerializable("DATA");
            // MealType mealType = MealType.DINNER; // TODO: remove for test
            alarmIntent.putExtra("DATA", mealType);
            startActivity(alarmIntent);
        }
    }
}
