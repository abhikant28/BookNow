package com.example.booknow.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booknow.AppObjects.EventsType;
import com.example.booknow.EventsListing;
import com.example.booknow.R;
import com.squareup.picasso.Picasso;


public class Category_RecyclerView_Adapter extends ListAdapter<EventsType, Category_RecyclerView_Adapter.MyViewHolder> {
    private  static final DiffUtil.ItemCallback<EventsType> DIFF_CALLBACK_TYPES = new DiffUtil.ItemCallback<EventsType>() {
        @Override
        public boolean areItemsTheSame(@NonNull EventsType oldItem, @NonNull EventsType newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull EventsType oldItem, @NonNull EventsType newItem) {
            return oldItem.getId().equals(newItem.getId()) && oldItem.getQuantity()==newItem.getQuantity();
        }
    };

    public Category_RecyclerView_Adapter() {
        super(DIFF_CALLBACK_TYPES);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_fragment_eventtype_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Category_RecyclerView_Adapter.MyViewHolder holder, int position) {
        String title = getItem(position).getName();
        int count = getItem(position).getQuantity();
        int imgSrc = getItem(position).getImage();




        holder.tv_count.setText(String.valueOf(count));
        holder.tv_name.setText(title);
        Picasso.get().load(getItem(position).getImage()).centerCrop()
                .resize(400,200)
                .into(holder.iv_bgImg);
    }

      public class MyViewHolder extends RecyclerView.ViewHolder {
          private TextView tv_name;
          private TextView tv_count;
          private ImageView iv_bgImg;

          public MyViewHolder(@NonNull View itemView) {
              super(itemView);
              iv_bgImg = itemView.findViewById(R.id.EventType_homeFragment_listItem_imageView_background);
              tv_count = itemView.findViewById(R.id.EventType_homeFragment_listItem_TextView_count);
              tv_name = itemView.findViewById(R.id.EventType_homeFragment_listItem_TextView_name);

              itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      clicked(getItem(getAdapterPosition()).getId(),view.getContext());
                  }
              });

        }
    }

    public interface OnItemClickListener {
        void OnItemClick(EventsType note);
    }

    public void clicked(String type, Context context){
        Intent intent = new Intent(context, EventsListing.class);
        intent.putExtra("TYPE", type);
        context.startActivity(intent);
    }

//    public void showPopup(View v, int pos){
//        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
//        popupMenu.inflate(R.menu.long_press_menu_notes);
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case R.id.Event_Option_Delete:
//                        deleteDialogBox(getItem(pos),v.getContext());
//                        return true;
//
//                    default:
//                        return false;
//                }
//            }
//        });
//        popupMenu.show();
//    }
//    private void deleteDialogBox(Event note, Context cxt) {
//        SharedPreferences sharedPreferences = cxt.getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
//        if(!sharedPreferences.getBoolean("NEVER_REMIND_NOTES_DELETE", false)){
//            Event_Delete_Dialog deleteDialog = new Event_Delete_Dialog(note);
//            deleteDialog.show(((FragmentActivity)cxt).getSupportFragmentManager(), "Delete Note");
//            return;
//        }
//        viewModel.delete(note);
//    }


}
