package vn.edu.fpt.mola.app.controller.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import vn.edu.fpt.mola.app.R;

public class MainTeacher extends Fragment {

    private LinearLayout mManageCourseLine;
    private View mManageTimeFrameLine;

    public MainTeacher() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_teacher, container, false);

        mManageCourseLine = (LinearLayout) rootView.findViewById(R.id.manage_course_line);
        mManageTimeFrameLine = (View) rootView.findViewById(R.id.manage_time_frame_line);


        mManageCourseLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToManageCourse();
            }
        });
        mManageTimeFrameLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToManageTimeFrame();
            }
        });

        return rootView;
    }

    private void goToManageCourse() {
        startActivity(new Intent(getActivity(), CourseListActivity.class));
    }

    private void goToManageTimeFrame() {
        startActivity(new Intent(getActivity(), TimeFrameActivity.class));
    }
}
