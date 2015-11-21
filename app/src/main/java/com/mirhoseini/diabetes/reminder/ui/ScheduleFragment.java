package com.mirhoseini.diabetes.reminder.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mirhoseini.diabetes.reminder.AppManager;
import com.mirhoseini.diabetes.reminder.MealInsulin;
import com.mirhoseini.diabetes.reminder.R;
import com.mirhoseini.diabetes.reminder.widget.ScheduleView;
import com.mirhoseini.diabetes.reminder.widget.ScheduleView.OnScheduleClickListener;

public class ScheduleFragment extends Fragment {
    final static String TAG = ScheduleFragment.class.getName();

    private ScheduleView scheduleView;

    public ScheduleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container,
                false);

        scheduleView = (ScheduleView) rootView.findViewById(R.id.scheduleView1);
        scheduleView.setSchedules(AppManager.getMeals(getActivity()));
        scheduleView.setOnScheduleClickListener(new OnScheduleClickListener() {

            @Override
            public void onMealClick(MealInsulin meal) {
                ScheduleDialogFragment scheduleDialogFragment = new ScheduleDialogFragment();
                Bundle args = new Bundle();
                args.putSerializable("DATA", meal);
                scheduleDialogFragment.setArguments(args);
                scheduleDialogFragment.show(getFragmentManager(), TAG);

                // create dialog and prevent null pointer exception
                scheduleDialogFragment.getFragmentManager()
                        .executePendingTransactions();
                scheduleDialogFragment.getDialog().setOnDismissListener(
                        new OnDismissListener() {

                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                scheduleView.invalidate();
                            }
                        });
            }

        });

        return rootView;
    }

}
