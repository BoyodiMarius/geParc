/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import tg.artp.geParc.services.UtilisateurDroitServiceBeanLocal;
import org.omnifaces.util.Ajax;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import tg.artp.geParc.entities.Droit;
import tg.artp.geParc.entities.DroitProfil;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Profil;
import tg.artp.geParc.entities.Utilisateur;
import tg.artp.geParc.entities.UtilisateurDroit;
import tg.artp.geParc.entities.UtilisateurModuleProfil;
import tg.artp.geParc.services.DroitProfilServiceBeanLocal;
import tg.artp.geParc.services.DroitServiceBeanLocal;
import tg.artp.geParc.services.ModuleServiceBeanLocal;
import tg.artp.geParc.services.ProfilServiceBeanLocal;
import tg.artp.geParc.services.UtilisateurModuleProfilServiceBeanLocal;

/**
 *
 * @author SENA
 */
@Named("profilManagedBean")
@ViewScoped
public class ProfilManagedBean implements Serializable {

    @EJB
    private DroitProfilServiceBeanLocal droitProfilService;
    @EJB
    private UtilisateurDroitServiceBeanLocal utilisateurDroitService;
    @EJB
    private UtilisateurModuleProfilServiceBeanLocal utilisateurModuleProfilService;
    @EJB
    private ProfilServiceBeanLocal profilService;
    @EJB
    private ModuleServiceBeanLocal moduleService;
    @EJB
    private DroitServiceBeanLocal droitService;
    private Profil profil, selectedProfil, intermediaire;
    private List<Modules> listModule, listSelectedModule, listModuleExistent;
    private Properties langue;
    private Modules moduleSelected;
    private Modules moduleSelected2;
    private List<Profil> listeProfilSelected;
    private List<Profil> listeProfil, listeProfilNonAssigner;
    private List<Droit> listeDroitToAdd, listeDroitTosubtract, allDroits, listDroitSelectionne, listDroitRestant, listeEnregistrement;
    private List<DroitProfil> listeDroitProfil;
    private String msgtransaction, msgSucces;
    private TreeNode root, rootUser;
    private TreeNode[] selectedNodes, selectedNodesToAdd, selectedNodesToSoubstract;
    private Boolean updtFiche, addAll, substractAll;
    private Integer compteurModif = 0;

    public ProfilManagedBean() {
        intermediaire = new Profil();
        moduleSelected = new Modules();
        profil = new Profil();
        listeProfilSelected = new ArrayList<>();
        listeDroitToAdd = new ArrayList<>();
        listeEnregistrement = new ArrayList<>();
        listeDroitTosubtract = new ArrayList<>();
        allDroits = new ArrayList<>();
        listDroitSelectionne = new ArrayList<>();
        listeDroitProfil = new ArrayList<>();
        listDroitRestant = new ArrayList<>();
        listModuleExistent = new ArrayList<>();

    }

    @PostConstruct
    public void init() {
        listeProfil = profilService.selectionnerTout();
        listeProfilNonAssigner = profilService.listeProfilNonAssigner();
        listModule = moduleService.selectionnerTout();
        listSelectedModule = moduleService.selectionnerTout();
        initOperation();
        initialisationDroits();
    }

    public void initialiser() {
        profil = new Profil();
        listeDroitProfil = new ArrayList<>();
        listModule = moduleService.selectionnerTout();
        selectedProfil = null;
        profil = new Profil();
        listeDroitToAdd = new ArrayList<>();
        listeEnregistrement = new ArrayList<>();
        listeDroitTosubtract = new ArrayList<>();
        listModuleExistent = new ArrayList<>();
        listeProfilNonAssigner = profilService.listeProfilNonAssigner();
    }

    public void initOperation() {
        addAll = Boolean.FALSE;
        substractAll = Boolean.FALSE;
        updtFiche = Boolean.FALSE;
    }

    public Properties getLangue() {
        return langue;
    }

    public void setLangue(Properties langue) {
        this.langue = langue;
    }

