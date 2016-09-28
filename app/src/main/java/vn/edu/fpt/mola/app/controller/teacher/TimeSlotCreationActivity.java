package vn.edu.fpt.mola.app.controller.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import org.joda.time.LocalTime;

import vn.edu.fpt.mola.app.R;
import vn.edu.fpt.mola.app.model.TimeSlot;

public class TimeSlotCreationActivity extends AppCompatActivity {

    public static final String NEW_TIME_SLOT = "vn.edu.fpt.mola.app.controller.teacher.TimeSlotCreationActivity.NewTimeSlot";
    
    private TimePicker mFromTimePicker;
    private TimePicker mToTimePicker;
    private Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_activity_time_slot_creation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFromTimePicker = (TimePicker) findViewById(R.id.fromTimePicker);
        mToTimePicker = (TimePicker) findViewById(R.id.toTimePicker);
        mSaveButton = (Button) findViewById(R.id.save_button);
        
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTimeSlot();
            }
        });
    }

    private void saveTimeSlot() {
        TimeSlot slot = new TimeSlot();
        LocalTime fromTime = new LocalTime(
                mFromTimePicker.getCurrentHour(),
                mFromTimePicker.getCurrentMinute()
        );
        slot.setFromTime(fromTime);
        LocalTime toTime = new LocalTime(
                mToTimePicker.getCurrentHour(),
                mToTimePicker.getCurrentMinute()
        );
        slot.setToTime(toTime);

        Intent data = new Intent();
        data.putExtra(NEW_TIME_SLOT, slot);

        this.setResult(RESULT_OK, data);
        this.finish();
    }

}
