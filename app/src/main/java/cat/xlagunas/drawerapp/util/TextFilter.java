package cat.xlagunas.drawerapp.util;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

import cat.xlagunas.drawerapp.api.model.TeamBasic;

/**
 * Created by xlagunas on 10/03/15.
 */
public class TextFilter extends Filter {

    private List<TeamBasic> originalData;

    public void setOriginalData(List<TeamBasic> originalData) {
        this.originalData = originalData;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        String filterString = constraint.toString().toLowerCase();

        FilterResults results = new FilterResults();


        int count = originalData.size();
        final List<TeamBasic> nlist = new ArrayList<TeamBasic>(count);

        TeamBasic filterableString ;

        for (int i = 0; i < count; i++) {
            filterableString = originalData.get(i);
            if (filterableString.getNom().toLowerCase().contains(filterString)) {
                nlist.add(filterableString);
            }
        }

        results.values = nlist;
        results.count = nlist.size();

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

    }
}