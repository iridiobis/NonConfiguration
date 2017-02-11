package es.iridiobis.nonconfiguration;

import android.app.Application;
import android.content.Context;


public class ExampleApplication extends Application {

    private ApplicationComponent applicationComponent;

    public static ApplicationComponent getApplicationComponent(final Context context) {
        return ((ExampleApplication) context.getApplicationContext()).applicationComponent;
    }

    @Override public void onCreate() {
        super.onCreate();

        applicationComponent = ApplicationComponent.Initializer.init();
    }
}

