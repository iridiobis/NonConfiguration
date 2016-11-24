package es.iridiobis.stickycomponent.presentation.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import es.iridiobis.stickycomponent.R;
import es.iridiobis.stickycomponent.core.injection.HasComponent;
import es.iridiobis.stickycomponent.core.injection.main.MainComponent;
import es.iridiobis.stickycomponent.presentation.main.firstname.FirstNameFragment;

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
    public void goToLastNameScreen() {
        //TODO switch fragment to last name fragment
        Toast.makeText(this, "Go to last name", Toast.LENGTH_LONG).show();
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
