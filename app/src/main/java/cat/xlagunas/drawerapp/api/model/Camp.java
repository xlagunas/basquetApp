
package cat.xlagunas.drawerapp.api.model;

import java.util.List;

public class Camp{
   	private String adresa;
   	private String codipostal;
   	private String estat;
   	private String existeix_canvi;
   	private String id_camp;
   	private String latitud;
   	private String longitud;
   	private String nom;
   	private String poblacio;

 	public String getAdresa(){
		return this.adresa;
	}
	public void setAdresa(String adresa){
		this.adresa = adresa;
	}
 	public String getCodipostal(){
		return this.codipostal;
	}
	public void setCodipostal(String codipostal){
		this.codipostal = codipostal;
	}
 	public String getEstat(){
		return this.estat;
	}
	public void setEstat(String estat){
		this.estat = estat;
	}
 	public String getExisteix_canvi(){
		return this.existeix_canvi;
	}
	public void setExisteix_canvi(String existeix_canvi){
		this.existeix_canvi = existeix_canvi;
	}
 	public String getId_camp(){
		return this.id_camp;
	}
	public void setId_camp(String id_camp){
		this.id_camp = id_camp;
	}
 	public String getLatitud(){
		return this.latitud;
	}
	public void setLatitud(String latitud){
		this.latitud = latitud;
	}
 	public String getLongitud(){
		return this.longitud;
	}
	public void setLongitud(String longitud){
		this.longitud = longitud;
	}
 	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
 	public String getPoblacio(){
		return this.poblacio;
	}
	public void setPoblacio(String poblacio){
		this.poblacio = poblacio;
	}
}
