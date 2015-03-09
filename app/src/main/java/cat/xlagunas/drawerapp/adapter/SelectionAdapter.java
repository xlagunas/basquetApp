package cat.xlagunas.drawerapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.api.model.TeamBasic;

/**
 * Created by xlagunas on 09/03/15.
 */
public class SelectionAdapter extends RecyclerView.Adapter {

    public List<TeamBasic> contents;

    public SelectionAdapter() {
        super();
    }

    public SelectionAdapter(List<TeamBasic> contents){
        this.contents = contents;
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
            ((SelectionViewHolder) holder).favTitle.setText(contents.get(position).getNom());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (contents == null)
            return 1;
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return contents != null ? contents.size() : 1;
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
}
