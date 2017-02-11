package es.iridiobis.nonconfiguration;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    final class Initializer {
        private Initializer() {
            //Only static methods, do not allow instances
        }

        public static ApplicationComponent init() {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule())
                    .build();
        }
    }
}