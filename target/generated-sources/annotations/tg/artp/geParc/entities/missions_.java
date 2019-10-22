package tg.artp.geParc.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.typeMission;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-19T23:18:06")
@StaticMetamodel(missions.class)
public class missions_ { 

    public static volatile SingularAttribute<missions, Date> dateRetour;
    public static volatile SingularAttribute<missions, typeMission> idTypeMission;
    public static volatile SingularAttribute<missions, Date> dateDepart;
    public static volatile SingularAttribute<missions, String> objetMission;
    public static volatile SingularAttribute<missions, String> idMission;

}