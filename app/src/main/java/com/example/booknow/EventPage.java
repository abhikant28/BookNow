package com.example.booknow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.booknow.AppObjects.Event;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class EventPage extends AppCompatActivity {


    Button b_bookNow;
    Toolbar tb_title;
    TextView tv_about;
    TextView tv_rating;
    TextView tv_lang;
    TextView tv_duration;
    TextView tv_price;
    ImageView iv_icon;

    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        setViews();
        String eId=getIntent().getExtras().getString("ID");
        event= LandingPage.eventsMap.get(eId);
        b_bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupTimings(view, event.getSlots(EventsListing.SelectedDay));
            }
        });
        setEventView();
    }

    private void setViews() {
        tb_title=findViewById(R.id.EventsPage_toolbar_event);
        tv_rating=findViewById(R.id.EventsPage_textView_rating);
        b_bookNow=findViewById(R.id.EventPage_button_book);
        tv_about=findViewById(R.id.EventsPage_textView_about);
        tv_duration=findViewById(R.id.EventsPage_textView_duration);
        tv_lang=findViewById(R.id.EventsPage_textView_language);
        tv_price=findViewById(R.id.EventsPage_textView_price);
        iv_icon=findViewById(R.id.EventsPage_imageView_image);
    }

    private void setEventView() {
        tv_price.setText("Price: ₹"+event.getPrice());
        tv_lang.setText(event.getLanguage());
        tv_about.setText(event.getAbout());
        tv_rating.setText(event.getRating());
        Picasso.get().load(event.getImg()[event.getImg().length-1]).into(iv_icon);
        tb_title.setTitle(event.getName());
    }

    private void popupTimings(View view, List<String> timings) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        int i=0;
//        Log.i("POPUP",timings.toString());
        for(String s: timings){
            popupMenu.getMenu().add(Menu.NONE,i,i++,s);
        }
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

//                Log.i("POPUP CLICK::::::::", String.valueOf(UsefulFunctions.timingsToSlot.get(item.getTitle())));
                Intent intent =new Intent(getApplicationContext(),EventBooking.class);
                intent.putExtra("ID", event.getId());
                intent.putExtra("SLOT", UsefulFunctions.timingsToSlot.get(item.getTitle()));
                startActivity(intent);
                return false;
            }
        });
    }
}