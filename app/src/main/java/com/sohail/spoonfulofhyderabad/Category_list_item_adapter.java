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
 * Created by SOHAIL on 29/01/18.
 */

public class Category_list_item_adapter extends RecyclerView.Adapter<Category_list_item_adapter.ViewHolder> {



    public List<Hotels_model> hotels;
    Context context;


    public Category_list_item_adapter(Context context, List<Hotels_model> hotels) {
        this.hotels = hotels;
        this.context=context;
    }


    @Override
    public Category_list_item_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_card,parent,false);
        return new Category_list_item_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.nameText.setText(hotels.get(position).getName());
        holder.address.setText(hotels.get(position).getAddress());
        holder.type.setText(hotels.get(position).getType());

//        holder.image.setText(hotels.get(position).getImage());
        // holder.contact.setText((int) hotels.get(position).getContact());

        Glide.with(holder.mView.getContext()).load(hotels.get(position).getImage()).into(holder.image);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,DetailActivity.class);
                i.putExtra("image_link",hotels.get(position).getImage());
                i.putExtra("name_string",hotels.get(position).getName());
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
        public TextView nameText,address,type;
        public CircularImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            nameText=(TextView)mView.findViewById(R.id.name_in_cat);
            address=(TextView)mView.findViewById(R.id.addre_in_cat);
            type=(TextView)mView.findViewById(R.id.type_in_cat);
            image=(CircularImageView) mView.findViewById(R.id.image_in_cat);
//            contact=(TextView)mView.findViewById(R.id.contact);

        }
    }

}
