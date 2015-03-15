
package cat.xlagunas.drawerapp.api.model;

import java.util.List;

public class Competicions{
   	private String codi_categoria;
   	private String codi_competicio;
   	private String id;
   	private String nom;
   	private String nomCompeticio;
   	private String num_grup;
   	private String temporada;
   	private String territorial;

 	public String getCodi_categoria(){
		return this.codi_categoria;
	}
	public void setCodi_categoria(String codi_categoria){
		this.codi_categoria = codi_categoria;
	}
 	public String getCodi_competicio(){
		return this.codi_competicio;
	}
	public void setCodi_competicio(String codi_competicio){
		this.codi_competicio = codi_competicio;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
 	public String getNomCompeticio(){
		return this.nomCompeticio;
	}
	public void setNomCompeticio(String nomCompeticio){
		this.nomCompeticio = nomCompeticio;
	}
 	public String getNum_grup(){
		return this.num_grup;
	}
	public void setNum_grup(String num_grup){
		this.num_grup = num_grup;
	}
 	public String getTemporada(){
		return this.temporada;
	}
	public void setTemporada(String temporada){
		this.temporada = temporada;
	}
 	public String getTerritorial(){
		return this.territorial;
	}
	public void setTerritorial(String territorial){
		this.territorial = territorial;
	}
}
