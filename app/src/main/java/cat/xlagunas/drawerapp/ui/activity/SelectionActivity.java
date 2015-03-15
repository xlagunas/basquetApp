package cat.xlagunas.drawerapp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.ui.fragment.TestFragment.OnFragmentInteractionListener;
import cat.xlagunas.drawerapp.ui.fragment.TestFragment;

/**
 * Created by xlagunas on 08/03/15.
 */
public class SelectionActivity extends ActionBarActivity implements OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, TestFragment.newInstance(3), "Test fragment")
                    .commit();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus){
            Fragment f = getSupportFragmentManager().findFragmentByTag("Test fragment");
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


}
