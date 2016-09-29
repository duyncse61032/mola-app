package vn.edu.fpt.mola.app.controller.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.edu.fpt.mola.app.R;
import vn.edu.fpt.mola.app.controller.teacher.dummy.DummyContent;
import vn.edu.fpt.mola.app.model.Chapter;
import vn.edu.fpt.mola.app.model.Course;
import vn.edu.fpt.mola.app.model.Lesson;

public class ChapterDetailActivity extends AppCompatActivity {

    public static final String ARG_COURSE_ID = "course_id";
    public static final String ARG_CHAPTER_ID = "chapter_id";
    private static final int CREATE_LESSON_RESULT = 1;
    private Chapter mChapter;
    private LessonRecyclerViewAdapter mViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_activity_chapter_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.create_lesson_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLessonCreation();
            }
        });

        if (savedInstanceState == null) {
            if (getIntent().hasExtra(ARG_CHAPTER_ID)) {
                Course course = DummyContent.COURSE_MAP.get(getIntent().getLongExtra(ARG_COURSE_ID, -1));
                mChapter = course.getChapterList().get(getIntent().getIntExtra(ARG_CHAPTER_ID, -1));
                CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
                if (appBarLayout != null) {
                    appBarLayout.setTitle(mChapter.getTitle());
                }

                if (mChapter != null) {
                    ((TextView) findViewById(R.id.description_view)).setText(mChapter.getDescription());

                    View recyclerView = findViewById(R.id.lesson_list);
                    assert recyclerView != null;
                    setupRecyclerView((RecyclerView) recyclerView);
                }
            }
        }
    }

    private void goToLessonCreation() {
        Intent intent = new Intent(this, LessonCreationActivity.class);
        startActivityForResult(intent, CREATE_LESSON_RESULT);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CREATE_LESSON_RESULT:
                if (resultCode == RESULT_OK) {
                    Lesson createdLesson = (Lesson) data.getSerializableExtra(LessonCreationActivity.NEW_LESSON);
                    mChapter.addLesson(createdLesson);
                    mViewAdapter.notifyDataSetChanged();
                }
        }
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        mViewAdapter = new LessonRecyclerViewAdapter(mChapter.getLessonList());
        recyclerView.setAdapter(mViewAdapter);
    }

    public class LessonRecyclerViewAdapter
            extends RecyclerView.Adapter<LessonRecyclerViewAdapter.ViewHolder> {

        private final List<Lesson> mLessonList;

        public LessonRecyclerViewAdapter(List<Lesson> lessonList) {
            this.mLessonList = lessonList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.teacher_lesson_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mLesson = mLessonList.get(position);
            holder.mTitleView.setText(mLessonList.get(position).getTitle());
            holder.mDurationView.setText(mLessonList.get(position).getDurationString());
        }

        @Override
        public int getItemCount() {
            return mLessonList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;
            public final TextView mTitleView;
            public final TextView mDurationView;
            public Lesson mLesson;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mTitleView = (TextView) view.findViewById(R.id.title_view);
                mDurationView = (TextView) view.findViewById(R.id.duration_view);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTitleView.getText() + "'";
            }
        }
    }
}
