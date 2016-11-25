package es.iridiobis.stickycomponent.presentation.main.farewell;

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
import es.iridiobis.stickycomponent.core.injection.main.farewell.FarewellComponent;
import es.iridiobis.stickycomponent.domain.model.Person;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FarewellFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FarewellFragment extends Fragment implements Farewell.View {

    private static final String PERSON_EXTRA = "FarewellFragment.PERSON_EXTRA";

    @BindView(R.id.farewell_message)
    TextView messageView;

    @Inject
    Farewell.Presenter presenter;

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @param person result of the flow
     * @return A new instance of fragment FarewellFragment.
     */
    public static FarewellFragment newInstance(final Person person) {
        final FarewellFragment fragment = new FarewellFragment();
        final Bundle extras = new Bundle();
        extras.putParcelable(PERSON_EXTRA, person);
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_farewell, container, false);
        FarewellComponent.Initializer
                .init(
                        getArguments().<Person>getParcelable(PERSON_EXTRA),
                        (HasComponent<MainComponent>) getActivity())
                .inject(this);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showPerson(final Person person) {
        messageView.setText(getString(R.string.farewell_message, person.getFirstName(), person.getLastName()));
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
