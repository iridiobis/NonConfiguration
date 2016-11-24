package es.iridiobis.stickycomponent.core.injection.main.firstname;


import dagger.Module;
import dagger.Provides;
import es.iridiobis.stickycomponent.core.injection.FragmentScope;
import es.iridiobis.stickycomponent.domain.usecases.salutation.FirstNameUseCase;
import es.iridiobis.stickycomponent.domain.usecases.salutation.SalutationUseCase;
import es.iridiobis.stickycomponent.presentation.main.MainNavigator;
import es.iridiobis.stickycomponent.presentation.main.firstname.FirstName;
import es.iridiobis.stickycomponent.presentation.main.firstname.FirstNamePresenter;

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
