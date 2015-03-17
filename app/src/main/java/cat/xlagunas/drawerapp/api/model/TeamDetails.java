
package cat.xlagunas.drawerapp.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamDetails implements Serializable{

    @Expose
    private Equip equip;
    @Expose
    private Camp camp;
    @Expose
    private List<Competicion> competicions = new ArrayList<Competicion>();
    @SerializedName("id_proxim_partit")
    @Expose
    private String idProximPartit;

    /**
     * 
     * @return
     *     The equip
     */
    public Equip getEquip() {
        return equip;
    }

    /**
     * 
     * @param equip
     *     The equip
     */
    public void setEquip(Equip equip) {
        this.equip = equip;
    }

    /**
     * 
     * @return
     *     The camp
     */
    public Camp getCamp() {
        return camp;
    }

    /**
     * 
     * @param camp
     *     The camp
     */
    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    /**
     * 
     * @return
     *     The competicions
     */
    public List<Competicion> getCompeticions() {
        return competicions;
    }

    /**
     * 
     * @param competicions
     *     The competicions
     */
    public void setCompeticions(List<Competicion> competicions) {
        this.competicions = competicions;
    }

    /**
     * 
     * @return
     *     The idProximPartit
     */
    public String getIdProximPartit() {
        return idProximPartit;
    }

    /**
     * 
     * @param idProximPartit
     *     The id_proxim_partit
     */
    public void setIdProximPartit(String idProximPartit) {
        this.idProximPartit = idProximPartit;
    }

}
