package cat.xlagunas.drawerapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.List;

import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.api.model.TeamBasic;
import cat.xlagunas.drawerapp.util.TextFilter;

/**
 * Created by xlagunas on 09/03/15.
 */
public class SelectionAdapter extends RecyclerView.Adapter implements Filterable{

    public List<TeamBasic> contents;
    public List<TeamBasic> filteredData;
    public SelectionCallback mCallback;

    private final static String TAG = SelectionAdapter.class.getSimpleName();

    public SelectionAdapter() {
        super();
    }

    public SelectionAdapter(List<TeamBasic> contents, SelectionCallback callback){
        this.contents = contents;
        this.mCallback = callback;
        this.filteredData = contents;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View v;
        if (viewType == 1){
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.no_favs_item, parent, false);
            holder = new NoElementsViewHolder(v);
        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.favs_item, parent, false);
            holder = new SelectionViewHolder(v);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NoElementsViewHolder == false) {
            final TeamBasic team = filteredData.get(position);
            ((SelectionViewHolder) holder).favTitle.setText(team.getNom());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallback != null){
                        mCallback.onItemSelected(team);
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (filteredData == null)
            return 1;
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return filteredData != null ? filteredData.size() : 1;
    }

    @Override
    public Filter getFilter() {
        TextFilter filter = new TextFilter(){
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredData = (List<TeamBasic>) results.values;
                notifyDataSetChanged();
            }
        };
        filter.setOriginalData(contents);
        return filter;
    }

    public void resetContent() {
        filteredData.clear();
        filteredData.addAll(contents);
        notifyDataSetChanged();
    }

    public static class SelectionViewHolder extends RecyclerView.ViewHolder{
        TextView favTitle;

        public SelectionViewHolder(View itemView) {
            super(itemView);
            favTitle = (TextView) itemView.findViewById(R.id.fav_title);
        }
    }

    public static class NoElementsViewHolder extends RecyclerView.ViewHolder{

        public NoElementsViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static interface SelectionCallback {
        public void onItemSelected(TeamBasic team);
    }
}
