package es.iridiobis.stickycomponent.presentation.main;


import es.iridiobis.stickycomponent.core.BasePresenter;
import es.iridiobis.stickycomponent.presentation.main.firstname.FirstName;
import es.iridiobis.stickycomponent.presentation.main.lastname.LastName;

public class MainNavigator
        extends BasePresenter<MainNavigatiorExecutor>
        implements FirstName.Navigator,
        LastName.Navigator {

    //TODO improve, make state independent
    private boolean navigationToLastNameScreenPending;
    private boolean navigationToConfirmationScreenPending;
    private String name;

    @Override
    public void goToLastNameScreen(final String firstName) {
        if (hasView()) {
            getView().goToLastNameScreen(firstName);
        } else {
            navigationToLastNameScreenPending = true;
            name = firstName;
        }
    }

    @Override
    protected void onViewAttached() {
        if (navigationToLastNameScreenPending) {
            getView().goToLastNameScreen(name);
            navigationToLastNameScreenPending = false;
        } else if (navigationToConfirmationScreenPending) {
            getView().goToConfirmationScreen(name);
            navigationToConfirmationScreenPending = false;
        }
    }

    @Override
    public void goToConfirmationScreen(final String fullName) {
        if (hasView()) {
            getView().goToConfirmationScreen(fullName);
        } else {
            navigationToConfirmationScreenPending = true;
            name = fullName;
        }
    }
}
