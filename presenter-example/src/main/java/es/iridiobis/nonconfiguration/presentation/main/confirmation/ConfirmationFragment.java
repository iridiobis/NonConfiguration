package es.iridiobis.nonconfiguration.presentation.main.confirmation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import es.iridiobis.nonconfiguration.domain.model.Person;
import es.iridiobis.nonconfiguration.presentation.main.HasNavigator;
import es.iridiobis.nonconfiguration.presentation.main.MainNavigatiorExecutor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmationFragment extends Fragment implements Confirmation.View, HasNonConfiguration<Confirmation.Presenter> {

    private static final String FIRST_NAME_EXTRA = "ConfirmationFragment.FIRST_NAME_EXTRA";
    private static final String LAST_NAME_EXTRA = "ConfirmationFragment.LAST_NAME_EXTRA";

    @BindView(R.id.confirmation_message)
    TextView messageView;

    private NonConfigurationManager<Confirmation.Presenter> nonConfigurationManager;

    public static ConfirmationFragment newInstance(final String firstName, final String lastName) {
        final ConfirmationFragment fragment = new ConfirmationFragment();
        final Bundle extras = new Bundle();
        extras.putString(FIRST_NAME_EXTRA, firstName);
        extras.putString(LAST_NAME_EXTRA, lastName);
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState) {

        nonConfigurationManager = new NonConfigurationManager<>(
                (HasNonConfigurationCache) getActivity(),
                this,
                savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_confirmation, container, false);
        ButterKnife.bind(this, view);
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
    public void showMessage(final String fullName) {
        messageView.setText(getString(R.string.confirmation_message, fullName));
    }

    @OnClick(R.id.confirmation_change)
    public void change() {
        nonConfigurationManager.getNonConfiguration().change();
    }

    @OnClick(R.id.confirmation_finish)
    public void finish() {
        nonConfigurationManager.getNonConfiguration().finish();
    }

    @NonNull
    @Override
    public Confirmation.Presenter createNonConfiguration(@Nullable final Bundle savedInstanceState) {
        return new ConfirmationPresenter(
                getArguments().getString(FIRST_NAME_EXTRA),
                getArguments().getString(LAST_NAME_EXTRA));
    }

    @Override
    public void goToFarewellScreen(final Person person) {
        ((MainNavigatiorExecutor)getActivity()).goToFarewell(person);
    }

    @Override
    public void goToFirstNameScreen(final String firstName) {
        ((MainNavigatiorExecutor)getActivity()).goToFirstNameScreen(firstName);
    }
}
