package vn.edu.fpt.mola.app.view.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import vn.edu.fpt.mola.app.R;

public class CourseCreationActivity extends AppCompatActivity {

    private EditText mTitleText;
    private EditText mTopicText;
    private RadioGroup mDegreeRadio;
    private RadioGroup mCourseTypeRadio;
    private EditText mDescriptionText;
    private Button mSaveButton;
    private Button mSaveAndAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_creation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSaveButton = (Button) findViewById(R.id.save_button);
        mSaveAndAddButton = (Button) findViewById(R.id.save_add_another_button);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCourse();
            }
        });

        mSaveAndAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndAddCourse();
            }
        });
    }

    private void saveAndAddCourse() {
        this.setResult(RESULT_OK);
        Intent intent = getIntent();
        this.finish();
        startActivity(intent);
    }

    private void saveCourse() {
        this.setResult(RESULT_OK);
        this.finish();
    }

}
