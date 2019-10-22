/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import java.util.List;
import javax.ejb.Local;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Profil;

/**
 *
 * @author mandataireTypeOperation-AKIM
 */
@Local
public interface ProfilDaoBeanLocal extends BaseDaoBeanLocal<Profil, Long> {

    public Profil find(String nomProfil);
    
     public List<Profil> listeProfilParModule(Modules modules);
     
     public List<Profil> listeProfilNonAssigner();
     
     public List<Profil> listeProfilAssigner();
}
