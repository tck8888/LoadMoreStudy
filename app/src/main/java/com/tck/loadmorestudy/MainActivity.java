package com.tck.loadmorestudy;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.tck.loadmorestudy.bean.PageModel;
import com.tck.loadmorestudy.fragment.PrimaryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager pager;

    private List<PageModel> mPageModelList = new ArrayList<>();

    {
        mPageModelList.add(new PageModel(PrimaryFragment.newInstance(R.layout.fragment_primary), R.string.primary));
    }

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mPageModelList.get(position).mFragment;
            }

            @Override
            public int getCount() {
                return mPageModelList == null ? 0 : mPageModelList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getString(mPageModelList.get(position).titleRes);
            }
        });
        tabLayout.setupWithViewPager(pager);
    }


}
