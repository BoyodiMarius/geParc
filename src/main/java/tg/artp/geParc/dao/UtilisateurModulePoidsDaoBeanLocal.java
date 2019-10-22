/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import java.util.List;
import javax.ejb.Local;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Utilisateur;
import tg.artp.geParc.entities.UtilisateurModulePoids;

/**
 *
 * @author ELVO
 */
@Local
public interface UtilisateurModulePoidsDaoBeanLocal extends BaseDaoBeanLocal<UtilisateurModulePoids, Long> {

    public List<UtilisateurModulePoids> selectionnerPoidsParUserModule(Modules module, Utilisateur utilisateur);
    
    public UtilisateurModulePoids find(Long idModule, String idUser);
}
