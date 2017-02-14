package es.iridiobis.nonconfiguration.presentation.main.firstname;

import es.iridiobis.nonconfiguration.core.BasePresenter;

public class FirstNamePresenter
        extends BasePresenter<FirstName.View>
        implements FirstName.Presenter {

    private String firstName;

    @Override
    public void updateFirstName(final String firstName) {
        this.firstName = firstName;
        if (hasView()) {
            updateNextButton();
        }
    }

    @Override
    public void confirmFirstName() {
        getView().goToLastNameScreen(firstName);
    }

    @Override
    protected void onViewAttached() {
        showFirstName();
        updateNextButton();
    }

    private void showFirstName() {
        getView().showFirstName(firstName);
    }

    private void updateNextButton() {
        getView().enableNextButton(firstName != null && !firstName.isEmpty());
    }
}
