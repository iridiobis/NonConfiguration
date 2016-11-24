package es.iridiobis.stickycomponent.presentation.main.confirmation;

import es.iridiobis.stickycomponent.domain.model.Person;

public interface Confirmation {

    interface Presenter extends es.iridiobis.stickycomponent.core.Presenter<View> {
        void change();
        void finish();
    }

    interface View {
        void showMessage(String fullName);
    }

    interface Navigator {
        void goToFarewellScreen(Person person);
        void goToFirstNameScreen(String firstName);
    }

}
