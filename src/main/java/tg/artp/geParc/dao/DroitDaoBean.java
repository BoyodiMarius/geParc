/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import tg.artp.geParc.dao.BaseDaoBean;
import tg.artp.geParc.dao.DroitDaoBeanLocal;
import tg.artp.geParc.entities.Droit;

/**
 *
 * @author SENA
 */
@Stateless
public class DroitDaoBean extends BaseDaoBean<Droit, Long> implements DroitDaoBeanLocal {

    public DroitDaoBean() {
        super(Droit.class);
    }

    @Override
    public List<Droit> listeDroitsSansParent() {
        List<Droit> droitsParents;
        Query query;
        try {
            query = em.createQuery("SELECT d FROM Droit d WHERE d.parent IS NULL");
            droitsParents = (List<Droit>) query.getResultList();
        } catch (Exception e) {
            droitsParents = new ArrayList<Droit>();
        }
        return droitsParents;
    }

    @Override
    public List<Droit> listeDroitsEnfantByParent(Droit droitParent) {
        List<Droit> droitsEnfants;
        Query query;
        try {
            query = em.createQuery("SELECT d FROM Droit d WHERE d.parent.idDroit =:parent");
            query.setParameter("parent", droitParent.getIdDroit());
            droitsEnfants = (List<Droit>) query.getResultList();
        } catch (Exception e) {
            droitsEnfants = new ArrayList<Droit>();
        }
        return droitsEnfants;
    }
    
    @Override
     public List<Droit> listeDroitsEnfant(String parent, String idUser){
         List<Droit> droitsEnfants;
         Query query;
        try {
            query = em.createQuery("SELECT d FROM Droit d WHERE d.codeDroit like :parent and d.idDroit in (select dp.droit.idDroit from DroitProfil dp where dp.profil.idProfil in (select up.profil.idProfil from UtilisateurModuleProfil up where up.utilisateur.idPersonne =:iduser))");
            query.setParameter("parent", parent+"%");
            query.setParameter("iduser", idUser);
            droitsEnfants = (List<Droit>) query.getResultList();
        } catch (Exception e) {
            droitsEnfants = new ArrayList<Droit>();
        }
        return droitsEnfants;
     }

}
