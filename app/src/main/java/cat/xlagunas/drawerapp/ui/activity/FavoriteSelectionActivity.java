package cat.xlagunas.drawerapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.api.model.BasicEntity;
import cat.xlagunas.drawerapp.api.model.ClubBasic;
import cat.xlagunas.drawerapp.api.model.TeamCategory;
import cat.xlagunas.drawerapp.database.DatabaseHelper;
import cat.xlagunas.drawerapp.ui.dialog.CategoryDialog;
import cat.xlagunas.drawerapp.ui.fragment.FavoriteSelectionFragment;
import cat.xlagunas.drawerapp.ui.fragment.OnFragmentInteractionListener;
import cat.xlagunas.drawerapp.ui.fragment.TeamSelectionFragment;

public class FavoriteSelectionActivity extends ActionBarActivity implements Persistable, OnFragmentInteractionListener{

    private DatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        Toolbar toolbar = (Toolbar)findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportFragmentManager().findFragmentByTag(FavoriteSelectionFragment.TAG) == null) {
            changeFragment(FavoriteSelectionFragment.findClubs());
        }
    }

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(FavoriteSelectionFragment.TAG)
                .commit();
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
    public void onFragmentInteraction(BasicEntity entity) {
        CategoryDialog dialog = CategoryDialog.getClubCategories(((ClubBasic) entity).getCodi_club());
        dialog.setCallback(new CategoryDialog.CategoryDialogCallback() {
            @Override
            public void OnTeamCategorySelected(TeamCategory team) {
                changeFragment(TeamSelectionFragment.newInstance(team));
            }
        });

        dialog.show(getSupportFragmentManager(), "Dialog");
    }

    public DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper =
                    OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
