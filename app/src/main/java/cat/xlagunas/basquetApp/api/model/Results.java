
package cat.xlagunas.basquetapp.api.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class Results {

    @Expose
    private String jornada;
    @Expose
    private List<Resultat> resultats = new ArrayList<Resultat>();

    /**
     * @return The jornada
     */
    public String getJornada() {
        return jornada;
    }

    /**
     * @param jornada The jornada
     */
    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    /**
     * @return The resultats
     */
    public List<Resultat> getResultats() {
        return resultats;
    }

    /**
     * @param resultats The resultats
     */
    public void setResultats(List<Resultat> resultats) {
        this.resultats = resultats;
    }

}