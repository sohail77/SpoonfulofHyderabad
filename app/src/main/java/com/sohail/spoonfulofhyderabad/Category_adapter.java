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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by SOHAIL on 28/01/18.
 */

public class Category_adapter extends RecyclerView.Adapter<Category_adapter.ViewHolder> {

    private List<Category_item> list;
    Context context;

    public Category_adapter(List<Category_item> list,Context context) {
        this.list = list;
        this.context=context;
    }

    @Override
    public Category_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Category_adapter.ViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getCategory_txt());
        Glide.with(holder.mView.getContext()).load(list.get(position).getCategory_img()).into(holder.imageView);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent i= new Intent(context,Category_Viewer_Activity.class);
                i.putExtra("Category_name",list.get(position).getCategory_txt());
                i.putExtra("Category_img",list.get(position).getCategory_img());
                holder.imageView.setTransitionName("image_transi");
                Pair<View,String> pair=Pair.create((View)holder.imageView,holder.imageView.getTransitionName());
                ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,pair);
                context.startActivity(i,optionsCompat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        ImageView imageView;
        TextView textView;
        public ViewHolder(View itemView) {

            super(itemView);
            mView=itemView;
            imageView=(ImageView)mView.findViewById(R.id.category_img);
            textView=(TextView)mView.findViewById(R.id.category_text);
        }
    }
}
