package es.iridiobis.stickycomponent.presentation.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.iridiobis.stickycomponent.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainComponent.Initializer.init(this).inject(this);
    }
}
