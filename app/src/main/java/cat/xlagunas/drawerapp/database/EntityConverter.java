package cat.xlagunas.drawerapp.database;

import java.util.ArrayList;
import java.util.List;

import cat.xlagunas.drawerapp.api.model.ClubBasic;
import cat.xlagunas.drawerapp.api.model.Competicion;
import cat.xlagunas.drawerapp.api.model.TeamDetails;

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

    public static Favorite convertFavoriteFromModels(Competicion competition, TeamDetails details){
        Favorite favorite = new Favorite();
        favorite.setCategoryId(competition.getCodiCategoria());
        favorite.setCategoryName(competition.getNomCompeticio());
        favorite.setGroupId(competition.getNumGrup());
        favorite.setTeamName(details.getEquip().getNom());
        favorite.setTeamId(details.getEquip().getIdEquip());
        favorite.setRegionId(competition.getTerritorial());
        favorite.setCompetitionId(competition.getCodiCompeticio());
        return favorite;
    }

    public static cat.xlagunas.drawerapp.api.model.Favorite parseFavoriteDatabase(Favorite favorite) {
        cat.xlagunas.drawerapp.api.model.Favorite fav = new cat.xlagunas.drawerapp.api.model.Favorite();

        fav.setTeamId(favorite.getTeamId());
        fav.setGroupId(favorite.getGroupId());
        fav.setCategoryName(favorite.getCategoryName());
        fav.setCategoryId(favorite.getCategoryId());
        fav.setClubId(favorite.getClub().getIdClub());
        fav.setCurrentRounds(favorite.getCurrentRound());
        fav.setMaxRounds(favorite.getMaxRounds());
        fav.setRegionId(favorite.getRegionId());
        fav.setCompetitionId(favorite.getCompetitionId());

        return fav;
    }

    public static List<cat.xlagunas.drawerapp.api.model.Favorite> parseFavoriteListFromDatabase(List<Favorite> favs) {
        List<cat.xlagunas.drawerapp.api.model.Favorite> favorites = new ArrayList<>(favs.size());

        for (int i=0; i<favs.size(); i++) {
            favorites.add(parseFavoriteDatabase(favs.get(i)));
        }

        return favorites;
    }
}
