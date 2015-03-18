package cat.xlagunas.drawerapp.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xlagunas on 15/03/15.
 */
public class TeamCategory implements BasicEntity, Parcelable {
    private String categoryName;
    private List<Team> teams;

    public TeamCategory() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    /**
     * From the server we can obtain duplicated categories,
     * this methods joins all the teams that belong to the same category
     * @param teams
     * @return
     */
    public static List<TeamCategory> obtainTeamCategories(List<Team> teams){
        Log.d("TeamCategory", "processing :"+(teams.size() -1 )+" teams");
        int teamsProcessed = 0;
        Map<String, TeamCategory> teamMap = new HashMap<>(teams.size());

        for (Team team : teams) {

            if (teamMap.containsKey(team.getNomCategoria()) ) {
                teamMap.get(team.getNomCategoria()).getTeams().add(team);
                Log.d("TeamCategory", "Found Existing Category, adding another team: "+team.getNomCategoria());
                teamsProcessed++;

            } else {

                TeamCategory teamCategory = new TeamCategory();
                teamCategory.setCategoryName(team.getNomCategoria());
                List<Team> subTeamList = new ArrayList<>();
                subTeamList.add(team);
                teamCategory.setTeams(subTeamList);
                teamMap.put(teamCategory.getCategoryName(), teamCategory);
                Log.d("TeamCategory", "Category not found, creating "+team.getNomCategoria());
                teamsProcessed++;
            }
        }

        Log.d("TeamCategory", "Total processed teams: "+teamsProcessed);


        return new ArrayList<> (teamMap.values());
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String getKeyValue() {
        return null;
    }

    @Override
    public int getType() {
        return TEAM_ENTITY;
    }

    protected TeamCategory(Parcel in) {
        categoryName = in.readString();
        if (in.readByte() == 0x01) {
            teams = new ArrayList<Team>();
            in.readList(teams, Team.class.getClassLoader());
        } else {
            teams = null;
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(categoryName);
        if (teams == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(teams);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TeamCategory> CREATOR = new Parcelable.Creator<TeamCategory>() {
        @Override
        public TeamCategory createFromParcel(Parcel in) {
            return new TeamCategory(in);
        }

        @Override
        public TeamCategory[] newArray(int size) {
            return new TeamCategory[size];
        }
    };

    @Override
    public String toString() {
        return getCategoryName().toUpperCase();
    }
}
