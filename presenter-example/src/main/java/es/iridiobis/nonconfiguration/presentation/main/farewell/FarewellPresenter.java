package es.iridiobis.nonconfiguration.presentation.main.farewell;

import es.iridiobis.nonconfiguration.core.BasePresenter;
import es.iridiobis.nonconfiguration.domain.model.Person;

public class FarewellPresenter
        extends BasePresenter<Farewell.View>
        implements Farewell.Presenter {

    private final Person person;

    public FarewellPresenter(final Person person) {
        this.person = person;
    }

    @Override
    protected void onViewAttached() {
        getView().showPerson(person);
    }

}
