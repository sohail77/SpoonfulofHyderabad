package com.sohail.spoonfulofhyderabad;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by SOHAIL on 29/01/18.
 */

public class RecylerItemDecorator extends RecyclerView.ItemDecoration {


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = (int) (view.getResources().getDisplayMetrics().density * -25);
        outRect.right = (int) (view.getResources().getDisplayMetrics().density * -25);
        outRect.top = 0;
        outRect.bottom = 0;
        //super.getItemOffsets(outRect, view, parent, state);
    }
}