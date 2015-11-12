package cat.xlagunas.basquetapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cat.xlagunas.basquetapp.R;
import cat.xlagunas.basquetapp.api.model.TeamStatistic;

/**
 * Created by xlagunas on 25/03/15.
 */
public class ClassificationAdapter extends BaseAdapter {

    private List<TeamStatistic> mStatistics;
    private LayoutInflater mInflater;

    public ClassificationAdapter(Context context, List<TeamStatistic> statistics) {
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mStatistics = statistics;
    }

    @Override
    public int getCount() {
        return mStatistics.size();
    }

    @Override
    public TeamStatistic getItem(int position) {
        return mStatistics.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(getItem(position).getId());
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.team_statistics_item, parent, false);
            holder = new ViewHolder();
            holder.teamName = (TextView) convertView.findViewById(R.id.team_name);
            holder.teamWins = (TextView) convertView.findViewById(R.id.team_wins);
            holder.teamLosses = (TextView) convertView.findViewById(R.id.team_losses);
            holder.teamPercent = (TextView) convertView.findViewById(R.id.team_percent);
            holder.teamRank = (TextView) convertView.findViewById(R.id.team_rank);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TeamStatistic stats = getItem(position);
        holder.teamName.setText(stats.getNom());
        holder.teamWins.setText(stats.getPg());
        holder.teamLosses.setText(stats.getPp());
        holder.teamPercent.setText(String.format("%.2f",  (Float.parseFloat(stats.getPg()) / Float.parseFloat(stats.getPj() ) )));
        holder.teamRank.setText(stats.getPosicio());

        return convertView;
    }

    public static class ViewHolder {
        TextView teamName, teamWins, teamLosses, teamPercent, teamRank;
    }
}
