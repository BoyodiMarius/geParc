/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import java.util.List;
import javax.ejb.Local;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.entities.Droit;
import tg.artp.geParc.entities.DroitProfil;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Profil;

/**
 *
 * @author SENA
 */
@Local
public interface DroitProfilDaoBeanLocal extends BaseDaoBeanLocal<DroitProfil, Long> {

    public List<Droit> listDroits(Modules module, Profil profil);

    public List<Droit> listDroits(Profil profil);
    
    public List<Profil> listProfilToUse();

    public DroitProfil find(Profil profil, Droit droit);

    public void deleteDroitProfil(Droit d, Profil p);
}
