package cat.xlagunas.basquetapp.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.xlagunas.basquetapp.R;
import cat.xlagunas.basquetapp.api.model.Favorite;
import cat.xlagunas.basquetapp.ui.adapter.ResultViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NestedViewPagerFragment extends Fragment implements ViewPager.OnPageChangeListener{

    private final static String EXTRA_ARG_FAVORITE = "favorite";
    private ViewPager mViewPager;
    private Favorite mFavorite;
    private ResultViewPagerAdapter mPagerAdapter;

    public NestedViewPagerFragment() {

    }

    public static NestedViewPagerFragment newInstance(Context context, Favorite favorite) {
        NestedViewPagerFragment fragment = new NestedViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_ARG_FAVORITE, favorite);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFavorite = getArguments().getParcelable(EXTRA_ARG_FAVORITE);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_nested_view_pager, container, false);
        mViewPager = (ViewPager) v.findViewById(R.id.nested_view_pager);
        mViewPager.setCurrentItem(mFavorite.getCurrentRounds());
        mViewPager.setOnPageChangeListener(this);

        mPagerAdapter = new ResultViewPagerAdapter(getChildFragmentManager(), mFavorite);
        onPageSelected(mFavorite.getCurrentRounds());

        mViewPager.setAdapter(mPagerAdapter);

        return v;
    }


    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
