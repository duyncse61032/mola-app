package vn.edu.fpt.mola.app.controller.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import vn.edu.fpt.mola.app.R;
import vn.edu.fpt.mola.app.dummy.DummyContent;
import vn.edu.fpt.mola.app.model.Course;

public class CourseCreationActivity extends AppCompatActivity {

    public static final String ARG_ITEM_ID = "item_id";
    private Course mItem;

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

        mTitleText = (EditText) findViewById(R.id.title_text);
        mTopicText = (EditText) findViewById(R.id.topic_text);
        mDegreeRadio = (RadioGroup) findViewById(R.id.degree_radio);
        mCourseTypeRadio = (RadioGroup) findViewById(R.id.course_type_radio);
        mDescriptionText = (EditText) findViewById(R.id.description_text);

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

        long courseId = getIntent().getLongExtra(ARG_ITEM_ID, -1);
        if (courseId != -1) {
            mItem = DummyContent.COURSE_MAP.get(courseId);
            mTitleText.setText(mItem.getTitle());
            mTopicText.setText(mItem.getTopic());
            mDescriptionText.setText(mItem.getDescription());
            switch (mItem.getDegree()) {
                case BEGINNER:
                    mDegreeRadio.check(R.id.beginner_degree_radio);
                    break;
                case INTERMEDIATE:
                    mDegreeRadio.check(R.id.intermediate_degree_radio);
                    break;
                case ADVANCED:
                    mDegreeRadio.check(R.id.advanced_degree_radio);
                    break;
            }
            mSaveAndAddButton.setEnabled(false);
        }
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
