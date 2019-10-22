/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import tg.artp.geParc.tools.Menucomparator;

/**
 *
 * @author poste
 */
public class ModuleProfilPoids implements Serializable {

    private Modules module;
    private Profil profil;
    private Poids poids;
    private TreeNode root;
    private TreeNode[] selectedNodes;

    public ModuleProfilPoids() {
    }

    public ModuleProfilPoids(Modules modules, Profil profil, Poids poids) {
        this.module = modules;
        this.profil = profil;
        this.poids = poids;
    }

    public Modules getModule() {
        return module;
    }

    public void setModule(Modules module) {
        this.module = module;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
        if (this.root == null) {
            initMenus(new ArrayList<Menu>());
        }
        if (profil != null && module != null) {
            List<ModuleMenu> listMM = module.getListModuleMenu();
            List<ProfilMenu> menusProfil = profil.getListProfilMenu();
            System.err.println("Module :" + module + " -----> length :" + listMM.size());
            System.err.println("Profil :" + profil + " -----> length :" + menusProfil.size());

            if (!listMM.isEmpty() && !menusProfil.isEmpty()) {
                Set<Menu> menus = new HashSet<Menu>();

                for (ModuleMenu mm : listMM) {
                    menus.add(mm.getMenu());
                }
                for (ProfilMenu pm : menusProfil) {
                    menus.add(pm.getMenu());
                }
                initMenus(new ArrayList<Menu>(menus));
                deselectAll(this.root);
                for (ProfilMenu pm : menusProfil) {
                    Menu menu = pm.getMenu();
                    DefaultTreeNode node = new DefaultTreeNode();
                    node.setData(menu);
                    selectAll(this.root, node);
                }
            }
        }
    }

    public Poids getPoids() {
        return poids;
    }

    public void setPoids(Poids poids) {
        this.poids = poids;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    private void deselectAll(TreeNode node) {
        for (TreeNode childNode : node.getChildren()) {
            childNode.setSelected(false);
        }
    }

    private void selectAll(TreeNode parentNode, TreeNode childNode) {
        for (TreeNode node : parentNode.getChildren()) {
            Menu nodeMenu = (Menu) node.getData();
            Menu menu = (Menu) childNode.getData();
            if (nodeMenu.getIdMenu() == menu.getIdMenu()) {
                node.setSelected(true);
                if (selectedNodes == null) {
                    selectedNodes = new TreeNode[1];
                    selectedNodes[0] = node;
                } else {
                    int lenght = selectedNodes.length;
                    TreeNode[] tmp = selectedNodes;
                    selectedNodes = new TreeNode[lenght + 1];
                    System.arraycopy(tmp, 0, selectedNodes, 0, lenght);
                    selectedNodes[lenght] = node;
                }
                node.getParent().setExpanded(true);
            }
            if (node.getChildCount() > 0) {
                node.setExpanded(true);
                selectAll(node, childNode);
            }
        }
    }

    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;

    }

    /*
     * charger la liste des menus dans le Tree
     */
    private void initMenus(List<Menu> list) {
        this.root = new DefaultTreeNode("Root", null);
        List<Menu> menus = list;
        if (!menus.isEmpty()) {
            Collections.sort(menus, new Menucomparator());
            for (Menu menu : menus) {
                if (!menu.hasParent()) {
                    TreeNode node = new DefaultTreeNode(menu, this.root);
                    if (menu.hasChildren()) {
                        loadChildren(node, menu);
                    }
                }
            }
        }

    }

    // charger la liste des sous-menus dans le TreeNode parent
    private void loadChildren(TreeNode parentNode, Menu menuParent) {
        for (Menu child : menuParent.getChildren()) {
            TreeNode node = new DefaultTreeNode(child, parentNode);
            if (child.hasChildren()) {
                loadChildren(node, child);
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.module != null ? this.module.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ModuleProfilPoids other = (ModuleProfilPoids) obj;
        if (this.module != other.module && (this.module == null || !this.module.equals(other.module))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ModuleProfilPoids{" + "module=" + module + ", profil=" + profil + ", poids=" + poids + '}';
    }
}
