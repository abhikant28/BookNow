package com.example.booknow.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booknow.AppObjects.Event;
import com.example.booknow.EventPage;
import com.example.booknow.R;
import com.squareup.picasso.Picasso;


public class Event_RecyclerView_Adapter extends ListAdapter<Event,Event_RecyclerView_Adapter.MyViewHolder> {
    private  static final DiffUtil.ItemCallback<Event> DIFF_CALLBACK_NOTES = new DiffUtil.ItemCallback<Event>() {
        @Override
        public boolean areItemsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem.getAbout().equals(newItem.getAbout());
        }
    };

    public Event_RecyclerView_Adapter() {
        super(DIFF_CALLBACK_NOTES);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_list_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Event_RecyclerView_Adapter.MyViewHolder holder, int position) {
        String title = getItem(position).getName();
        String rating = getItem(position).getRating();
        String imgLink= getItem(position).getImg()[0];
        String lang= getItem(position).getLanguage();

        Log.i("POSITION", title);
        holder.tv_title.setText(title);
        holder.tv_Rating.setText(rating);
        holder.tv_lang.setText(lang);
        Picasso.get().load(imgLink).centerCrop()
                .resize(350,400)
                .into(holder.iv_icon);



    }

      public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_Rating;
        private TextView tv_lang;
        private ImageView iv_icon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_lang = itemView.findViewById(R.id.EventsList_listItem_textView_language);
            tv_title = itemView.findViewById(R.id.EventsList_listItem_textView_title);
            tv_Rating = itemView.findViewById(R.id.EventsList_listItem_textView_rating);
            iv_icon=itemView.findViewById(R.id.EventsList_listItem_imageView_icon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    clicked(getItem(getAdapterPosition()).getId(),view.getContext());
                }
            });

        }
    }

    private void clicked(String id, Context context) {

        Intent intent=new Intent(context, EventPage.class);
        intent.putExtra("ID", id);
        context.startActivity(intent);
    }


}
