package cat.xlagunas.basquetapp.ui.activity;

import cat.xlagunas.basquetapp.database.DatabaseHelper;

/**
 * Created by xlagunas on 18/03/15.
 */
public interface Persistable {
    public DatabaseHelper getHelper();
}
