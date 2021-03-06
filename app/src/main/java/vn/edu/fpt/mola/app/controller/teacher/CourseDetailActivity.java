package vn.edu.fpt.mola.app.controller.teacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import vn.edu.fpt.mola.app.model.enumerate.CourseStatus;

/**
 * An activity representing a single Course detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link CourseListActivity}.
 */
public class CourseDetailActivity extends AppCompatActivity {

    public static final String ARG_COURSE_ID = "course_id";
    private static final int EDIT_COURSE_RESULT = 1;
    private static final int CREATE_CHAPTER_RESULT = 2;
    private Course mCourse;
    private ChapterRecyclerViewAdapter mViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_activity_course_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {

            if (getIntent().hasExtra(ARG_COURSE_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                mCourse = DummyContent.COURSE_MAP.get(getIntent().getLongExtra(ARG_COURSE_ID, -1));

                CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
                if (appBarLayout != null) {
                    appBarLayout.setTitle(mCourse.getTitle());
                }

                // Show the dummy content as text in a TextView.
                if (mCourse != null) {
                    ((TextView) findViewById(R.id.topic_view)).setText(mCourse.getTopic());
                    ((TextView) findViewById(R.id.degree_view)).setText(mCourse.getDegree().toString());
                    ((TextView) findViewById(R.id.description_view)).setText(mCourse.getDescription());
                    ((TextView) findViewById(R.id.create_date_view)).setText(mCourse.getCreateDateString());
                    ((TextView) findViewById(R.id.status_view)).setText(mCourse.getState().toString());


                    View recyclerView = findViewById(R.id.chapter_list);
                    assert recyclerView != null;
                    setupRecyclerView((RecyclerView) recyclerView);
                }
            }
        }

        FloatingActionButton editButton = (FloatingActionButton) findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCourseEdition();
            }
        });

        FloatingActionButton deleteButton = (FloatingActionButton) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCourse();
            }
        });

        FloatingActionButton openButton = (FloatingActionButton) findViewById(R.id.open_button);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCourse();
            }
        });

        FloatingActionButton closeButton = (FloatingActionButton) findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeCourse();
            }
        });

        if (mCourse.getState() == CourseStatus.DRAFT || mCourse.getState() == CourseStatus.CLOSED) {
            closeButton.setVisibility(View.INVISIBLE);
        } else {
            openButton.setVisibility(View.INVISIBLE);
        }

        FloatingActionButton createChapterButton = (FloatingActionButton) findViewById(R.id.create_chapter_button);
        createChapterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChapterCreation();
            }
        });
    }

    private void closeCourse() {
        mCourse.setState(CourseStatus.CLOSED);
        this.finish();
        startActivity(getIntent());
    }

    private void openCourse() {
        mCourse.setState(CourseStatus.OPENED);
        this.finish();
        startActivity(getIntent());
    }


    private void deleteCourse() {
        DummyContent.COURSE_LIST.remove(mCourse);
        DummyContent.COURSE_MAP.remove(mCourse);
        this.finish();
    }

    private void goToCourseEdition() {
        Intent intent = new Intent(CourseDetailActivity.this, CourseCreationActivity.class);
        intent.putExtra(CourseCreationActivity.ARG_ITEM_ID, mCourse.getId());
        startActivityForResult(intent, EDIT_COURSE_RESULT);
    }

    private void goToChapterCreation() {
        Intent intent = new Intent(this, ChapterCreationActivity.class);
        startActivityForResult(intent, CREATE_CHAPTER_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case EDIT_COURSE_RESULT:
                this.finish();
                startActivity(getIntent());
                break;
            case CREATE_CHAPTER_RESULT:
                if (resultCode == RESULT_OK) {
                    Chapter createdChapter = (Chapter) data.getSerializableExtra(ChapterCreationActivity.NEW_CHAPTER);
                    mCourse.addChapter(createdChapter);
                    mViewAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, CourseListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        mViewAdapter = new ChapterRecyclerViewAdapter(mCourse.getChapterList());
        recyclerView.setAdapter(mViewAdapter);
    }

    public class ChapterRecyclerViewAdapter
            extends RecyclerView.Adapter<ChapterRecyclerViewAdapter.ViewHolder> {

        private final List<Chapter> mChapterList;

        public ChapterRecyclerViewAdapter(List<Chapter> items) {
            mChapterList = items;
        }

        @Override
        public ChapterRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.teacher_chapter_list_content, parent, false);
            return new ChapterRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ChapterRecyclerViewAdapter.ViewHolder holder, final int position) {
            holder.mChapter = mChapterList.get(position);
            holder.mTitleView.setText(mChapterList.get(position).getTitle());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();
                    Intent intent = new Intent(context, ChapterDetailActivity.class);
                    intent.putExtra(ChapterDetailActivity.ARG_COURSE_ID, mCourse.getId());
                    intent.putExtra(ChapterDetailActivity.ARG_CHAPTER_ID, position);

                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mChapterList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mTitleView;
            public Chapter mChapter;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mTitleView = (TextView) view.findViewById(R.id.title_view);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTitleView.getText() + "'";
            }
        }
    }
}
