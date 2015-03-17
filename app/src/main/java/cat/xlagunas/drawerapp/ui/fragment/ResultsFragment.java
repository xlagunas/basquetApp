package cat.xlagunas.drawerapp.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import cat.xlagunas.drawerapp.CustomApplication;
import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.api.ApiTest;
import cat.xlagunas.drawerapp.api.model.BasicEntity;
import cat.xlagunas.drawerapp.api.model.Competicion;
import cat.xlagunas.drawerapp.api.model.Resultat;
import cat.xlagunas.drawerapp.ui.adapter.SelectionAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ResultsFragment extends Fragment {

    private static final String COMPETITION_PARAM = "competition";
    private static final String WEEK_PARAM = "week";
    private static final String TAG = ResultsFragment.class.getSimpleName();

    private Competicion mCompetition;
    private RecyclerView mRecycler;
    private SelectionAdapter mAdapter;
    private ProgressBar mProgressBar;
    private int mWeek;

    private ApiTest mApiTest;

    public static ResultsFragment newInstance(Competicion competition, String week) {
        ResultsFragment resultsFragment = null;

        try {
            Integer weekNumber = Integer.parseInt(week);
            resultsFragment = newInstance(competition, weekNumber);
        } catch (NumberFormatException e) {
            Log.e(TAG, "Error: week not valid", e);
        }

        return resultsFragment;
    }

    public static ResultsFragment newInstance(Competicion competition, int week) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putParcelable(COMPETITION_PARAM, competition);
        args.putInt(WEEK_PARAM, week);
        fragment.setArguments(args);
        return fragment;
    }

    public ResultsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomApplication app = (CustomApplication) getActivity().getApplication();
        mApiTest = app.getApiService();

        if (getArguments() != null) {
            mCompetition = getArguments().getParcelable(COMPETITION_PARAM);
            mWeek = getArguments().getInt(WEEK_PARAM);

            mApiTest.getWeekResultsByWeekNumber(mCompetition.getTerritorial(),
                    mCompetition.getCodiCategoria(),
                    mCompetition.getCodiCompeticio(),
                    mCompetition.getNumGrup(), mWeek, new Callback<List<Resultat>>() {
                        @Override
                        public void success(List<Resultat> results, Response response) {
                            List<? extends BasicEntity> catResults = results;
                            mAdapter = new SelectionAdapter((List<BasicEntity>) catResults, new SelectionAdapter.SelectionCallback() {
                                @Override
                                public void onItemSelected(BasicEntity entity) {
                                    Resultat result = (Resultat) entity;
                                    Toast.makeText(getActivity(), result.getTantsL() + " - "+result.getTantsV(), Toast.LENGTH_SHORT).show();
                                }
                            });

                            LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            mRecycler.setLayoutManager(manager);
                            mRecycler.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();

                            mProgressBar.setVisibility(View.GONE);
                            mRecycler.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            mProgressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "Error retrieving request", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_selection, container, false);
        mRecycler = (RecyclerView) view.findViewById(android.R.id.list);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        mRecycler.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
