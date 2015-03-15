package cat.xlagunas.drawerapp.ui.fragment;


import android.support.v4.app.Fragment;

import de.greenrobot.event.EventBus;

/**
 * Created by xlagunas on 15/03/15.
 */
public abstract class BusFragment extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
