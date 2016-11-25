package es.iridiobis.stickycomponent.presentation.main.confirmation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.iridiobis.stickycomponent.R;
import es.iridiobis.stickycomponent.core.injection.HasComponent;
import es.iridiobis.stickycomponent.core.injection.main.MainComponent;
import es.iridiobis.stickycomponent.core.injection.main.confirmation.ConfirmationComponent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmationFragment extends Fragment implements Confirmation.View {

    private static final String FULL_NAME_EXTRA = "ConfirmationFragment.FULL_NAME_EXTRA";

    @BindView(R.id.confirmation_message)
    TextView messageView;

    @Inject
    Confirmation.Presenter presenter;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_confirmation, container, false);
        ConfirmationComponent.Initializer
                .init(
                        getArguments().getString(FULL_NAME_EXTRA),
                        (HasComponent<MainComponent>) getActivity())
                .inject(this);
        ButterKnife.bind(this, view);
        return view;
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

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach(this);
    }

    @Override
    public void onPause() {
        presenter.detach(this);
        super.onPause();
    }
}
