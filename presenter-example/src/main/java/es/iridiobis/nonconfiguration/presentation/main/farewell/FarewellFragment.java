package es.iridiobis.nonconfiguration.presentation.main.farewell;

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
import butterknife.Unbinder;
import es.iridiobis.nonconfiguration.HasNonConfiguration;
import es.iridiobis.nonconfiguration.HasNonConfigurationCache;
import es.iridiobis.nonconfiguration.NonConfigurationManager;
import es.iridiobis.nonconfiguration.R;
import es.iridiobis.nonconfiguration.domain.model.Person;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FarewellFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FarewellFragment extends Fragment implements Farewell.View, HasNonConfiguration<Farewell.Presenter> {

    private static final String PERSON_EXTRA = "FarewellFragment.PERSON_EXTRA";

    @BindView(R.id.farewell_message)
    TextView messageView;

    private NonConfigurationManager<Farewell.Presenter> nonConfigurationManager;

    private Unbinder unbinder;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        nonConfigurationManager = new NonConfigurationManager<>(
                (HasNonConfigurationCache) getActivity(),
                this,
                savedInstanceState);

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_farewell, container, false);
        unbinder = ButterKnife.bind(this, view);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showPerson(final Person person) {
        messageView.setText(getString(R.string.farewell_message, person.getFirstName(), person.getLastName()));
    }

    @NonNull
    @Override
    public Farewell.Presenter createNonConfiguration(@Nullable final Bundle savedInstanceState) {
        return new FarewellPresenter(getArguments().<Person>getParcelable(PERSON_EXTRA));
    }
}
