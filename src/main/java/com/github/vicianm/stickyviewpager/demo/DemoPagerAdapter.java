package com.github.vicianm.stickyviewpager.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.vicianm.stickyviewpager.VerticalPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class DemoPagerAdapter extends VerticalPagerAdapter {

    final List<Integer> layoutResIds = new ArrayList<>();

    public DemoPagerAdapter(Context context) {
        super(context);

        layoutResIds.add(R.layout.section_01);
        layoutResIds.add(R.layout.section_02);
        layoutResIds.add(R.layout.section_03);
        layoutResIds.add(R.layout.section_04);
        layoutResIds.add(R.layout.section_05);
        layoutResIds.add(R.layout.section_06);
        layoutResIds.add(R.layout.section_07);
        layoutResIds.add(R.layout.section_08);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, final int position) {
        int layoutResId = layoutResIds.get(position);
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
        return layoutResIds.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
