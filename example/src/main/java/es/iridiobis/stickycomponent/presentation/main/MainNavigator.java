package es.iridiobis.stickycomponent.presentation.main;


import es.iridiobis.stickycomponent.core.BasePresenter;
import es.iridiobis.stickycomponent.presentation.main.firstname.FirstName;
import es.iridiobis.stickycomponent.presentation.main.lastname.LastName;

public class MainNavigator
        extends BasePresenter<MainNavigatiorExecutor>
        implements FirstName.Navigator,
        LastName.Navigator {

    private Operation operation;

    @Override
    public void goToLastNameScreen(final String firstName) {
        processOperation(new GoToLastScreenOperation(firstName));
    }

    @Override
    public void goToConfirmationScreen(final String fullName) {
        processOperation(new GoToConfirmationScreenOperation(fullName));
    }

    @Override
    protected void onViewAttached() {
        if (operation != null) {
            operation.run();
        }
    }

    private void processOperation(final Operation operation) {
        if (hasView()) {
            operation.run();
        } else {
            this.operation = operation;
        }
    }

    interface Operation {
        void run();
    }

    private class GoToLastScreenOperation implements Operation {

        private final String firstName;

        GoToLastScreenOperation(final String firstName) {
            this.firstName = firstName;
        }

        @Override
        public void run() {
            getView().goToLastNameScreen(firstName);
        }
    }

    private class GoToConfirmationScreenOperation implements Operation {

        private final String fullName;

        GoToConfirmationScreenOperation(final String fullName) {
            this.fullName = fullName;
        }

        @Override
        public void run() {
            getView().goToConfirmationScreen(fullName);
        }
    }

}
