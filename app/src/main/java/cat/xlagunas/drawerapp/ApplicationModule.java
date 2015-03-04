package cat.xlagunas.drawerapp;

import javax.inject.Singleton;

import cat.xlagunas.drawerapp.api.ApiTest;
import cat.xlagunas.drawerapp.api.BasicAuthInterceptor;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by xlagunas on 03/03/15.
 */
@Module(
        library = true
)
public class ApplicationModule {

    @Provides
    @Singleton
    ApiTest provideApi(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.ENDPOINT)
                .setRequestInterceptor(new BasicAuthInterceptor())
                .build();

        ApiTest service = restAdapter.create(ApiTest.class);
        return service;
    }
}
