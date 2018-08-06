package com.sohail.spoonfulofhyderabad.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sohail.spoonfulofhyderabad.Activities.Image_Viewer_Activity;
import com.sohail.spoonfulofhyderabad.Models.Images_model;
import com.sohail.spoonfulofhyderabad.R;

import java.util.List;

/**
 * Created by SOHAIL on 02/03/18.
 */

public class Image_Adapter extends RecyclerView.Adapter<Image_Adapter.ViewHolder> {

    List<Images_model> menu_images;
    Context context;

    public Image_Adapter(List<Images_model> menu_images, Context context) {
        this.menu_images = menu_images;
        this.context = context;
    }

    @Override
    public Image_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);
        return new Image_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Image_Adapter.ViewHolder holder, final int position) {
        final String imageUrl=menu_images.get(position).getImage();
        Glide.with(holder.mView.getContext()).load(imageUrl).into(holder.menu_image);
        holder.menu_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(context,Image_Viewer_Activity.class);
                i.putExtra("imageUrl",menu_images.get(position).getImage());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menu_images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public ImageView menu_image;
        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            menu_image=(ImageView) mView.findViewById(R.id.menu_image);

        }
    }
}
