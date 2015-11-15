package cat.xlagunas.basquetapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import cat.xlagunas.basquetapp.R;
import cat.xlagunas.basquetapp.api.model.Favorite;
import cat.xlagunas.basquetapp.ui.adapter.ResultViewPagerAdapter;

public class CompetitionActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener {
    private final static String EXTRA_FAVORITE = "favorite";
    private Favorite mFavorite;
    private ResultViewPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);

        mFavorite = getIntent().getParcelableExtra(EXTRA_FAVORITE);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTitle = (TextView) findViewById(R.id.activity_title);
        mPagerAdapter = new ResultViewPagerAdapter(getFragmentManager(), mFavorite);

        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(mFavorite.getCurrentRounds());
        mViewPager.setOnPageChangeListener(this);
        onPageSelected(mFavorite.getCurrentRounds());

    }

    public static Intent makeIntent(Context context, Favorite favorite){
        Intent intent = new Intent(context, CompetitionActivity.class);
        intent.putExtra(EXTRA_FAVORITE, favorite);
        return intent;
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        mTitle.setText(getResources().getString(R.string.competition_label, mFavorite.getCategoryName(), i+1));
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
