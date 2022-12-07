package com.example.booknow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.booknow.Adapters.Checkout_Tickets_RecyclerView_Adapter;
import com.example.booknow.AppObjects.Ticket;
import com.example.booknow.Database.SharedPrefs;

import java.util.ArrayList;

public class FinalCheckout extends AppCompatActivity {

    static ArrayList<Ticket> pendingTickets= new ArrayList<>();


    RecyclerView rv_tickets;
    static Checkout_Tickets_RecyclerView_Adapter checkout_tickets_recyclerView_adapter= new Checkout_Tickets_RecyclerView_Adapter();

    TextView tv_userName;
    TextView tv_userNum;
    TextView tv_totalAmount;
    TextView tv_convFee;
    RadioGroup rg_modes;
    Button b_pay;
    ConstraintLayout csv_layout;
    TextView tv_empty;

    String userName;
    String userNumber;
    String amount;
    String convFee="40.00";
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,LandingPage.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_checkout);

        String[] u= SharedPrefs.loadUserData(getApplicationContext()).split("&&");
        userName=u[0];
        userNumber=u[1];
        pendingTickets= SharedPrefs.loadPendingTickets(getApplicationContext());
        if(pendingTickets.size()!=0){
            checkout_tickets_recyclerView_adapter.submitList(pendingTickets);
            amount=String.valueOf(findTotal(pendingTickets)+40);
            setViews();
        }else{
            csv_layout=findViewById(R.id.FinalCheckout_constlay_);
            csv_layout.setVisibility(View.GONE);
            tv_empty=findViewById(R.id.FinalCheckout_tv_empty);
            tv_empty.setVisibility(View.VISIBLE);
        }

    }

    private int findTotal(ArrayList<Ticket> pendingTickets) {
        int sum=0;
        for(Ticket ticket : pendingTickets){
            sum+=Integer.parseInt(ticket.getTotalAmount());
        }
        return sum;
    }

    public static void removeTicket(int i, Context context){
        pendingTickets.remove(i);
        SharedPrefs.updatePending(context, pendingTickets);
        checkout_tickets_recyclerView_adapter.submitList(null);
        Log.i("REMOVE:::::::", "CLICKED");
        context.startActivity(new Intent(context,FinalCheckout.class));
//        this.recreate();

    }

    private void fun() {
    }

    private void setViews() {
        tv_userName=findViewById(R.id.FinalCheckout_tv_userName);
        tv_userNum=findViewById(R.id.FinalCheckout_tv_contactNum);
        tv_totalAmount=findViewById(R.id.FinalCheckout_tv_total);
        tv_convFee=findViewById(R.id.FinalCheckout_tv_convFee);
        rv_tickets=findViewById(R.id.FinalCheckout_rv_ticketList);
        b_pay=findViewById(R.id.FinalCheckout_btn_makePayment);
        rg_modes=findViewById(R.id.FinalCheckout_rg_paymentModes);

        rv_tickets.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_tickets.setHasFixedSize(true);
        rv_tickets.setItemAnimator(new DefaultItemAnimator());
        rv_tickets.setAdapter(checkout_tickets_recyclerView_adapter);

        b_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefs.buyAllTickets(getApplicationContext());
                startActivity(new Intent(getApplicationContext(),BookingConfirmation.class));
                finish();
            }
        });

        tv_totalAmount.setText("₹"+amount);
        tv_convFee.setText("₹"+convFee);
        tv_userNum.setText(userNumber);
        tv_userName.setText(userName);
//        rg_modes.
    }
}