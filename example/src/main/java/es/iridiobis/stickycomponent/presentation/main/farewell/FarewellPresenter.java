package es.iridiobis.stickycomponent.presentation.main.farewell;

import javax.inject.Inject;

import es.iridiobis.stickycomponent.core.BasePresenter;
import es.iridiobis.stickycomponent.core.injection.FragmentScope;
import es.iridiobis.stickycomponent.domain.model.Person;

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
