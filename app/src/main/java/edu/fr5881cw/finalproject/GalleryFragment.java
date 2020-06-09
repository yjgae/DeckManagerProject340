package edu.fr5881cw.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class GalleryFragment extends Fragment {

    Button galclk;

    public static final String FRAGMENT_TAG = "GALLEERY_FRAGMENT";

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);
        galclk = v.findViewById(R.id.movDetail_button);

        //galclk.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        startActivity(new Intent(getActivity(),StockDetail.class));
        //    }
        //});


        return v;
    }
}
