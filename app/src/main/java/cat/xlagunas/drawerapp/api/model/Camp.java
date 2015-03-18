
package cat.xlagunas.drawerapp.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Camp implements Serializable {

    @SerializedName("id_camp")
    @Expose
    private String idCamp;
    @Expose
    private String nom;
    @Expose
    private String adresa;
    @Expose
    private String codipostal;
    @Expose
    private String poblacio;
    @Expose
    private String estat;
    @SerializedName("existeix_canvi")
    @Expose
    private Object existeixCanvi;
    @Expose
    private Object latitud;
    @Expose
    private Object longitud;

    /**
     * 
     * @return
     *     The idCamp
     */
    public String getIdCamp() {
        return idCamp;
    }

    /**
     * 
     * @param idCamp
     *     The id_camp
     */
    public void setIdCamp(String idCamp) {
        this.idCamp = idCamp;
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
     *     The adresa
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * 
     * @param adresa
     *     The adresa
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    /**
     * 
     * @return
     *     The codipostal
     */
    public String getCodipostal() {
        return codipostal;
    }

    /**
     * 
     * @param codipostal
     *     The codipostal
     */
    public void setCodipostal(String codipostal) {
        this.codipostal = codipostal;
    }

    /**
     * 
     * @return
     *     The poblacio
     */
    public String getPoblacio() {
        return poblacio;
    }

    /**
     * 
     * @param poblacio
     *     The poblacio
     */
    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
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
     *     The existeixCanvi
     */
    public Object getExisteixCanvi() {
        return existeixCanvi;
    }

    /**
     * 
     * @param existeixCanvi
     *     The existeix_canvi
     */
    public void setExisteixCanvi(Object existeixCanvi) {
        this.existeixCanvi = existeixCanvi;
    }

    /**
     * 
     * @return
     *     The latitud
     */
    public Object getLatitud() {
        return latitud;
    }

    /**
     * 
     * @param latitud
     *     The latitud
     */
    public void setLatitud(Object latitud) {
        this.latitud = latitud;
    }

    /**
     * 
     * @return
     *     The longitud
     */
    public Object getLongitud() {
        return longitud;
    }

    /**
     * 
     * @param longitud
     *     The longitud
     */
    public void setLongitud(Object longitud) {
        this.longitud = longitud;
    }

}
