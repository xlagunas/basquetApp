package cat.xlagunas.drawerapp.api.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xlagunas on 17/03/15.
 */
public class Favorite implements Parcelable {

    private String categoryName;
    private int maxRounds;
    private int currentRounds;
    private String clubId;
    private String teamId;
    private String categoryId;
    private String groupId;
    private String regionId;
    private String competitionId;


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

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    //Is used in TestFragment's dialog to show the category of each of the teams of a club
    @Override
    public String toString() {
        return getCategoryName();
    }


    protected Favorite(Parcel in) {
        categoryName = in.readString();
        maxRounds = in.readInt();
        currentRounds = in.readInt();
        clubId = in.readString();
        teamId = in.readString();
        categoryId = in.readString();
        groupId = in.readString();
        regionId = in.readString();
        competitionId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(categoryName);
        dest.writeInt(maxRounds);
        dest.writeInt(currentRounds);
        dest.writeString(clubId);
        dest.writeString(teamId);
        dest.writeString(categoryId);
        dest.writeString(groupId);
        dest.writeString(regionId);
        dest.writeString(competitionId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Favorite> CREATOR = new Parcelable.Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel in) {
            return new Favorite(in);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };
}