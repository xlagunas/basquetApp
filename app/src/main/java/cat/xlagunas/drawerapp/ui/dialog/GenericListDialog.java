package cat.xlagunas.drawerapp.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import cat.xlagunas.drawerapp.R;

/**
 * Created by xlagunas on 18/03/15.
 */
public class GenericListDialog <T> extends DialogFragment {

    private ListView mListView;
    private ProgressBar mProgressBar;
    private List<T> mElementList;

    private String mClubId;
    private String mTitle;

    private final static String EXTRA_CLUB_ID = "clubId";
    private final static String EXTRA_TITLE = "title";

    private OnElementClickedListener mCallback;

    public static GenericListDialog init(String title) {
        GenericListDialog frag = new GenericListDialog();
        Bundle args = new Bundle();
        args.putString(EXTRA_TITLE, title);
        frag.setArguments(args);
        return frag;
    }

    public void setContent(List<T> content) {
        mElementList = content;
    }

    public void OnElementClickedListener(OnElementClickedListener listener) {
        mCallback = listener;
    }


    public void setContent(List content, OnElementClickedListener listener) {
        setContent(content);
        OnElementClickedListener(listener);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mClubId = getArguments().getString(EXTRA_CLUB_ID);
        mTitle = getArguments().getString(EXTRA_TITLE);
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_list, null);
        view.findViewById(R.id.progress_bar).setVisibility(View.INVISIBLE);
        mListView = (ListView) view.findViewById(R.id.dialog_list);

        initListView(mElementList);

        return new AlertDialog.Builder(getActivity())
                .setTitle(mTitle)
                .setView(view)
                .create();
    }

    private void initListView(final List items) {
        mListView.setAdapter(new ArrayAdapter<T>(getActivity(), android.R.layout.simple_list_item_1, items) {
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
                T element = mElementList.get(position);
                mCallback.OnElementClicked(element);
                dismiss();
            }
        });

    }

    public interface OnElementClickedListener <T> {
        public void OnElementClicked(T element);
    }
}
