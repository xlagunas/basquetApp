package cat.xlagunas.basquetapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by xlagunas on 18/03/15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private final String TAG = DatabaseHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "basquetApp.db";
    private static final int DATABASE_VERSION = 5;

    private Dao<Club, String> clubDao;
    private Dao<Favorite, Integer> favoriteDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        Log.i(TAG, "Creating database schema and tables...");
        try {
            TableUtils.createTable(connectionSource, Favorite.class);
            TableUtils.createTable(connectionSource, Club.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create datbases", e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.i(TAG, "Upgrading database schema and/or tables...");
        try {
            TableUtils.dropTable(connectionSource, Favorite.class, true);
            TableUtils.dropTable(connectionSource, Club.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(TAG, "Unable to upgrade database from version " + oldVersion + " to new "
                    + newVersion, e);
        }
    }

    public Dao<Favorite, Integer> getFavoriteDao() throws SQLException {
        if (favoriteDao == null) {
            favoriteDao = getDao(Favorite.class);
        }
        return favoriteDao;
    }

    public Dao<Club, String> getClubDao() throws SQLException {
        if (clubDao == null) {
            clubDao = getDao(Club.class);
        }
        return clubDao;
    }


}
