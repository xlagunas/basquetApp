package cat.xlagunas.drawerapp.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;

import cat.xlagunas.drawerapp.CustomApplication;
import cat.xlagunas.drawerapp.api.RestAPI;
import cat.xlagunas.drawerapp.api.model.Results;
import cat.xlagunas.drawerapp.database.DatabaseHelper;
import cat.xlagunas.drawerapp.database.Favorite;
import retrofit.RetrofitError;


public class UpdateService extends IntentService {

    private static final String ACTION_UPDATE = "cat.xlagunas.drawerapp.service.action.UPDATE";
    private static final String TAG = UpdateService.class.getSimpleName();

    private RestAPI mRestAPI;
    private DatabaseHelper databaseHelper = null;

    public UpdateService() {
        super("UpdateService");
    }

    public static void startActionUpdate(Context context) {
        Intent intent = new Intent(context, UpdateService.class);
        intent.setAction(ACTION_UPDATE);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mRestAPI = ((CustomApplication)getApplication()).getApiService();
    }


    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper =
                    OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE.equals(action)) {
                handleUpdateAction();
            }
        }
    }

    private void handleUpdateAction() {

        Log.i(TAG, "updating the service");
        try {
            List<Favorite> favoriteList = getHelper().getFavoriteDao().queryForAll();

            for (Favorite favorite : favoriteList) {
                try {
                    Results results = mRestAPI.getLastWeekResults(favorite.getRegionId(),
                            favorite.getCategoryId(), favorite.getCompetitionId(), favorite.getGroupId());
                    favorite.setCurrentRound(Integer.parseInt(results.getJornada()));
                    getHelper().getFavoriteDao().update(favorite);
                } catch (RetrofitError e) {
                    Log.e(TAG, "Retrofit Error", e);
                }
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error updating current round", e);
        }
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
