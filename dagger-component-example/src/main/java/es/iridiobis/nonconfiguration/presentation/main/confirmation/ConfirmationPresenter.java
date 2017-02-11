package es.iridiobis.nonconfiguration.presentation.main.confirmation;

import javax.inject.Inject;

import es.iridiobis.nonconfiguration.core.BasePresenter;
import es.iridiobis.nonconfiguration.core.injection.FragmentScope;
import es.iridiobis.nonconfiguration.domain.model.Person;
import es.iridiobis.nonconfiguration.domain.usecases.salutation.ConfirmationUseCase;

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
