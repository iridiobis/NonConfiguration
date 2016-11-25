package es.iridiobis.stickycomponent.core.injection.main.farewell;


import dagger.Component;
import es.iridiobis.stickycomponent.core.injection.FragmentScope;
import es.iridiobis.stickycomponent.core.injection.HasComponent;
import es.iridiobis.stickycomponent.core.injection.main.MainComponent;
import es.iridiobis.stickycomponent.domain.model.Person;
import es.iridiobis.stickycomponent.presentation.main.farewell.FarewellFragment;

@FragmentScope
@Component(dependencies = MainComponent.class, modules = FarewellModule.class)
public interface FarewellComponent {

    void inject(FarewellFragment fragment);

    final class Initializer {
        private Initializer() {
            //Only static methods, do not allow instances
        }

        public static FarewellComponent init(
                final Person person,
                final HasComponent<MainComponent> componentOwner) {
            return DaggerFarewellComponent.builder()
                    .mainComponent(componentOwner.getComponent())
                    .farewellModule(new FarewellModule(person))
                    .build();
        }
    }
}
