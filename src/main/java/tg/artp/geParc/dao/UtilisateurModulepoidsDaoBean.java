/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Utilisateur;
import tg.artp.geParc.entities.UtilisateurModulePoids;

/**
 *
 * @author ELVO
 */
@Stateless
public class UtilisateurModulepoidsDaoBean extends BaseDaoBean<UtilisateurModulePoids, Long> implements UtilisateurModulePoidsDaoBeanLocal {

    public UtilisateurModulepoidsDaoBean() {
        super(UtilisateurModulePoids.class);
    }

    @Override
    public List<UtilisateurModulePoids> selectionnerPoidsParUserModule(Modules module, Utilisateur utilisateur) {
        String req = "select u from UtilisateurModulePoids u where u.module = :module and u.utilisateur = :utilisateur ";

        try {
            Query query = this.em.createQuery(req)
                    .setParameter("module", module)
                    .setParameter("utilisateur", utilisateur);

            return (List<UtilisateurModulePoids>) query.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public UtilisateurModulePoids find(Long idModule, String idUser) {
        try {
            Query query = em.createQuery("SELECT t FROM UtilisateurModulePoids t WHERE t.module.idModule =:idModule AND t.utilisateur.idPersonne =:idUser");
            query.setParameter("idModule", idModule);
            query.setParameter("idUser", idUser);
            return (UtilisateurModulePoids) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
