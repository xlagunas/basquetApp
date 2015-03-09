package cat.xlagunas.drawerapp;

import android.app.Application;
import android.util.Log;

import cat.xlagunas.drawerapp.api.ApiTest;
import cat.xlagunas.drawerapp.api.BasicAuthInterceptor;
import retrofit.RestAdapter;

/**
 * Created by xlagunas on 03/03/15.
 */
public class CustomApplication extends Application {

    private ApiTest mService;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("CustomApplication", "onCreate");
        initApi();

    }

    private void initApi() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.ENDPOINT)
                .setRequestInterceptor(new BasicAuthInterceptor())
                .build();

        mService = restAdapter.create(ApiTest.class);
    }

    public ApiTest getApiService() {
        return mService;
    }


}
