package cat.xlagunas.drawerapp.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import cat.xlagunas.drawerapp.R;


/**
 * Created by xlagunas on 24/03/15.
 */
public class LeagueActivity extends ActionBarActivity implements ListView.OnItemClickListener{

    private DrawerLayout mDrawerLayout;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mListView = (ListView) findViewById(R.id.left_drawer);
        String[] options = getResources().getStringArray(R.array.league_drawer_options);
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, options));
        mListView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Click "+position, Toast.LENGTH_LONG).show();
        mDrawerLayout.closeDrawer(mListView);
//        FragmentManager fm = getSupportFragmentManager();
//
//        switch (position) {
//            //Classificacio
//            fm.beginTransaction().replace(R.id.content_frame, )
//            case 0:
//                break;
//            //Resultats
//            case 1:
//        }
    }
}
