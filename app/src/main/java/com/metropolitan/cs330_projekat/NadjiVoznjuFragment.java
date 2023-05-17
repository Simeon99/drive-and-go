package com.metropolitan.cs330_projekat;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class NadjiVoznjuFragment extends Fragment {

    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_nadji_voznju, container,false);

        btn = v.findViewById(R.id.btn_nadnjiVoznju);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getActivity(),PretragaVoznje.class);
                startActivity(i);
            }
        });

        return v;
    }
}