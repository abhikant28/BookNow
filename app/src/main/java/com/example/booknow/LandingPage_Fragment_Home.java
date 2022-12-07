package com.example.booknow;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booknow.Adapters.Category_RecyclerView_Adapter;
import com.example.booknow.Adapters.Event_RecyclerView_Adapter;
import com.example.booknow.AppObjects.Event;
import com.example.booknow.AppObjects.EventsType;

import java.util.ArrayList;
import java.util.List;

public class LandingPage_Fragment_Home extends Fragment {

    String[] eventTypes= new String[]{"Explore","Movies","Plays","Comedy"};
    int[] eventTypesPics=new int[]{R.drawable.img,R.drawable.movies_img,R.drawable.play_img,R.drawable.comedy_img};
    int[] eventTypesQuantity= new int[]{0,1,2,3};

    RecyclerView rv_categories;
    Category_RecyclerView_Adapter category_recyclerView_adapter=new Category_RecyclerView_Adapter();
    private List<EventsType> categoryList=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landing_home, container, false);

        setViews(view);
        setAdapter();
        generateTypeList();

        category_recyclerView_adapter.submitList(categoryList);


        return view;
    }

    private void generateTypeList() {
        for(int i=0;i<eventTypes.length;i++){
            categoryList.add(new EventsType(eventTypes[i],eventTypesQuantity[i],eventTypesPics[i],String.valueOf(i)));
        }
    }

    private void setAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_categories.setLayoutManager(layoutManager);
        rv_categories.setHasFixedSize(true);
        rv_categories.setItemAnimator(new DefaultItemAnimator());
        rv_categories.setAdapter(category_recyclerView_adapter);
    }
    private void setViews(View view) {
        rv_categories=view.findViewById(R.id.LandingPage_eventsFragment_events_recyclerView);

        setClicks();
    }

    private void setClicks() {
    }
}
