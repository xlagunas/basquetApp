package cat.xlagunas.drawerapp.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cat.xlagunas.drawerapp.CustomApplication;
import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.api.RestAPI;
import cat.xlagunas.drawerapp.api.model.Favorite;
import cat.xlagunas.drawerapp.api.model.TeamStatistic;
import cat.xlagunas.drawerapp.ui.adapter.ClassificationAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xlagunas on 25/03/15.
 */
public class ClassificationFragment extends Fragment implements Callback<List<TeamStatistic>> {

    private final static String ARG_FAVORITE = "favorite";
    private static final String TAG = ClassificationFragment.class.getSimpleName();

    private Favorite mFavorite;
    private RestAPI mRestAPI;
    private ListView mListView;
    private ClassificationAdapter mAdapter;

    public static ClassificationFragment newInstance(Favorite favorite) {
        ClassificationFragment fragment = new ClassificationFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_FAVORITE, favorite);
        fragment.setArguments(bundle);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomApplication app = (CustomApplication) getActivity().getApplication();
        mRestAPI = app.getApiService();

        if (getArguments() != null) {
            mFavorite = getArguments().getParcelable(ARG_FAVORITE);
            mRestAPI.getClassifications(mFavorite.getRegionId(),
                    mFavorite.getCategoryId(),
                    mFavorite.getCompetitionId(),
                    mFavorite.getGroupId(),
                    mFavorite.getCurrentRounds(), this);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(android.R.layout.list_content,
                container, false);
        mListView = new ListView(inflater.getContext());
        View header = inflater.inflate(R.layout.team_statistics_item, mListView, false);
        initHeader(header);
        mListView.addHeaderView(header);
        mListView.setDivider(null);
        frameLayout.addView(mListView);
        return frameLayout;
    }

    private void initHeader(View header) {
        header.setBackgroundColor(getResources().getColor(R.color.my_awesome_color));
        TextView teamTV = ((TextView) header.findViewById(R.id.team_name));
        teamTV.setText("EQUIP");
        teamTV.setGravity(Gravity.CENTER);
        ((TextView) header.findViewById(R.id.team_wins)).setText("V");
        ((TextView) header.findViewById(R.id.team_losses)).setText("D");
        ((TextView) header.findViewById(R.id.team_percent)).setText("%");
        ((TextView) header.findViewById(R.id.team_rank)).setText("#");
    }

    @Override
    public void success(List<TeamStatistic> teamStatistics, Response response) {
        mAdapter = new ClassificationAdapter(getActivity(), teamStatistics);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetInvalidated();
    }

    @Override
    public void failure(RetrofitError error) {
        Log.e(TAG, "Error retrofit", error);
    }
}
