package cat.xlagunas.basquetapp.database;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by xlagunas on 18/03/15.
 */

@DatabaseTable(tableName = "club")
public class Club {

    public final static String CLUB_ID = "club_id";
    public final static String CLUB_NAME = "club_name";
    public final static String CLUB_FAVORITE = "club_favorite";

    @DatabaseField(id = true, columnName = CLUB_ID)
    private String idClub;

    @DatabaseField(canBeNull = false, columnName = CLUB_NAME)
    private String name;

    @ForeignCollectionField(eager = false, columnName = CLUB_FAVORITE, foreignFieldName = "club")
    ForeignCollection<Favorite> favorites;

    public Club() {
    }

    public String getIdClub() {
        return idClub;
    }

    public void setIdClub(String idClub) {
        this.idClub = idClub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForeignCollection<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(ForeignCollection<Favorite> favorites) {
        this.favorites = favorites;
    }
}
