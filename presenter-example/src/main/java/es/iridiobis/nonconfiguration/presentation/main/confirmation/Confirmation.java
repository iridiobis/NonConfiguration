package es.iridiobis.nonconfiguration.presentation.main.confirmation;

import es.iridiobis.nonconfiguration.domain.model.Person;

public interface Confirmation {

    interface Presenter extends es.iridiobis.nonconfiguration.core.Presenter<View> {
        void change();
        void finish();
    }

    interface View extends Confirmation.Navigator{
        void showMessage(String fullName);
    }

    interface Navigator {
        void goToFarewellScreen(Person person);
        void goToFirstNameScreen(String firstName);
    }

}
