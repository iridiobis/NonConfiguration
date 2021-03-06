package es.iridiobis.nonconfiguration.core.injection.main.confirmation;


import dagger.Component;
import es.iridiobis.nonconfiguration.core.injection.FragmentScope;
import es.iridiobis.nonconfiguration.core.injection.HasComponent;
import es.iridiobis.nonconfiguration.core.injection.main.MainComponent;
import es.iridiobis.nonconfiguration.presentation.main.confirmation.ConfirmationFragment;

@FragmentScope
@Component(dependencies = MainComponent.class, modules = ConfirmationModule.class)
public interface ConfirmationComponent {

    void inject(ConfirmationFragment fragment);

    final class Initializer {
        private Initializer() {
            //Only static methods, do not allow instances
        }

        public static ConfirmationComponent init(
                final String fullName,
                final HasComponent<MainComponent> componentOwner) {
            return DaggerConfirmationComponent.builder()
                    .mainComponent(componentOwner.getComponent())
                    .confirmationModule(new ConfirmationModule(fullName))
                    .build();
        }
    }
}
