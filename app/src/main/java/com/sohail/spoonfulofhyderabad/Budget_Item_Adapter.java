package com.sohail.spoonfulofhyderabad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

/**
 * Created by SOHAIL on 03/02/18.
 */

public class Budget_Item_Adapter extends RecyclerView.Adapter<Budget_Item_Adapter.ViewHolder> {


    public List<Hotels_model> hotels;
    Context context;

    public Budget_Item_Adapter(List<Hotels_model> hotels, Context context) {
        this.hotels = hotels;
        this.context = context;
    }

    @Override
    public Budget_Item_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_item_layout,parent,false);
        return new Budget_Item_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Budget_Item_Adapter.ViewHolder holder, final int position) {
        holder.nameText.setText(hotels.get(position).getName());
        holder.budget.setText("Budget:" +String.valueOf(hotels.get(position).getBudget()) +" approx" );

        Glide.with(holder.mView.getContext()).load(hotels.get(position).getImage()).into(holder.image);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,DetailActivity.class);
                i.putExtra("image_link",hotels.get(position).getImage());
                i.putExtra("name_string",hotels.get(position).getName());
//                context.startActivity(i);
                holder.image.setTransitionName("image_transition");
                Pair<View,String> pair=Pair.create((View)holder.image,holder.image.getTransitionName());
                ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,pair);
                context.startActivity(i,optionsCompat.toBundle());

            }
        });
    }


    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public TextView nameText,budget;
        public CircularImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            nameText=(TextView)mView.findViewById(R.id.budget_item_name);
            budget=(TextView)mView.findViewById(R.id.budget_item_price);
            image=(CircularImageView) mView.findViewById(R.id.budget_item_image);
        }
    }
}
