package tg.artp.geParc.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.Droit;
import tg.artp.geParc.entities.Modules;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-19T23:18:06")
@StaticMetamodel(Droit.class)
public class Droit_ { 

    public static volatile SingularAttribute<Droit, Droit> parent;
    public static volatile ListAttribute<Droit, Droit> children;
    public static volatile SingularAttribute<Droit, Long> idDroit;
    public static volatile SingularAttribute<Droit, Modules> module;
    public static volatile SingularAttribute<Droit, String> codeDroit;
    public static volatile SingularAttribute<Droit, String> libelleDroit;

}