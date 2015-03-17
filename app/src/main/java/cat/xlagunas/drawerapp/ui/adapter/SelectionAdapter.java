package cat.xlagunas.drawerapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.List;

import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.api.model.BasicEntity;
import cat.xlagunas.drawerapp.api.model.Results;
import cat.xlagunas.drawerapp.holder.ClubSelectionViewHolder;
import cat.xlagunas.drawerapp.holder.NoElementsViewHolder;
import cat.xlagunas.drawerapp.holder.ResultSelectionViewHolder;
import cat.xlagunas.drawerapp.holder.TeamSelectionViewHolder;
import cat.xlagunas.drawerapp.util.TextFilter;

/**
 * Created by xlagunas on 09/03/15.
 */
public class SelectionAdapter extends RecyclerView.Adapter implements Filterable {

    public List<BasicEntity> contents;
    public List<BasicEntity> filteredData;
    public SelectionCallback mCallback;

    private final static String TAG = SelectionAdapter.class.getSimpleName();

    public SelectionAdapter() {
        super();
    }

    public SelectionAdapter(List<BasicEntity> contents, SelectionCallback callback){
        this.contents = contents;
        this.mCallback = callback;
        this.filteredData = contents;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;

        //viewType matches the R.layout.* that the holder relates,
        // so that we don't have to check it according to the switch
        View v;

        //The switch is used for instantiating the right ViewHolder
        switch (viewType) {
            case BasicEntity.NO_ENTITY:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.no_favs_item, parent, false);
                holder = new NoElementsViewHolder(v);
                break;
            case BasicEntity.CLUB_ENTITY:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favs_item, parent, false);
                holder = new ClubSelectionViewHolder(v);
                break;

            case BasicEntity.TEAM_ENTITY:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_item, parent, false);
                //TODO Change to the new view holder
                holder = new TeamSelectionViewHolder(v);
                break;
            case BasicEntity.RESULT_ENTITY:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_item, parent, false);
                holder = new ResultSelectionViewHolder(v);

                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NoElementsViewHolder == false) {
            final BasicEntity entity = filteredData.get(position);
            switch (entity.getType()) {
                case BasicEntity.CLUB_ENTITY:
                    ClubSelectionViewHolder.onBindViewHolder(holder, entity);
                    break;
                case BasicEntity.TEAM_ENTITY:
                    TeamSelectionViewHolder.onBindViewHolder(holder, entity);
                    break;
                case BasicEntity.RESULT_ENTITY:
                    ResultSelectionViewHolder.onBindViewHolder(holder, entity);
                    break;
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallback != null) {
                        mCallback.onItemSelected(entity);
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (filteredData == null)
            return BasicEntity.NO_ENTITY;
        return filteredData.get(position).getType();
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
                filteredData = (List<BasicEntity>) results.values;
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

    public static interface SelectionCallback {
        public void onItemSelected(BasicEntity team);
    }
}
