package vn.edu.fpt.mola.app.controller.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.edu.fpt.mola.app.R;
import vn.edu.fpt.mola.app.dummy.DummyContent;
import vn.edu.fpt.mola.app.model.Chapter;

public class ChapterCreationActivity extends AppCompatActivity {

    public static final String ARG_ITEM_ID = "item_id";

    private Chapter mItem;

    private EditText mTitleText;
    private EditText mDescriptionText;
    private Button mSaveButton;
    private Button mSaveAndAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_activity_chapter_creation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitleText = (EditText) findViewById(R.id.title_text);
        mDescriptionText = (EditText) findViewById(R.id.description_text);

        mSaveButton = (Button) findViewById(R.id.save_button);
        mSaveAndAddButton = (Button) findViewById(R.id.save_add_another_button);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveChapter();
            }
        });
        mSaveAndAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndAddChapter();
            }
        });

        if (savedInstanceState == null) {
            long chapterId = getIntent().getLongExtra(ARG_ITEM_ID, -1);
            if (chapterId != -1) {
                mItem = DummyContent.CHAPTER_MAP.get(chapterId);
                mTitleText.setText(mItem.getTitle());
                mDescriptionText.setText(mItem.getDescription());
                mSaveAndAddButton.setEnabled(false);
            }
        }
    }

    private void saveAndAddChapter() {
        this.setResult(RESULT_OK);
        Intent intent = getIntent();
        this.finish();
        startActivity(intent);
    }

    private void saveChapter() {
        this.setResult(RESULT_OK);
        this.finish();
    }

}
