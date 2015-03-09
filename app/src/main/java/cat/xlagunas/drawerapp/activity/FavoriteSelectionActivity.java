package cat.xlagunas.drawerapp.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.fragment.FavoriteSelectionFragment;
import cat.xlagunas.drawerapp.fragment.OnFragmentInteractionListener;

public class FavoriteSelectionActivity extends FragmentActivity implements OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportFragmentManager().findFragmentByTag(FavoriteSelectionFragment.TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, FavoriteSelectionFragment.newInstance(FavoriteSelectionFragment.TYPE_CLUB), FavoriteSelectionFragment.TAG)
                    .addToBackStack(FavoriteSelectionFragment.TAG)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favorite_selection, menu);
        return true;
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
    public void onFragmentInteraction(Uri uri) {

    }
}
