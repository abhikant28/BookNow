package com.example.booknow;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booknow.objects.Event;


public class Event_RecyclerView_Adapter extends ListAdapter<Event,Event_RecyclerView_Adapter.MyViewHolder> {
    private OnItemClickListener listener;
    private  static final DiffUtil.ItemCallback<Event> DIFF_CALLBACK_NOTES = new DiffUtil.ItemCallback<Event>() {
        @Override
        public boolean areItemsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem.getAbout().equals(newItem.getAbout()) && oldItem.getStatus().equals(newItem.getStatus());
        }
    };

    public Event_RecyclerView_Adapter() {
        super(DIFF_CALLBACK_NOTES);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_super_list_item_note, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Event_RecyclerView_Adapter.MyViewHolder holder, int position) {
        String title = getItem(position).getTitle();
        String about = getItem(position).getBody();
        String date = getItem(position).getDate();

        if (title.length()== 0) {
            holder.tv_title.setText(about);
            holder.tv_full.setText("");
        }else{
            holder.tv_title.setText(title);
            holder.tv_full.setText(about);
        }

        holder.tv_date.setText(date);
    }

      public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_full;
        private TextView tv_date;
        private LinearLayout ll_main;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_main = itemView.findViewById(R.id.Main_RecyclerView_List_Item_LinearLayout1);
            tv_date = itemView.findViewById(R.id.Main_RecyclerView_List_Item_Time);
            tv_title = itemView.findViewById(R.id.Main_TextView_List_Item_Title);
            tv_full = itemView.findViewById(R.id.Main_RecyclerView_List_Item_Sub);

            ll_main.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Log.i("LONG:::","CLICKED");
                    showPopup(view, getAdapterPosition());
                    return true;
                }
            });
            ll_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.OnItemClick(getItem(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(Event note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
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
