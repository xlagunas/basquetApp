package cat.xlagunas.basquetapp.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import cat.xlagunas.basquetapp.R;
import cat.xlagunas.basquetapp.api.model.Favorite;
import cat.xlagunas.basquetapp.ui.fragment.ClassificationFragment;
import cat.xlagunas.basquetapp.ui.fragment.NestedViewPagerFragment;


/**
 * Created by xlagunas on 24/03/15.
 */
public class LeagueActivity extends ActionBarActivity implements ListView.OnItemClickListener{
    private final static String EXTRA_FAVORITE = "favorite";
    private static final String TAG = LeagueActivity.class.getSimpleName();

    private DrawerLayout mDrawerLayout;
    private ListView mListView;
    private Favorite mFavorite;

    private Fragment mResultsFragment;
    private Fragment mClassificationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFavorite = getIntent().getParcelableExtra(EXTRA_FAVORITE);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mListView = (ListView) findViewById(R.id.left_drawer);
        String[] options = getResources().getStringArray(R.array.league_drawer_options);
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, options));
        mListView.setOnItemClickListener(this);
        if (mResultsFragment == null) {
            mResultsFragment = NestedViewPagerFragment.newInstance(this, mFavorite);
        }

        getFragmentManager()
                .beginTransaction()
                .add(R.id.content_frame, mResultsFragment)
                .commit();

    }

    public static Intent makeIntent(Context context, Favorite favorite){
        Intent intent = new Intent(context, LeagueActivity.class);
        intent.putExtra(EXTRA_FAVORITE, favorite);
        return intent;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Click "+position, Toast.LENGTH_LONG).show();
        mDrawerLayout.closeDrawer(mListView);
        FragmentManager fm = getFragmentManager();
        Fragment switchFragment = null;

        switch (position) {
            case 0:
                if (mClassificationFragment == null) {
                    mClassificationFragment = ClassificationFragment.newInstance(mFavorite);
                }
                switchFragment = mClassificationFragment;
                break;
            case 1:
                switchFragment = mResultsFragment;
                break;
        }

        if (switchFragment != null) {
            fm.beginTransaction().replace(R.id.content_frame, switchFragment).commit();
        } else {
            Log.w(TAG, "error, switchFragment is null");
        }
    }
}
