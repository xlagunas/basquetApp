package cat.xlagunas.drawerapp.ui.fragment;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.sql.SQLException;
import java.util.List;

import cat.xlagunas.drawerapp.CustomApplication;
import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.database.Club;
import cat.xlagunas.drawerapp.database.DatabaseHelper;
import cat.xlagunas.drawerapp.database.EntityConverter;
import cat.xlagunas.drawerapp.ui.activity.Persistable;
import cat.xlagunas.drawerapp.ui.adapter.SelectionAdapter;
import cat.xlagunas.drawerapp.api.ApiTest;
import cat.xlagunas.drawerapp.api.model.BasicEntity;
import cat.xlagunas.drawerapp.api.model.ClubBasic;
import cat.xlagunas.drawerapp.api.model.Team;
import cat.xlagunas.drawerapp.api.model.TeamCategory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class FavoriteSelectionFragment extends Fragment {

    public final static int TYPE_TEAM = 10000;
    public final static int TYPE_CLUB = 10001;
    public final static String TAG = FavoriteSelectionFragment.class.getSimpleName();

    private static final String ARG_TYPE = "type";
    private static final String ARG_CLUB_ID = "club_id";
    private DatabaseHelper mHelper;
    private ApiTest apiTest;

    private int mType;
    private String mTeamId;

    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;
    private SelectionAdapter mAdapter;
    private ProgressBar mProgressBar;

    private LinearLayoutManager mLinearLayout;

    public static FavoriteSelectionFragment findClubs() {
        FavoriteSelectionFragment fragment = new FavoriteSelectionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, TYPE_CLUB);
        fragment.setArguments(args);
        return fragment;
    }

    public static FavoriteSelectionFragment findTeamsByClubId(String teamId){
        FavoriteSelectionFragment fragment = new FavoriteSelectionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, TYPE_TEAM);
        args.putString(ARG_CLUB_ID, teamId);

        fragment.setArguments(args);
        return fragment;
    }

    public FavoriteSelectionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getInt(ARG_TYPE);
            mTeamId = mType == TYPE_TEAM ? getArguments().getString(ARG_CLUB_ID) : null;

        }

        CustomApplication app = (CustomApplication) getActivity().getApplication();
        apiTest = app.getApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorite_selection, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(android.R.id.list);
        mProgressBar = (ProgressBar) v.findViewById(R.id.progress_bar);
        mRecyclerView.setVisibility(View.GONE);
        mLinearLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter == null) {
            mRecyclerView.setLayoutManager(mLinearLayout);
            switch (mType) {
                case TYPE_CLUB:
                    requestClubCall();
                    break;
                case TYPE_TEAM:
                    requestTeamsCall();
                    break;
            }
        }

    }

    private void requestTeamsCall() {
        apiTest
                .getTeamsByClubId(mTeamId)
                .subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Team>>() {
                    @Override
                    public void call(List<Team> teams) {

                        List<? extends BasicEntity> teamList = TeamCategory.obtainTeamCategories(teams);

                        mAdapter = new SelectionAdapter((List<BasicEntity>) teamList, new SelectionAdapter.SelectionCallback() {
                            @Override
                            public void onItemSelected(BasicEntity team) {
                                mListener.onFragmentInteraction((TeamCategory) team);
                            }
                        });
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                        showRecyclerView();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "error", throwable);
                    }
                });

    }

    private void requestClubCall() {
        long totalClubs = getTotalClubs();

        if (totalClubs > 0) {
            Log.i(TAG, "Total clubs: " +totalClubs);
            initClubView();
        } else {

            apiTest
                    .getClubsList()
                    .subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<List<ClubBasic>>() {
                        @Override
                        public void call(List<ClubBasic> teamList) {
                            List<? extends BasicEntity> entities = teamList;
                            persistClubBasicData(teamList);
                            enableRecyclerView(teamList);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.e(TAG, "Error", throwable);
                        }
                    });
        }
    }

    private void initClubView() {
        try {
            List<Club> clubList = mHelper.getClubDao().queryForAll();
            List<? extends BasicEntity> clubBasicList = EntityConverter.convertFromClubListToClubBasicList(clubList);
            enableRecyclerView(clubBasicList);
        } catch (SQLException e) {
            Log.e(TAG, "Error SQL initClubView", e);
        }
    }

    private void enableRecyclerView(List<? extends BasicEntity> entities){
        mAdapter = new SelectionAdapter((List<BasicEntity>) entities, new SelectionAdapter.SelectionCallback() {
            @Override
            public void onItemSelected(BasicEntity entity) {
                Log.d(TAG, "Selected code: " + entity.getKeyValue());
                mListener.onFragmentInteraction((ClubBasic) entity);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        showRecyclerView();
    }
    private void persistClubData(List<Club> clubList) {
        final SQLiteDatabase db = mHelper.getWritableDatabase();
        db.beginTransaction();
        try{
            for (int i=0; i<clubList.size(); i++) {
                mHelper.getClubDao().create(clubList.get(i));
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e(TAG, "Error performing sql bulk insert", e);
        }

        finally {
            db.endTransaction();
        }
    }
    private void persistClubBasicData(List<ClubBasic> entities) {
        List<Club> clubEntities = EntityConverter.convertFromClubBasicListToClubList(entities);
        persistClubData(clubEntities);
    }

    private long getTotalClubs() {
        try {
            return mHelper.getClubDao().countOf();
        } catch (SQLException e) {
            Log.e(TAG, "SQL Exception", e);
            return 0;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
            mHelper = ((Persistable) activity).getHelper();
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_favorite_selection, menu);

        // Get the SearchView and set the searchable configuration
        final SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
//        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null && !query.equals("")){
                    mAdapter.getFilter().filter(query);
                    searchView.clearFocus();
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.equals("") && newText.length() > 3){
                    mAdapter.getFilter().filter(newText);
                    return true;
                }
                return false;
            }

        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mAdapter.resetContent();
                mRecyclerView.getLayoutManager().scrollToPosition(0);
                searchView.clearFocus();
                return true;
            }
        });

        super.onCreateOptionsMenu(menu,inflater);
    }

    private void showRecyclerView() {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

}
