/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.converter;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tg.artp.geParc.entities.BaseEntity;

/**
 *
 * @author Geek
 */
public class BaseConverter<T extends BaseEntity, K extends Serializable> implements Converter {

    private Class<T> type;
    @PersistenceContext(unitName = "geParc1.0_PU")
    protected EntityManager em;

    public BaseConverter() {
    }

    public BaseConverter(Class<T> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equalsIgnoreCase("null")) {
            System.out.println("JAI RETOURNE NULL");
            return null;
        }
        try {
            System.out.println("em===" + em);
            return (T) em.find(this.type, Long.valueOf(value));
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.getClass().getSimpleName().equalsIgnoreCase("string")) {
            return "";
        }
        T t = (T) value;
        return t.getId();

    }
}
