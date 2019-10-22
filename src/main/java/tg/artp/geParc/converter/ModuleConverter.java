/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.converter;

import javax.faces.convert.FacesConverter;
import tg.artp.geParc.entities.Modules;

/**
 *
 * @author Geek
 */
@FacesConverter(forClass = Modules.class, value = "moduleConverter")
public class ModuleConverter extends BaseConverter<Modules, Long> {

    public ModuleConverter() {
        super(Modules.class);
    }
}
