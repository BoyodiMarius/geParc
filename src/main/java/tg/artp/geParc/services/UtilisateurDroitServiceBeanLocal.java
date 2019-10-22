/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import java.util.List;
import javax.ejb.Local;
import tg.artp.geParc.entities.Droit;
import tg.artp.geParc.entities.Profil;
import tg.artp.geParc.entities.Utilisateur;
import tg.artp.geParc.entities.UtilisateurDroit;

/**
 *
 * @author SENA
 */
@Local
public interface UtilisateurDroitServiceBeanLocal extends BaseServiceBeanLocal<UtilisateurDroit, Long> {

    public UtilisateurDroit find(Utilisateur user, Droit droit);

    public void deleteUserDroit(Droit d, Profil p);
    
    public List<UtilisateurDroit> listUserDroit(Utilisateur user, Profil p);
    
    

}
