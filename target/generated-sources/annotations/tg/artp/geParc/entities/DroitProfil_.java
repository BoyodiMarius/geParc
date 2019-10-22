package tg.artp.geParc.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.Droit;
import tg.artp.geParc.entities.Profil;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-22T22:15:39")
@StaticMetamodel(DroitProfil.class)
public class DroitProfil_ { 

    public static volatile SingularAttribute<DroitProfil, Long> idDroitProfil;
    public static volatile SingularAttribute<DroitProfil, Profil> profil;
    public static volatile SingularAttribute<DroitProfil, Droit> droit;

}