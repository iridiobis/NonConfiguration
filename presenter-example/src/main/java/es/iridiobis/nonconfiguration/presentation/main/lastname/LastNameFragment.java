package es.iridiobis.nonconfiguration.presentation.main.lastname;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.iridiobis.nonconfiguration.HasNonConfiguration;
import es.iridiobis.nonconfiguration.HasNonConfigurationCache;
import es.iridiobis.nonconfiguration.NonConfigurationManager;
import es.iridiobis.nonconfiguration.R;
import es.iridiobis.nonconfiguration.presentation.listeners.TextChangedListener;
import es.iridiobis.nonconfiguration.presentation.main.HasNavigator;
import es.iridiobis.nonconfiguration.presentation.main.MainNavigatiorExecutor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LastNameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LastNameFragment extends Fragment implements LastName.View, HasNonConfiguration<LastName.Presenter> {

    private static final String FIRST_NAME_EXTRA = "LastNameFragment.FIRST_NAME_EXTRA";

    @BindView(R.id.last_name_message)
    TextView messageView;
    @BindView(R.id.last_name)
    TextInputEditText lastNameView;
    @BindView(R.id.last_name_next)
    View nextButton;

    private NonConfigurationManager<LastName.Presenter> nonConfigurationManager;

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @param lastName last name, to be displayed while requesting the last name
     * @return A new instance of fragment LastNameFragment.
     */
    public static LastNameFragment newInstance(final String lastName) {
        final LastNameFragment fragment = new LastNameFragment();
        final Bundle extras = new Bundle();
        extras.putString(FIRST_NAME_EXTRA, lastName);
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        nonConfigurationManager = new NonConfigurationManager<>(
                (HasNonConfigurationCache) getActivity(),
                this,
                savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_last_name, container, false);
        ButterKnife.bind(this, view);
        lastNameView.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(@NonNull final String newText) {
                nonConfigurationManager.getNonConfiguration().updateLastName(newText);
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
        nonConfigurationManager.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showMessage(final String firstName) {
        messageView.setText(getString(R.string.last_name_message, firstName));
    }

    @Override
    public void showLastName(final String lastName) {
        lastNameView.setText(lastName);
    }

    @Override
    public void enableNextButton(final boolean enabled) {
        nextButton.setEnabled(enabled);
    }

    @OnClick(R.id.last_name_next)
    public void goToNext() {
        nonConfigurationManager.getNonConfiguration().confirmLastName();
    }

    @NonNull
    @Override
    public LastName.Presenter createNonConfiguration(@Nullable final Bundle savedInstanceState) {
        return new LastNamePresenter(getArguments().getString(FIRST_NAME_EXTRA));
    }

    @Override
    public void goToConfirmationScreen(final String firstName, final String lastName) {
        ((MainNavigatiorExecutor)getActivity()).goToConfirmationScreen(firstName, lastName);
    }
}
