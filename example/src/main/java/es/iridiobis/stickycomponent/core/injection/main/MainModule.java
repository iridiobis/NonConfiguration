package es.iridiobis.stickycomponent.core.injection.main;


import dagger.Module;
import dagger.Provides;
import es.iridiobis.stickycomponent.core.injection.ActivityScope;
import es.iridiobis.stickycomponent.domain.usecases.salutation.SalutationUseCase;
import es.iridiobis.stickycomponent.presentation.main.MainNavigator;

@Module
public class MainModule {

    @ActivityScope
    @Provides
    public MainNavigator provideNavigator() {
        return new MainNavigator();
    }

    @ActivityScope
    @Provides
    public SalutationUseCase provideSalutationUseCase() {
        return new SalutationUseCase();
    }
}
