package com.example.soft7035project1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragment_VehicleListing extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_listing, container, false);

        int selectedTab = getArguments().getInt("selectedTab");

        TextView fragmentHeader = view.findViewById(R.id.fragmentHeading);
        if (selectedTab==0){
            //set this to change a variable for tabname, instead of hardcoding text
            fragmentHeader.setText("Car");
        } else if (selectedTab==1) {
            fragmentHeader.setText("Bike");
        } else {
            fragmentHeader.setText("Other");
        }

        // Inflate the layout for this fragment
        return view;
    }

    public static Fragment_VehicleListing newInstance(int selectedTab) {
        Fragment_VehicleListing fragment = new Fragment_VehicleListing();
        Bundle args = new Bundle();
        args.putInt("selectedTab", selectedTab);
        fragment.setArguments(args);
        return fragment;
    }
}