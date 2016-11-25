package es.iridiobis.stickycomponent.core.injection.main.farewell;


import dagger.Module;
import dagger.Provides;
import es.iridiobis.stickycomponent.core.injection.FragmentScope;
import es.iridiobis.stickycomponent.domain.model.Person;
import es.iridiobis.stickycomponent.domain.usecases.salutation.ConfirmationUseCase;
import es.iridiobis.stickycomponent.domain.usecases.salutation.SalutationUseCase;
import es.iridiobis.stickycomponent.presentation.main.MainNavigator;
import es.iridiobis.stickycomponent.presentation.main.confirmation.Confirmation;
import es.iridiobis.stickycomponent.presentation.main.confirmation.ConfirmationPresenter;
import es.iridiobis.stickycomponent.presentation.main.farewell.Farewell;
import es.iridiobis.stickycomponent.presentation.main.farewell.FarewellPresenter;

@Module
public class FarewellModule {

    private final Person person;

    public FarewellModule(final Person person) {
        this.person = person;
    }

    @FragmentScope
    @Provides
    public Person providePerson() {
        return person;
    }

    @FragmentScope
    @Provides
    public Farewell.Presenter providePresenter(final FarewellPresenter presenter) {
        return presenter;
    }

}
