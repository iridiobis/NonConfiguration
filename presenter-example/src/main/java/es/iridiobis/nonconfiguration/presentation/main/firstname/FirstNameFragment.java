package es.iridiobis.nonconfiguration.presentation.main.firstname;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.iridiobis.nonconfiguration.HasNonConfiguration;
import es.iridiobis.nonconfiguration.HasNonConfigurationCache;
import es.iridiobis.nonconfiguration.NonConfigurationManager;
import es.iridiobis.nonconfiguration.presentation.listeners.TextChangedListener;
import es.iridiobis.nonconfiguration.presentation.main.HasNavigator;
import es.iridiobis.nonconfiguration.R;
import es.iridiobis.nonconfiguration.presentation.main.MainNavigatiorExecutor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstNameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstNameFragment extends Fragment implements FirstName.View, HasNonConfiguration<FirstName.Presenter> {

    @BindView(R.id.first_name)
    TextInputEditText firstNameView;
    @BindView(R.id.first_name_next)
    View nextButton;

    private NonConfigurationManager<FirstName.Presenter> nonConfigurationManager;

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment FirstNameFragment.
     */
    public static FirstNameFragment newInstance() {
        return new FirstNameFragment();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        nonConfigurationManager = new NonConfigurationManager<>(
                (HasNonConfigurationCache) getActivity(),
                this,
                savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_first_name, container, false);
        ButterKnife.bind(this, view);
        firstNameView.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(@NonNull final String newText) {
                nonConfigurationManager.getNonConfiguration().updateFirstName(newText);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        nonConfigurationManager.getNonConfiguration().attach(this);
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        nonConfigurationManager.saveState(outState);
    }

    @Override
    public void onPause() {
        nonConfigurationManager.getNonConfiguration().detach(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        nonConfigurationManager.onDestroy();
    }

    @Override
    public void showFirstName(final String firstName) {
        firstNameView.setText(firstName);
    }

    @Override
    public void enableNextButton(final boolean enabled) {
        nextButton.setEnabled(enabled);
    }

    @OnClick(R.id.first_name_next)
    public void goToNext() {
        nonConfigurationManager.getNonConfiguration().confirmFirstName();
    }

    @Override
    public @NonNull FirstName.Presenter createNonConfiguration(@Nullable final Bundle savedInstanceState) {
        return new FirstNamePresenter();
    }

    @Override
    public void goToLastNameScreen(final String firstName) {
        ((MainNavigatiorExecutor)getActivity()).goToLastNameScreen(firstName);
    }
}
