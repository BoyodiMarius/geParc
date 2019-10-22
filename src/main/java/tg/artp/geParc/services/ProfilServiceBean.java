/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.ProfilDaoBeanLocal;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Profil;

/**
 *
 * @author mandataireTypeOperation-AKIM
 */
@Stateless
public class ProfilServiceBean extends BaseServiceBean<Profil, Long> implements ProfilServiceBeanLocal {

    @EJB
    private ProfilDaoBeanLocal dao;

    @Override
    protected BaseDaoBeanLocal<Profil, Long> getDao() {
        return this.dao;
    }

    @Override
    public Profil find(String nomProfil) {
        return this.dao.find(nomProfil);
    }

    @Override
    public List<Profil> listeProfilParModule(Modules modules) {

        return this.dao.listeProfilParModule(modules);
    }

    @Override
    public List<Profil> listeProfilNonAssigner() {
        return this.dao.listeProfilNonAssigner();
    }
    
    @Override
    public List<Profil> listeProfilAssigner() {
        return this.dao.listeProfilAssigner();
    }
    
     @Override
    public String controlSaveProfil(Profil profil, Profil selectProfil) {
        String message = "OK";
        if (selectProfil == null) {
            if (exists(profil.getLibelleProfil(),"libelleProfil")) {
                message = "Ce nom de profil existe déjà , veuillez le changer";
            }
        } else {
//            if(modifDoublon(selectProfil.getIdProfil(), profil.getLibelleProfil(), "idProfil", "libelleProfil")){
//                message = "Ce nom de profil existe déjà , veuillez le changer";
//            }
        }
        return message;
    }
}
