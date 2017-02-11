package es.iridiobis.nonconfiguration.core.injection.main.farewell;


import dagger.Module;
import dagger.Provides;
import es.iridiobis.nonconfiguration.core.injection.FragmentScope;
import es.iridiobis.nonconfiguration.domain.model.Person;
import es.iridiobis.nonconfiguration.presentation.main.farewell.Farewell;
import es.iridiobis.nonconfiguration.presentation.main.farewell.FarewellPresenter;

@Module
public class FarewellModule {

    private final Person person;

    public FarewellModule(final Person person) {
        this.person = person;
    }

    @FragmentScope
    @Provides
    public Person providePerson() {
        return person;
    }

    @FragmentScope
    @Provides
    public Farewell.Presenter providePresenter(final FarewellPresenter presenter) {
        return presenter;
    }

}
