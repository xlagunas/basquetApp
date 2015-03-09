package cat.xlagunas.drawerapp.activity;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import java.util.List;

import cat.xlagunas.drawerapp.CustomApplication;
import cat.xlagunas.drawerapp.fragment.NavigationDrawerFragment;
import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.api.ApiTest;
import cat.xlagunas.drawerapp.api.model.Results;

import rx.Observable;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainActivity extends FragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    private String TAG = this.getClass().getSimpleName();
    private NavigationDrawerFragment mNavigationDrawerFragmentStart;
    private int mTotalPages;
    private Context mContext;
    private ApiTest apiTest;


    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Creating Activity");


        CustomApplication app = (CustomApplication) getApplication();

        setContentView(R.layout.activity_main);

        mNavigationDrawerFragmentStart = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer_start);

        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragmentStart.setUp(
                R.id.navigation_drawer_start,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        apiTest = app.getApiService();

    }

    private void initResultCalls(){
        AppObservable.bindFragment(this, apiTest.getMaximumRounds());

        apiTest
                .getMaximumRounds()
                .flatMap(new Func1<List<Integer>, Observable<Results>>() {
                    @Override
                    public Observable<Results> call(List<Integer> integers) {
                        mTotalPages = integers != null && integers.size() > 0 ? integers.get(0) : 0;
                        return apiTest.getLastWeekResults();
                    }
                })
                .subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Results>() {
                    @Override
                    public void call(Results results) {
                        Log.i(TAG, "SUCCESS " + results.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "Error", throwable);
                    }
                });
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
//                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragmentStart.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "destroying activity");
    }
}
