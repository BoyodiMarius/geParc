/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Profil;

/**
 *
 * @author mandataireTypeOperation-AKIM
 */
@Stateless
public class ProfilDaoBean extends BaseDaoBean<Profil, Long> implements ProfilDaoBeanLocal {

    public ProfilDaoBean() {
        super(Profil.class);
    }

    @Override
    public Profil find(String nomProfil) {
        List<Profil> found = em.createQuery("SELECT p FROM Profil p "
                + "WHERE p.libelleProfil =:nom")
                .setParameter("nom", nomProfil)
                .getResultList();
        return found.isEmpty() ? null : found.get(0);
    }

    @Override
    public List<Profil> listeProfilParModule(Modules modules) {
        try {
            Query query;
            query = (Query) em.createQuery("SELECT DISTINCT r.profil FROM DroitProfil r where r.droit.module.idModule =:modul");
            query.setParameter("modul", modules.getIdModule());
            return (List<Profil>) query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Profil> listeProfilNonAssigner() {
        List<Profil> lProfil;
        try {
            Query query;
            query = (Query) em.createQuery("SELECT p FROM Profil p WHERE p.idProfil NOT IN (SELECT DISTINCT dp.profil.idProfil FROM DroitProfil dp)");
            lProfil = (List<Profil>) query.getResultList();
        } catch (NoResultException e) {
            lProfil = new ArrayList<Profil>();
        }
        return lProfil;
    }

    @Override
    public List<Profil> listeProfilAssigner() {
        List<Profil> lProfilAssign;
        try {
            Query query;
            query = (Query) em.createQuery("SELECT DISTINCT dp.profil FROM DroitProfil dp");
            lProfilAssign = (List<Profil>) query.getResultList();
        } catch (NoResultException e) {
            lProfilAssign = new ArrayList<Profil>();
        }
        return lProfilAssign;
    }
}
