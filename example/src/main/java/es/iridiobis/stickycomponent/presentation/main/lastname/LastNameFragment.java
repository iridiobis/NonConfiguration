package es.iridiobis.stickycomponent.presentation.main.lastname;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
import es.iridiobis.stickycomponent.core.injection.main.lastname.LastNameComponent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LastNameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LastNameFragment extends Fragment implements LastName.View {

    private static final String FIRST_NAME_EXTRA = "LastNameFragment.FIRST_NAME_EXTRA";

    @BindView(R.id.last_name_message)
    TextView messageView;
    @BindView(R.id.last_name)
    TextInputEditText lastNameView;
    @BindView(R.id.last_name_next)
    View nextButton;

    @Inject
    LastName.Presenter presenter;

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
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_last_name, container, false);
        LastNameComponent.Initializer
                .init(
                        getArguments().getString(FIRST_NAME_EXTRA),
                        (HasComponent<MainComponent>) getActivity())
                .inject(this);
        ButterKnife.bind(this, view);
        lastNameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {

            }

            @Override
            public void onTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {

            }

            @Override
            public void afterTextChanged(final Editable editable) {
                presenter.updateLastName(editable.toString());
            }
        });
        return view;
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
        presenter.confirmLastName();
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
