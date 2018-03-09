package com.github.vicianm.stickyviewpager.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.vicianm.stickyviewpager.VerticalPagerAdapter;

public class DemoViewPager extends VerticalPagerAdapter {

    public DemoViewPager(Context context) {
        super(context);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, final int position) {

        int layoutResId = 0;
        switch (position) {
            case 0:
                layoutResId = R.layout.test1_fragment;
                break;
            case 1:
                layoutResId = R.layout.test2_fragment;
                break;
            case 2:
                layoutResId = R.layout.test3_fragment;
                break;
            case 3:
                layoutResId = R.layout.test4_fragment;
                break;
            default:
                throw new RuntimeException("Invalid index: " + position);
        }

        LayoutInflater inflater = LayoutInflater.from(getContext());
        ViewGroup layout = (ViewGroup) inflater.inflate(layoutResId, collection, false);
        collection.addView(layout);

        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
