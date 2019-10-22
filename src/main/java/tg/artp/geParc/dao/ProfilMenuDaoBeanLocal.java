/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import javax.ejb.Local;
import tg.artp.geParc.entities.Menu;
import tg.artp.geParc.entities.Profil;
import tg.artp.geParc.entities.ProfilMenu;

/**
 *
 * @author mandataireTypeOperation-AKIM
 */
@Local
public interface ProfilMenuDaoBeanLocal extends BaseDaoBeanLocal<ProfilMenu, Long> {
    /*
     * Recuperer un profilMenu 
     * 
     * @param menu :: le menu
     * @param profil :: le profil
     * 
     * @return ProfilMenu
     */

    public ProfilMenu find(Menu menu, Profil profil);
}
