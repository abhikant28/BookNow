package com.example.booknow;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LandingPage_Fragment_Bookings extends Fragment {

    TextView tv_booked;
    TextView tv_cart;
    TextView tv_updateUser;
    TextView tv_;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_landing_bookings, container, false);


        tv_booked=v.findViewById(R.id.LandingPage_ticketsFragment_bookedTickets);
        tv_cart=v.findViewById(R.id.LandingPage_ticketsFragment_pending);
        tv_updateUser=v.findViewById(R.id.LandingPage_ticketsFragment_updateDetails);

        tv_booked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),BookedTickets.class));
            }
        });
        tv_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),FinalCheckout.class));
            }
        });
        tv_updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),Login.class));
            }
        });
        return v;
    }
}
