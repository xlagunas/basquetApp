package cat.xlagunas.drawerapp.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Competicion implements Parcelable {

    @Expose
    private String id;
    @Expose
    private String nom;
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
    private String territorial;
    @Expose
    private String temporada;
    @Expose
    private String nomCompeticio;

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
     *     The nomCompeticio
     */
    public String getNomCompeticio() {
        return nomCompeticio;
    }

    /**
     *
     * @param nomCompeticio
     *     The nomCompeticio
     */
    public void setNomCompeticio(String nomCompeticio) {
        this.nomCompeticio = nomCompeticio;
    }


    protected Competicion(Parcel in) {
        id = in.readString();
        nom = in.readString();
        codiCategoria = in.readString();
        codiCompeticio = in.readString();
        numGrup = in.readString();
        territorial = in.readString();
        temporada = in.readString();
        nomCompeticio = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nom);
        dest.writeString(codiCategoria);
        dest.writeString(codiCompeticio);
        dest.writeString(numGrup);
        dest.writeString(territorial);
        dest.writeString(temporada);
        dest.writeString(nomCompeticio);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Competicion> CREATOR = new Parcelable.Creator<Competicion>() {
        @Override
        public Competicion createFromParcel(Parcel in) {
            return new Competicion(in);
        }

        @Override
        public Competicion[] newArray(int size) {
            return new Competicion[size];
        }
    };
}