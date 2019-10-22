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
public class BaseConverterString<T extends BaseEntity, K extends Serializable> implements Converter {

    private Class<T> type;
    @PersistenceContext(unitName = "geParc1.0_PU")
    protected EntityManager em;

    public BaseConverterString() {
    }

    public BaseConverterString(Class<T> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        T object = null;
        try {
            System.out.println(value);
            object = (T) em.find(this.type, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value.getClass().getSimpleName().equalsIgnoreCase("string")) {
            return value.toString();
        }
        T t = (T) value;
        return t.getId();

    }
}
