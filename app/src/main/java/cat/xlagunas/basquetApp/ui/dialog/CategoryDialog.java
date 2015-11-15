package cat.xlagunas.basquetapp.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import cat.xlagunas.basquetapp.CustomApplication;
import cat.xlagunas.basquetapp.R;
import cat.xlagunas.basquetapp.api.model.Team;
import cat.xlagunas.basquetapp.api.model.TeamCategory;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xlagunas on 18/03/15.
 */
public class CategoryDialog extends DialogFragment {

    private ListView mListView;
    private ProgressBar mProgressBar;
    private List<Team> mTeamList;

    private String mClubId;

    private final static String EXTRA_CLUB_ID = "clubId";

    private CategoryDialogCallback mCallback;

    public static CategoryDialog getClubCategories(String clubId) {
        CategoryDialog frag = new CategoryDialog();
        Bundle args = new Bundle();
        args.putString(EXTRA_CLUB_ID, clubId);
        frag.setArguments(args);
        return frag;
    }

    public void setCallback(CategoryDialogCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mClubId = getArguments().getString(EXTRA_CLUB_ID);
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_list, null);
        mListView = (ListView) view.findViewById(R.id.dialog_list);
        mListView.setVisibility(View.GONE);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Selecciona Categoria")
                .setView(view)
                .create();
    }

    @Override
    public void onResume() {
        super.onResume();
        CustomApplication app = (CustomApplication) getActivity().getApplication();
        app.getApiService().getTeamsByClubId(mClubId, new Callback<List<Team>>() {
            @Override
            public void success(final List<Team> teams, Response response) {
                initListView(teams);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(CategoryDialog.class.getSimpleName(), "Error querying rest service");
            }
        });
    }

    private void initListView(final List<Team> teams) {
        final List<TeamCategory> teamCategoryList = TeamCategory.obtainTeamCategories(teams);
        mListView.setAdapter(new ArrayAdapter<TeamCategory>(getActivity(), android.R.layout.simple_list_item_1, teamCategoryList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);
                return view;
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mCallback != null) {
                    mCallback.OnTeamCategorySelected(teamCategoryList.get(position));
                }
                dismiss();
            }
        });
        mListView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    public interface CategoryDialogCallback {
        public void OnTeamCategorySelected(TeamCategory team);
    }
}
