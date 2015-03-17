package cat.xlagunas.drawerapp.api.model;

/**
 * Created by xlagunas on 17/03/15.
 */
public class Favorite {

    private String categoryName;
    private int maxRounds;
    private int currentRounds;
    private String clubId;
    private String teamId;
    private String categoryId;
    private String groupId;


    public Favorite() {
        super();
    }

    public Favorite(String categoryName, int maxRounds, int currentRounds, String clubId, String teamId, String categoryId, String groupId) {
        this.categoryName = categoryName;
        this.maxRounds = maxRounds;
        this.currentRounds = currentRounds;
        this.clubId = clubId;
        this.teamId = teamId;
        this.categoryId = categoryId;
        this.groupId = groupId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getMaxRounds() {
        return maxRounds;
    }

    public void setMaxRounds(int maxRounds) {
        this.maxRounds = maxRounds;
    }

    public int getCurrentRounds() {
        return currentRounds;
    }

    public void setCurrentRounds(int currentRounds) {
        this.currentRounds = currentRounds;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
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
}
