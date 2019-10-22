/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import java.util.List;
import javax.ejb.Local;
import tg.artp.geParc.entities.chauffeurs;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Local
public interface chauffeursServiceBeanLocal extends BaseServiceBeanLocal<chauffeurs, String> {

    List<chauffeurs> recupererListePriveDe(chauffeurs c);

    public List<chauffeurs> recupererListeOrdonne(String statut);
}
