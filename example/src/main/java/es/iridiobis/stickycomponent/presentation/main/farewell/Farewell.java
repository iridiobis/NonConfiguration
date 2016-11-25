package es.iridiobis.stickycomponent.presentation.main.farewell;

import es.iridiobis.stickycomponent.domain.model.Person;

public interface Farewell {

    interface Presenter extends es.iridiobis.stickycomponent.core.Presenter<View> {
    }

    interface View {
        void showPerson(Person person);
    }

}
