package es.iridiobis.nonconfiguration.presentation.main.confirmation;

import es.iridiobis.nonconfiguration.core.BasePresenter;
import es.iridiobis.nonconfiguration.domain.model.Person;

public class ConfirmationPresenter
        extends BasePresenter<Confirmation.View>
        implements Confirmation.Presenter {

    private final String firstName;
    private final String lastName;

    public ConfirmationPresenter(
            final String firstName,
            final String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public void change() {
        getView().goToFirstNameScreen(firstName);
    }

    @Override
    public void finish() {
        getView().goToFarewellScreen(
                Person.newBuilder()
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .build());
    }

    @Override
    protected void onViewAttached() {
        getView().showMessage(String.format("%s %s", firstName, lastName));
    }
}
