package cat.xlagunas.drawerapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import javax.inject.Inject;

import cat.xlagunas.drawerapp.api.ApiTest;

/**
 * Created by xlagunas on 05/03/15.
 */
public class ResultsViewPager extends FragmentPagerAdapter {

    @Inject
    ApiTest apiTest;

    private List<Fragment> mFragments;

    public ResultsViewPager(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> fragments){
     this.mFragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment nextFragment = mFragments.get(position);
        
        if (nextFragment == null)
            nextFragment = initFragment();

        return nextFragment;
    }

    private Fragment initFragment() {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
