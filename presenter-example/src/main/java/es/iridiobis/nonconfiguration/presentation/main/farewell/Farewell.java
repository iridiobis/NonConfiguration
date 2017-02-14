package es.iridiobis.nonconfiguration.presentation.main.farewell;

import es.iridiobis.nonconfiguration.domain.model.Person;

public interface Farewell {

    interface Presenter extends es.iridiobis.nonconfiguration.core.Presenter<View> {
    }

    interface View {
        void showPerson(Person person);
    }

}
