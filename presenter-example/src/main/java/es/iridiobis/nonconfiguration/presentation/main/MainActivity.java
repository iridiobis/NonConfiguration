package es.iridiobis.nonconfiguration.presentation.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import es.iridiobis.nonconfiguration.HasNonConfigurationCache;
import es.iridiobis.nonconfiguration.NonConfigurationCache;
import es.iridiobis.nonconfiguration.R;
import es.iridiobis.nonconfiguration.domain.model.Person;
import es.iridiobis.nonconfiguration.presentation.main.confirmation.ConfirmationFragment;
import es.iridiobis.nonconfiguration.presentation.main.farewell.FarewellFragment;
import es.iridiobis.nonconfiguration.presentation.main.firstname.FirstNameFragment;
import es.iridiobis.nonconfiguration.presentation.main.lastname.LastNameFragment;

public class MainActivity
        extends AppCompatActivity
        implements MainNavigatiorExecutor,
        HasNonConfigurationCache {

    private NonConfigurationCache nonConfigurationCache;

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
    public void goToConfirmationScreen(final String firstName, final String lastName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, ConfirmationFragment.newInstance(firstName, lastName))
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
    public NonConfigurationCache getCache() {
        return nonConfigurationCache;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        //fragments are recreated in super.oncreate, so we need this done before
        nonConfigurationCache = new NonConfigurationCache(getLastCustomNonConfigurationInstance());
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
    public Object onRetainCustomNonConfigurationInstance() {
        return nonConfigurationCache.onRetainCustomNonConfigurationInstance();
    }

}
