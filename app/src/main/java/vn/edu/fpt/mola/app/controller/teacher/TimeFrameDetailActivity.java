package vn.edu.fpt.mola.app.controller.teacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import vn.edu.fpt.mola.app.model.TimeFrame;
import vn.edu.fpt.mola.app.model.TimeSlot;

/**
 * An activity representing a single TimeFrame detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link TimeFrameListActivity}.
 */
public class TimeFrameDetailActivity extends AppCompatActivity {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private TimeFrame mTimeFrame;

    private static final int CREATE_TIME_SLOT_RESULT = 1;
    private TimeSlotRecyclerViewAdapter mViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_activity_timeframe_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton deleteButton = (FloatingActionButton) findViewById(R.id.delete_time_frame_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton AddTimeSlotButton = (FloatingActionButton) findViewById(R.id.create_time_slot_button);
        AddTimeSlotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTimeSlotCreation();
            }
        });

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
            if (getIntent().hasExtra(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                mTimeFrame = DummyContent.TIME_FRAME_MAP.get(getIntent().getLongExtra(ARG_ITEM_ID, -1));

                CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
                if (appBarLayout != null) {
                    appBarLayout.setTitle(mTimeFrame.toString());
                }

                ((TextView) findViewById(R.id.date_view)).setText(mTimeFrame.getStartEndDate());
                ((TextView) findViewById(R.id.time_view)).setText(mTimeFrame.getFromToTime());
                ((TextView) findViewById(R.id.agenda_view)).setText(mTimeFrame.getAgenda());

                View recyclerView = findViewById(R.id.time_slot_list);
                assert recyclerView != null;
                setupRecyclerView((RecyclerView) recyclerView);
            }
        }

    }

    private void goToTimeSlotCreation() {
        Intent intent = new Intent(this, TimeSlotCreationActivity.class);
        startActivityForResult(intent, CREATE_TIME_SLOT_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CREATE_TIME_SLOT_RESULT:
                if (resultCode == RESULT_OK) {
                    TimeSlot createdSlot = (TimeSlot) data.getSerializableExtra(TimeSlotCreationActivity.NEW_TIME_SLOT);
                    mTimeFrame.addSlot(createdSlot);
                    mViewAdapter.notifyDataSetChanged();
                }
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
            navigateUpTo(new Intent(this, TimeFrameListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        mViewAdapter = new TimeFrameDetailActivity.TimeSlotRecyclerViewAdapter(this.mTimeFrame.getSlotList());
        recyclerView.setAdapter(mViewAdapter);
    }

    public class TimeSlotRecyclerViewAdapter
            extends RecyclerView.Adapter<TimeFrameDetailActivity.TimeSlotRecyclerViewAdapter.ViewHolder> {

        private final List<TimeSlot> mValues;

        public TimeSlotRecyclerViewAdapter(List<TimeSlot> items) {
            mValues = items;
        }

        @Override
        public TimeFrameDetailActivity.TimeSlotRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.teacher_timeslot_list_content, parent, false);
            return new TimeFrameDetailActivity.TimeSlotRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final TimeFrameDetailActivity.TimeSlotRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mTimeView.setText(mValues.get(position).toString());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mTimeView;
            public TimeSlot mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mTimeView = (TextView) view.findViewById(R.id.time_view);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTimeView.getText() + "'";
            }
        }
    }
}
