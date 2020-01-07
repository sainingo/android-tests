package com.example.test.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;


public class FragmentContent extends Fragment {

    private static final String KEY_TITLE = "content";

}


    public FragmentContent() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentContent newInstance(String param1, String param2) {
        FragmentContent fragment = new FragmentContent();
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, param1);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_content, container, false);
    }

    @Override
    public View onViewCreated(View view, @Nullable Bundle savedInstanceState) {

            super.onViewCreated(view, savedInstanceState);

            String title = getArguments().getString(KEY_TITLE);
            ((TextView)view.findViewById(R.id.title)).setText(title);

    }




}

