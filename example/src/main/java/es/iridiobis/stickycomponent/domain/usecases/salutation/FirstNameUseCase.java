package es.iridiobis.stickycomponent.domain.usecases.salutation;


public interface FirstNameUseCase {

    void giveFirstName(String firstName, Listener listener);

    interface Listener {
        void askForLastName(String firstName);
    }
}
