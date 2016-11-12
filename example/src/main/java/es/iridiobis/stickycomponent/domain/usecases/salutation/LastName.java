package es.iridiobis.stickycomponent.domain.usecases.salutation;


public interface LastName {
    interface UseCase {
        void giveLastName(String lastName, Listener listener);
    }

    interface Listener {
        void showSalutation(String fullName);
    }
}