    public void getRealListeProfil() {
        try {
            listeProfil = profilService.listeProfilParModule(moduleSelected2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Modules> getListModule() {
        return listModule;
    }

    public void setListModule(List<Modules> listModule) {
        this.listModule = listModule;
    }

    public Modules getModuleSelected() {
        return moduleSelected;
    }

    public void setModuleSelected(Modules moduleSelected) {
        this.moduleSelected = moduleSelected;
    }

    public List<Droit> getListeDroitToAdd() {
        return listeDroitToAdd;
    }

    public void setListeDroitToAdd(List<Droit> listeDroitToAdd) {
        this.listeDroitToAdd = listeDroitToAdd;
    }

    public List<Droit> getListeDroitTosubtract() {
        return listeDroitTosubtract;
    }

    public void setListeDroitTosubtract(List<Droit> listeDroitTosubtract) {
        this.listeDroitTosubtract = listeDroitTosubtract;
    }

    public List<Profil> getListeProfilSelected() {
        return listeProfilSelected;
    }

    public void setListeProfilSelected(List<Profil> listeProfilSelected) {
        this.listeProfilSelected = listeProfilSelected;
    }

    public List<Profil> getListeProfil() {
        return listeProfil;
    }

    public void setListeProfil(List<Profil> listeProfil) {
        this.listeProfil = listeProfil;
    }

    public List<Droit> getListeDroitParProfilModul() {
        return this.droitProfilService.listDroits(moduleSelected, profil);
    }

    public List<Droit> getAllDroits() {
        return allDroits;
    }

    public void setAllDroits(List<Droit> allDroits) {
        this.allDroits = allDroits;
    }

    public List<Droit> getListDroitSelectionne() {
        return listDroitSelectionne;
    }

    public void setListDroitSelectionne(List<Droit> listDroitSelectionne) {
        this.listDroitSelectionne = listDroitSelectionne;
    }

    public List<DroitProfil> getListeDroitProfil() {
        return listeDroitProfil;
    }

    public void setListeDroitProfil(List<DroitProfil> listeDroitProfil) {
        this.listeDroitProfil = listeDroitProfil;
    }

    public String getMsgtransaction() {
        return msgtransaction;
    }

    public void setMsgtransaction(String msgtransaction) {
        this.msgtransaction = msgtransaction;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;

    }

    public TreeNode getRootUser() {
        return rootUser;
    }

    public void setRootUser(TreeNode rootUser) {
        this.rootUser = rootUser;
    }

    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }

    public TreeNode[] getSelectedNodesToAdd() {
        return selectedNodesToAdd;
    }

    public void setSelectedNodesToAdd(TreeNode[] selectedNodesToAdd) {
        this.selectedNodesToAdd = selectedNodesToAdd;
    }

    public TreeNode[] getSelectedNodesToSoubstract() {
        return selectedNodesToSoubstract;
    }

    public void setSelectedNodesToSoubstract(TreeNode[] selectedNodesToSoubstract) {
        this.selectedNodesToSoubstract = selectedNodesToSoubstract;
    }

    public Modules getModuleSelected2() {
        return moduleSelected2;
    }

    public void setModuleSelected2(Modules moduleSelected2) {
        this.moduleSelected2 = moduleSelected2;
    }

    public List<Modules> getListSelectedModule() {
        return listSelectedModule;
    }

    public void setListSelectedModule(List<Modules> listSelectedModule) {
        this.listSelectedModule = listSelectedModule;
    }

    public List<Modules> getListModuleExistent() {
        return listModuleExistent;
    }

    public void setListModuleExistent(List<Modules> listModuleExistent) {
        this.listModuleExistent = listModuleExistent;
    }

    public Profil getSelectedProfil() {
        return selectedProfil;
    }

    public void setSelectedProfil(Profil selectedProfil) {
        this.selectedProfil = selectedProfil;
    }

    public Boolean getUpdtFiche() {
        return updtFiche;
    }

    public void setUpdtFiche(Boolean updtFiche) {
        this.updtFiche = updtFiche;
    }

    public Boolean getAddAll() {
        return addAll;
    }

    public void setAddAll(Boolean addAll) {
        this.addAll = addAll;
    }

    public Boolean getSubstractAll() {
        return substractAll;
    }

    public void setSubstractAll(Boolean substractAll) {
        this.substractAll = substractAll;
    }

    public List<Profil> getListeProfilNonAssigner() {
        return listeProfilNonAssigner;
    }

    public void setListeProfilNonAssigner(List<Profil> listeProfilNonAssigner) {
        this.listeProfilNonAssigner = listeProfilNonAssigner;
    }

    public Integer getCompteurModif() {
        return compteurModif;
    }

    public void setCompteurModif(Integer compteurModif) {
        this.compteurModif = compteurModif;
    }

    public String getMsgSucces() {
        return msgSucces;
    }

    public void setMsgSucces(String msgSucces) {
        this.msgSucces = msgSucces;
    }

    public List<Droit> getListDroitRestant() {
        return listDroitRestant;
    }

    public void setListDroitRestant(List<Droit> listDroitRestant) {
        this.listDroitRestant = listDroitRestant;
    }

    private void initDroit(List<Droit> list, List<Droit> listSelected) {
        root = new DefaultTreeNode("", null);
        List<Droit> Ldroits = list;
        if (Ldroits.size() > 0) {
            for (Droit droi : Ldroits) {
                if (!droi.hasParent()) {
                    System.err.println("++++++++++++++++DROIT ++++++++++++++== " + droi.getLibelleDroit());
                    /*Si le droit existe  deja pour le profil selectionné (cas de modification)*/
                    if (!listSelected.contains(droi)) {
                        TreeNode node = new DefaultTreeNode(droi, root);
                        if (droi.hasChildren()) {
                            loadChildren(node, droi, listSelected);
                        }
                    }

                }
            }
        }

    }

    private void initDroitForProfilSelect(List<Droit> list, List<Droit> listSelected) {
        root = new DefaultTreeNode("", null);
        rootUser = new DefaultTreeNode("", null);
        List<Droit> Ldroits = list;
        if (Ldroits.size() > 0) {
            for (Droit droi : Ldroits) {
                if (!droi.hasParent()) {
                    System.err.println("++++++++++++++++DROIT ++++++++++++++== " + droi.getLibelleDroit());
                    /*Si le droit existe  deja pour le profil selectionné (cas de modification)*/
                    if (!listSelected.contains(droi)) {
                        TreeNode node = new DefaultTreeNode(droi, root);
                        if (droi.hasChildren()) {
                            loadChildren(node, droi, listSelected);
                        }
                    } else {
                        TreeNode node = new DefaultTreeNode(droi, rootUser);
                        if (droi.hasChildren()) {
                            loadChildrenForUpdate(node, droi, listSelected);
                        }
                    }

                }
            }
        }
    }

    private void initForUpdate(List<Droit> list, List<Droit> listSelected) {
        rootUser = new DefaultTreeNode("", null);
        List<Droit> Ldroits = list;
        if (Ldroits.size() > 0) {
            for (Droit droi : Ldroits) {
                if (!droi.hasParent()) {
                    /*SI LE PARENT CONTIENT AU MOINS UN FILS DES LES ELEMENTS SELECTIONNES*/
                    if (droi.hasChildren()) {
                        Integer compteur = 0;
                        //Vérifier si un enfant du droit est contenu dans la liste de droits sélectionnés
                        for (Droit dr : droi.getChildren()) {
                            if (listSelected.contains(dr)) {
                                compteur++;
                            }
                        }
                        System.err.println("compteur size " + compteur);
                        if (compteur > 0) {//Cas d'au moins un enfant du droit enfant contenu dans la sélection
                            if (!listSelected.contains(droi)) {
                                System.err.println("-----DROIT NOT IN LISTE----- " + droi.getLibelleDroit());
                                TreeNode node = new DefaultTreeNode(droi, rootUser);
                                node.setSelectable(Boolean.FALSE);
                                loadChildrenForUpdate(node, droi, listSelected);
                            }
                            if (listSelected.contains(droi)) {
                                System.err.println("---***---DROIT IN LISTE---***--- " + droi.getLibelleDroit());
                                TreeNode node = new DefaultTreeNode(droi, rootUser);
                                node.setSelectable(Boolean.TRUE);
                                loadChildrenForUpdate(node, droi, listSelected);
                            }
                        } else //System.err.println("trouver(droi, listSelected) " + trouver(droi, listSelected));
                        if (trouver(droi, listSelected)) {
                            System.err.println("DROIT SANS FILS " + droi.getLibelleDroit());
                            TreeNode node = new DefaultTreeNode(droi, rootUser);
                            node.setSelectable(Boolean.FALSE);
                            loadChildrenForUpdate(node, droi, listSelected);
                        }
                    } else if (!droi.hasChildren()) {
                        //CAS DES DROIT PARENT N'AYANT PAS DE FILS
                        if (listSelected.contains(droi)) {
                            System.err.println("DROIT SANS FILS " + droi.getLibelleDroit());
                            TreeNode node = new DefaultTreeNode(droi, rootUser);
                            node.setSelectable(Boolean.FALSE);
                            loadChildrenForUpdate(node, droi, listSelected);
                        }
                    }
                }
//                else {
//
//                    if (!listSelected.contains(droi.getParent())) {
//                        listSelected.add(droi.getParent());
//                        TreeNode node = new DefaultTreeNode(droi.getParent(), rootUser);
//                        node.setSelectable(Boolean.FALSE);
//                        loadChildrenForUpdate(node, droi.getParent(), listSelected);
//                    }
//                }
            }
        }
    }

    /*Remplissage de la liste des droits à ajouter à l'utilisateur*/
    public void ajoutDroits() {
        if (selectedNodesToAdd != null && selectedNodesToAdd.length > 0) {
            for (TreeNode node : selectedNodesToAdd) {
                Droit unDroid = (Droit) node.getData();
                System.err.println("DROIT TO ADD " + unDroid.getLibelleDroit());
                listeDroitToAdd.add(unDroid);
            }
            listeEnregistrement = listeDroitToAdd;
            System.err.println("taille listeDroitToAdd " + listeDroitToAdd.size());
        }
    }


    /*Remplissage de la liste des droits à retirer à l'utilisateur*/
    public void retraitDroits() {
        if (selectedNodesToSoubstract != null && selectedNodesToSoubstract.length > 0) {
            for (TreeNode node : selectedNodesToSoubstract) {
                Droit unDroid = (Droit) node.getData();
                listeDroitTosubtract.add(unDroid);
            }
        }
    }

    // charger la liste des sous-menus dans le TreeNode parent
    private void loadChildren(TreeNode parentNode, Droit droitParent, List<Droit> listSelected) {
        for (Droit child : droitParent.getChildren()) {
            if (!listSelected.contains(child)) {
                TreeNode node = new DefaultTreeNode(child, parentNode);
                if (child.hasChildren()) {
                    loadChildren(node, child, listSelected);
                }
            }

        }
    }

    private void loadChildrenForUpdate(TreeNode parentNode, Droit droitParent, List<Droit> listSelected) {
        System.err.println("*******  droitParent  ******** " + droitParent.getLibelleDroit());
        for (Droit child : droitParent.getChildren()) {
            if (listSelected.contains(child) || trouver(child, listSelected)) {
                System.err.println("Enfant trouvé : " + child.getLibelleDroit());
                TreeNode node = new DefaultTreeNode(child, parentNode);
                if (trouver(child, listSelected) && !listSelected.contains(child)) {
                    node.setSelectable(Boolean.FALSE);
                }
                if (child.hasChildren()) {
                    loadChildrenForUpdate(node, child, listSelected);
                }
            }
        }
    }

    public Boolean trouver(Droit child, List<Droit> selection) {
        Boolean rep = Boolean.FALSE;
        if (child.hasChildren()) {
            for (Droit childnew : child.getChildren()) {
                gr:
                if (selection.contains(childnew)) {
                    rep = Boolean.TRUE;
                    System.err.println("REPONSE TROUVE " + rep + " enfant [" + childnew + " ] Parent [" + child.getLibelleDroit() + "]");
                    break gr;
                }
                if (rep) {
                    break;
                } else if (childnew.hasChildren()) {
                    return trouver(childnew, selection);
                }
            }
        }
        System.err.println("REPONSE AVANT RETURN " + rep);
        return rep;
    }

    public void saveOctroieDroit() throws Exception {
//        UserTransaction transaction = MicrofinaUtils.getUserTransaction();
        try {
//            transaction.begin();
            for (Droit unDroid : listeEnregistrement) {
//            for (Droit unDroid : listeDroitToAdd) {
                DroitProfil dr = new DroitProfil(unDroid, profil);
                listeDroitProfil.add(dr);
            }
            droitProfilService.ajouter(listeDroitProfil);
//            transaction.commit();
            msgSucces = "Droits assignés avec succès au profil";
            Ajax.oncomplete("PF('succesOctroiDroit').show()");
            initialiser();
            rootUser = new DefaultTreeNode("", null);
            listeProfil = profilService.listeProfilAssigner();

        } catch (Exception ex) {
            ex.printStackTrace();
            msgtransaction = "Transaction échouée : Erreur lors de l'assignation de droits";
            Ajax.oncomplete("PF('echecOctroiDroit').show()");
//            transaction.rollback();

        }

    }

    //à mettre dans un service
    public void updateOctroieDroit() throws Exception {
//        UserTransaction transaction = MicrofinaUtils.getUserTransaction();
        List<DroitProfil> droitProfilExistant = droitProfilService.selectionnerParTableAttribut(intermediaire.getIdProfil(), "profil.idProfil");
        listeDroitProfil = new ArrayList();
        try {
            droitProfilService.delete(droitProfilExistant);
            System.out.println(listeDroitToAdd.size());
            System.out.println(listeEnregistrement.size());
            for (Droit unDroid : listeEnregistrement) {
//            for (Droit unDroid : listeDroitToAdd) {
                DroitProfil dr = new DroitProfil(unDroid, intermediaire);
                listeDroitProfil.add(dr);
                try {
                    droitProfilService.ajouter(dr);
                } catch (Exception e) {
                }
            }
            //Suppression de données dans les tables UtilisateurModuleProfil et UtilisateurDroit
            addUserData();
            msgSucces = "Enregistrement effectué avec succès";
            Ajax.oncomplete("PF('succesUpdateOctroiDroit').show()");
            initialiser();
            rootUser = new DefaultTreeNode("", null);
            listeProfil = profilService.listeProfilAssigner();
        } catch (Exception e) {
            msgtransaction = "Transaction échouée : erreur de modification de droit profil";
            Ajax.oncomplete("PF('echecUpdateOctroiDroit').show()");
        }
    }

    public void saveAjoutProfil() throws Exception {
//        langue = LangueManagedBean.langueEnCours();
//        UserTransaction transaction = MicrofinaUtils.getUserTransaction();
        RequestContext context = RequestContext.getCurrentInstance();
        try {
//            transaction.begin();
            if (selectedProfil == null) {
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                javax.validation.Validator validator = factory.getValidator();
                Set<ConstraintViolation<Profil>> constraintViolations = validator.validate(profil);
                if (constraintViolations.size() > 0) {
                    System.out.println("Constraint Violations occurred..");
                    for (ConstraintViolation<Profil> contraints : constraintViolations) {
                        System.out.println(contraints.getRootBeanClass().getSimpleName()
                                + "." + contraints.getPropertyPath() + " " + contraints.getMessage());
                    }
                }

                profilService.ajouter(profil);
            } else {
                profilService.modifier(profil);
            }
//            transaction.commit();
            Ajax.oncomplete("PF('succesProfil').show()");
            profil = new Profil();
            listeDroitTosubtract = new ArrayList<>();
            initialiser();
        } catch (Exception ex) {
//            transaction.rollback();
            msgtransaction = "Echec d'enregistrement du profil : Transaction échouée";
            Ajax.oncomplete("PF('echecProfil').show()");
            ex.printStackTrace();
        }

    }

    public void initialisationDroits() {
        List<Droit> listSelectedDroits;
        try {
            if (selectedProfil != null) {
                listSelectedDroits = droitProfilService.listDroits(selectedProfil);
            } else {
                listSelectedDroits = new ArrayList<>();
            }
        } catch (Exception e) {
            listSelectedDroits = new ArrayList<>();
        }

        try {
            List<Droit> lDroits = new ArrayList<>();
            for (Modules m : listSelectedModule) {
                lDroits.addAll(m.getListDroit());
                listDroitRestant = lDroits;
            }
            if (lDroits.size() > 0) {
                initDroit(lDroits, listSelectedDroits);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialisationDroitsForUpdate() {
        List<Droit> listSelectedDroits;
        try {
            if (selectedProfil != null) {
                listSelectedDroits = droitProfilService.listDroits(selectedProfil);
            } else {
                listSelectedDroits = new ArrayList<>();
            }
        } catch (Exception e) {
            listSelectedDroits = new ArrayList<>();
        }

        try {
            List<Droit> lDroits = new ArrayList<>();
            for (Modules m : listSelectedModule) {
                lDroits.addAll(m.getListDroit());
            }
            if (lDroits.size() > 0) {
                initDroitForProfilSelect(lDroits, listSelectedDroits);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //AJOUT DES DROITS SELECTIONNES A UN PROFIL;
    public void updateDroits() {
        System.err.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        List<Droit> listSelectedDroits, pereToMove;
        try {
            if (selectedProfil != null) {
                listSelectedDroits = droitProfilService.listDroits(selectedProfil);
            } else {
                listSelectedDroits = new ArrayList<>();
            }
        } catch (Exception e) {
            listSelectedDroits = new ArrayList<>();
        }

        try {
            List<Droit> lDroits = new ArrayList<>();
            for (Modules m : listSelectedModule) {
                System.err.println("MODULE SELCTIONNE " + m.getLibelleModule());
                lDroits.addAll(m.getListDroit());
            }
            System.err.println("lDroits.size() " + lDroits.size());

            if (lDroits.size() > 0) {
                ajoutDroits();
                retraitDroits();
                if (listeDroitToAdd.size() > 0) {
                    pereToMove = new ArrayList<>();
                    if (listeDroitTosubtract.size() > 0) {
                        listeDroitToAdd.removeAll(listeDroitTosubtract);
                        pereToMove = parentRetrait(lDroits, listeDroitTosubtract);
                        listeDroitTosubtract = new ArrayList<>();

                    }
                    List<Droit> droitRestDroits = new ArrayList<>();

                    System.err.println("CAS NORMAL");
                    System.err.println("****lDroits.size()*********** " + lDroits.size());
                    listSelectedDroits.addAll(listeDroitToAdd);
                    listSelectedDroits.removeAll(pereToMove);
                    initForUpdate(lDroits, listSelectedDroits);

                    droitRestDroits = lDroits;
                    droitRestDroits.removeAll(listeDroitToAdd);
                    for (Droit drMv : pereToMove) {
                        if (!droitRestDroits.contains(drMv)) {
                            droitRestDroits.add(drMv);
                        }
                    }
                    initDroit(droitRestDroits, listSelectedDroits);
                    listDroitRestant = lDroits;
                    listDroitRestant.removeAll(listeDroitToAdd);
                    System.err.println("ALL DROITS SIZE " + lDroits.size());
                    System.err.println("DROITS TO ADD SIZE " + listeDroitToAdd.size());
                    System.err.println("DROITS TO SUBSTRACT SIZE " + listeDroitTosubtract.size());
                    listeDroitTosubtract = new ArrayList<>();
                }
                System.err.println("taille listeDroitToAdd dans updateDroit " + listeDroitToAdd.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //AJOUT DES DROITS SELECTIONNES A UN PROFIL;
    public void updateProfilEtDroits() {
        List<Droit> listSelectedDroits, pereToMove;
        listSelectedDroits = new ArrayList<>();

        try {
            List<Droit> lDroits = new ArrayList<>();
            for (Modules m : listSelectedModule) {
                System.err.println("MODULE SELCTIONNE " + m.getLibelleModule());
                lDroits.addAll(m.getListDroit());
            }
            System.err.println("lDroits.size() " + lDroits.size());

            if (lDroits.size() > 0) {
                ajoutDroits();
                retraitDroits();
                if (listeDroitToAdd.size() > 0) {
                    System.err.println("listeDroitToAdd.size() > 0");
                    pereToMove = new ArrayList<>();
                    if (listeDroitTosubtract.size() > 0) {
                        System.err.println("CAS NORMAL DE TRAITEMENT DES MENU PERE : " + listeDroitTosubtract.size());
                        listeDroitToAdd.removeAll(listeDroitTosubtract);
                        pereToMove = parentRetrait(lDroits, listeDroitTosubtract);
                        listeDroitTosubtract = new ArrayList<>();
                    }

                    List<Droit> droitRestDroits;
                    System.err.println("CAS NORMAL DE MODIFICATION");

                    listSelectedDroits.addAll(listeDroitToAdd);
                    listSelectedDroits.removeAll(pereToMove);
                    initForUpdate(lDroits, listSelectedDroits);

                    droitRestDroits = lDroits;
                    droitRestDroits.removeAll(listeDroitToAdd);
                    for (Droit drMv : pereToMove) {
                        if (!droitRestDroits.contains(drMv)) {
                            droitRestDroits.add(drMv);
                        }
                    }
                    initDroit(droitRestDroits, listSelectedDroits);
                    listeDroitTosubtract = new ArrayList<>();
                }
            }
            System.out.println(listeDroitToAdd.size());
            listeEnregistrement = listeDroitToAdd;
            System.out.println(listeEnregistrement.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Droit> parentRetrait(List<Droit> allDroit, List<Droit> droitAretirer) {
        List<Droit> parents = new ArrayList<>();
        for (Droit mesDroit : allDroit) {
            Integer compteur = 0;
            if (!mesDroit.hasParent()) {
                if (mesDroit.hasChildren()) {
                    for (Droit dr : mesDroit.getChildren()) {
                        if (droitAretirer.contains(dr)) {
                            compteur++;
                        }
                    }
                    if (compteur > 0) {
                        System.err.println("============= DROIT =========== " + mesDroit.getLibelleDroit());
                        parents.add(mesDroit);
                    }
                }
            }
        }
        return parents;
    }

    /**
     * Exécuter a la selection d'un droit. En mode modification elle ajoute
     * automatiquement ce droit a tous les utlisateurs ayant le profil
     */
    public void onNodeSelect(NodeSelectEvent event) {
        if (updtFiche) {
//            UserTransaction transaction = MicrofinaUtils.getUserTransaction();
            try {
//                transaction.begin();
                Droit d = (Droit) event.getTreeNode().getData();
                List<Utilisateur> listUser = utilisateurModuleProfilService.findUserByProfil(profil);
                for (Utilisateur u : listUser) {
                    UtilisateurDroit ud = new UtilisateurDroit();
                    ud.setDroit(d);
                    ud.setUtilisateur(u);
                    if (utilisateurDroitService.find(u, d) == null) {
                        utilisateurDroitService.ajouter(ud);
                    }
                }
                DroitProfil dp = new DroitProfil();
                dp.setDroit(d);
                dp.setProfil(profil);
                if (droitProfilService.find(profil, d) == null) {
                    droitProfilService.ajouter(dp);
                }

                if (d.hasChildren()) {
                    addChildDroitOnNodeSelect(listUser, d);
                }

//                transaction.commit();
            } catch (Exception e) {
                try {
//                    transaction.rollback();
                } catch (Exception ex) {
                }
                e.printStackTrace();
            }

        }

    }

    public void onNodeSelectAjout(NodeSelectEvent event) {
        Droit d = (Droit) event.getTreeNode().getData();
        System.err.println("DROIT A AJOUTER " + d.getLibelleDroit());
        listeDroitToAdd.add(d);

    }

    public void onNodeUnselectAjout(NodeUnselectEvent event) {
        Droit d = (Droit) event.getTreeNode().getData();
        System.err.println("DROIT A RETIRER " + d.getLibelleDroit());
        listeDroitToAdd.remove(d);

    }

    public void onNodeSelectRetrait(NodeSelectEvent event) {
        Droit d = (Droit) event.getTreeNode().getData();
        System.err.println("DROIT A AJOUTER " + d.getLibelleDroit());
        listeDroitTosubtract.add(d);

    }

    public void onNodeUnselectRetrait(NodeUnselectEvent event) {
        Droit d = (Droit) event.getTreeNode().getData();
        System.err.println("DROIT A RETIRER " + d.getLibelleDroit());
        listeDroitTosubtract.remove(d);

    }

    public void addChildDroitOnNodeSelect(List<Utilisateur> listUser, Droit droitParent) {
        for (Droit d : droitParent.getChildren()) {
            for (Utilisateur u : listUser) {
                UtilisateurDroit ud = new UtilisateurDroit();
                ud.setDroit(d);
                ud.setUtilisateur(u);
                if (utilisateurDroitService.find(u, d) == null) {
                    utilisateurDroitService.ajouter(ud);
                }
            }

            DroitProfil dp = new DroitProfil();
            dp.setDroit(d);
            dp.setProfil(profil);

            if (droitProfilService.find(profil, d) == null) {
                droitProfilService.ajouter(dp);
            }

            if (d.hasChildren()) {
                addChildDroitOnNodeSelect(listUser, d);
            }

        }
    }

    /**
     * Exécuter a la selection d'un droit. En mode modification elle retir
     * automatiquement ce droit a tous les utlisateurs ayant le profil
     *
     * @param event
     */
    public void onNodeUnselect(NodeUnselectEvent event) {
        if (updtFiche) {
//            UserTransaction transaction = MicrofinaUtils.getUserTransaction();

            try {
//                transaction.begin();
                Droit d = (Droit) event.getTreeNode().getData();
                droitProfilService.deleteDroitProfil(d, profil);
                utilisateurDroitService.deleteUserDroit(d, profil);
                if (d.hasChildren()) {
                    deleteChildDroitOnNodeUnSelect(d);
                }
//                transaction.commit();
            } catch (Exception e) {
                try {
//                    transaction.rollback();
                } catch (Exception Exception) {
                }
                e.printStackTrace();
            }
        }

    }

    public void deleteChildDroitOnNodeUnSelect(Droit droitParent) {

        for (Droit d : droitParent.getChildren()) {
            droitProfilService.deleteDroitProfil(d, profil);
            utilisateurDroitService.deleteUserDroit(d, profil);

            if (d.hasChildren()) {
                deleteChildDroitOnNodeUnSelect(d);
            }
        }

    }

    public void handleProfilSelect() {
        updtFiche = true;
        listeDroitToAdd = new ArrayList<>();
        listSelectedModule = moduleService.listeModuleByProfil(selectedProfil);
        listModuleExistent = moduleService.listeModuleByProfil(selectedProfil);
        compteurModif = 1;
        if (selectedProfil != null) {
            listeDroitToAdd = droitProfilService.listDroits(selectedProfil);
        }
        initialisationDroitsForUpdate();
        System.out.println(selectedProfil.getLibelleProfil());
        intermediaire = new Profil();
        intermediaire = selectedProfil;
        // updateProfilEtDroits();
    }

    public String controlUpdateDroitProfil() {
        String message = "OK";
        if (listeDroitToAdd.isEmpty()) {
            message = "Vous n'avez selectionner aucun droit pour effectuer la modification";
        }
        return message;
    }

    public void confirmUpdateDroitProfil() {
        if ("OK".equals(controlUpdateDroitProfil())) {
            Ajax.oncomplete("PF('confirmUpdateOctroiDroit').show()");
        } else {
            msgtransaction = controlUpdateDroitProfil();
            Ajax.oncomplete("PF('echecUpdateOctroiDroit').show()");
        }
    }

    public void confirmProfil() {
        if ("OK".equals(profilService.controlSaveProfil(profil, selectedProfil))) {
            Ajax.oncomplete("PF('confirmProfil').show()");
        } else {
            msgtransaction = profilService.controlSaveProfil(profil, selectedProfil);
            Ajax.oncomplete("PF('echecProfil').show()");
        }
    }

    //Supprimer Utilisateur droit et Utilisateur module profil
    public void deleteUserData(List<Utilisateur> listeUsers) {
        List<UtilisateurDroit> listeUserDroit = new ArrayList<>();
        for (Utilisateur user : listeUsers) {
            listeUserDroit.addAll(utilisateurDroitService.listUserDroit(user, selectedProfil));
        }
        try {
            List<UtilisateurModuleProfil> listeUserMddProf = utilisateurModuleProfilService.selectionnerParTableAttribut(selectedProfil.getIdProfil(), "profil.idProfil");
            if (!listeUserMddProf.isEmpty()) {
                utilisateurModuleProfilService.delete(listeUserMddProf);
            }
        } catch (Exception e) {
        }

        if (!listeUserDroit.isEmpty()) {
            utilisateurDroitService.delete(listeUserDroit);
        }

    }

    //Ajouter Utilisateur droit et Utilisateur module profil
    public void addUserData() {
        List<Utilisateur> users = utilisateurModuleProfilService.findUserByProfil(intermediaire);
//        List<Utilisateur> users = utilisateurModuleProfilService.findUserByProfil(selectedProfil);
        for (Utilisateur user : users) {
            for (Modules mod : listSelectedModule) {
                if (!listModuleExistent.contains(mod)) {
                    System.err.println("**************ok**************");
                    UtilisateurModuleProfil userModuleProfil = new UtilisateurModuleProfil();
                    userModuleProfil.setModule(mod);
                    userModuleProfil.setProfil(intermediaire);
//                    userModuleProfil.setProfil(selectedProfil);
                    userModuleProfil.setUtilisateur(user);
                    utilisateurModuleProfilService.ajouter(userModuleProfil);
                }

            }

        }
    }

    // CONTROL OCTROIE DROITS
    public String controlOctroiDroit() {
        String message = "OK";
        if (listeDroitToAdd.isEmpty()) {
            message = "La liste des droit à octroyer ne peut être vide";
        }
        return message;
    }

    public void confirmOctroiDroit() {
        if ("OK".equals(controlOctroiDroit())) {
            Ajax.oncomplete("PF('confirmOctroiDroit').show()");
        } else {
            msgtransaction = controlOctroiDroit();
            Ajax.oncomplete("PF('echecOctroiDroit').show()");
        }
    }

    //Selectionner profil lors de la mise àn jour du profil 
    public void selectionnerProfil() {
        if (selectedProfil != null) {
            profil = selectedProfil;
        }
    }
}
