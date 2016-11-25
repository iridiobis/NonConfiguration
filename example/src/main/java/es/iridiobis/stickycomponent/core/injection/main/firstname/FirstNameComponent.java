package es.iridiobis.stickycomponent.core.injection.main.firstname;


import dagger.Component;
import es.iridiobis.stickycomponent.core.injection.FragmentScope;
import es.iridiobis.stickycomponent.core.injection.HasComponent;
import es.iridiobis.stickycomponent.core.injection.main.MainComponent;
import es.iridiobis.stickycomponent.presentation.main.firstname.FirstNameFragment;

@FragmentScope
@Component(dependencies = MainComponent.class, modules = FirstNameModule.class)
public interface FirstNameComponent {

    void inject(FirstNameFragment fragment);

    final class Initializer {
        private Initializer() {
            //Only static methods, do not allow instances
        }

        public static FirstNameComponent init(final HasComponent<MainComponent> componentOwner) {
            return DaggerFirstNameComponent.builder()
                    .mainComponent(componentOwner.getComponent())
                    .firstNameModule(new FirstNameModule())
                    .build();
        }
    }
}
