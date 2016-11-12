package es.iridiobis.stickycomponent.domain.usecases.salutation;


public interface FirstName {
    interface UseCase {
        void giveFirstName(String firstName, Listener listener);
    }

    interface Listener {
        void askForLastName(String firstName);
    }
}
