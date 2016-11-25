package es.iridiobis.stickycomponent.core.injection.main.confirmation;


import dagger.Module;
import dagger.Provides;
import es.iridiobis.stickycomponent.core.injection.FragmentScope;
import es.iridiobis.stickycomponent.domain.usecases.salutation.ConfirmationUseCase;
import es.iridiobis.stickycomponent.domain.usecases.salutation.SalutationUseCase;
import es.iridiobis.stickycomponent.presentation.main.MainNavigator;
import es.iridiobis.stickycomponent.presentation.main.confirmation.Confirmation;
import es.iridiobis.stickycomponent.presentation.main.confirmation.ConfirmationPresenter;

@Module
public class ConfirmationModule {

    private final String fullName;

    public ConfirmationModule(final String fullName) {
        this.fullName = fullName;
    }

    @FragmentScope
    @Provides
    public String provideFullName() {
        return fullName;
    }

    @FragmentScope
    @Provides
    public Confirmation.Presenter providePresenter(final ConfirmationPresenter presenter) {
        return presenter;
    }

    @FragmentScope
    @Provides
    public Confirmation.Navigator provideNavigator(final MainNavigator navigator) {
        return navigator;
    }

    @FragmentScope
    @Provides
    public ConfirmationUseCase provideUseCase(final SalutationUseCase useCase) {
        return useCase;
    }
}
