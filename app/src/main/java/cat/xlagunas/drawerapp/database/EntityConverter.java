package cat.xlagunas.drawerapp.database;

import java.util.ArrayList;
import java.util.List;

import cat.xlagunas.drawerapp.api.model.ClubBasic;

/**
 * Created by xlagunas on 18/03/15.
 */
public class EntityConverter {

    public static Club convertFromClubBasicToClub(ClubBasic clubBasic) {
        Club club = new Club();
        club.setIdClub(clubBasic.getCodi_club());
        club.setName(clubBasic.getDisplayName());
        return club;
    }

    public static List<Club> convertFromClubBasicListToClubList(List<ClubBasic> clubBasicList) {
        List<Club> clubList = new ArrayList<Club>(clubBasicList.size());
        for (ClubBasic clubBasic : clubBasicList) {
            Club club = convertFromClubBasicToClub(clubBasic);
            if (club != null) {
                clubList.add(club);
            }
        }

        return clubList;
    }

    public static List<ClubBasic> convertFromClubListToClubBasicList(List<Club> clubList) {
        List<ClubBasic> clubBasicList = new ArrayList<>(clubList.size());
        for (int i=0; i< clubList.size(); i++) {
            ClubBasic clubBasic = new ClubBasic();
            clubBasic.setCodi_club(clubList.get(i).getIdClub());
            clubBasic.setNom(clubList.get(i).getName());
            clubBasicList.add(clubBasic);
        }

        return clubBasicList;
    }
}
