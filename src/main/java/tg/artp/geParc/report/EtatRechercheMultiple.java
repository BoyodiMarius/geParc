/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tg.artp.geParc.report;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

/**
 *
 * @author MANU
 */
public class EtatRechercheMultiple extends AbstractBaseReportBean{
    
    private List<?> etats;
    private boolean map;
    
    public EtatRechercheMultiple(List<?> etatsP){
        this.etats = etatsP;
        this.map = false;
    }
    
    public EtatRechercheMultiple(List<Map<String, ?>> etatsP, boolean map) {
        this.etats = etatsP;
        this.map = true;
    }

    //@Override
    public JRMapCollectionDataSource getJRMapCollectionDataSource() {
        return null;
    }

   // @Override
    public JRBeanCollectionDataSource getJRBeanCollectionDataSource() {
        return new JRBeanCollectionDataSource(this.etats);
    }

    public List<?> getEtats() {
        return etats;
    }

    public void setEtats(List<Etat> etats) {
        this.etats = etats;
    }
    
    public void exporter() throws JRException, IOException{
        this.prepareReport();
    }
    
    public void exporter(Map<String, Object> parametres) throws JRException, IOException{
        if (map) {
            this.prepareReportWithParam(this.getCompileFileName(), parametres, new JRMapCollectionDataSource((List)this.etats));
        } else {
            this.prepareReportWithParam(this.getCompileFileName(), parametres, new JRBeanCollectionDataSource(this.etats));
        }
    }
    
    public void changerFormat(String format){
        if(format.equalsIgnoreCase(AbstractBaseReportBean.ExportOption.PDF.toString()))
            this.setExportOption(AbstractBaseReportBean.ExportOption.PDF);
        else if(format.equalsIgnoreCase(AbstractBaseReportBean.ExportOption.HTML.toString()))
            this.setExportOption(AbstractBaseReportBean.ExportOption.HTML);
        else if(format.equalsIgnoreCase(AbstractBaseReportBean.ExportOption.EXCEL.toString()))
            this.setExportOption(AbstractBaseReportBean.ExportOption.EXCEL);
        else if(format.equalsIgnoreCase(AbstractBaseReportBean.ExportOption.RTF.toString()))
            this.setExportOption(AbstractBaseReportBean.ExportOption.RTF);
        else if(format.equalsIgnoreCase(AbstractBaseReportBean.ExportOption.XML.toString()))
            this.setExportOption(AbstractBaseReportBean.ExportOption.XML);
        else if(format.equalsIgnoreCase(AbstractBaseReportBean.ExportOption.CSV.toString()))
            this.setExportOption(AbstractBaseReportBean.ExportOption.CSV);
    }
}
