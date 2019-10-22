/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import java.util.List;
import javax.ejb.Local;
import tg.artp.geParc.entities.vehicules;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Local
public interface vehiculesDaoBeanLocal extends BaseDaoBeanLocal<vehicules, String> {

    List<vehicules> listeVehicules();

    public List<Object[]> recupererRatioConsommation(vehicules ve);
}
