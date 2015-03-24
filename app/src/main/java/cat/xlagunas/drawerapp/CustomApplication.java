package cat.xlagunas.drawerapp;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

import cat.xlagunas.drawerapp.api.RestAPI;
import cat.xlagunas.drawerapp.api.BasicAuthInterceptor;
import cat.xlagunas.drawerapp.service.AlarmReceiver;
import cat.xlagunas.drawerapp.service.UpdateService;
import retrofit.RestAdapter;

/**
 * Created by xlagunas on 03/03/15.
 */
public class CustomApplication extends Application {

    private RestAPI mService;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("CustomApplication", "onCreate");
        initApi();
        initUpdateAlarm();

    }

    private void initUpdateAlarm() {
        AlarmManager alarmMgr;
        PendingIntent alarmIntent;
        Context context = getApplicationContext();

        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        // Set the alarm to start at 8:30 a.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 54);
        calendar.set(Calendar.SECOND, 0);

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 , alarmIntent);

    }

    private void initApi() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.ENDPOINT)
                .setRequestInterceptor(new BasicAuthInterceptor())
                .build();

        mService = restAdapter.create(RestAPI.class);
    }

    public RestAPI getApiService() {
        return mService;
    }


}
