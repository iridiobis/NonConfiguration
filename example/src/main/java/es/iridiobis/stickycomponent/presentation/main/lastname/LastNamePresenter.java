package es.iridiobis.stickycomponent.presentation.main.lastname;

import javax.inject.Inject;

import es.iridiobis.stickycomponent.core.BasePresenter;
import es.iridiobis.stickycomponent.core.injection.FragmentScope;
import es.iridiobis.stickycomponent.domain.usecases.salutation.LastNameUseCase;

@FragmentScope
public class LastNamePresenter
        extends BasePresenter<LastName.View>
        implements LastName.Presenter,
        LastNameUseCase.Listener {

    private final LastName.Navigator navigator;
    private final LastNameUseCase useCase;
    private final String firstName;
    private String lastName;

    @Inject
    public LastNamePresenter(
            final String firstName,
            final LastName.Navigator navigator,
            final LastNameUseCase useCase) {

        this.firstName = firstName;
        this.navigator = navigator;
        this.useCase = useCase;
    }

    @Override
    public void updateLastName(final String lastName) {
        this.lastName = lastName;
        updateNextButton();
    }

    @Override
    public void confirmLastName() {
        useCase.giveLastName(lastName, this);
    }

    @Override
    public void showSalutation(final String fullName) {
        navigator.goToConfirmationScreen(fullName);
    }

    @Override
    protected void onViewAttached() {
        getView().showMessage(firstName);
        showLastName();
        updateNextButton();
    }

    private void showLastName() {
        getView().showLastName(lastName);
    }

    private void updateNextButton() {
        if (hasView()) {
            getView().enableNextButton(lastName != null && !lastName.isEmpty());
        }
    }
}
