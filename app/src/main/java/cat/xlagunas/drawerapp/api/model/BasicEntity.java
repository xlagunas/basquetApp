package cat.xlagunas.drawerapp.api.model;

/**
 * Created by xlagunas on 14/03/15.
 */
public interface BasicEntity {

    public final static int CLUB_ENTITY = 101010;
    public final static int TEAM_ENTITY = 101011;
    public final static int NO_ENTITY = 101012;
    public final static int TEAM_CATEGORY_ENTITY = 101013;
    public final static int RESULT_ENTITY = 101014;

    public String getDisplayName();

    public String getKeyValue();

    public int getType();
}
