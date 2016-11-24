package es.iridiobis.stickycomponent.presentation.main;


import es.iridiobis.stickycomponent.domain.model.Person;

public interface MainNavigatiorExecutor {
    void goToFirstNameScreen(String firstName);
    void goToLastNameScreen(String firstName);
    void goToConfirmationScreen(String fullName);
    void goToFarewell(Person person);
}
