/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.tools;

import java.util.Comparator;
import tg.artp.geParc.entities.Menu;

/**
 *
 * @author mandataireTypeOperation-Akim
 */
public class Menucomparator implements Comparator<Menu> {

    @Override
    public int compare(Menu o1, Menu o2) {
        return o1.getLibelleMenu().compareTo(o2.getLibelleMenu());
    }
}
