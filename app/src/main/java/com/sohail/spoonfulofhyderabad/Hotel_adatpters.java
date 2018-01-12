package com.sohail.spoonfulofhyderabad;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

/**
 * Created by SOHAIL on 10/01/18.
 */

public class Hotel_adatpters extends RecyclerView.Adapter<Hotel_adatpters.ViewHolder> {

    public List<Hotels_model> hotels;
    Context context;

    public Hotel_adatpters(Context context,List<Hotels_model> hotels) {
        this.hotels = hotels;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Hotel_adatpters.ViewHolder holder, final int position) {
        holder.nameText.setText(hotels.get(position).getName());
//        holder.image.setText(hotels.get(position).getImage());
       // holder.contact.setText((int) hotels.get(position).getContact());

        Glide.with(holder.mView.getContext()).load(hotels.get(position).getImage()).into(holder.image);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,DetailActivity.class);
                i.putExtra("image_link",hotels.get(position).getImage());
                i.putExtra("name_string",hotels.get(position).getName());
                context.startActivity(i);


            }
        });

    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public TextView nameText,contact;
        public CircularImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            nameText=(TextView)mView.findViewById(R.id.name_text);
            image=(CircularImageView) mView.findViewById(R.id.image);
//            contact=(TextView)mView.findViewById(R.id.contact);

        }
    }

}
