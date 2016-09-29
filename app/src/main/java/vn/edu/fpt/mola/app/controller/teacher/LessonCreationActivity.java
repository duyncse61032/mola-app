package vn.edu.fpt.mola.app.controller.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.joda.time.Period;

import vn.edu.fpt.mola.app.R;
import vn.edu.fpt.mola.app.model.Lesson;

public class LessonCreationActivity extends AppCompatActivity {

    public static final String NEW_LESSON = "vn.edu.fpt.mola.app.controller.teacher.LessonCreationActivity.NewLesson";

    private EditText mTitleText;
    private EditText mHourText;
    private EditText mMinuteText;
    private Button mSaveButton;
    private Button mSaveAndAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_activity_lesson_creation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitleText = (EditText) findViewById(R.id.title_text);
        mHourText = (EditText) findViewById(R.id.hour_text);
        mMinuteText = (EditText) findViewById(R.id.minute_text);
        mSaveButton = (Button) findViewById(R.id.save_button);
        mSaveAndAddButton = (Button) findViewById(R.id.save_add_another_button);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveLesson();
            }
        });

        mSaveAndAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndAddLesson();
            }
        });
    }

    private Lesson createLesson() {
        Lesson lesson = new Lesson();
        lesson.setTitle(mTitleText.getText().toString());
        int hours = 0;
        try {
            hours = Integer.parseInt(mHourText.getText().toString());
        } catch (NumberFormatException e) { }
        int minutes = 0;
        try {
            minutes = Integer.parseInt(mMinuteText.getText().toString());
        } catch (NumberFormatException e) { }
        Period p = new Period(
                hours,
                minutes,
                0, // second
                0 // millis
        );
        lesson.setPeriod(p);
        return lesson;
    }

    private void saveAndAddLesson() {
        Lesson lesson = createLesson();
        Intent data = new Intent();
        data.putExtra(NEW_LESSON, lesson);
        this.setResult(RESULT_OK, data);

        Intent intent = getIntent();
        this.finish();
        startActivity(intent);
    }

    private void saveLesson() {
        Lesson lesson = createLesson();
        Intent data = new Intent();
        data.putExtra(NEW_LESSON, lesson);
        this.setResult(RESULT_OK, data);

        this.finish();
    }

}
