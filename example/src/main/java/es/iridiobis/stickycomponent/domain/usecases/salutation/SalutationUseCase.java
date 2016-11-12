package es.iridiobis.stickycomponent.domain.usecases.salutation;


import es.iridiobis.stickycomponent.core.injection.ActivityScope;
import es.iridiobis.stickycomponent.domain.model.Person;

@ActivityScope
public class SalutationUseCase implements FirstName.UseCase, LastName.UseCase, Salutation.UseCase {

    private String firstName;
    private String lastName;

    @Override
    public void giveFirstName(final String firstName, final FirstName.Listener listener) {
        this.firstName = firstName;
        listener.askForLastName(firstName);
    }

    @Override
    public void giveLastName(final String lastName, final LastName.Listener listener) {
        this.lastName = lastName;
        listener.showSalutation(String.format("%s %s", firstName, lastName));
    }

    @Override
    public void finish(final Salutation.Listener listener) {
        listener.goToFarewell(
                Person.newBuilder()
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .build()
        );
    }
}
