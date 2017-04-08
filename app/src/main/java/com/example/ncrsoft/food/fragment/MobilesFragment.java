package com.example.ncrsoft.food.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ncrsoft.food.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MobilesFragment extends Fragment {


    public MobilesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("Mobiles");
        return textView;
    }

}
