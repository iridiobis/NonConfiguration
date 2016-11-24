package es.iridiobis.stickycomponent.presentation.main.firstname;

public interface FirstName {

    interface Presenter extends es.iridiobis.stickycomponent.core.Presenter<View> {
        void updateFirstName(String firstName);
        void confirmFirstName();
    }

    interface View {
        void showFirstName(String firstName);
        void enableNextButton(boolean enabled);
    }

    interface Navigator {
        void goToLastNameScreen(String firstName);
    }

}
