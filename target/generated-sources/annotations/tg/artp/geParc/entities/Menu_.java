package tg.artp.geParc.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.Menu;
import tg.artp.geParc.entities.ModuleMenu;
import tg.artp.geParc.entities.ProfilMenu;
import tg.artp.geParc.entities.UtilisateurMenu;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-19T23:18:06")
@StaticMetamodel(Menu.class)
public class Menu_ { 

    public static volatile SingularAttribute<Menu, String> libelleMenu;
    public static volatile ListAttribute<Menu, UtilisateurMenu> ListUtilisateurMenu;
    public static volatile SingularAttribute<Menu, Menu> parent;
    public static volatile ListAttribute<Menu, Menu> children;
    public static volatile SingularAttribute<Menu, Long> idMenu;
    public static volatile ListAttribute<Menu, ProfilMenu> ListProfilMenu;
    public static volatile ListAttribute<Menu, ModuleMenu> ListModuleMenu;

}