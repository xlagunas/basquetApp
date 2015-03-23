package cat.xlagunas.drawerapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import cat.xlagunas.drawerapp.CustomApplication;
import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.api.RestAPI;
import cat.xlagunas.drawerapp.api.model.BasicEntity;
import cat.xlagunas.drawerapp.api.model.Competicion;
import cat.xlagunas.drawerapp.api.model.Favorite;
import cat.xlagunas.drawerapp.api.model.Resultat;
import cat.xlagunas.drawerapp.ui.adapter.SelectionAdapter;
import cat.xlagunas.drawerapp.ui.component.CustomRecyclerView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ResultsFragment extends Fragment implements Callback<List<Resultat>>{

    private static final String COMPETITION_PARAM = "competition";
    private static final String FAVORITE_PARAM = "favorite";
    private static final String WEEK_PARAM = "week";
    private static final String TYPE_REQUEST = "type";

    private static final int TYPE_COMPETITION = 7777;
    private static final int TYPE_FAVORITE = 6666;

    private static final String TAG = ResultsFragment.class.getSimpleName();

    private Competicion mCompetition;
    private Favorite mFavorite;

    private CustomRecyclerView mRecycler;
    private LinearLayoutManager mLinearLayoutManager;
    private SelectionAdapter mAdapter;
    private ProgressBar mProgressBar;
    private int mWeek;

    private RestAPI mRestAPI;



    public ResultsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomApplication app = (CustomApplication) getActivity().getApplication();
        mRestAPI = app.getApiService();

        if (getArguments() != null) {
            mWeek = getArguments().getInt(WEEK_PARAM);
            Log.d(TAG, "Entra al onCreate de la setmana: "+mWeek);


            switch (getArguments().getInt(TYPE_REQUEST)){
                case TYPE_COMPETITION:
                    mCompetition = getArguments().getParcelable(COMPETITION_PARAM);

                    mRestAPI.getWeekResultsByWeekNumber(mCompetition.getTerritorial(),
                            mCompetition.getCodiCategoria(),
                            mCompetition.getCodiCompeticio(),
                            mCompetition.getNumGrup(), mWeek, this);
                    break;
                case TYPE_FAVORITE:
                    mFavorite = getArguments().getParcelable(FAVORITE_PARAM);
                    mRestAPI.getWeekResultsByWeekNumber(mFavorite.getRegionId(),
                            mFavorite.getCategoryId(),
                            mFavorite.getCompetitionId(),
                            mFavorite.getGroupId(), mWeek, this);
                    break;
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_selection, container, false);
        mRecycler = (CustomRecyclerView) view.findViewById(android.R.id.list);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        mRecycler.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if ( mAdapter!= null && mAdapter.getItemCount() > 0) {
            mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            mRecycler.setLayoutManager(mLinearLayoutManager);
            mRecycler.setAdapter(mAdapter);
            mProgressBar.setVisibility(View.GONE);
            mRecycler.setVisibility(View.VISIBLE);
        }
    }

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

        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(mLinearLayoutManager);
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

    public static ResultsFragment newInstance(Favorite favorite, int week) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putParcelable(FAVORITE_PARAM, favorite);
        args.putInt(TYPE_REQUEST, TYPE_FAVORITE);
        args.putInt(WEEK_PARAM, week);
        fragment.setArguments(args);
        return fragment;
    }

    public static ResultsFragment newInstance(Competicion competition, int week) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putParcelable(COMPETITION_PARAM, competition);
        args.putInt(WEEK_PARAM, week);
        args.putInt(TYPE_REQUEST, TYPE_COMPETITION);
        fragment.setArguments(args);
        return fragment;
    }

}
