package com.example.camerapoc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CaptureImageFragment extends Fragment {

    public CaptureImageFragment() {
        // Currently empty.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_capture_image, container, false);
        return rootView;
    }
}