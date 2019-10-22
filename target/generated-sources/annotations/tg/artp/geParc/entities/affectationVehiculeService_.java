package tg.artp.geParc.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.services;
import tg.artp.geParc.entities.vehicules;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-22T22:15:39")
@StaticMetamodel(affectationVehiculeService.class)
public class affectationVehiculeService_ { 

    public static volatile SingularAttribute<affectationVehiculeService, Date> dateFinAffectation;
    public static volatile SingularAttribute<affectationVehiculeService, vehicules> idVehicule;
    public static volatile SingularAttribute<affectationVehiculeService, String> idAffectationVehiculeService;
    public static volatile SingularAttribute<affectationVehiculeService, services> idService;
    public static volatile SingularAttribute<affectationVehiculeService, Date> dateDebutAffectation;

}