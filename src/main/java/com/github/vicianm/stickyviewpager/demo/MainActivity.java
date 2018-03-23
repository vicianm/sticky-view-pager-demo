package com.github.vicianm.stickyviewpager.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.github.vicianm.stickyviewpager.VerticalViewPager;

public class MainActivity extends DataBindingActivity {

    private DemoPagerAdapter demoPagerAdapter;

    private DemoHeaderAdapter demoHeaderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        demoHeaderAdapter = new DemoHeaderAdapter(this);

        demoPagerAdapter = new DemoPagerAdapter(this);

        RecyclerView headerRecyclerView = findViewById(R.id.recycler_view_header);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        headerRecyclerView.setLayoutManager(layoutManager);
        headerRecyclerView.setAdapter(demoHeaderAdapter);

        VerticalViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(demoPagerAdapter);
        viewPager.addOnPageChangeListener(new PageChangeListener(
                this,
                demoPagerAdapter,
                demoHeaderAdapter,
                headerRecyclerView
        ));
    }

    static class PageChangeListener implements ViewPager.OnPageChangeListener {

        private Context context;

        private DemoPagerAdapter demoPagerAdapter;

        private DemoHeaderAdapter demoHeaderAdapter;

        private RecyclerView recyclerView;

        private int currentPageIndex;

        public PageChangeListener(Context context, DemoPagerAdapter demoPagerAdapter, DemoHeaderAdapter demoHeaderAdapter, RecyclerView recyclerView) {
            this.context = context;
            this.demoPagerAdapter = demoPagerAdapter;
            this.demoHeaderAdapter = demoHeaderAdapter;
            this.recyclerView = recyclerView;

            Object currentPageObject = this.demoPagerAdapter.getPrimaryItemObject();
            currentPageIndex = this.demoPagerAdapter.getItemPosition(currentPageObject);
        }

        @Override
        public void onPageSelected(int position) {
            int posOld = currentPageIndex;
            int posNew = position;

            if (posNew == posOld) {
                // no header needs to be updated
            } else if (posNew > posOld) {
                // new headers need to be added
                for (int i = posOld+1; i<=posNew; i++) {
                    demoHeaderAdapter.addDatasetItem("Header item " + i);
                }
            } else {
                // old headers need to be removed
                for (int i = posOld; i>posNew; i--) {
                    demoHeaderAdapter.removeDatasetItem(i);
                }
            }

            currentPageIndex = posNew;

            RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(context) {
                @Override protected int getVerticalSnapPreference() {
                    return LinearSmoothScroller.SNAP_TO_END;
                }

                @Override
                protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                    // TODO what's the default scroll speed
                    return 0.5f;
                }
            };
            smoothScroller.setTargetPosition(position);
            recyclerView.getLayoutManager().startSmoothScroll(smoothScroller);

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

    }

}
