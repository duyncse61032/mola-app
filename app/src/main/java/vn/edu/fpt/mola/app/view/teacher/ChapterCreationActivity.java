package vn.edu.fpt.mola.app.view.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.edu.fpt.mola.app.R;

public class ChapterCreationActivity extends AppCompatActivity {

    private EditText mTitleText;
    private EditText mDescriptionText;
    private Button mSaveButton;
    private Button mSaveAndAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_creation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
