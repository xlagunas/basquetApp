package cat.xlagunas.drawerapp.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by xlagunas on 24/03/15.
 */
public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("AlarmReceiver", "Starting onReceive");
        UpdateService.startActionUpdate(context);
    }
}
