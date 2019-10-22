package tg.artp.geParc.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.DroitProfil;
import tg.artp.geParc.entities.ProfilMenu;
import tg.artp.geParc.entities.UtilisateurModuleProfil;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-19T23:18:06")
@StaticMetamodel(Profil.class)
public class Profil_ { 

    public static volatile ListAttribute<Profil, ProfilMenu> ListProfilMenu;
    public static volatile ListAttribute<Profil, UtilisateurModuleProfil> ListUserModuleProfil;
    public static volatile ListAttribute<Profil, DroitProfil> ListDroitProfil;
    public static volatile SingularAttribute<Profil, String> description;
    public static volatile SingularAttribute<Profil, String> libelleProfil;
    public static volatile SingularAttribute<Profil, Long> idProfil;

}