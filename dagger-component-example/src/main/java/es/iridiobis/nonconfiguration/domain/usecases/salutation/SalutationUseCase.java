package es.iridiobis.nonconfiguration.domain.usecases.salutation;


import es.iridiobis.nonconfiguration.core.injection.ActivityScope;
import es.iridiobis.nonconfiguration.domain.model.Person;

@ActivityScope
public class SalutationUseCase implements FirstNameUseCase, LastNameUseCase, ConfirmationUseCase {

    private String firstName;
    private String lastName;

    @Override
    public void giveFirstName(final String firstName, final FirstNameUseCase.Listener listener) {
        this.firstName = firstName;
        listener.askForLastName(firstName);
    }

    @Override
    public void giveLastName(final String lastName, final LastNameUseCase.Listener listener) {
        this.lastName = lastName;
        listener.showSalutation(String.format("%s %s", firstName, lastName));
    }

    @Override
    public void change(final ConfirmationUseCase.Listener listener) {
        listener.goToFirstName(firstName);
    }

    @Override
    public void finish(final ConfirmationUseCase.Listener listener) {
        listener.goToFarewell(
                Person.newBuilder()
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .build()
        );
    }
}
