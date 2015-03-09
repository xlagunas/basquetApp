package cat.xlagunas.drawerapp.api.model;

/**
 * Created by xlagunas on 09/03/15.
 */
public class TeamBasic {

    private String nom;
    private String codi_club;

    public TeamBasic() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodi_club() {
        return codi_club;
    }

    public void setCodi_club(String codi_club) {
        this.codi_club = codi_club;
    }
}
