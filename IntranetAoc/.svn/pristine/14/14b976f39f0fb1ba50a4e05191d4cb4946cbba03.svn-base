package com.land.front.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class UtilDownload {
	
	public static void exportTextPlainOLD(byte[] exportContent, String fileName) {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.responseReset();
		ec.setResponseContentType("text/plain");
		ec.setResponseContentLength(exportContent.length);
		String attachmentName = "attachment; filename=\"" + fileName + ".csv\"";
		ec.setResponseHeader("Content-Disposition", attachmentName);
		try {
			OutputStream output = ec.getResponseOutputStream();
			output.write(exportContent);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		fc.responseComplete();
	}
	
	public static void exportPDFOLD(byte[] exportContent, String fileName) {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.responseReset();
		ec.setResponseContentType("application/pdf");
		ec.setResponseContentLength(exportContent.length);
		String attachmentName = "attachment; filename=\"" + fileName + ".pdf\"";
		ec.setResponseHeader("Content-Disposition", attachmentName);
		try {
			OutputStream output = ec.getResponseOutputStream();
			output.write(exportContent);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		fc.responseComplete();
	}
	/**
	 * 
	 * @param exportContent
	 * @param fileName
	 * Works for CSV fileName: "filename.csv"
	 */
	public static void exportTextPlain(byte[] exportContent,String fileName){
		export(exportContent, "text/plain", fileName);
	}
	
	public static void exportOctetStream(byte[] exportContent,String fileName){
		export(exportContent, "application/octet-stream", fileName);
	}
	
	public static void exportPdf(byte[] exportContent,String fileName){
		export(exportContent, "application/pdf", fileName);
	}
	
	public static void exportExcel(byte[] exportContent,String fileName){
		export(exportContent, "application/vnd.ms-excel", fileName);
	}
	
	public static void openPdf(byte[] exportContent,String fileName){
		open(exportContent, "application/pdf", fileName);
	}
	
	private static void export(byte[] exportContent, String responseContentType, String fileName) {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.responseReset();
		ec.setResponseContentType(responseContentType);
		ec.setResponseContentLength(exportContent.length);
		String attachmentName = "attachment; filename=\"" + fileName + "\"";
		ec.setResponseHeader("Content-disposition", attachmentName);
		try {
			OutputStream output = ec.getResponseOutputStream();
			output.write(exportContent);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		fc.responseComplete();
	}
	
	private static void open(byte[] exportContent, String responseContentType, String fileName) {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.responseReset();
		ec.setResponseContentType(responseContentType);
		ec.setResponseContentLength(exportContent.length);
		String attachmentName = "inline; filename=\"" + fileName + "\"";
		ec.setResponseHeader("Content-disposition", attachmentName);
		try {
			OutputStream output = ec.getResponseOutputStream();
			output.write(exportContent);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		fc.responseComplete();
	}

}