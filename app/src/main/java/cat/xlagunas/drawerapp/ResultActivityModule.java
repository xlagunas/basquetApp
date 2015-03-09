package cat.xlagunas.drawerapp;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import javax.inject.Singleton;

import cat.xlagunas.drawerapp.activity.MainActivity;
import cat.xlagunas.drawerapp.adapter.ResultsViewPager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by xlagunas on 03/03/15.
 */

@Module(
        injects = { MainActivity.class, MainActivity.PlaceholderFragment.class}, library = true, complete = false, addsTo = ApplicationModule.class
)
public class ResultActivityModule {

    private FragmentActivity mActivity;

    public ResultActivityModule(FragmentActivity activity){
        this.mActivity = activity;
    }

    @Provides @Singleton
    public Context provideActivityContext(){
        return mActivity;
    }

    @Provides @Singleton
    FragmentManager provideFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }

    @Provides @Singleton
    FragmentPagerAdapter providePagerAdapter() {
        return new ResultsViewPager(provideFragmentManager());
    }

}
