package vn.edu.fpt.mola.app.controller.learner;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.fpt.mola.app.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainLearner extends Fragment {


    public MainLearner() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_learner, container, false);
    }

}
