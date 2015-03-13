package cat.xlagunas.drawerapp.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.api.model.Results;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link ResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultsFragment extends Fragment {

    private static final String COMPETITION_PARAM = "competition";
    private static final String WEEK_PARAM = "week";

    private String mCompetition;
    private String mWeek;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param competition Parameter 1.
     * @param week Parameter 2.
     * @return A new instance of fragment ResultsFragment.
     */
    public static ResultsFragment newInstance(String competition, String week) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putString(COMPETITION_PARAM, competition);
        args.putString(WEEK_PARAM, week);
        fragment.setArguments(args);
        return fragment;
    }

    public ResultsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCompetition = getArguments().getString(COMPETITION_PARAM);
            mWeek = getArguments().getString(WEEK_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false);
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
