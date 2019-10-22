/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import tg.artp.geParc.entities.Droit;
import tg.artp.geParc.entities.Profil;
import tg.artp.geParc.entities.Utilisateur;
import tg.artp.geParc.entities.UtilisateurDroit;

/**
 *
 * @author SENA
 */
@Stateless
public class UtilisateurDroitDaoBean extends BaseDaoBean<UtilisateurDroit, Long> implements UtilisateurDroitDaoBeanLocal {

    public UtilisateurDroitDaoBean() {
        super(UtilisateurDroit.class);
    }

    
    @Override
     public UtilisateurDroit find(Utilisateur user, Droit droit){
         
          try {
            Query query = em.createQuery("SELECT t FROM UtilisateurDroit t WHERE t.utilisateur.idPersonne =:idUser AND t.droit.idDroit =:idDroit");
            query.setParameter("idDroit", droit.getIdDroit());
//            query.setParameter("idUser", user.getIdPersonne());
            return (UtilisateurDroit) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
     }
     
     @Override
     public void deleteUserDroit(Droit d, Profil p){
        
         Query query = null;
         try {
             
             query = em.createQuery("DELETE FROM UtilisateurDroit t WHERE t.droit.idDroit =:idDroit AND t.utilisateur.idPersonne IN (SELECT u.utilisateur.idPersonne FROM UtilisateurModuleProfil u WHERE u.profil.idProfil =:idProfil)");
             query.setParameter("idDroit", d.getIdDroit());
             query.setParameter("idProfil", p.getIdProfil());
             query.executeUpdate();
             
         } catch (Exception e) {
             e.printStackTrace();
         }
        
     }

    @Override
    public List<UtilisateurDroit> listUserDroit(Utilisateur user, Profil p) {
        
        List<UtilisateurDroit> listeSelected;
         try {
             Query query = em.createQuery("SELECT t FROM UtilisateurDroit t WHERE t.utilisateur.idPersonne =:user AND t.droit.idDroit IN (SELECT u.droit.idDroit FROM DroitProfil u WHERE u.profil.idProfil =:profil)");
//             query.setParameter("user", user.getIdPersonne());
             query.setParameter("profil", p.getIdProfil());
             listeSelected = (List<UtilisateurDroit> )query.getResultList();       
         } catch (Exception e) {
             listeSelected = new ArrayList<UtilisateurDroit>();
             e.printStackTrace();
         }
        
         return listeSelected;
    }
     
     
  
}
