package cat.xlagunas.drawerapp;

import android.app.Application;

import dagger.ObjectGraph;

/**
 * Created by xlagunas on 03/03/15.
 */
public class CustomApplication extends Application {

    private ObjectGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();
        graph = ObjectGraph.create(Modules.list().toArray());

    }

    public void inject(Object object) {
        graph.inject(object);
    }

    public ObjectGraph createScopedGraph(Object... modules) {
        return graph.plus(modules);
    }
}
