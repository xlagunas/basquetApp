package cat.xlagunas.drawerapp.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;


import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.api.model.TeamCategory;
import cat.xlagunas.drawerapp.service.TeamService;
import cat.xlagunas.drawerapp.util.event.TeamDetailsEvent;

public class TeamSelectionFragment extends BusFragment {

    private static final String ARG_CATEGORY = "category";
    private TeamCategory mTeamCategory;

    public static TeamSelectionFragment newInstance(TeamCategory teamCategory) {
        TeamSelectionFragment fragment = new TeamSelectionFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CATEGORY, teamCategory);
        fragment.setArguments(args);
        return fragment;
    }

    public TeamSelectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTeamCategory = getArguments().getParcelable(ARG_CATEGORY);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TeamService.getTeams(getActivity(), mTeamCategory);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =   inflater.inflate(R.layout.fragment_team_selection, container, false);

        ProgressBar progressBar = new ProgressBar(getActivity());
        container.addView(progressBar);
        return view;

    }

    // Called in Android UI's main thread
    public void onEventMainThread(TeamDetailsEvent event) {
        Toast.makeText(getActivity(), "Event is propagated from the Service and received", Toast.LENGTH_SHORT).show();
    }

}
