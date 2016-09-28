package vn.edu.fpt.mola.app.controller.teacher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import vn.edu.fpt.mola.app.R;
import vn.edu.fpt.mola.app.dummy.DummyContent;
import vn.edu.fpt.mola.app.model.TimeFrame;

public class TimeFrameCreationActivity extends AppCompatActivity {

    private DatePicker mStartDatePicker;
    private DatePicker mEndDatePicker;
    private TimePicker mFromTimePicker;
    private TimePicker mToTimePicker;
    private RadioGroup mAgendaRadio;

    private LinearLayout mWeekdayCheckbox;
    private CheckBox mSundayCheckbox;
    private CheckBox mMondayCheckbox;
    private CheckBox mTuesdayCheckbox;
    private CheckBox mWednesdayCheckbox;
    private CheckBox mThursdayCheckbox;
    private CheckBox mFridayCheckbox;
    private CheckBox mSaturdayCheckbox;

    private Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_frame_creation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mStartDatePicker = (DatePicker) findViewById(R.id.startDatePicker);
        mEndDatePicker = (DatePicker) findViewById(R.id.endDatePicker);
        mFromTimePicker = (TimePicker) findViewById(R.id.fromTimePicker);
        mToTimePicker = (TimePicker) findViewById(R.id.toTimePicker);
        mAgendaRadio = (RadioGroup) findViewById(R.id.agenda_radio);
        mWeekdayCheckbox = (LinearLayout) findViewById(R.id.week_day_checkbox);
        mWeekdayCheckbox.setVisibility(View.GONE);

        mSundayCheckbox = (CheckBox) findViewById(R.id.sunday_checkbox);
        mMondayCheckbox = (CheckBox) findViewById(R.id.monday_checkbox);
        mTuesdayCheckbox = (CheckBox) findViewById(R.id.tuesday_checkbox);
        mWednesdayCheckbox = (CheckBox) findViewById(R.id.wednesday_checkbox);
        mThursdayCheckbox = (CheckBox) findViewById(R.id.thursday_checkbox);
        mFridayCheckbox = (CheckBox) findViewById(R.id.friday_checkbox);
        mSaturdayCheckbox = (CheckBox) findViewById(R.id.sunday_checkbox);

        mSaveButton = (Button) findViewById(R.id.save_button);

        mAgendaRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.daily_agenda_radio) {
                    mWeekdayCheckbox.setVisibility(View.GONE);
                } else if (checkedId == R.id.weekly_agenda_radio) {
                    mWeekdayCheckbox.setVisibility(View.VISIBLE);
                }
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTimeFrame();
            }
        });
    }

    private void saveTimeFrame() {
        TimeFrame timeFrame = new TimeFrame();
        LocalDate startDate = new LocalDate(
                mStartDatePicker.getYear(),
                mStartDatePicker.getMonth() + 1,
                mStartDatePicker.getDayOfMonth()
        );
        timeFrame.setStartDate(startDate);
        LocalDate endDate = new LocalDate(
                mEndDatePicker.getYear(),
                mEndDatePicker.getMonth() + 1,
                mEndDatePicker.getDayOfMonth()
        );
        timeFrame.setEndDate(endDate);
        LocalTime fromTime = new LocalTime(
                mFromTimePicker.getCurrentHour(),
                mFromTimePicker.getCurrentMinute()
        );
        timeFrame.setFromTime(fromTime);
        LocalTime toTime = new LocalTime(
                mToTimePicker.getCurrentHour(),
                mToTimePicker.getCurrentMinute()
        );
        timeFrame.setToTime(toTime);

        int agenda = mAgendaRadio.getCheckedRadioButtonId();
        if (agenda == R.id.daily_agenda_radio) {
            timeFrame.setDaily(true);
        } else if (agenda == R.id.weekly_agenda_radio) {
            int weekDaySum = 0;
            if (mSundayCheckbox.isChecked()) weekDaySum += 2;
            if (mMondayCheckbox.isChecked()) weekDaySum += 4;
            if (mTuesdayCheckbox.isChecked()) weekDaySum += 8;
            if (mWednesdayCheckbox.isChecked()) weekDaySum += 16;
            if (mThursdayCheckbox.isChecked()) weekDaySum += 32;
            if (mFridayCheckbox.isChecked()) weekDaySum += 64;
            if (mSaturdayCheckbox.isChecked()) weekDaySum += 128;
            timeFrame.setWeekly(weekDaySum);
        }
        DummyContent.TIME_FRAME_LIST.add(timeFrame);
        timeFrame.setId(DummyContent.TIME_FRAME_LIST.size());
        DummyContent.TIME_FRAME_MAP.put(timeFrame.getId(), timeFrame);

        this.setResult(RESULT_OK);
        this.finish();
    }

}
