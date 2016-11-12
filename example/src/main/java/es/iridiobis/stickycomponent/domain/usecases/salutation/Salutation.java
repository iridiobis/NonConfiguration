package es.iridiobis.stickycomponent.domain.usecases.salutation;


import es.iridiobis.stickycomponent.domain.model.Person;

public interface Salutation {
    interface UseCase {
        void finish(Listener listener);
    }

    interface Listener {
        void goToFarewell(Person person);
    }
}
