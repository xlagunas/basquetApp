package cat.xlagunas.drawerapp.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cat.xlagunas.drawerapp.CustomApplication;
import cat.xlagunas.drawerapp.api.RestAPI;
import cat.xlagunas.drawerapp.api.model.Competicion;
import cat.xlagunas.drawerapp.api.model.Results;
import cat.xlagunas.drawerapp.api.model.Team;
import cat.xlagunas.drawerapp.api.model.TeamCategory;
import cat.xlagunas.drawerapp.api.model.TeamDetails;
import cat.xlagunas.drawerapp.database.Club;
import cat.xlagunas.drawerapp.database.DatabaseHelper;
import cat.xlagunas.drawerapp.database.EntityConverter;
import cat.xlagunas.drawerapp.database.Favorite;
import cat.xlagunas.drawerapp.util.event.TeamDetailsEvent;
import de.greenrobot.event.EventBus;

public class ApiService extends IntentService {

    private final static String TAG = ApiService.class.getSimpleName();
    private static final String ACTION_TEAM = "cat.xlagunas.drawerapp.service.ACTION_TEAM";
    private static final String ACTION_LEAGUE = "cat.xlagunas.drawerapp.service.ACTION_LEAGUE";

    private static final String EXTRA_TEAM_DETAILS = "teamDetails";
    private static final String  EXTRA_TEAM_CATEGORY = "teamCategory";
    private static final String  EXTRA_COMPETITION = "competition";

    private RestAPI mRestAPI;
    private DatabaseHelper databaseHelper = null;

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper =
                    OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

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
        mRestAPI = ((CustomApplication)getApplication()).getApiService();
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
                final TeamDetails teamDetails = intent.getParcelableExtra(EXTRA_TEAM_DETAILS);
                parseCompetition(competition, teamDetails);
            }
        }
    }

    private void parseCompetition(Competicion competition, TeamDetails details) {
        List<Integer> numRounds = mRestAPI.getMaximumRounds(competition.getTerritorial(),
                competition.getCodiCategoria(),competition.getCodiCompeticio(), competition.getNumGrup());

        if (numRounds != null && numRounds.size() > 0) {
            Log.d(TAG, "Total rounds on the competition: " + numRounds.get(0));
            Results results = mRestAPI.getLastWeekResults(competition.getTerritorial(),
                    competition.getCodiCategoria(),competition.getCodiCompeticio(), competition.getNumGrup());

            Log.d(TAG, "Category Results: " + results.toString());

            Favorite favorite = EntityConverter.convertFavoriteFromModels(competition, details);
            favorite.setMaxRounds(numRounds.get(0));
            favorite.setCurrentRound(Integer.parseInt(results.getJornada()));

            saveFavorite(favorite, details.getEquip().getIdClub());

        } else {
            Log.e(TAG, "error obtaining total rounds for the competition, won't go further");
        }

    }

    private void saveFavorite(Favorite favorite, String idClub) {
        final SQLiteDatabase db = getHelper().getWritableDatabase();
        db.beginTransaction();
        try{
            Club club = getHelper().getClubDao().queryForId(idClub);
            if (club != null) {
                favorite.setClub(club);
                getHelper().getFavoriteDao().create(favorite);
            } else {
                Log.e(TAG, "Error reading club from DB, can't create favorite");
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e(TAG, "Error performing sql bulk insert", e);
        }

        finally {
            db.endTransaction();
        }
    }

    private void parseTeamsFromCategory(TeamCategory teamCategory) {
        long requestTime;
        Log.i(TAG, "Requesting data for teams");
        List<TeamDetails> teamDetails = new ArrayList<>(teamCategory.getTeams().size());
        requestTime = System.currentTimeMillis();
        for (Team team : teamCategory.getTeams()){
            teamDetails.add(mRestAPI.getTeamDetails(team.getCodiClub(), team.getCodiEquip()));
            Log.d(TAG, "Request took: " + (System.currentTimeMillis() - requestTime));
        }

        Log.i(TAG, "Finished requesting data in "+ (System.currentTimeMillis() - requestTime) +" ms");

        EventBus.getDefault().post(new TeamDetailsEvent(teamDetails));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Destroying openHelper from intentService");
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }


}
