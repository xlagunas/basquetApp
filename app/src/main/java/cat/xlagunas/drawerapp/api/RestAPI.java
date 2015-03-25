package cat.xlagunas.drawerapp.api;

import java.util.List;

import cat.xlagunas.drawerapp.api.model.ClubBasic;
import cat.xlagunas.drawerapp.api.model.Resultat;
import cat.xlagunas.drawerapp.api.model.Results;
import cat.xlagunas.drawerapp.api.model.Team;
import cat.xlagunas.drawerapp.api.model.TeamDetails;
import cat.xlagunas.drawerapp.api.model.TeamStatistic;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by xlagunas on 03/03/15.
 */
public interface RestAPI {

    @GET("/resultats/get_resultats_ultima_jornada_jugada/{RegionId}/{categoryId}/{competitionId}/{groupId}")
    public Results getLastWeekResults(@Path("RegionId") String RegionId,
                                      @Path("categoryId") String categoryId,
                                      @Path("competitionId") String competitionId,
                                      @Path("groupId") String groupId);

    @GET("/resultats/get_resultats_jornada/{RegionId}/{categoryId}/{competitionId}/{groupId}/{weekId}")
    public void getWeekResultsByWeekNumber(@Path("RegionId") String RegionId,
                                      @Path("categoryId") String categoryId,
                                      @Path("competitionId") String competitionId,
                                      @Path("groupId") String groupId,
                                      @Path("weekId") int weekNumber, Callback< List<Resultat> > callback);

    @GET("/competicions/get_jornada_maxima_per_competicio/{RegionId}/{categoryId}/{competitionId}/{groupId}")
    public List<Integer> getMaximumRounds(@Path("RegionId") String RegionId,
                                    @Path("categoryId") String categoryId,
                                    @Path("competitionId") String competitionId,
                                    @Path("groupId") String groupId);

    @GET("/classificacions/get_classificacions_per_competicio/{RegionId}/{categoryId}/{competitionId}/{groupId}/{weekId}")
    public void getClassifications(@Path("RegionId") String RegionId,
                                    @Path("categoryId") String categoryId,
                                    @Path("competitionId") String competitionId,
                                    @Path("groupId") String groupId,
                                    @Path("weekId") int weekId, Callback<List<TeamStatistic>> callback);

    @GET("/clubs/buscador_per_lletra")
    public Observable<List<ClubBasic>> getClubsList();

    @GET("/equips/llistat_equips_club/{clubId}")
    public Observable<List<Team>> getTeamsByClubId(@Path("clubId") String clubId);

    @GET("/equips/llistat_equips_club/{clubId}")
    public void getTeamsByClubId(@Path("clubId") String clubId, Callback<List<Team>> callback);

    @GET("/equips/fitxa/{categoryId}/{teamId}")
    public TeamDetails getTeamDetails(@Path("categoryId")String categoryId, @Path("teamId") String teamId);




}
