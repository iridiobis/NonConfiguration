package es.iridiobis.nonconfiguration.presentation.main.farewell;

import javax.inject.Inject;

import es.iridiobis.nonconfiguration.core.BasePresenter;
import es.iridiobis.nonconfiguration.core.injection.FragmentScope;
import es.iridiobis.nonconfiguration.domain.model.Person;

@FragmentScope
public class FarewellPresenter
        extends BasePresenter<Farewell.View>
        implements Farewell.Presenter {

    private final Person person;

    @Inject
    public FarewellPresenter(final Person person) {
        this.person = person;
    }

    @Override
    protected void onViewAttached() {
        getView().showPerson(person);
    }

}
