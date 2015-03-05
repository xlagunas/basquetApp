package cat.xlagunas.drawerapp;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import cat.xlagunas.drawerapp.api.ApiTest;
import cat.xlagunas.drawerapp.api.model.Results;
import cat.xlagunas.drawerapp.api.model.WeatherModel;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragmentStart;
    private String TAG = this.getClass().getSimpleName();


    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomApplication app = (CustomApplication) getApplication();
        app.inject(this);

        setContentView(R.layout.activity_main);

        mNavigationDrawerFragmentStart = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer_start);

        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragmentStart.setUp(
                R.id.navigation_drawer_start,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        @Inject ApiTest apiTest;
        private final static String TAG = PlaceholderFragment.class.getSimpleName();
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private TextView mTextView;
        private int mTotalPages;
        private Context mContext;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mTextView = (TextView) rootView.findViewById(R.id.section_label);
            return rootView;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            CustomApplication app = (CustomApplication) getActivity().getApplication();
            app.inject(this);
            Log.d(TAG, "Call onActivityCreated");
            initResultCalls();

        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            Log.d(TAG, "onAttach");
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));

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
                            mTextView.setText(results.toString());

                            Log.d(TAG, getActivity() == null ? "is null" : "it's not");
                            Toast.makeText(getActivity(), "" +mTotalPages, Toast.LENGTH_SHORT).show();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.e(TAG, "Error", throwable);
                        }
                    });
        }


    }

}
