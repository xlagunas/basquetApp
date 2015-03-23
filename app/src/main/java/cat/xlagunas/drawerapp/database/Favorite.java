package cat.xlagunas.drawerapp.database;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by xlagunas on 18/03/15.
 */
@DatabaseTable(tableName = "favorite")
public class Favorite {

    public final static String FAVORITE_ID = "favorite_id";
    public final static String FAVORITE_TEAM_ID = "team_id";
    public final static String FAVORITE_TEAM_NAME = "team_name";
    public final static String FAVORITE_CATEGORY_ID = "category_id";
    public final static String FAVORITE_GROUP_ID = "group_id";
    public final static String FAVORITE_MAX_ROUNDS = "max_rounds";
    public final static String FAVORITE_CURRENT_ROUND = "current_round";
    public final static String FAVORITE_CATEGORY_NAME = "category_name";
    public final static String FAVORITE_COMPETITION_ID = "competition_id";
    public final static String FAVORITE_REGION_ID = "region_id";
    public final static String FAVORITE_CLUB_ID = "club_id";

    @DatabaseField(generatedId = true, columnName = FAVORITE_ID)
    private int favoriteId;

    @DatabaseField(canBeNull = false, columnName = FAVORITE_TEAM_ID)
    private String teamId;

    @DatabaseField(canBeNull = false, columnName = FAVORITE_CATEGORY_ID)
    private String categoryId;

    @DatabaseField(canBeNull = false, columnName = FAVORITE_GROUP_ID)
    private String groupId;

    @DatabaseField(canBeNull = false, columnName = FAVORITE_REGION_ID)
    private String regionId;

    @DatabaseField(canBeNull = false, columnName = FAVORITE_MAX_ROUNDS)
    private int maxRounds;

    @DatabaseField(canBeNull = false, columnName = FAVORITE_CURRENT_ROUND)
    private int currentRound;

    @DatabaseField(canBeNull = false, columnName = FAVORITE_TEAM_NAME)
    private String teamName;

    @DatabaseField(canBeNull = false, columnName = FAVORITE_CATEGORY_NAME)
    private String categoryName;

    @DatabaseField(canBeNull = false, columnName = FAVORITE_COMPETITION_ID)
    private String competitionId;

    @DatabaseField(foreign = true, canBeNull = false)
    private Club club;

    public Favorite() {
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public int getMaxRounds() {
        return maxRounds;
    }

    public void setMaxRounds(int maxRounds) {
        this.maxRounds = maxRounds;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    @Override
    public String toString() {
        return getCategoryName();
    }
}
