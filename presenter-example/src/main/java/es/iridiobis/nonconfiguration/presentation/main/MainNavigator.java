package es.iridiobis.nonconfiguration.presentation.main;


import es.iridiobis.nonconfiguration.core.BasePresenter;
import es.iridiobis.nonconfiguration.domain.model.Person;
import es.iridiobis.nonconfiguration.presentation.main.confirmation.Confirmation;
import es.iridiobis.nonconfiguration.presentation.main.firstname.FirstName;
import es.iridiobis.nonconfiguration.presentation.main.lastname.LastName;

public class MainNavigator
        extends BasePresenter<MainNavigatiorExecutor>
        implements FirstName.Navigator,
        LastName.Navigator,
        Confirmation.Navigator {

    private Operation operation;

    @Override
    public void goToLastNameScreen(final String firstName) {
        processOperation(new GoToLastScreenOperation(firstName));
    }

    @Override
    public void goToConfirmationScreen(final String firstName, final String lastName) {
        processOperation(new GoToConfirmationScreenOperation(firstName, lastName));
    }

    @Override
    protected void onViewAttached() {
        if (operation != null) {
            operation.run();
            operation = null;
        }
    }

    @Override
    public void goToFarewellScreen(final Person person) {
        processOperation(new GoToFarewellScreenOperation(person));
    }

    @Override
    public void goToFirstNameScreen(final String firstName) {
        processOperation(new GoToFirstNameScreenOperation(firstName));
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

    private class GoToFirstNameScreenOperation implements Operation {

        private final String firstName;

        GoToFirstNameScreenOperation(final String firstName) {
            this.firstName = firstName;
        }

        @Override
        public void run() {
            getView().goToFirstNameScreen(firstName);
        }
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

        private final String firstName;
        private final String lastName;

        GoToConfirmationScreenOperation(final String firstName, final String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public void run() {
            getView().goToConfirmationScreen(firstName, lastName);
        }
    }

    private class GoToFarewellScreenOperation implements Operation {

        private final Person person;

        GoToFarewellScreenOperation(final Person person) {
            this.person = person;
        }

        @Override
        public void run() {
            getView().goToFarewell(person);
        }
    }

}
