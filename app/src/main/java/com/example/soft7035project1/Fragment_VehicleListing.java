package com.example.soft7035project1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Fragment_VehicleListing extends Fragment implements Fragment_VehicleListing_RV_Adapter.ItemClickListener {

    Fragment_VehicleListing_RV_Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_listing, container, false);
        Context context = getActivity();

        int selectedTab = getArguments().getInt("selectedTab");
        String selectedTabXmlFilePath = "tabs/" + Integer.toString(selectedTab) + ".xml";
        Log.d("myDebug", selectedTabXmlFilePath);

        XmlParser xmlParser = new XmlParser();


        ArrayList<String> modelNames=new ArrayList<>();
        this.adapter = new Fragment_VehicleListing_RV_Adapter(this, modelNames);


        try {
            List<XmlParser.Entry> entryList = xmlParser.parseFile(selectedTabXmlFilePath, context);

            for (XmlParser.Entry entry: entryList) {
                modelNames.add(entry.model);
            }

        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        carModels.add("Horse");
//        carModels.add("Sheep");
//        carModels.add("Goat");
//        carModels.add("Rabbit");


        assert getArguments() != null;

        TextView fragmentHeader = view.findViewById(R.id.fragmentHeading);
        assert context != null;
        String HeaderString = (
                getResources().getString(
                        (
                                getResources().getIdentifier(
                                        "tab"+selectedTab, "string", context.getPackageName()
                                )
                        )
                ).replaceAll(".$","") + " Listings:"

        );

        fragmentHeader.setText(
            HeaderString
        );


        RecyclerView recyclerView=view.findViewById(R.id.recyclerVehicleListing);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }

    public void onItemClick(View view, int position){
        Toast.makeText(getActivity(), "You clicked "+ adapter.getItem(position) +" on row number " + position, Toast.LENGTH_SHORT).show();
    }

    public static Fragment_VehicleListing newInstance(int selectedTab) {
        Fragment_VehicleListing fragment = new Fragment_VehicleListing();
        Bundle args = new Bundle();
        args.putInt("selectedTab", selectedTab);
        fragment.setArguments(args);
        return fragment;
    }


}