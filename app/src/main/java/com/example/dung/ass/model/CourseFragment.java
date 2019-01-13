package com.example.dung.ass.model;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.dung.ass.R;


public class CourseFragment extends Fragment {
    private View statics;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        statics = inflater.inflate(R.layout.coursefragment, container, false );

        return statics;
    }
}
