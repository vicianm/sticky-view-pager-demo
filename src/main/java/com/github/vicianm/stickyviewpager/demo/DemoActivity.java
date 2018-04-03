package com.github.vicianm.stickyviewpager.demo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.vicianm.stickyviewpager.HeaderPagerSynchronizer;
import com.github.vicianm.stickyviewpager.VerticalViewPager;

public class DemoActivity extends DataBindingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        DemoHeaderAdapter demoHeaderAdapter = new DemoHeaderAdapter(this);

        DemoPagerAdapter demoPagerAdapter = new DemoPagerAdapter(this);

        RecyclerView headerRecyclerView = initUiHeader(demoHeaderAdapter);
        initUiPager(
                headerRecyclerView,
                demoHeaderAdapter,
                demoPagerAdapter);
    }

    protected VerticalViewPager initUiPager(RecyclerView headerRecyclerView,
                                            DemoHeaderAdapter demoHeaderAdapter,
                                            DemoPagerAdapter demoPagerAdapter) {

        HeaderPagerSynchronizer headerPagerSynchronizer = new HeaderPagerSynchronizer(
                this,
                demoPagerAdapter,
                demoHeaderAdapter,
                headerRecyclerView
        );

        VerticalViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(demoPagerAdapter);
        viewPager.addOnPageChangeListener(headerPagerSynchronizer);

        return viewPager;
    }

    protected RecyclerView initUiHeader(DemoHeaderAdapter demoHeaderAdapter) {
        RecyclerView headerRecyclerView = findViewById(R.id.recycler_view_header);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        headerRecyclerView.setLayoutManager(layoutManager);
        headerRecyclerView.setAdapter(demoHeaderAdapter);
        return headerRecyclerView;
    }


}
