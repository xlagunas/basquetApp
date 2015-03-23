package cat.xlagunas.drawerapp.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.api.model.Competicion;
import cat.xlagunas.drawerapp.api.model.TeamCategory;
import cat.xlagunas.drawerapp.api.model.TeamDetails;
import cat.xlagunas.drawerapp.service.ApiService;
import cat.xlagunas.drawerapp.ui.activity.CompetitionActivity;
import cat.xlagunas.drawerapp.util.event.TeamDetailsEvent;

public class TeamSelectionFragment extends BusFragment {

    private static final String ARG_CATEGORY = "category";
    private TeamCategory mTeamCategory;
    private ViewGroup mRootViewGroup;

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
        ApiService.getTeams(getActivity(), mTeamCategory);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_team_selection, container, false);
        mRootViewGroup = (ViewGroup) view.findViewById(R.id.main_layout);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mRootViewGroup.addView(new ProgressBar(getActivity()), params);

        return view;

    }

    // Called in Android UI's main thread
    public void onEventMainThread(TeamDetailsEvent event) {
        ViewGroup viewGroup = mRootViewGroup;

        if (event != null && event.getmTeamDetails() != null && event.getmTeamDetails().size() > 0){
            viewGroup.removeAllViewsInLayout();
            LayoutInflater mInflater = getActivity().getLayoutInflater();

            for (TeamDetails teamDetails :event.getmTeamDetails()) {
                View v = mInflater.inflate(R.layout.team_selection_item, viewGroup, false);
                TextView titleView = ((TextView) v.findViewById(R.id.team_name));
                titleView.setText(teamDetails.getEquip().getNom());

                fillTeamDetailsView(v, teamDetails);


                for (Competicion competicion : teamDetails.getCompeticions()) {
                    ViewGroup container = (ViewGroup) v.findViewById(R.id.category_name_container);
                    View competitionView = mInflater.inflate(R.layout.team_competition_item, container, false);
                    fillCompetitionView(competitionView, competicion, teamDetails);
                    container.addView(competitionView);
                }

                viewGroup.addView(v);

            }

            viewGroup.requestLayout();

        }

    }

    private void fillTeamDetailsView(View view, final TeamDetails details) {
        TextView dayTextView = (TextView) view.findViewById(R.id.match_day);
        TextView dateTextView = (TextView) view.findViewById(R.id.match_hour);

        dayTextView.setText(details.getEquip().getDiaJoc());
        dateTextView.setText(details.getEquip().getHoraJoc());
    }

    private void fillCompetitionView(View view, final Competicion competicio, final TeamDetails teamDetails) {
        Button button = (Button) view.findViewById(R.id.category_selection_btn);
        button.setText(competicio.getNomCompeticio());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService.getCompetition(getActivity(), competicio, teamDetails);

//                startActivity(CompetitionActivity.makeIntent(getActivity(), competicio));
                getActivity().finish();
            }
        });
    }

}
