package cat.xlagunas.drawerapp.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cat.xlagunas.drawerapp.R;
import cat.xlagunas.drawerapp.api.model.BasicEntity;
import cat.xlagunas.drawerapp.api.model.Resultat;

/**
 * Created by xlagunas on 17/03/15.
 */
public class ResultSelectionViewHolder extends RecyclerView.ViewHolder {

    TextView categoryName, matchDate, localTeam, visitorTeam, localResult, visitorResult;

    public ResultSelectionViewHolder(View itemView) {
        super(itemView);
//        categoryName = (TextView) itemView.findViewById(R.id.category_name);
        matchDate = (TextView)itemView.findViewById(R.id.match_date);
        localTeam = (TextView)itemView.findViewById(R.id.local_team);
        visitorTeam = (TextView)itemView.findViewById(R.id.visitor_team);
        localResult = (TextView)itemView.findViewById(R.id.local_result);
        visitorResult = (TextView)itemView.findViewById(R.id.visitor_result);
    }

    public static void onBindViewHolder(RecyclerView.ViewHolder holder, BasicEntity entity){
        ResultSelectionViewHolder vHolder = (ResultSelectionViewHolder) holder;
        Resultat result = (Resultat) entity;
//        vHolder.categoryName.setText("");
        vHolder.matchDate.setText(result.getDataJocReal() + " " + result.getHoraJocReal());
        vHolder.localTeam.setText(result.getNomEquipLocal().toUpperCase());
        vHolder.visitorTeam.setText(result.getNomEquipVisitant().toUpperCase());
        vHolder.localResult.setText(result.getTantsL());
        vHolder.visitorResult.setText(result.getTantsV());

    }
}
