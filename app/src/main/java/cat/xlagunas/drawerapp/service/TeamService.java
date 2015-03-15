package cat.xlagunas.drawerapp.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cat.xlagunas.drawerapp.CustomApplication;
import cat.xlagunas.drawerapp.api.ApiTest;
import cat.xlagunas.drawerapp.api.model.Team;
import cat.xlagunas.drawerapp.api.model.TeamCategory;
import cat.xlagunas.drawerapp.api.model.TeamDetails;
import cat.xlagunas.drawerapp.util.event.TeamDetailsEvent;
import de.greenrobot.event.EventBus;

public class TeamService extends IntentService {

    private final static String TAG = TeamService.class.getSimpleName();
    private static final String ACTION_TEAM = "cat.xlagunas.drawerapp.service.ACTION_TEAM";

    private static String  EXTRA_TEAM_CATEGORY = "teamCategory";
    private ApiTest mApiTest;

    public static void getTeams(Context context, TeamCategory mTeamCategory) {
        Intent intent = new Intent(context, TeamService.class);
        intent.setAction(ACTION_TEAM);
        intent.putExtra(EXTRA_TEAM_CATEGORY, mTeamCategory);
        context.startService(intent);
    }


    public TeamService() {
        super("TeamService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApiTest = ((CustomApplication)getApplication()).getApiService();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_TEAM.equals(action)) {
                final TeamCategory teamCategory = intent.getParcelableExtra(EXTRA_TEAM_CATEGORY);
                parseTeamsFromCategory(teamCategory);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void parseTeamsFromCategory(TeamCategory teamCategory) {
        long requestTime;
        Log.i(TAG, "Requesting data for teams");
        List<TeamDetails> teamDetails = new ArrayList<>(teamCategory.getTeams().size());
        requestTime = System.currentTimeMillis();
        for (Team team : teamCategory.getTeams()){
            teamDetails.add(mApiTest.getTeamDetails(team.getCodiClub(), team.getCodiEquip()));
            Log.d(TAG, "Request took: " + (System.currentTimeMillis() - requestTime));
        }

        Log.i(TAG, "Finished requesting data in "+ (System.currentTimeMillis() - requestTime) +" ms");

        EventBus.getDefault().post(new TeamDetailsEvent(teamDetails));
    }


}
