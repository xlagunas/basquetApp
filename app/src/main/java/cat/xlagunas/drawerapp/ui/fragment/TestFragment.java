package cat.xlagunas.drawerapp.ui.fragment;

import android.app.Activity;
import android.content.Context;
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

import com.melnykov.fab.FloatingActionButton;

import cat.xlagunas.drawerapp.CustomApplication;
import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.ui.adapter.SelectionAdapter;

public class TestFragment extends Fragment implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private FloatingActionButton mActionButton;
    private OnFragmentInteractionListener mListener;

    private static final String TAG = TestFragment.class.getSimpleName();
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private View mRootView;
    private int mTotalPages;
    private Context mContext;


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TestFragment newInstance(int sectionNumber) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRootView.findViewById(R.id.fab).setOnClickListener(this);
        mActionButton = (FloatingActionButton) mRootView.findViewById(R.id.fab);
        mActionButton.attachToRecyclerView(mRecyclerView);

        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        animateView();

        mRecyclerView.setAdapter(new SelectionAdapter());

        mRecyclerView.getAdapter().notifyDataSetChanged();

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
        CustomApplication app = (CustomApplication) getActivity().getApplication();
        Log.d(TAG, "Call onActivityCreated");
//            initResultCalls();

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