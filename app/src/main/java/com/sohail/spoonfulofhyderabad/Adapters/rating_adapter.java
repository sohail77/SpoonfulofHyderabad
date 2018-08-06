package com.sohail.spoonfulofhyderabad.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.sohail.spoonfulofhyderabad.Models.rating_model;
import com.sohail.spoonfulofhyderabad.R;

import java.util.List;

/**
 * Created by SOHAIL on 07/02/18.
 */

public class rating_adapter extends RecyclerView.Adapter<rating_adapter.ViewHolder> {


    public List<rating_model> reviews;
    Context context;


    public rating_adapter(List<rating_model> reviews, Context context) {
        this.reviews = reviews;
        this.context = context;
    }

    @Override
    public rating_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_item,parent,false);
        return new rating_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(rating_adapter.ViewHolder holder, int position) {
        holder.rating_item_name.setText(reviews.get(position).getUser());
        holder.rating_item_comment.setText(reviews.get(position).getComment());
        holder.rating_item_score.setText(String.valueOf(reviews.get(position).getRating()));

        RequestOptions options=new RequestOptions()
                .error(R.drawable.icon_default_profile);
        Glide.with(holder.mView.getContext()).load(reviews.get(position).getImage_url()).apply(options).into(holder.profileImg);

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        CircularImageView profileImg;
        TextView rating_item_name,rating_item_comment,rating_item_score;
        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            profileImg=(CircularImageView) mView.findViewById(R.id.rating_item_image);
            rating_item_name=(TextView)mView.findViewById(R.id.rating_item_name);
            rating_item_comment=(TextView)mView.findViewById(R.id.rating_item_comment);
            rating_item_score=(TextView)mView.findViewById(R.id.rating_item_score);
        }
    }
}
