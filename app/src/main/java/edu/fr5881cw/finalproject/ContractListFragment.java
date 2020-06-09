package edu.fr5881cw.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class ContractListFragment extends Fragment {

    Button flclk;
    public static final String FRAGMENT_TAG = "CAMERA_FRAGMENT";

    public ContractListFragment() {
        // Required Empty Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_manage_contract, container, false);
        flclk = v.findViewById(R.id.fdclck_button);

        flclk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });

        return v;
    }
}
