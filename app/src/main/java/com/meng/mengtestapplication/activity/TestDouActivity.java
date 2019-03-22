package com.meng.mengtestapplication.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.meng.mengtestapplication.R;
import com.meng.mengtestapplication.base.BaseRecAdapter;
import com.meng.mengtestapplication.base.BaseRecViewHolder;
import com.meng.mengtestapplication.fragment.TestDouFragment2;
import com.meng.mengtestapplication.fragment.TestDouSlidingFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestDouActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyFragment adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dou);
        viewPager = findViewById(R.id.vp_dou);
        adapter = new MyFragment(getSupportFragmentManager(), getFragment());

        viewPager.setAdapter(adapter);
        //初始位置为第二个
        viewPager.setCurrentItem(1);
    }


    private List<Fragment> getFragment() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TestDouFragment2());
        fragments.add(new TestDouSlidingFragment());
        fragments.add(new TestDouFragment2());
        return fragments;
    }

    /**
     * 为fragment定义adapter
     */
    class MyFragment extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public MyFragment(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }
    }

}
