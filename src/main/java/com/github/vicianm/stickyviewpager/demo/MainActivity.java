package com.github.vicianm.stickyviewpager.demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.vicianm.stickyviewpager.VerticalViewPager;

public class MainActivity extends DataBindingActivity {

    private DemoPagerAdapter demoPagerAdapter;

    private DemoHeaderAdapter demoHeaderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        demoHeaderAdapter = new DemoHeaderAdapter();
        demoPagerAdapter = new DemoPagerAdapter(this);

        RecyclerView headerRecyclerView = findViewById(R.id.recycler_view_header);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        headerRecyclerView.setLayoutManager(layoutManager);
        headerRecyclerView.setAdapter(demoHeaderAdapter);

        VerticalViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(demoPagerAdapter);
        viewPager.addOnPageChangeListener(new PageChangeListener(
                demoPagerAdapter,
                demoHeaderAdapter,
                headerRecyclerView
        ));


    }

    static class PageChangeListener implements ViewPager.OnPageChangeListener {

        private DemoPagerAdapter demoPagerAdapter;

        private DemoHeaderAdapter demoHeaderAdapter;

        private RecyclerView recyclerView;

        private int currentPageIndex;

        public PageChangeListener(DemoPagerAdapter demoPagerAdapter, DemoHeaderAdapter demoHeaderAdapter, RecyclerView recyclerView) {
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
            recyclerView.smoothScrollToPosition(demoHeaderAdapter.getItemCount());
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

    }

}
