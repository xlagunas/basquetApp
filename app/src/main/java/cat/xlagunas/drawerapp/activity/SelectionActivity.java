package cat.xlagunas.drawerapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import cat.xlagunas.drawerapp.adapter.SelectionAdapter;
import cat.xlagunas.drawerapp.fragment.OnFragmentInteractionListener;
import cat.xlagunas.drawerapp.fragment.TestFragment;

/**
 * Created by xlagunas on 08/03/15.
 */
public class SelectionActivity extends FragmentActivity implements OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, TestFragment.newInstance(3), "Test fragment")
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
