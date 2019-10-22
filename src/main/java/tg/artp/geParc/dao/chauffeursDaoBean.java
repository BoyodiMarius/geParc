/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import tg.artp.geParc.entities.chauffeurs;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class chauffeursDaoBean extends BaseDaoBean<chauffeurs, String> implements chauffeursDaoBeanLocal {

    public chauffeursDaoBean() {
        super(chauffeurs.class);
    }

    @Override
    public List<chauffeurs> recupererListePriveDe(chauffeurs c) {
        Query q = this.em.createQuery("SELECT c FROM chauffeurs c WHERE c NOT IN (SELECT c FROM chauffeurs c WHERE c.idChauffeur = :ch)").setParameter("ch", c.getIdChauffeur());
        return q.getResultList();
    }

    @Override
    public List<chauffeurs> recupererListeOrdonne(String statut) {
        Query q = this.em.createQuery("SELECT c FROM chauffeurs c WHERE c.statut = '" + statut + "' ORDER BY c.motantTotalMission ASC");
        return q.getResultList();
    }
}
