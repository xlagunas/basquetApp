package cat.xlagunas.drawerapp.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team {

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

}