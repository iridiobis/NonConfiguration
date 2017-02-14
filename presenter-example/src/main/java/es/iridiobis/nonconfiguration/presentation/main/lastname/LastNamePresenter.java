package es.iridiobis.nonconfiguration.presentation.main.lastname;

import es.iridiobis.nonconfiguration.core.BasePresenter;

public class LastNamePresenter
        extends BasePresenter<LastName.View>
        implements LastName.Presenter {

    private final String firstName;
    private String lastName;

    public LastNamePresenter(final String firstName) {

        this.firstName = firstName;
    }

    @Override
    public void updateLastName(final String lastName) {
        this.lastName = lastName;
        if (hasView()) {
            updateNextButton();
        }
    }

    @Override
    public void confirmLastName() {
        getView().goToConfirmationScreen(firstName, lastName);
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
