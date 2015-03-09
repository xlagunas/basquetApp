package cat.xlagunas.drawerapp.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cat.xlagunas.drawerapp.CustomApplication;
import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.adapter.SelectionAdapter;
import cat.xlagunas.drawerapp.api.ApiTest;
import cat.xlagunas.drawerapp.api.model.TeamBasic;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class FavoriteSelectionFragment extends Fragment {

    public final static int TYPE_TEAM = 10000;
    public final static int TYPE_CLUB = 10001;
    public final static String TAG = FavoriteSelectionFragment.class.getSimpleName();

    private static final String ARG_TYPE = "type";
    private ApiTest apiTest;

    private int mType;

    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;
    private SelectionAdapter mAdapter;


    public static FavoriteSelectionFragment newInstance(int param1) {
        FavoriteSelectionFragment fragment = new FavoriteSelectionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, param1);
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
        }
        CustomApplication app = (CustomApplication) getActivity().getApplication();
        apiTest = app.getApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorite_selection, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(android.R.id.list);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter == null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            switch (mType) {
                case TYPE_CLUB:
                    requestClubCall();
                    break;
                case TYPE_TEAM:
                    requestTeamCall();
                    break;
            }
        }

    }

    private void requestTeamCall() {

    }

    private void requestClubCall() {

        apiTest
                .getClubsList()
                .subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<TeamBasic>>() {
                    @Override
                    public void call(List<TeamBasic> teamList) {
                        mAdapter = new SelectionAdapter(teamList, new SelectionAdapter.SelectionCallback() {
                            @Override
                            public void onItemSelected(TeamBasic team) {
                                Log.d(TAG, "Selected code: "+team.getCodi_club());
                            }
                        });
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "Error", throwable);
                    }
                });
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
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

}