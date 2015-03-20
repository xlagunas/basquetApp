package cat.xlagunas.drawerapp.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

import com.j256.ormlite.stmt.QueryBuilder;
import com.melnykov.fab.FloatingActionButton;

import java.sql.SQLException;
import java.util.List;

import cat.xlagunas.drawerapp.CustomApplication;
import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.database.Club;
import cat.xlagunas.drawerapp.database.DatabaseHelper;
import cat.xlagunas.drawerapp.database.Favorite;
import cat.xlagunas.drawerapp.database.util.ClubHelper;
import cat.xlagunas.drawerapp.ui.activity.Persistable;
import cat.xlagunas.drawerapp.ui.adapter.SelectionAdapter;

public class TestFragment extends Fragment implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private FloatingActionButton mActionButton;
    private OnFragmentInteractionListener mListener;

    private static final String TAG = TestFragment.class.getSimpleName();

    private View mRootView;
    private int mTotalPages;
    private Context mContext;
    private List<Club> mClubs;
    private List<Favorite> mFavorites;

    private DatabaseHelper mHelper;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TestFragment newInstance() {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public TestFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_favorite_landing, container, false);
        mRecyclerView = (RecyclerView) mRootView.findViewById(android.R.id.list);
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRootView.findViewById(R.id.fab).setOnClickListener(this);
        mActionButton = (FloatingActionButton) mRootView.findViewById(R.id.fab);
        mActionButton.attachToRecyclerView(mRecyclerView);

        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        animateView();

        if (mClubs != null && mClubs.size() > 0) {
            Log.i(TAG, "Favorites found: "+mClubs.size());
        }
        else {
            mRecyclerView.setAdapter(new SelectionAdapter());
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }

    }

    private void getFavoriteList2(){
        try {
            QueryBuilder<Favorite, Integer> mBuilder = mHelper.getFavoriteDao().queryBuilder();
            QueryBuilder<Club, String> mLeftJoin = mHelper.getClubDao().queryBuilder().leftJoin(mBuilder);
            Log.d(TAG, mLeftJoin.prepareStatementString());
            mBuilder.groupBy(Favorite.FAVORITE_CLUB_ID);
            Log.d(TAG, mBuilder.prepareStatementString());
            mBuilder.query();
            mFavorites = mHelper.getFavoriteDao().queryForAll();
        } catch (SQLException e) {
            Log.e(TAG, "Error getting favorites", e);
        }
    }


    public void animateView(){

        TranslateAnimation translation;
        translation = new TranslateAnimation(0f, 0F, -mRootView.getHeight(), 0);
        translation.setStartOffset(500);
        translation.setFillAfter(true);
        translation.setDuration(500);
        mRootView.startAnimation(translation);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "Call onActivityCreated");
        //TODO HE MOGUT AIXO DEL ONRESUME AL ONACTIVITYCREATED
        mClubs = ClubHelper.getFavoritedClubs(mHelper);




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
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.fab:
                mListener.onFragmentInteraction(Uri.parse("favorite://add/club"));
                break;
        }
    }

    public static interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }


    }