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
import butterknife.Unbinder;
import es.iridiobis.nonconfiguration.HasNonConfiguration;
import es.iridiobis.nonconfiguration.HasNonConfigurationCache;
import es.iridiobis.nonconfiguration.NonConfigurationManager;
import es.iridiobis.nonconfiguration.R;
import es.iridiobis.nonconfiguration.core.injection.HasComponent;
import es.iridiobis.nonconfiguration.core.injection.main.MainComponent;
import es.iridiobis.nonconfiguration.core.injection.main.confirmation.ConfirmationComponent;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmationFragment extends Fragment implements Confirmation.View, HasNonConfiguration<ConfirmationComponent> {

    private static final String FULL_NAME_EXTRA = "ConfirmationFragment.FULL_NAME_EXTRA";

    @BindView(R.id.confirmation_message)
    TextView messageView;

    @Inject
    Confirmation.Presenter presenter;

    private NonConfigurationManager<ConfirmationComponent> nonConfigurationManager;

    private Unbinder unbinder;

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @param fullName full name, to be displayed while requesting confirmation
     * @return A new instance of fragment ConfirmationFragment.
     */
    public static ConfirmationFragment newInstance(final String fullName) {
        final ConfirmationFragment fragment = new ConfirmationFragment();
        final Bundle extras = new Bundle();
        extras.putString(FULL_NAME_EXTRA, fullName);
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
        nonConfigurationManager.getNonConfiguration().inject(this);

        final View view = inflater.inflate(R.layout.fragment_confirmation, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach(this);
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        nonConfigurationManager.saveState(outState);
    }

    @Override
    public void onPause() {
        presenter.detach(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        nonConfigurationManager.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showMessage(final String fullName) {
        messageView.setText(getString(R.string.confirmation_message, fullName));
    }

    @OnClick(R.id.confirmation_change)
    public void change() {
        presenter.change();
    }

    @OnClick(R.id.confirmation_finish)
    public void finish() {
        presenter.finish();
    }

    @NonNull
    @Override
    public ConfirmationComponent createNonConfiguration(@Nullable final Bundle savedInstanceState) {
        //noinspection unchecked
        return ConfirmationComponent.Initializer.init(
                getArguments().getString(FULL_NAME_EXTRA),
                (HasComponent<MainComponent>) getActivity());
    }

}
