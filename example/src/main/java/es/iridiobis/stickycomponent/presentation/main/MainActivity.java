package es.iridiobis.stickycomponent.presentation.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import es.iridiobis.stickycomponent.R;
import es.iridiobis.stickycomponent.core.injection.HasComponent;
import es.iridiobis.stickycomponent.core.injection.main.MainComponent;
import es.iridiobis.stickycomponent.domain.model.Person;
import es.iridiobis.stickycomponent.presentation.main.confirmation.ConfirmationFragment;
import es.iridiobis.stickycomponent.presentation.main.firstname.FirstNameFragment;
import es.iridiobis.stickycomponent.presentation.main.lastname.LastNameFragment;

public class MainActivity
        extends AppCompatActivity
        implements MainNavigatiorExecutor,
        HasComponent<MainComponent> {

    @Inject MainNavigator navigator;
    private MainComponent component;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO use the proper origin
        if (savedInstanceState == null) {
            component = MainComponent.Initializer.init(this);
        } else {
            //TODO recover component
        }
        component.inject(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, FirstNameFragment.newInstance())
                    .addToBackStack(null)
                    .commit();

        }
    }

    @Override
    public void goToFirstNameScreen(final String firstName) {
        getSupportFragmentManager().popBackStack(LastNameFragment.class.getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
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
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToFarewell(final Person person) {
        //TODO switch fragment to farewell fragment
        Toast.makeText(this, person.getFirstName(), Toast.LENGTH_LONG).show();
    }

    @Override
    public MainComponent getComponent() {
        return component;
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
