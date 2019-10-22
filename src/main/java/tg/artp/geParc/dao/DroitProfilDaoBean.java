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
import tg.artp.geParc.entities.Droit;
import tg.artp.geParc.entities.DroitProfil;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Profil;

/**
 *
 * @author SENA
 */
@Stateless
public class DroitProfilDaoBean extends BaseDaoBean<DroitProfil, Long> implements DroitProfilDaoBeanLocal {

    public DroitProfilDaoBean() {
        super(DroitProfil.class);
    }

    @Override
    public List<Droit> listDroits(Modules module, Profil profil) {

        List<Droit> listDroit = new ArrayList<>();
        try {
            Query query;
            query = (Query) em.createQuery("SELECT d.droit FROM DroitProfil d WHERE d.droit.module.idModule =:module AND d.profil.idProfil =:profil");
            query.setParameter("module", module.getIdModule());
            query.setParameter("profil", profil.getIdProfil());
            listDroit = (List<Droit>) query.getResultList();
            return listDroit;
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Droit> listDroits(Profil profil) {

        List<Droit> listDroit;
        try {
            Query query;
            query = (Query) em.createQuery("SELECT d.droit FROM DroitProfil d WHERE d.profil.idProfil =:profil");
            query.setParameter("profil", profil.getIdProfil());
            listDroit = (List<Droit>) query.getResultList();

        } catch (NoResultException e) {
            listDroit = new ArrayList<>();
        }
        return listDroit;
    }

    @Override
    public DroitProfil find(Profil profil, Droit droit) {

        try {
            Query query;
            query = (Query) em.createQuery("SELECT d FROM DroitProfil d WHERE d.profil.idProfil =:profil AND d.droit.idDroit =:droit");
            query.setParameter("profil", profil.getIdProfil());
            query.setParameter("droit", droit.getIdDroit());
            return (DroitProfil) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteDroitProfil(Droit d, Profil p) {

        Query query;
        try {
            query = (Query) em.createQuery("DELETE FROM DroitProfil d WHERE d.profil.idProfil =:idProfil AND d.droit.idDroit =:idDroit");
            query.setParameter("idProfil", p.getIdProfil());
            query.setParameter("idDroit", d.getIdDroit());
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Profil> listProfilToUse() {
        List<Profil> listProf ;
        try {
            Query query;
            query = (Query) em.createQuery("SELECT DISTINCT d.profil FROM DroitProfil d ");
            listProf = (List<Profil>) query.getResultList();
            return listProf;
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }
}
