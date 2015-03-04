package cat.xlagunas.drawerapp.api;

import android.util.Base64;

import cat.xlagunas.drawerapp.Constants;
import retrofit.RequestInterceptor;

/**
 * Created by xlagunas on 04/03/15.
 */
public class BasicAuthInterceptor implements RequestInterceptor {
    @Override
    public void intercept(RequestFacade request) {
        String userAuth = String.format("%s:%s", Constants.USERNAME, Constants.PASSWORD);
        String string = "Basic " + Base64.encodeToString(userAuth.getBytes(), Base64.NO_WRAP);
        request.addHeader("Authorization", string);
    }
}
