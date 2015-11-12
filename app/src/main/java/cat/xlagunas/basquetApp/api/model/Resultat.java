
package cat.xlagunas.basquetapp.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resultat implements BasicEntity{

    @Expose
    private String id;
    @Expose
    private String temporada;
    @Expose
    private String territorial;
    @SerializedName("codi_categoria")
    @Expose
    private String codiCategoria;
    @SerializedName("codi_competicio")
    @Expose
    private String codiCompeticio;
    @SerializedName("num_grup")
    @Expose
    private String numGrup;
    @Expose
    private String jornada;
    @SerializedName("estat_partit")
    @Expose
    private Object estatPartit;
    @SerializedName("tants_l")
    @Expose
    private String tantsL;
    @SerializedName("tants_v")
    @Expose
    private String tantsV;
    @SerializedName("data_joc_real")
    @Expose
    private String dataJocReal;
    @SerializedName("hora_joc_real")
    @Expose
    private String horaJocReal;
    @SerializedName("codi_equip_l")
    @Expose
    private String codiEquipL;
    @SerializedName("codi_club_l")
    @Expose
    private String codiClubL;
    @SerializedName("nom_equip_local")
    @Expose
    private String nomEquipLocal;
    @SerializedName("codi_equip_v")
    @Expose
    private String codiEquipV;
    @SerializedName("codi_club_v")
    @Expose
    private String codiClubV;
    @SerializedName("nom_equip_visitant")
    @Expose
    private String nomEquipVisitant;
    @Expose
    private String estat;
    @SerializedName("is_comunicada_AS")
    @Expose
    private Object isComunicadaAS;

    public Resultat() {
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The temporada
     */
    public String getTemporada() {
        return temporada;
    }

    /**
     * 
     * @param temporada
     *     The temporada
     */
    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    /**
     * 
     * @return
     *     The territorial
     */
    public String getTerritorial() {
        return territorial;
    }

    /**
     * 
     * @param territorial
     *     The territorial
     */
    public void setTerritorial(String territorial) {
        this.territorial = territorial;
    }

    /**
     * 
     * @return
     *     The codiCategoria
     */
    public String getCodiCategoria() {
        return codiCategoria;
    }

    /**
     * 
     * @param codiCategoria
     *     The codi_categoria
     */
    public void setCodiCategoria(String codiCategoria) {
        this.codiCategoria = codiCategoria;
    }

    /**
     * 
     * @return
     *     The codiCompeticio
     */
    public String getCodiCompeticio() {
        return codiCompeticio;
    }

    /**
     * 
     * @param codiCompeticio
     *     The codi_competicio
     */
    public void setCodiCompeticio(String codiCompeticio) {
        this.codiCompeticio = codiCompeticio;
    }

    /**
     * 
     * @return
     *     The numGrup
     */
    public String getNumGrup() {
        return numGrup;
    }

    /**
     * 
     * @param numGrup
     *     The num_grup
     */
    public void setNumGrup(String numGrup) {
        this.numGrup = numGrup;
    }

    /**
     * 
     * @return
     *     The jornada
     */
    public String getJornada() {
        return jornada;
    }

    /**
     * 
     * @param jornada
     *     The jornada
     */
    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    /**
     * 
     * @return
     *     The estatPartit
     */
    public Object getEstatPartit() {
        return estatPartit;
    }

    /**
     * 
     * @param estatPartit
     *     The estat_partit
     */
    public void setEstatPartit(Object estatPartit) {
        this.estatPartit = estatPartit;
    }

    /**
     * 
     * @return
     *     The tantsL
     */
    public String getTantsL() {
        return tantsL;
    }

    /**
     * 
     * @param tantsL
     *     The tants_l
     */
    public void setTantsL(String tantsL) {
        this.tantsL = tantsL;
    }

    /**
     * 
     * @return
     *     The tantsV
     */
    public String getTantsV() {
        return tantsV;
    }

    /**
     * 
     * @param tantsV
     *     The tants_v
     */
    public void setTantsV(String tantsV) {
        this.tantsV = tantsV;
    }

    /**
     * 
     * @return
     *     The dataJocReal
     */
    public String getDataJocReal() {
        return dataJocReal;
    }

    /**
     * 
     * @param dataJocReal
     *     The data_joc_real
     */
    public void setDataJocReal(String dataJocReal) {
        this.dataJocReal = dataJocReal;
    }

    /**
     * 
     * @return
     *     The horaJocReal
     */
    public String getHoraJocReal() {
        return horaJocReal;
    }

    /**
     * 
     * @param horaJocReal
     *     The hora_joc_real
     */
    public void setHoraJocReal(String horaJocReal) {
        this.horaJocReal = horaJocReal;
    }

    /**
     * 
     * @return
     *     The codiEquipL
     */
    public String getCodiEquipL() {
        return codiEquipL;
    }

    /**
     * 
     * @param codiEquipL
     *     The codi_equip_l
     */
    public void setCodiEquipL(String codiEquipL) {
        this.codiEquipL = codiEquipL;
    }

    /**
     * 
     * @return
     *     The codiClubL
     */
    public String getCodiClubL() {
        return codiClubL;
    }

    /**
     * 
     * @param codiClubL
     *     The codi_club_l
     */
    public void setCodiClubL(String codiClubL) {
        this.codiClubL = codiClubL;
    }

    /**
     * 
     * @return
     *     The nomEquipLocal
     */
    public String getNomEquipLocal() {
        return nomEquipLocal;
    }

    /**
     * 
     * @param nomEquipLocal
     *     The nom_equip_local
     */
    public void setNomEquipLocal(String nomEquipLocal) {
        this.nomEquipLocal = nomEquipLocal;
    }

    /**
     * 
     * @return
     *     The codiEquipV
     */
    public String getCodiEquipV() {
        return codiEquipV;
    }

    /**
     * 
     * @param codiEquipV
     *     The codi_equip_v
     */
    public void setCodiEquipV(String codiEquipV) {
        this.codiEquipV = codiEquipV;
    }

    /**
     * 
     * @return
     *     The codiClubV
     */
    public String getCodiClubV() {
        return codiClubV;
    }

    /**
     * 
     * @param codiClubV
     *     The codi_club_v
     */
    public void setCodiClubV(String codiClubV) {
        this.codiClubV = codiClubV;
    }

    /**
     * 
     * @return
     *     The nomEquipVisitant
     */
    public String getNomEquipVisitant() {
        return nomEquipVisitant;
    }

    /**
     * 
     * @param nomEquipVisitant
     *     The nom_equip_visitant
     */
    public void setNomEquipVisitant(String nomEquipVisitant) {
        this.nomEquipVisitant = nomEquipVisitant;
    }

    /**
     * 
     * @return
     *     The estat
     */
    public String getEstat() {
        return estat;
    }

    /**
     * 
     * @param estat
     *     The estat
     */
    public void setEstat(String estat) {
        this.estat = estat;
    }

    /**
     * 
     * @return
     *     The isComunicadaAS
     */
    public Object getIsComunicadaAS() {
        return isComunicadaAS;
    }

    /**
     * 
     * @param isComunicadaAS
     *     The is_comunicada_AS
     */
    public void setIsComunicadaAS(Object isComunicadaAS) {
        this.isComunicadaAS = isComunicadaAS;
    }

    @Override
    public String getDisplayName() {
        return "";
    }

    @Override
    public String getKeyValue() {
        return null;
    }

    @Override
    public int getType() {
        return RESULT_ENTITY;
    }
}
