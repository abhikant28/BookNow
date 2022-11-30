package com.example.booknow;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LandingPage_Fragment_Home extends Fragment {

    String[] eventTypes= new String[]{"Everything","Movies","Plays","Comedy"};
    String[] getEventTypesPics=new String[]{"","","",""};

    RecyclerView rv_categories;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landing_home, container, false);

        setViews(view);



        return view;
    }
    private void setAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv_categories.setLayoutManager(layoutManager);
        rv_categories.setHasFixedSize(true);
        rv_categories.setItemAnimator(new DefaultItemAnimator());
        rv_categories.setAdapter(allNotes_adapter);
        allNotes_adapter.setOnItemClickListener(new Notes_RecyclerView_Adapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Notes note) {
                Intent intent = new Intent(MainActivity.this, EditNote.class);
                intent.putExtra("isNew", false);
                intent.putExtra("note_id", note.getN_id());
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_left);
            }
        });
    }
    private void setViews(View view) {
        rv_categories=view.findViewById(R.id.LandingPage_eventsFragment_events_recyclerView);

        setClicks();
    }

    private void setClicks() {
    }
}
