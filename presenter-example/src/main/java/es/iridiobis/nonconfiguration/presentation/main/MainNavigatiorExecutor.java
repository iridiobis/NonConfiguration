package es.iridiobis.nonconfiguration.presentation.main;


import es.iridiobis.nonconfiguration.domain.model.Person;

public interface MainNavigatiorExecutor {
    void goToFirstNameScreen(String firstName);
    void goToLastNameScreen(String firstName);
    void goToConfirmationScreen(final String firstName, final String lastName);
    void goToFarewell(Person person);
}
