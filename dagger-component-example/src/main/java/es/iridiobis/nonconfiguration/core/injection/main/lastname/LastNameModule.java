package es.iridiobis.nonconfiguration.core.injection.main.lastname;


import dagger.Module;
import dagger.Provides;
import es.iridiobis.nonconfiguration.core.injection.FragmentScope;
import es.iridiobis.nonconfiguration.domain.usecases.salutation.LastNameUseCase;
import es.iridiobis.nonconfiguration.domain.usecases.salutation.SalutationUseCase;
import es.iridiobis.nonconfiguration.presentation.main.MainNavigator;
import es.iridiobis.nonconfiguration.presentation.main.lastname.LastName;
import es.iridiobis.nonconfiguration.presentation.main.lastname.LastNamePresenter;

@Module
public class LastNameModule {

    private final String firstName;

    public LastNameModule(final String firstName) {
        this.firstName = firstName;
    }

    @FragmentScope
    @Provides
    public String provideFirstName() {
        return firstName;
    }

    @FragmentScope
    @Provides
    public LastName.Presenter providePresenter(final LastNamePresenter presenter) {
        return presenter;
    }

    @FragmentScope
    @Provides
    public LastName.Navigator provideNavigator(final MainNavigator navigator) {
        return navigator;
    }

    @FragmentScope
    @Provides
    public LastNameUseCase provideUseCase(final SalutationUseCase useCase) {
        return useCase;
    }
}
