package vn.edu.fpt.mola.app.view.teacher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;

import vn.edu.fpt.mola.app.R;
import vn.edu.fpt.mola.app.dummy.DummyContent;
import vn.edu.fpt.mola.app.model.Chapter;
import vn.edu.fpt.mola.app.model.Course;
import vn.edu.fpt.mola.app.model.Lesson;

/**
 * A fragment representing a single Course detail screen.
 * This fragment is either contained in a {@link CourseListActivity}
 * in two-pane mode (on tablets) or a {@link CourseDetailActivity}
 * on handsets.
 */
public class CourseDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Course mItem;

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CourseDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.COURSE_MAP.get(getArguments().getLong(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getTitle());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.course_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.course_detail)).setText(mItem.getDescription());

            View recyclerView = rootView.findViewById(R.id.chapter_list);
            assert recyclerView != null;
            //setupRecyclerView((RecyclerView) recyclerView);
        }


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        expandableListView = (ExpandableListView) view.findViewById(R.id.course_outline_list_view);
        expandableListAdapter = new CourseOutlineListAdapter(getContext(), DummyContent.CHAPTER_LIST);
        expandableListView.setAdapter(expandableListAdapter);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new ChapterRecyclerViewAdapter(DummyContent.CHAPTER_LIST));
    }

    public class CourseOutlineListAdapter extends BaseExpandableListAdapter {

        private Context context;
        private List<Chapter> chapterList;

        public CourseOutlineListAdapter(Context context, List<Chapter> chapterList) {
            this.context = context;
            this.chapterList = chapterList;
        }

        @Override
        public int getGroupCount() {
            return chapterList.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return chapterList.get(i).getLessonList().size();
        }

        @Override
        public Object getGroup(int i) {
            return chapterList.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return chapterList.get(i).getLessonList().get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return chapterList.get(i).getId();
        }

        @Override
        public long getChildId(int i, int i1) {
            return chapterList.get(i).getLessonList().get(i1).getId();
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int chapterIndex, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this.context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.chapter_list_content, null);
            }

            TextView idView = (TextView) convertView.findViewById(R.id.id);
            TextView contentView = (TextView) convertView.findViewById(R.id.content);
            Chapter chapter = (Chapter) getGroup(chapterIndex);
            idView.setTypeface(null, Typeface.BOLD);
            idView.setText(Long.toString(chapter.getId()));
            contentView.setText(chapter.getTitle());

            return convertView;
        }

        @Override
        public View getChildView(int chapterIndex, int lessonIndex, boolean isLastChild, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this.context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.lesson_list_content, null);
            }

            TextView idView = (TextView) convertView.findViewById(R.id.id);
            TextView contentView = (TextView) convertView.findViewById(R.id.content);
            Lesson lesson = (Lesson) getChild(chapterIndex, lessonIndex);

            idView.setText(Long.toString(lesson.getId()));
            contentView.setText(lesson.getTitle());
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }

    public class ChapterRecyclerViewAdapter
            extends RecyclerView.Adapter<ChapterRecyclerViewAdapter.ViewHolder> {

        private final List<Chapter> mValues;

        public ChapterRecyclerViewAdapter(List<Chapter> items) {
            mValues = items;
        }

        @Override
        public ChapterRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chapter_list_content, parent, false);
            return new ChapterRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ChapterRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(Long.toString(mValues.get(position).getId()));
            holder.mContentView.setText(mValues.get(position).getTitle());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Context context = v.getContext();
                        Intent intent = new Intent(context, CourseDetailActivity.class);
                        intent.putExtra(CourseDetailFragment.ARG_ITEM_ID, holder.mItem.getId());

                        context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public Chapter mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
