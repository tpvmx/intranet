package com.land.front.util;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class CreateJasper {


	@SuppressWarnings("rawtypes")
	private static JasperPrint createJasperPrint(Map<String, Object> params, List dataList, String relativeWebPath) throws JRException {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);

		JRBeanCollectionDataSource collectionDS = new JRBeanCollectionDataSource(dataList);
		JasperReport report = (JasperReport) JRLoader.loadObject(new File(absoluteDiskPath));
		//JasperReport report = JasperCompileManager.compileReport(absoluteDiskPath);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, collectionDS);
		return jasperPrint;
	}
	
	/**
	 * 
	 * relativeWebPath = "\\resources\\jrxml\\prueba1.jrxml";
	 * @throws JRException 
	 */
	@SuppressWarnings("rawtypes") 
	public static byte[] createByteArrayPdf(Map<String, Object> params, List dataList, String relativeWebPath) throws JRException{
		JasperPrint jasperPrint = createJasperPrint(params, dataList, relativeWebPath);
		byte[] byteArr = JasperExportManager.exportReportToPdf(jasperPrint);
		return byteArr;
	}
//
//	@SuppressWarnings("rawtypes") 
//	public static byte[] createByteArrayMSExcel(Map<String, Object> params, List dataList, String relativeWebPath) throws JRException{
//		JasperPrint jasperPrint = createJasperPrint(params, dataList, relativeWebPath);
//		ByteArrayOutputStream  xlsReport = new ByteArrayOutputStream();
//		
//		JRXlsExporter exporter = new JRXlsExporter();
//		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
//
//		SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
//		xlsReportConfiguration.setOnePagePerSheet(false);
//		xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(false);
//		xlsReportConfiguration.setDetectCellType(false);
//		xlsReportConfiguration.setWhitePageBackground(false);
//		
//		exporter.setConfiguration(xlsReportConfiguration);
//		
//		exporter.exportReport();
//		byte[] byteArr = xlsReport.toByteArray();
//		return byteArr;
//	}

}
