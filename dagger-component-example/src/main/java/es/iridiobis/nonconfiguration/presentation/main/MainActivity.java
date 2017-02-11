package es.iridiobis.nonconfiguration.presentation.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import es.iridiobis.nonconfiguration.ActivityWithConfiguration;
import es.iridiobis.nonconfiguration.CoreNonConfigurationManager;
import es.iridiobis.nonconfiguration.R;
import es.iridiobis.nonconfiguration.NonConfigurationCache;
import es.iridiobis.nonconfiguration.core.injection.HasComponent;
import es.iridiobis.nonconfiguration.core.injection.main.MainComponent;
import es.iridiobis.nonconfiguration.domain.model.Person;
import es.iridiobis.nonconfiguration.presentation.main.confirmation.ConfirmationFragment;
import es.iridiobis.nonconfiguration.presentation.main.farewell.FarewellFragment;
import es.iridiobis.nonconfiguration.presentation.main.firstname.FirstNameFragment;
import es.iridiobis.nonconfiguration.presentation.main.lastname.LastNameFragment;

public class MainActivity
        extends AppCompatActivity
        implements MainNavigatiorExecutor,
        ActivityWithConfiguration<MainComponent>,
        HasComponent<MainComponent> {

    @Inject
    MainNavigator navigator;

    private CoreNonConfigurationManager<MainComponent> nonConfigurationManager;

    @Override
    public void goToFirstNameScreen(final String firstName) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void goToLastNameScreen(final String firstName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, LastNameFragment.newInstance(firstName))
                .addToBackStack(LastNameFragment.class.getName())
                .commit();
    }

    @Override
    public void goToConfirmationScreen(final String fullName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, ConfirmationFragment.newInstance(fullName))
                .addToBackStack(ConfirmationFragment.class.getName())
                .commit();
    }

    @Override
    public void goToFarewell(final Person person) {
        getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, FarewellFragment.newInstance(person))
                .commit();
    }

    @Override
    public MainComponent getComponent() {
        return nonConfigurationManager.getNonConfiguration();
    }

    @Override
    public NonConfigurationCache getCache() {
        return nonConfigurationManager.getCache();
    }

    @NonNull
    @Override
    public MainComponent createNonConfiguration(@Nullable final Bundle savedInstanceState) {
        return MainComponent.Initializer.init(this);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        //fragments are recreated in super.oncreate, so we need this done before
        nonConfigurationManager = new CoreNonConfigurationManager<>(this, savedInstanceState);
        nonConfigurationManager.getNonConfiguration().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, FirstNameFragment.newInstance())
                    .commit();

        }
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        nonConfigurationManager.saveState(outState);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return nonConfigurationManager.onRetainCustomNonConfigurationInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigator.attach(this);
    }

    @Override
    protected void onPause() {
        navigator.detach(this);
        super.onPause();
    }

}
