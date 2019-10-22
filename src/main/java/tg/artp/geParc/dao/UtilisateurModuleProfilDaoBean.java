/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Profil;
import tg.artp.geParc.entities.Utilisateur;
import tg.artp.geParc.entities.UtilisateurModuleProfil;

/**
 *
 * @author mandataireTypeOperation-AKIM
 */
@Stateless
public class UtilisateurModuleProfilDaoBean extends BaseDaoBean<UtilisateurModuleProfil, Long> implements UtilisateurModuleProfilDaoBeanLocal {

    public UtilisateurModuleProfilDaoBean() {
        super(UtilisateurModuleProfil.class);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public UtilisateurModuleProfil find(Long idModule, String idUser) {

        try {
            Query query = em.createQuery("SELECT t FROM UtilisateurModuleProfil t WHERE t.module.idModule =:idModule AND t.utilisateur.idPersonne =:idUser");
            query.setParameter("idModule", idModule);
            query.setParameter("idUser", idUser);
            return (UtilisateurModuleProfil) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<Utilisateur> findUserByProfil(Profil p) {

        try {
            Query query = em.createQuery("SELECT DISTINCT t.utilisateur FROM UtilisateurModuleProfil t WHERE t.profil.idProfil =:idProfil");
            query.setParameter("idProfil", p.getIdProfil());
            return (List<Utilisateur>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Modules> listeModuleByUserProfil(Profil profil, Utilisateur user) {
        try {
            Query query = em.createQuery("SELECT t.module FROM UtilisateurModuleProfil t WHERE t.profil.idProfil =:profil AND t.utilisateur.idPersonne =:idUser");
            query.setParameter("profil", profil.getIdProfil());
//            query.setParameter("idUser", user.getIdPersonne());
            return (List<Modules>) query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
