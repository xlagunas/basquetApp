package cat.xlagunas.basquetapp.ui.activity;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import cat.xlagunas.basquetapp.R;
import cat.xlagunas.basquetapp.database.DatabaseHelper;
import cat.xlagunas.basquetapp.ui.fragment.TestFragment.OnFragmentInteractionListener;
import cat.xlagunas.basquetapp.ui.fragment.TestFragment;

/**
 * Created by xlagunas on 08/03/15.
 */
public class SelectionActivity extends ActionBarActivity implements OnFragmentInteractionListener, Persistable {

    private DatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, TestFragment.newInstance(), "Test fragment")
                    .commit();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus){
            Fragment f = getFragmentManager().findFragmentByTag("Test fragment");
            if (f != null && f instanceof TestFragment){
                ((TestFragment) f).animateView();
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        String scheme = uri.getScheme();
        String host = uri.getHost();
        String path = uri.getPath();

        switch (scheme) {
            case "favorite":
                startActivity(new Intent(this, FavoriteSelectionActivity.class));
                break;
        }
    }


    @Override
    public DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper =
                    OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }
}
