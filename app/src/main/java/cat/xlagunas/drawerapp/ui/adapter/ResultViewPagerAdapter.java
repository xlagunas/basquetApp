package cat.xlagunas.drawerapp.ui.adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import cat.xlagunas.drawerapp.api.model.Favorite;
import cat.xlagunas.drawerapp.ui.fragment.ResultsFragment;

/**
 * Created by xlagunas on 23/03/15.
 */
public class ResultViewPagerAdapter extends FragmentPagerAdapter {

    private Favorite mFavorite;
    private ResultsFragment[] mResults;

    //TODO Maybe is not neeeded
    // private int mMaxResults;


    public ResultViewPagerAdapter(FragmentManager fm, Favorite favorite) {
        super(fm);
        this.mFavorite = favorite;
        mResults = new ResultsFragment[mFavorite.getMaxRounds()];
    }

    @Override
    public Fragment getItem(int i) {
        Fragment f = mResults[i];
        return f != null ? f : createFragment(i);
    }

    private Fragment createFragment(int position) {
        //Potser la posicio es -1;
        mResults[position] = ResultsFragment.newInstance(mFavorite, position + 1);
        return mResults[position];
    }

    @Override
    public int getCount() {
        return mResults.length;
    }
}
