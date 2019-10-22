/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import tg.artp.geParc.entities.Utilisateur;

/**
 *
 * @author Elvis
 */
@Stateless
public class UtilisateurDaoBean extends BaseDaoBean<Utilisateur, Long> implements UtilisateurDaoBeanLocal {

    public UtilisateurDaoBean() {
        super(Utilisateur.class);
    }

    @Override
    public Utilisateur loadUserByLogin(String login) {
        try {
            //System.err.println("*****login****** " + login);
            Utilisateur user;
            Query query = em.createQuery("SELECT t FROM Utilisateur t WHERE t.login =:login");
            query.setParameter("login", login);
            //System.err.println("query " + query);
            user = (Utilisateur) query.getSingleResult();
            //System.err.println("**** USER ****** " + user.getNom());
            return user;
        } catch (NoResultException e) {
            //System.err.println("ERREUR USER ");
            return null;
        }
    }

    @Override
    public List<String> listePermission(Utilisateur user) {
        List<String> perm;
        try {
            Query query = em.createQuery("SELECT u.droit.codeDroit FROM UtilisateurDroit u WHERE u.utilisateur.idUser =:user");
            query.setParameter("user", user.getIdUser());
            perm = (List<String>) query.getResultList();
        } catch (Exception e) {
            perm = new ArrayList<>();
        }
        return perm;
    }

    @Override
    public List<String> listePermissionParProfil(Utilisateur user) {
        List<String> perm;
        try {
            Query query = em.createQuery("SELECT u.droit.codeDroit FROM DroitProfil u WHERE u.profil.idProfil "
                    + "in (select up.profil.idProfil from UtilisateurModuleProfil up where up.utilisateur.idUser=:user)");
            query.setParameter("user", user.getIdUser());
            perm = (List<String>) query.getResultList();
            System.out.println("permission "+perm.size());
        } catch (Exception e) {
            perm = new ArrayList<>();
        }
        return perm;
    }

    @Override
    public List<String> listeRoleShiro(Utilisateur user) {
        List<String> rol;
        try {
            Query query = em.createQuery("SELECT u.profil.libelleProfil FROM UtilisateurModuleProfil u "
                    + "WHERE u.utilisateur.idUser =:user");
            query.setParameter("user", user.getIdUser());
            rol = (List<String>) query.getResultList();
            System.out.println("selection "+rol.size());
        } catch (Exception e) {
            rol = new ArrayList();
        }
        return rol;
    }
}
