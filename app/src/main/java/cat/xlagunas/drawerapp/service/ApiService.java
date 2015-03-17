package cat.xlagunas.drawerapp.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cat.xlagunas.drawerapp.CustomApplication;
import cat.xlagunas.drawerapp.api.ApiTest;
import cat.xlagunas.drawerapp.api.model.Competicion;
import cat.xlagunas.drawerapp.api.model.Results;
import cat.xlagunas.drawerapp.api.model.Team;
import cat.xlagunas.drawerapp.api.model.TeamCategory;
import cat.xlagunas.drawerapp.api.model.TeamDetails;
import cat.xlagunas.drawerapp.util.event.TeamDetailsEvent;
import de.greenrobot.event.EventBus;

public class ApiService extends IntentService {

    private final static String TAG = ApiService.class.getSimpleName();
    private static final String ACTION_TEAM = "cat.xlagunas.drawerapp.service.ACTION_TEAM";
    private static final String ACTION_LEAGUE = "cat.xlagunas.drawerapp.service.ACTION_LEAGUE";

    private static final String EXTRA_TEAM_DETAILS = "teamDetails";
    private static final String  EXTRA_TEAM_CATEGORY = "teamCategory";
    private static final String  EXTRA_COMPETITION = "competition";

    private ApiTest mApiTest;

    public static void getTeams(Context context, TeamCategory mTeamCategory) {
        Intent intent = new Intent(context, ApiService.class);
        intent.setAction(ACTION_TEAM);
        intent.putExtra(EXTRA_TEAM_CATEGORY, mTeamCategory);
        context.startService(intent);
    }

    public static void getCompetition(Context context, Competicion mCompetition, TeamDetails mTeamDetails) {
        Intent intent = new Intent(context, ApiService.class);
        intent.setAction(ACTION_LEAGUE);
        intent.putExtra(EXTRA_COMPETITION, mCompetition);
        intent.putExtra(EXTRA_TEAM_DETAILS, mTeamDetails);
        context.startService(intent);
    }


    public ApiService() {
        super("ApiService");
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
            } else if (ACTION_LEAGUE.equals(action)) {
                final Competicion competition = intent.getParcelableExtra(EXTRA_COMPETITION);
                final TeamDetails teamDetails = (TeamDetails) intent.getSerializableExtra(EXTRA_TEAM_DETAILS);
                parseCompetition(competition, teamDetails);
            }
        }
    }

    private void parseCompetition(Competicion competition, TeamDetails details) {
        List<Integer> numRounds = mApiTest.getMaximumRounds(competition.getTerritorial(),
                competition.getCodiCategoria(),competition.getCodiCompeticio(), competition.getNumGrup());

        if (numRounds != null && numRounds.size() > 0) {
            Log.d(TAG, "Total rounds on the competition: " + numRounds.get(0));
            Results results = mApiTest.getLastWeekResults(competition.getTerritorial(),
                    competition.getCodiCategoria(),competition.getCodiCompeticio(), competition.getNumGrup());

            Log.d(TAG, "Category Results: " + results.toString());

            //TODO: Save in database the current week, name of the team and lastResults

        } else {
            Log.e(TAG, "error obtaining total rounds for the competition, won't go further");
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
