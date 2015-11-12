package cat.xlagunas.basquetapp.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamDetails implements Parcelable {

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


    protected TeamDetails(Parcel in) {
        equip = (Equip) in.readValue(Equip.class.getClassLoader());
        camp = (Camp) in.readValue(Camp.class.getClassLoader());
        if (in.readByte() == 0x01) {
            competicions = new ArrayList<Competicion>();
            in.readList(competicions, Competicion.class.getClassLoader());
        } else {
            competicions = null;
        }
        idProximPartit = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(equip);
        dest.writeValue(camp);
        if (competicions == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(competicions);
        }
        dest.writeString(idProximPartit);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TeamDetails> CREATOR = new Parcelable.Creator<TeamDetails>() {
        @Override
        public TeamDetails createFromParcel(Parcel in) {
            return new TeamDetails(in);
        }

        @Override
        public TeamDetails[] newArray(int size) {
            return new TeamDetails[size];
        }
    };
}