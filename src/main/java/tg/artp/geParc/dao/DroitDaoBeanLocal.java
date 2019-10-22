/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import java.util.List;
import javax.ejb.Local;
import tg.artp.geParc.entities.Droit;

/**
 *
 * @author SENA
 */
@Local
public interface DroitDaoBeanLocal extends BaseDaoBeanLocal<Droit, Long> {

    public List<Droit> listeDroitsSansParent();

    public List<Droit> listeDroitsEnfantByParent(Droit droitParent);
    
    public List<Droit> listeDroitsEnfant(String parent, String idUser);
    


}
