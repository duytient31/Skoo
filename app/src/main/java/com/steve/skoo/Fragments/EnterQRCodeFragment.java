package com.steve.skoo.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.steve.skoo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnterQRCodeFragment extends Fragment {


    public EnterQRCodeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toolbar toolbar = getActivity().findViewById(R.id.enterQR_fragment_toolbar);
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


        return inflater.inflate(R.layout.fragment_enter_qrcode, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        this.getFragmentManager().beginTransaction().remove(this).commit();
        return super.onOptionsItemSelected(item);
    }


}
