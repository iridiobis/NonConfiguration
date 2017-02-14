package es.iridiobis.nonconfiguration.presentation.main.lastname;

import es.iridiobis.nonconfiguration.presentation.main.firstname.FirstName;

public interface LastName {

    interface Presenter extends es.iridiobis.nonconfiguration.core.Presenter<View> {
        void updateLastName(String lastName);
        void confirmLastName();
    }

    interface View extends Navigator {
        void showMessage(String firstName);
        void showLastName(String lastName);
        void enableNextButton(boolean enabled);
    }

    interface Navigator {
        void goToConfirmationScreen(String firstName, String lastName);
    }

}
