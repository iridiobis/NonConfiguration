package es.iridiobis.nonconfiguration.domain.usecases.salutation;


public interface FirstNameUseCase {

    void giveFirstName(String firstName, Listener listener);

    interface Listener {
        void askForLastName(String firstName);
    }
}
