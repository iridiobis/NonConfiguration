package es.iridiobis.stickycomponent.presentation.main;


import es.iridiobis.stickycomponent.core.BasePresenter;
import es.iridiobis.stickycomponent.presentation.main.firstname.FirstName;

public class MainNavigator extends BasePresenter<MainNavigatiorExecutor> implements FirstName.Navigator {

    private boolean navigationToLastNameScreenPending;

    @Override
    public void goToLastNameScreen() {
        if (hasView()) {
            getView().goToLastNameScreen();
        } else {
            navigationToLastNameScreenPending = true;
        }
    }

    @Override
    protected void onViewAttached() {
        if (navigationToLastNameScreenPending) {
            getView().goToLastNameScreen();
            navigationToLastNameScreenPending = false;
        }
    }
}
