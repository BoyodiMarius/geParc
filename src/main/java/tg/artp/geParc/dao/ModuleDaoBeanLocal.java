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
public interface ModuleDaoBeanLocal extends BaseDaoBeanLocal<Modules, Long> {

    public List<Modules> listeModuleByProfil(Profil profil);
}
