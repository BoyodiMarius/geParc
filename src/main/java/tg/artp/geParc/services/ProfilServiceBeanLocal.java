/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import java.util.List;
import javax.ejb.Local;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Profil;

/**
 *
 * @author mandataireTypeOperation-AKIM
 */
@Local
public interface ProfilServiceBeanLocal extends BaseServiceBeanLocal<Profil, Long> {

    /*
     * Récuperer un profil 
     * @param nomProfil : nom du profil
     * @return Profil
     */
    public Profil find(String nomProfil);

    public List<Profil> listeProfilParModule(Modules modules);

    public List<Profil> listeProfilNonAssigner();

    public List<Profil> listeProfilAssigner();
    
    public String controlSaveProfil(Profil profil,Profil selectProfil);
}
