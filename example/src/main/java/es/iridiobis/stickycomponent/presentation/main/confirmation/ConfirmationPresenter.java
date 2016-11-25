package es.iridiobis.stickycomponent.presentation.main.confirmation;

import javax.inject.Inject;

import es.iridiobis.stickycomponent.core.BasePresenter;
import es.iridiobis.stickycomponent.core.injection.FragmentScope;
import es.iridiobis.stickycomponent.domain.model.Person;
import es.iridiobis.stickycomponent.domain.usecases.salutation.ConfirmationUseCase;
import es.iridiobis.stickycomponent.domain.usecases.salutation.LastNameUseCase;

@FragmentScope
public class ConfirmationPresenter
        extends BasePresenter<Confirmation.View>
        implements Confirmation.Presenter,
        ConfirmationUseCase.Listener {

    private final Confirmation.Navigator navigator;
    private final ConfirmationUseCase useCase;
    private final String fullName;

    @Inject
    public ConfirmationPresenter(
            final String fullName,
            final Confirmation.Navigator navigator,
            final ConfirmationUseCase useCase) {

        this.fullName = fullName;
        this.navigator = navigator;
        this.useCase = useCase;
    }

    @Override
    public void change() {
        useCase.change(this);
    }

    @Override
    public void finish() {
        useCase.finish(this);
    }

    @Override
    protected void onViewAttached() {
        getView().showMessage(fullName);
    }

    @Override
    public void goToFirstName(final String firstName) {
        navigator.goToFirstNameScreen(firstName);
    }

    @Override
    public void goToFarewell(final Person person) {
        navigator.goToFarewellScreen(person);
    }
}
