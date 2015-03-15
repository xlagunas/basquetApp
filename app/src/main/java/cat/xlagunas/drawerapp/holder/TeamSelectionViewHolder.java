package cat.xlagunas.drawerapp.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.api.model.BasicEntity;
import cat.xlagunas.drawerapp.api.model.Team;
import cat.xlagunas.drawerapp.api.model.TeamCategory;

/**
 * Created by xlagunas on 14/03/15.
 */
public class TeamSelectionViewHolder extends RecyclerView.ViewHolder{
    private TextView teamName;
    private TextView codiEquip;
    private TextView codiClub;
    private TextView codiCategoria;
    private TextView nomCategoria;

    public TeamSelectionViewHolder(View itemView) {
        super(itemView);
        teamName = (TextView) itemView.findViewById(R.id.team_name);
        codiEquip = (TextView) itemView.findViewById(R.id.codi_equip);
        codiCategoria = (TextView) itemView.findViewById(R.id.codi_categoria);
        codiClub = (TextView) itemView.findViewById(R.id.codi_club);
        nomCategoria = (TextView) itemView.findViewById(R.id.category_name);
    }

    public static void onBindViewHolder(RecyclerView.ViewHolder holder, BasicEntity entity){
        if (entity instanceof TeamCategory){
            TeamCategory team = (TeamCategory) entity;
            ((TeamSelectionViewHolder) holder).teamName.setText(team.getTeams().get(0).getNom());
            ((TeamSelectionViewHolder) holder).codiEquip.setText(team.getTeams().get(0).getCodiEquip());
            ((TeamSelectionViewHolder) holder).codiCategoria.setText(team.getTeams().get(0).getCodiCategoria());
            ((TeamSelectionViewHolder) holder).codiClub.setText(team.getTeams().get(0).getCodiClub());
            ((TeamSelectionViewHolder) holder).nomCategoria.setText(team.getCategoryName());
        } else {
            Team team = (Team) entity;

            ((TeamSelectionViewHolder) holder).teamName.setText(team.getNom());
            ((TeamSelectionViewHolder) holder).codiEquip.setText(team.getCodiEquip());
            ((TeamSelectionViewHolder) holder).codiCategoria.setText(team.getCodiCategoria());
            ((TeamSelectionViewHolder) holder).codiClub.setText(team.getCodiClub());
            ((TeamSelectionViewHolder) holder).nomCategoria.setText(team.getNomCategoria());
        }

    }
}
