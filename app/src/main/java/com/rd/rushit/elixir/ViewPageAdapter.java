package com.rd.rushit.elixir;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by admin on 22-09-2017.
 */

public class ViewPageAdapter extends PagerAdapter {

    Activity activity;
    int[] images;
    Context ctx;
    LayoutInflater inflater;

    public ViewPageAdapter(Activity activity, int[] images) {
        this.activity = activity;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.viewpager_item,container,false);

        ImageView image = (ImageView) itemView.findViewById(R.id.view1_img);
        DisplayMetrics dis = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dis);
        int height = dis.heightPixels;
        int width = dis.widthPixels;
        image.setMinimumHeight(height);
        image.setMinimumWidth(width);

        try {
            Picasso.with(activity.getBaseContext()).load(images[position]).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(image);
        }
        catch (Exception ex) {

        }
        container.addView(itemView);
        return itemView;
    }
}
