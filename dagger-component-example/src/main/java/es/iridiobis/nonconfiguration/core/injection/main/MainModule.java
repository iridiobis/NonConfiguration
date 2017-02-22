package es.iridiobis.nonconfiguration.core.injection.main;


import dagger.Module;
import dagger.Provides;
import es.iridiobis.nonconfiguration.core.injection.ActivityScope;
import es.iridiobis.nonconfiguration.domain.usecases.salutation.SalutationUseCase;

@Module
public class MainModule {

    @ActivityScope
    @Provides
    public static SalutationUseCase provideSalutationUseCase() {
        return new SalutationUseCase();
    }
}
