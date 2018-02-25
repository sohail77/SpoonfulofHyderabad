package com.sohail.spoonfulofhyderabad;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

/**
 * Created by SOHAIL on 25/02/18.
 */

public class offer_item_adapter extends RecyclerView.Adapter<offer_item_adapter.ViewHolder> {

    public List<offers_model> offers;
    Context context;

    public offer_item_adapter(List<offers_model> offers, Context context) {
        this.offers = offers;
        this.context = context;
    }

    @Override
    public offer_item_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item,parent,false);
        return new offer_item_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final offer_item_adapter.ViewHolder holder, final int position) {
        holder.titleText.setText(offers.get(position).getTitle());
        holder.desc.setText(offers.get(position).getDesc());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,CouponActivity.class);
                i.putExtra("coupon_title",offers.get(position).getTitle());
                i.putExtra("hotel_name",offers.get(position).getHotel());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public TextView titleText,desc;
        String hotel_name;
        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            titleText=(TextView)mView.findViewById(R.id.offer_title);
            desc=(TextView)mView.findViewById(R.id.offer_desc);
        }
    }
}
