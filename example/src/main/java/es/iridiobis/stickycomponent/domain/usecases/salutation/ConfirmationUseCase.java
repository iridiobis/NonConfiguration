package es.iridiobis.stickycomponent.domain.usecases.salutation;


import es.iridiobis.stickycomponent.domain.model.Person;

public interface ConfirmationUseCase {
    void change(Listener listener);
    void finish(Listener listener);

    interface Listener {
        void goToFirstName(String firstName);
        void goToFarewell(Person person);
    }
}
