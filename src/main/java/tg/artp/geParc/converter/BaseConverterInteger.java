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
import javax.persistence.Persistence;
import tg.artp.geParc.entities.BaseEntity;

/**
 *
 * @author M
 */
public class BaseConverterInteger<T extends BaseEntity, K extends Serializable> implements Converter {

    private Class<T> type;

    public BaseConverterInteger() {
    }

    public BaseConverterInteger(Class<T> type) {
        this.type = type;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        EntityManager em = Persistence.createEntityManagerFactory("geParc1.0_PU").createEntityManager();
        return em.find(this.type, Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println(value.getClass().getSimpleName());
        T t = (T) value;
        return t.getId();
    }
}
