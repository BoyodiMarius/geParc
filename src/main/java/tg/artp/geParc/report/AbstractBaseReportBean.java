/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.report;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

public  class AbstractBaseReportBean {

    public enum ExportOption {

        PDF, HTML, EXCEL, RTF, XML, CSV
    }
    private ExportOption exportOption;
    private final String COMPILE_DIR = "/resources/report/";
    
    protected  JRMapCollectionDataSource jRMapCollectionDataSource;

    protected  JRBeanCollectionDataSource jRBeanCollectionDataSource;

    protected  String compileFileName;

    protected  Map<String, Object> reportParameters;
    
    protected JasperPrint jasperPrint;

    /**
     *
     * @return
     */
    

    public AbstractBaseReportBean() {
        super();
    }

    public void prepareReport() throws JRException, IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        ServletContext context = (ServletContext) externalContext.getContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        ReportConfigUtil.compileReport(context, getCompileDir(), getCompileFileName());

        File reportFile = new File(ReportConfigUtil.getJasperFilePath(context, getCompileDir(), getCompileFileName() + ".jasper"));

        JasperPrint jasperPrintTmp = ReportConfigUtil.fillReport(reportFile, getReportParameters(), getjRMapCollectionDataSource());

        this.jasperPrint = jasperPrintTmp;
        request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrintTmp);
        response.sendRedirect(request.getContextPath() + "/servlets/report/" + getExportOption());

        FacesContext.getCurrentInstance().responseComplete();
    }

    public void prepareReportWithParam(String compileFileName, Map<String, Object> reportParameters, JRMapCollectionDataSource jrMapCollectionDataSource) throws JRException, IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        ServletContext context = (ServletContext) externalContext.getContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        ReportConfigUtil.compileReport(context, getCompileDir(), compileFileName);

        File reportFile = new File(ReportConfigUtil.getJasperFilePath(context, getCompileDir(), compileFileName + ".jasper"));

        this.jasperPrint = ReportConfigUtil.fillReport(reportFile, reportParameters, jrMapCollectionDataSource);
        

        request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, this.jasperPrint);
        response.sendRedirect(request.getContextPath() + "/servlets/report/" + getExportOption());

        FacesContext.getCurrentInstance().responseComplete();
    }

    public void prepareReportWithParam(String compileFileName, Map<String, Object> reportParameters, JRBeanCollectionDataSource jrBeanCollectionDataSource) throws JRException, IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        ServletContext context = (ServletContext) externalContext.getContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        ReportConfigUtil.compileReport(context, getCompileDir(), compileFileName);

        File reportFile = new File(ReportConfigUtil.getJasperFilePath(context, getCompileDir(), compileFileName + ".jasper"));

        this.jasperPrint = ReportConfigUtil.fillReport(reportFile, reportParameters, jrBeanCollectionDataSource);

        request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, this.jasperPrint);
        response.sendRedirect(request.getContextPath() + "/servlets/report/" + getExportOption());

        FacesContext.getCurrentInstance().responseComplete();
    }
    
//    public void prepareReportWithConnexion(String compileFileName, Map<String, Object> reportParameters, 
//             Connection connexion) throws JRException, IOException{
//          ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//
//        ServletContext context = (ServletContext) externalContext.getContext();
//        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
//        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
//
//        ReportConfigUtil.compileReport(context, getCompileDir(), compileFileName);
//
//        File reportFile = new File(ReportConfigUtil.getJasperFilePath(context, getCompileDir(), compileFileName + ".jasper"));
//
//        JasperPrint jasperPrint = ReportConfigUtil.fillReport(reportFile, reportParameters, connexion);
//
//        request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
//        response.sendRedirect(request.getContextPath() + "/servlets/report/" + getExportOption());
//
//
//        FacesContext.getCurrentInstance().responseComplete();
//         
//     }

    public ExportOption getExportOption() {
        return exportOption;
    }

    public void setExportOption(ExportOption exportOption) {
        this.exportOption = exportOption;
    }

    protected String getCompileDir() {
        return COMPILE_DIR;
    }

    public JRMapCollectionDataSource getjRMapCollectionDataSource() {
        return jRMapCollectionDataSource;
    }

    public void setjRMapCollectionDataSource(JRMapCollectionDataSource jRMapCollectionDataSource) {
        this.jRMapCollectionDataSource = jRMapCollectionDataSource;
    }

    
   
    public JRBeanCollectionDataSource getjRBeanCollectionDataSource() {
        return jRBeanCollectionDataSource;
    }

    public void setjRBeanCollectionDataSource(JRBeanCollectionDataSource jRBeanCollectionDataSource) {
        this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
    }

    public String getCompileFileName() {
        return compileFileName;
    }

    public void setCompileFileName(String compileFileName) {
        this.compileFileName = compileFileName;
    }

    public Map<String, Object> getReportParameters() {
        return reportParameters;
    }

    public void setReportParameters(Map<String, Object> reportParameters) {
        this.reportParameters = reportParameters;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }
    
    
    

}
