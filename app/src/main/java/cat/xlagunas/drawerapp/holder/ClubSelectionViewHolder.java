package cat.xlagunas.drawerapp.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.api.model.BasicEntity;

/**
 * Created by xlagunas on 14/03/15.
 */
public class ClubSelectionViewHolder extends RecyclerView.ViewHolder{
    public TextView favTitle;

    public ClubSelectionViewHolder(View itemView) {
        super(itemView);
        favTitle = (TextView) itemView.findViewById(R.id.fav_title);
    }

    public static void onBindViewHolder(RecyclerView.ViewHolder holder, BasicEntity entity){
        ((ClubSelectionViewHolder) holder).favTitle.setText(entity.getDisplayName());

    }
}
