package cat.xlagunas.drawerapp.api;

import cat.xlagunas.drawerapp.api.model.Results;
import cat.xlagunas.drawerapp.api.model.WeatherModel;
import retrofit.Callback;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by xlagunas on 03/03/15.
 */
public interface ApiTest {

    @GET("/resultats/get_resultats_ultima_jornada_jugada/1/041/101/02")
    public Observable<Results> lastWeekResults();


}
