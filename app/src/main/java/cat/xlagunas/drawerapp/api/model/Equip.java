package cat.xlagunas.drawerapp.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Equip implements Parcelable {

    @SerializedName("id_club")
    @Expose
    private String idClub;
    @Expose
    private String nom;
    @SerializedName("id_equip")
    @Expose
    private String idEquip;
    @Expose
    private Object foto;
    @Expose
    private String categoria;
    @SerializedName("nom_categoria")
    @Expose
    private String nomCategoria;
    @SerializedName("dia_joc")
    @Expose
    private String diaJoc;
    @SerializedName("hora_joc")
    @Expose
    private String horaJoc;
    @SerializedName("color_sam")
    @Expose
    private String colorSam;
    @SerializedName("color_pan")
    @Expose
    private String colorPan;
    @SerializedName("equip_sec")
    @Expose
    private Object equipSec;

    /**
     *
     * @return
     *     The idClub
     */
    public String getIdClub() {
        return idClub;
    }

    /**
     *
     * @param idClub
     *     The id_club
     */
    public void setIdClub(String idClub) {
        this.idClub = idClub;
    }

    /**
     *
     * @return
     *     The nom
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     *     The nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return
     *     The idEquip
     */
    public String getIdEquip() {
        return idEquip;
    }

    /**
     *
     * @param idEquip
     *     The id_equip
     */
    public void setIdEquip(String idEquip) {
        this.idEquip = idEquip;
    }

    /**
     *
     * @return
     *     The foto
     */
    public Object getFoto() {
        return foto;
    }

    /**
     *
     * @param foto
     *     The foto
     */
    public void setFoto(Object foto) {
        this.foto = foto;
    }

    /**
     *
     * @return
     *     The categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     *
     * @param categoria
     *     The categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     *
     * @return
     *     The nomCategoria
     */
    public String getNomCategoria() {
        return nomCategoria;
    }

    /**
     *
     * @param nomCategoria
     *     The nom_categoria
     */
    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    /**
     *
     * @return
     *     The diaJoc
     */
    public String getDiaJoc() {
        return diaJoc;
    }

    /**
     *
     * @param diaJoc
     *     The dia_joc
     */
    public void setDiaJoc(String diaJoc) {
        this.diaJoc = diaJoc;
    }

    /**
     *
     * @return
     *     The horaJoc
     */
    public String getHoraJoc() {
        return horaJoc;
    }

    /**
     *
     * @param horaJoc
     *     The hora_joc
     */
    public void setHoraJoc(String horaJoc) {
        this.horaJoc = horaJoc;
    }

    /**
     *
     * @return
     *     The colorSam
     */
    public String getColorSam() {
        return colorSam;
    }

    /**
     *
     * @param colorSam
     *     The color_sam
     */
    public void setColorSam(String colorSam) {
        this.colorSam = colorSam;
    }

    /**
     *
     * @return
     *     The colorPan
     */
    public String getColorPan() {
        return colorPan;
    }

    /**
     *
     * @param colorPan
     *     The color_pan
     */
    public void setColorPan(String colorPan) {
        this.colorPan = colorPan;
    }

    /**
     *
     * @return
     *     The equipSec
     */
    public Object getEquipSec() {
        return equipSec;
    }

    /**
     *
     * @param equipSec
     *     The equip_sec
     */
    public void setEquipSec(Object equipSec) {
        this.equipSec = equipSec;
    }


    protected Equip(Parcel in) {
        idClub = in.readString();
        nom = in.readString();
        idEquip = in.readString();
        foto = (Object) in.readValue(Object.class.getClassLoader());
        categoria = in.readString();
        nomCategoria = in.readString();
        diaJoc = in.readString();
        horaJoc = in.readString();
        colorSam = in.readString();
        colorPan = in.readString();
        equipSec = (Object) in.readValue(Object.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idClub);
        dest.writeString(nom);
        dest.writeString(idEquip);
        dest.writeValue(foto);
        dest.writeString(categoria);
        dest.writeString(nomCategoria);
        dest.writeString(diaJoc);
        dest.writeString(horaJoc);
        dest.writeString(colorSam);
        dest.writeString(colorPan);
        dest.writeValue(equipSec);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Equip> CREATOR = new Parcelable.Creator<Equip>() {
        @Override
        public Equip createFromParcel(Parcel in) {
            return new Equip(in);
        }

        @Override
        public Equip[] newArray(int size) {
            return new Equip[size];
        }
    };
}