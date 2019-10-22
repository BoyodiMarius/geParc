package tg.artp.geParc.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.UtilisateurModuleProfil;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-22T22:15:38")
@StaticMetamodel(Utilisateur.class)
public class Utilisateur_ { 

    public static volatile SingularAttribute<Utilisateur, Long> idUser;
    public static volatile SingularAttribute<Utilisateur, String> typeUtilisateur;
    public static volatile SingularAttribute<Utilisateur, String> image;
    public static volatile SingularAttribute<Utilisateur, String> password;
    public static volatile ListAttribute<Utilisateur, UtilisateurModuleProfil> ListUtilisateurModuleProfil;
    public static volatile SingularAttribute<Utilisateur, String> login;
    public static volatile SingularAttribute<Utilisateur, Boolean> activation;
    public static volatile SingularAttribute<Utilisateur, String> nom;
    public static volatile SingularAttribute<Utilisateur, String> prenom;
    public static volatile SingularAttribute<Utilisateur, String> email;

}