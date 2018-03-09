package com.github.vicianm.stickyviewpager.demo;

import android.os.Bundle;

import com.github.vicianm.stickyviewpager.VerticalViewPager;

public class MainActivity extends DataBindingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        VerticalViewPager viewPager = (VerticalViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new DemoViewPager(this));
    }

}
