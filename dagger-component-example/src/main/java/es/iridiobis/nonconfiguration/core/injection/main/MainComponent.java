package es.iridiobis.nonconfiguration.core.injection.main;

import android.content.Context;

import dagger.Component;
import es.iridiobis.nonconfiguration.ApplicationComponent;
import es.iridiobis.nonconfiguration.ExampleApplication;
import es.iridiobis.nonconfiguration.core.injection.ActivityScope;
import es.iridiobis.nonconfiguration.domain.usecases.salutation.SalutationUseCase;
import es.iridiobis.nonconfiguration.presentation.main.MainActivity;
import es.iridiobis.nonconfiguration.presentation.main.MainNavigator;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = MainModule.class)
public interface MainComponent {
    final class Initializer {
        private Initializer() {
            //Only static methods, do not allow instances
        }

        public static MainComponent init(final Context context) {
            return DaggerMainComponent.builder()
                    .applicationComponent(ExampleApplication.getApplicationComponent(context))
                    .mainModule(new MainModule())
                    .build();
        }
    }

    void inject(MainActivity activity);

    MainNavigator provideNavigator();

    SalutationUseCase provideUseCase();

}
