package cat.xlagunas.drawerapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.api.model.Competicion;
import cat.xlagunas.drawerapp.ui.fragment.ResultsFragment;

public class CompetitionActivity extends ActionBarActivity {
    private final static String EXTRA_COMPETITITON = "competitition";
    Competicion mCompetition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_competition);
        mCompetition = getIntent().getParcelableExtra(EXTRA_COMPETITITON);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, ResultsFragment.newInstance(mCompetition, 22), "TEST FRAGMENT RESULT")
                .commit();
    }

    public static Intent makeIntent(Context context, Competicion competition){
        Intent intent = new Intent(context, CompetitionActivity.class);
        intent.putExtra(EXTRA_COMPETITITON, competition);
        return intent;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_competition, menu);
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
}
