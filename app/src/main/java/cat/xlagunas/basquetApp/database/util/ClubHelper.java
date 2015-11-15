package cat.xlagunas.basquetapp.database.util;

import android.util.Log;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import cat.xlagunas.basquetapp.database.Club;
import cat.xlagunas.basquetapp.database.DatabaseHelper;
import cat.xlagunas.basquetapp.database.Favorite;

/**
 * Created by xlagunas on 20/03/15.
 */
public class ClubHelper {

    private final static String TAG = ClubHelper.class.getSimpleName();

    public final static List<Club> getFavoritedClubs(DatabaseHelper mHelper) {
        List<Club> mClubs;

        try {
            QueryBuilder<Club, String> mBuilder = mHelper.getClubDao().queryBuilder();
            QueryBuilder<Favorite, Integer> mFavorite = mHelper.getFavoriteDao().queryBuilder();

            mBuilder.join(mFavorite);
            mBuilder.groupBy(Club.CLUB_ID);

            Log.d(TAG, mBuilder.prepareStatementString());
            mClubs = mBuilder.query();
        } catch (SQLException e) {
            Log.e(TAG, "Error getting clubs", e);
            mClubs = null;
        }

        return mClubs;
    }
}
