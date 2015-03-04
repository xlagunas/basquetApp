package cat.xlagunas.drawerapp;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xlagunas on 03/03/15.
 */
public class Modules {
    public static List<Object> list() {
        return Arrays.asList( new ApplicationModule(), new TestModule() );
    }

}
