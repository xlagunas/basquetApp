package cat.xlagunas.drawerapp.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by xlagunas on 18/03/15.
 */

@DatabaseTable(tableName = "club")
public class Club {

    public final static String CLUB_ID = "club_id";
    public final static String CLUB_NAME = "club_name";

    @DatabaseField(id = true, columnName = CLUB_ID)
    private String idClub;

    @DatabaseField(canBeNull = false, columnName = CLUB_NAME)
    private String name;

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
}
