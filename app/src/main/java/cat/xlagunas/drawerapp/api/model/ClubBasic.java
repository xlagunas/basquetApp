package cat.xlagunas.drawerapp.api.model;

/**
 * Created by xlagunas on 09/03/15.
 */
public class ClubBasic implements BasicEntity {

    private String nom;
    private String codi_club;

    public ClubBasic() {
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

    @Override
    public String getDisplayName() {
        return nom;
    }

    @Override
    public String getKeyValue() {
        return codi_club;
    }

    @Override
    public int getType() {
        return BasicEntity.CLUB_ENTITY;
    }
}
