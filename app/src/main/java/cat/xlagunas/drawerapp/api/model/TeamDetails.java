
package cat.xlagunas.drawerapp.api.model;

import java.util.List;

public class TeamDetails{
   	private Camp camp;
   	private List competicions;
   	private Equip equip;
   	private String id_proxim_partit;

 	public Camp getCamp(){
		return this.camp;
	}
	public void setCamp(Camp camp){
		this.camp = camp;
	}
 	public List getCompeticions(){
		return this.competicions;
	}
	public void setCompeticions(List competicions){
		this.competicions = competicions;
	}
 	public Equip getEquip(){
		return this.equip;
	}
	public void setEquip(Equip equip){
		this.equip = equip;
	}
 	public String getId_proxim_partit(){
		return this.id_proxim_partit;
	}
	public void setId_proxim_partit(String id_proxim_partit){
		this.id_proxim_partit = id_proxim_partit;
	}
}
