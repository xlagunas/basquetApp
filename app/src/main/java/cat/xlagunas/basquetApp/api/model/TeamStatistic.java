package cat.xlagunas.basquetapp.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamStatistic {

    @Expose
    private String id;
    @Expose
    private String temporada;
    @SerializedName("codi_categoria")
    @Expose
    private String codiCategoria;
    @SerializedName("codi_competicio")
    @Expose
    private String codiCompeticio;
    @SerializedName("nom_categoria")
    @Expose
    private Object nomCategoria;
    @SerializedName("num_grup")
    @Expose
    private String numGrup;
    @Expose
    private String jornada;
    @Expose
    private String territorial;
    @SerializedName("codi_club")
    @Expose
    private String codiClub;
    @SerializedName("codi_equip")
    @Expose
    private String codiEquip;
    @Expose
    private String nom;
    @Expose
    private String pj;
    @Expose
    private String pg;
    @Expose
    private String pp;
    @Expose
    private String np;
    @Expose
    private String tf;
    @Expose
    private String tc;
    @Expose
    private String punts;
    @Expose
    private String posicio;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The temporada
     */
    public String getTemporada() {
        return temporada;
    }

    /**
     *
     * @param temporada
     * The temporada
     */
    public void setTemporada(String temporada) {
        this.temporada = temporada;
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
     * The codiCompeticio
     */
    public String getCodiCompeticio() {
        return codiCompeticio;
    }

    /**
     *
     * @param codiCompeticio
     * The codi_competicio
     */
    public void setCodiCompeticio(String codiCompeticio) {
        this.codiCompeticio = codiCompeticio;
    }

    /**
     *
     * @return
     * The nomCategoria
     */
    public Object getNomCategoria() {
        return nomCategoria;
    }

    /**
     *
     * @param nomCategoria
     * The nom_categoria
     */
    public void setNomCategoria(Object nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    /**
     *
     * @return
     * The numGrup
     */
    public String getNumGrup() {
        return numGrup;
    }

    /**
     *
     * @param numGrup
     * The num_grup
     */
    public void setNumGrup(String numGrup) {
        this.numGrup = numGrup;
    }

    /**
     *
     * @return
     * The jornada
     */
    public String getJornada() {
        return jornada;
    }

    /**
     *
     * @param jornada
     * The jornada
     */
    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    /**
     *
     * @return
     * The territorial
     */
    public String getTerritorial() {
        return territorial;
    }

    /**
     *
     * @param territorial
     * The territorial
     */
    public void setTerritorial(String territorial) {
        this.territorial = territorial;
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
     * The pj
     */
    public String getPj() {
        return pj;
    }

    /**
     *
     * @param pj
     * The pj
     */
    public void setPj(String pj) {
        this.pj = pj;
    }

    /**
     *
     * @return
     * The pg
     */
    public String getPg() {
        return pg;
    }

    /**
     *
     * @param pg
     * The pg
     */
    public void setPg(String pg) {
        this.pg = pg;
    }

    /**
     *
     * @return
     * The pp
     */
    public String getPp() {
        return pp;
    }

    /**
     *
     * @param pp
     * The pp
     */
    public void setPp(String pp) {
        this.pp = pp;
    }

    /**
     *
     * @return
     * The np
     */
    public String getNp() {
        return np;
    }

    /**
     *
     * @param np
     * The np
     */
    public void setNp(String np) {
        this.np = np;
    }

    /**
     *
     * @return
     * The tf
     */
    public String getTf() {
        return tf;
    }

    /**
     *
     * @param tf
     * The tf
     */
    public void setTf(String tf) {
        this.tf = tf;
    }

    /**
     *
     * @return
     * The tc
     */
    public String getTc() {
        return tc;
    }

    /**
     *
     * @param tc
     * The tc
     */
    public void setTc(String tc) {
        this.tc = tc;
    }

    /**
     *
     * @return
     * The punts
     */
    public String getPunts() {
        return punts;
    }

    /**
     *
     * @param punts
     * The punts
     */
    public void setPunts(String punts) {
        this.punts = punts;
    }

    /**
     *
     * @return
     * The posicio
     */
    public String getPosicio() {
        return posicio;
    }

    /**
     *
     * @param posicio
     * The posicio
     */
    public void setPosicio(String posicio) {
        this.posicio = posicio;
    }

}