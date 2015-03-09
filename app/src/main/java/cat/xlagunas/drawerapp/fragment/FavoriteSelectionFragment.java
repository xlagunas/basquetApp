package cat.xlagunas.drawerapp.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.xlagunas.drawerapp.R;


public class FavoriteSelectionFragment extends Fragment {

    public final static int TYPE_TEAM = 10000;
    public final static int TYPE_CLUB = 10001;
    public final static String TAG = FavoriteSelectionFragment.class.getSimpleName();

    private static final String ARG_TYPE = "type";

    private int mParam1;

    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;


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
            mParam1 = getArguments().getInt(ARG_TYPE);
        }
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));

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
