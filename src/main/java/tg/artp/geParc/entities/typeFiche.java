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
@Table(name = "typeFiche")
public class typeFiche extends BaseEntity {

    @Id
    @NotNull
    @Column(name = "ID_TYPE_FICHE")
    private String idTypeFiche;
    @NotNull
    @Column(name = "LIBELLE_TYPE_FICHE")
    private String libelleTypeFiche;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idTypeFiche")
    private List<fiche> listeFiche = new ArrayList();
    
    public typeFiche(){
        
    }

    public typeFiche(String idTypeFiche, String libelleTypeFiche) {
        this.idTypeFiche = idTypeFiche;
        this.libelleTypeFiche = libelleTypeFiche;
    }

    public String getIdTypeFiche() {
        return idTypeFiche;
    }

    public void setIdTypeFiche(String idTypeFiche) {
        this.idTypeFiche = idTypeFiche;
    }

    public String getLibelleTypeFiche() {
        return libelleTypeFiche;
    }

    public void setLibelleTypeFiche(String libelleTypeFiche) {
        this.libelleTypeFiche = libelleTypeFiche;
    }

    public List<fiche> getListeFiche() {
        return listeFiche;
    }

    public void setListeFiche(List<fiche> listeFiche) {
        this.listeFiche = listeFiche;
    }
    
    
    
    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
