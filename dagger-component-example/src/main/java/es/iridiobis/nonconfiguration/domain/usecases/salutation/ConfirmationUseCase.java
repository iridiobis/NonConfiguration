package es.iridiobis.nonconfiguration.domain.usecases.salutation;


import es.iridiobis.nonconfiguration.domain.model.Person;

public interface ConfirmationUseCase {
    void change(Listener listener);
    void finish(Listener listener);

    interface Listener {
        void goToFirstName(String firstName);
        void goToFarewell(Person person);
    }
}
