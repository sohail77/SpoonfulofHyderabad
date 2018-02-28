package com.sohail.spoonfulofhyderabad;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SOHAIL on 28/02/18.
 */

public class Profile_item_Adapter extends RecyclerView.Adapter<Profile_item_Adapter.ViewHolder> {

    List<Orders_Model> profile_orders;
    Context context;

    public Profile_item_Adapter(List<Orders_Model> profile_orders, Context context) {
        this.profile_orders = profile_orders;
        this.context = context;
    }

    @Override
    public Profile_item_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item,parent,false);
        return new Profile_item_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Profile_item_Adapter.ViewHolder holder, int position) {
        holder.code.setText("Code: " + profile_orders.get(position).getCode());
        holder.titleText.setText(profile_orders.get(position).getTitle());
        holder.hotelName.setText(profile_orders.get(position).getHotel());
        if(profile_orders.get(position).isUsed()){
            holder.status.setText("Status: Used");
        }else {
            holder.status.setText("Status: Un-used");
        }
    }

    @Override
    public int getItemCount() {
        return profile_orders.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public TextView titleText,code,status,hotelName;
        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            titleText=(TextView)mView.findViewById(R.id.profileTitleTxt);
            code=(TextView)mView.findViewById(R.id.profileCodeText);
            hotelName=(TextView)mView.findViewById(R.id.profileHotelName);
            status=(TextView)mView.findViewById(R.id.statusTxt);
        }
    }
}
