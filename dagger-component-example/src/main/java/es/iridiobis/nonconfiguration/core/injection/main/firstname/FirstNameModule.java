package es.iridiobis.nonconfiguration.core.injection.main.firstname;


import dagger.Module;
import dagger.Provides;
import es.iridiobis.nonconfiguration.core.injection.FragmentScope;
import es.iridiobis.nonconfiguration.domain.usecases.salutation.FirstNameUseCase;
import es.iridiobis.nonconfiguration.domain.usecases.salutation.SalutationUseCase;
import es.iridiobis.nonconfiguration.presentation.main.MainNavigator;
import es.iridiobis.nonconfiguration.presentation.main.firstname.FirstName;
import es.iridiobis.nonconfiguration.presentation.main.firstname.FirstNamePresenter;

@Module
public class FirstNameModule {

    @FragmentScope
    @Provides
    public FirstName.Presenter providePresenter(final FirstNamePresenter presenter) {
        return presenter;
    }

    @FragmentScope
    @Provides
    public FirstName.Navigator provideNavigator(final MainNavigator navigator) {
        return navigator;
    }

    @FragmentScope
    @Provides
    public FirstNameUseCase provideUseCase(final SalutationUseCase useCase) {
        return useCase;
    }
}
