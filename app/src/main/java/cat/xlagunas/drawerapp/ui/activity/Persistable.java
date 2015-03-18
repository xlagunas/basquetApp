package cat.xlagunas.drawerapp.ui.activity;

import cat.xlagunas.drawerapp.database.DatabaseHelper;

/**
 * Created by xlagunas on 18/03/15.
 */
public interface Persistable {
    public DatabaseHelper getHelper();
}
