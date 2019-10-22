package tg.artp.geParc.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.Droit;
import tg.artp.geParc.entities.ModuleMenu;
import tg.artp.geParc.entities.UtilisateurModulePoids;
import tg.artp.geParc.entities.UtilisateurModuleProfil;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-19T23:18:06")
@StaticMetamodel(Modules.class)
public class Modules_ { 

    public static volatile ListAttribute<Modules, Droit> ListDroit;
    public static volatile ListAttribute<Modules, UtilisateurModulePoids> ListUtilisateurModulePoids;
    public static volatile ListAttribute<Modules, UtilisateurModuleProfil> ListUserModuleProfil;
    public static volatile SingularAttribute<Modules, Long> idModule;
    public static volatile SingularAttribute<Modules, String> libelleModule;
    public static volatile ListAttribute<Modules, ModuleMenu> ListModuleMenu;

}