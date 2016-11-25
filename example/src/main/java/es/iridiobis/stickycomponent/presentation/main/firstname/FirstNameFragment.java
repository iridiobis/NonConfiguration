package es.iridiobis.stickycomponent.presentation.main.firstname;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.iridiobis.stickycomponent.R;
import es.iridiobis.stickycomponent.core.injection.HasComponent;
import es.iridiobis.stickycomponent.core.injection.main.firstname.FirstNameComponent;
import es.iridiobis.stickycomponent.core.injection.main.MainComponent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstNameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstNameFragment extends Fragment implements FirstName.View {

    @BindView(R.id.first_name)
    TextInputEditText firstNameView;
    @BindView(R.id.first_name_next)
    View nextButton;

    @Inject
    FirstName.Presenter presenter;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_first_name, container, false);
        FirstNameComponent.Initializer.init((HasComponent<MainComponent>) getActivity()).inject(this);
        ButterKnife.bind(this, view);
        firstNameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {

            }

            @Override
            public void onTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {

            }

            @Override
            public void afterTextChanged(final Editable editable) {
                presenter.updateFirstName(editable.toString());
            }
        });
        return view;
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
        presenter.confirmFirstName();
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
