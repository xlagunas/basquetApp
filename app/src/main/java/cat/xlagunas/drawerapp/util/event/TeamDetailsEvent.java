package cat.xlagunas.drawerapp.util.event;

import java.util.List;

import cat.xlagunas.drawerapp.api.model.TeamDetails;

/**
 * Created by xlagunas on 15/03/15.
 */
public class TeamDetailsEvent {
    private List<TeamDetails> mTeamDetails;

    public TeamDetailsEvent(List<TeamDetails> mTeamDetails) {
        this.mTeamDetails = mTeamDetails;
    }

    public List<TeamDetails> getmTeamDetails() {
        return mTeamDetails;
    }
}
