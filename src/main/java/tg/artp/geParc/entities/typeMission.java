/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "typeMission")
public class typeMission extends BaseEntity {

    @Id
    @NotNull
    @Column(name = "ID_TYPE_MISSION")
    private String idTypeMission;
    @NotNull
    @Column(name = "LIBELLE_TYPE_MISSION")
    private String libelleTypeMission;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idTypeMission")
    private List<missions> listeMission = new ArrayList();
    
    public typeMission(){
        
    }

    public typeMission(String idTypeMission, String libelleTypeMission) {
        this.idTypeMission = idTypeMission;
        this.libelleTypeMission = libelleTypeMission;
    }

    public String getIdTypeMission() {
        return idTypeMission;
    }

    public void setIdTypeMission(String idTypeMission) {
        this.idTypeMission = idTypeMission;
    }

    public String getLibelleTypeMission() {
        return libelleTypeMission;
    }

    public void setLibelleTypeMission(String libelleTypeMission) {
        this.libelleTypeMission = libelleTypeMission;
    }

    public List<missions> getListeMission() {
        return listeMission;
    }

    public void setListeMission(List<missions> listeMission) {
        this.listeMission = listeMission;
    }
    
    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
