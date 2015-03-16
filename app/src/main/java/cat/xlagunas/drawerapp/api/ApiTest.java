package cat.xlagunas.drawerapp.api;

import java.util.List;

import cat.xlagunas.drawerapp.api.model.ClubBasic;
import cat.xlagunas.drawerapp.api.model.Results;
import cat.xlagunas.drawerapp.api.model.Team;
import cat.xlagunas.drawerapp.api.model.TeamDetails;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by xlagunas on 03/03/15.
 */
public interface ApiTest {

    @GET("/resultats/get_resultats_ultima_jornada_jugada/{RegionId}/{categoryId}/{competitionId}/{groupId}")
    public Results getLastWeekResults(@Path("RegionId") String RegionId,
                                      @Path("categoryId") String categoryId,
                                      @Path("competitionId") String competitionId,
                                      @Path("groupId") String groupId);

    @GET("/competicions/get_jornada_maxima_per_competicio/{RegionId}/{categoryId}/{competitionId}/{groupId}")
    public List<Integer> getMaximumRounds(@Path("RegionId") String RegionId,
                                    @Path("categoryId") String categoryId,
                                    @Path("competitionId") String competitionId,
                                    @Path("groupId") String groupId);

    @GET("/clubs/buscador_per_lletra")
    public Observable<List<ClubBasic>> getClubsList();

    @GET("/equips/llistat_equips_club/{clubId}")
    public Observable<List<Team>> getTeamsByClubId(@Path("clubId") String clubId);

    @GET("/equips/fitxa/{categoryId}/{teamId}")
    public TeamDetails getTeamDetails(@Path("categoryId")String categoryId, @Path("teamId") String teamId);




}
