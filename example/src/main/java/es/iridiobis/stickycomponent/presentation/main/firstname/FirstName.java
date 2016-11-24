package es.iridiobis.stickycomponent.presentation.main.firstname;

public interface FirstName {

    interface Presenter extends es.iridiobis.stickycomponent.core.Presenter<View> {
        void updateFirstName(final String firstName);
        void confirmFirstName();
    }

    interface View {
        void showFirstName(String string);
        void enableNextButton(boolean enabled);
    }

    interface Navigator {
        void goToLastNameScreen();
    }

}
