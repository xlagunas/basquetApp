package cat.xlagunas.basquetapp.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team implements BasicEntity, Parcelable{

    @Expose
    private String nom;
    @SerializedName("codi_equip")
    @Expose
    private String codiEquip;
    @SerializedName("codi_club")
    @Expose
    private String codiClub;
    @SerializedName("codi_categoria")
    @Expose
    private String codiCategoria;
    @SerializedName("nom_categoria")
    @Expose
    private String nomCategoria;

    /**
     *
     * @return
     * The nom
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     * The nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return
     * The codiEquip
     */
    public String getCodiEquip() {
        return codiEquip;
    }

    /**
     *
     * @param codiEquip
     * The codi_equip
     */
    public void setCodiEquip(String codiEquip) {
        this.codiEquip = codiEquip;
    }

    /**
     *
     * @return
     * The codiClub
     */
    public String getCodiClub() {
        return codiClub;
    }

    /**
     *
     * @param codiClub
     * The codi_club
     */
    public void setCodiClub(String codiClub) {
        this.codiClub = codiClub;
    }

    /**
     *
     * @return
     * The codiCategoria
     */
    public String getCodiCategoria() {
        return codiCategoria;
    }

    /**
     *
     * @param codiCategoria
     * The codi_categoria
     */
    public void setCodiCategoria(String codiCategoria) {
        this.codiCategoria = codiCategoria;
    }

    /**
     *
     * @return
     * The nomCategoria
     */
    public String getNomCategoria() {
        return nomCategoria;
    }

    /**
     *
     * @param nomCategoria
     * The nom_categoria
     */
    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    @Override
    public String getDisplayName() {
        return getNomCategoria();
    }

    @Override
    public String getKeyValue() {
        return getCodiCategoria();
    }

    @Override
    public int getType() {
        return BasicEntity.TEAM_ENTITY;
    }

    protected Team(Parcel in) {
        nom = in.readString();
        codiEquip = in.readString();
        codiClub = in.readString();
        codiCategoria = in.readString();
        nomCategoria = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(codiEquip);
        dest.writeString(codiClub);
        dest.writeString(codiCategoria);
        dest.writeString(nomCategoria);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    @Override
    public String toString() {
        return getNomCategoria().toUpperCase();
    }
}