package com.land.back.util;

import java.text.DecimalFormat;

public class UtilBodyEmail {

	public static DecimalFormat formatDecimal = new DecimalFormat("$ ###,###,###,###,###,###,###,###,###,###.00");
	
	public static String plantillaNotaCredito(String doa,String Modelo,String Serie,String Factura) {
		String header="<!DOCTYPE html><html>" + 
				"<meta charset=\"UTF-8\">" + 
				"<meta name=\"viewport\" content=\"width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1\">" + 
				" <title>" + 
				"     	AOC" + 
				" </title>" + 
				"<body>" + 
				"<header>" + 
				"<h1><b>AOC Web</b></h1>" + 
				"</header>";
		String contenido="<main>" + 
				"<div class=\"contenedor\">" + 
				"<h3>Favor de Generar Nota de Crédito a AEM Computación por el siguiente producto: </h3>" + 
				"<b>DOA: " +doa+ 
				"<br/>MODELO: "+ Modelo + 
				"<br/>SERIE: "+Serie + 
				"<br/> FACTURA: </b>"+Factura + 
				"</div>" + 
				"</main>";
		String footer="</body>" + 
				"<footer>" + 
				"<h5 ><strong>&copy; Copyright 2017 </strong> " + 
				"<a style='color: white' href='http://latin.aoc.com/'> AOC </a> Derechos Reservados. " + 
				"<b > AOC Web </b></h5><br/>" + 
				"</footer>" + 
				"<div class=\"contenedor_footer\">" + 
				"<br/>La información contenida en este mensaje es confidencial y privada, está protegida por secreto profesional y está destinada únicamente para el uso de" + 
				"la(s) persona(s) arriba indicada(s). Está estrictamente prohibida cualquier difusión, distribución, impresión, edición no autorizada, o copia de este mensaje." + 
				" Si ha recibido esta comunicación o copia de este mensaje por error, favor de desecharlo permanentemente o destruirlo inmediatamente y comunicarlo al remitente" + 
				" o llamar al (52) (55) 51332500. El uso o diseminación no autorizados de esta información confidencial serán perseguidos conforme a derecho. Este mensaje no c" + 
				"rea obligaciones para el remitente ni pretende ser usado como medio para celebrar convenios o contratos de cualquier tipo. Gracias " + 
				"<br/><br/>The information contained in this message is protected by professional privilege, is confidential and private, and is intended only for the use of the" + 
				" individual(s) named above. Any unauthorized dissemination, distribution, edition, print or copy of this communicatión is strictly prohibited. If by error, you " + 
				"have received this communication, or its copy, please delete it or destroy it immediately and contact the sender, or call (52) (55) 51332500. The unauthorized us" + 
				"e or dissemination of this confidential information will be sanctioned according to the applicable law. This data message does not" + 
				" create obligations for the sender and is not an instrument to agree on contracts of any kind. Thank You." + 
				"</div>";
		String estilos="<style type=\"text/css\"> " + 
				"body{ " + 
				"	font-family: 'times new roman', sans-serif; " + 
				"} " + 
				"header{ " + 
				"	width: 100%; " + 
				"	height: 10%; " + 
				"	display: block; " + 
				"	background: #00537B; " + 
				"	color: white; " + 
				"    text-align: center; " + 
				"	position: absolute; " + 
				"	top: 0; " + 
				"	left: 0; " + 
				"	z-index: 100; " + 
				"} " + 
				"main{ " + 
				"	display: block; " + 
				"	margin-top: 5%; " + 
				"	width: 100%; " + 
				"	height: 40%; " + 
				"	background:#DEDEDE; " + 
				"	color:  #00537B; " + 
				"} " + 
				"footer{ " + 
				"	position: fixed; " + 
				"	display: block; " + 
				"	width: 100%; " + 
				"	margin-top: 5%; " + 
				"	background: #00537B; " + 
				"	text-align: center; " + 
				"    justify-content: center; " + 
				"    clear: both; " + 
				"} " + 
				".contenedor{ " + 
				"	width: 98%; " + 
				"	margin: auto; " + 
				"	display: table;" + 
				"} " + 
				".contenedor_footer{" + 
				"	position: absolute;" + 
				"    width: 98%;" + 
				"	margin-top: 11%;" + 
				"	display: table;" + 
				"	background: white;" + 
				"	text-align:justify;" + 
				"	font: small-caps 70%/150% serif;" + 
				"}" + 
				"</style>" + 
				"</html>";
	return header+contenido+footer+estilos ;
	}
	
	public static String pantillaRestablecimiento(String url) {
		String cabecera = "<h1 style='color: #00537B;text-align: center'><b>AOC Web</b></h1>"
				+ "<h3 style='color: #00537B;text-align: center'>Restablecimiento de contraseña</h3>";
		String contenido = "<div style='text-align:center'>" + "<table border='0' >"
				+ "<caption><strong style='color: white;'>Resumen de Operación</strong></caption>" + "<tr>"
				+ "<td style='background-color:#00537B;'><strong><h4 style='color: white;'>" + url
				+ "</h4></strong></td>" + "</tr>" + "</table>" + "</div>";
		String footer = "<br/><footer><h5 style='text-align:center'><strong>&copy; Copyright 2017 </strong> "
				+ "<a style='color: #00537B' href='http://latin.aoc.com/'> AOC </a> Derechos Reservados. "
				+ " <b style='color: #00537B'> AOC Web </b></h5><br/>"
				+ "<div style='text-align:justify;font: small-caps 70%/150% serif;'>"
				+ "<br/>La información contenida en este mensaje es confidencial y privada, está protegida por secreto profesional y está destinada únicamente para el uso de"
				+ "la(s) persona(s) arriba indicada(s). Está estrictamente prohibida cualquier difusión, distribución, impresión, edición no autorizada, o copia de este mensaje."
				+ " Si ha recibido esta comunicación o copia de este mensaje por error, favor de desecharlo permanentemente o destruirlo inmediatamente y comunicarlo al remitente"
				+ " o llamar al (52) (55) 51332500. El uso o diseminación no autorizados de esta información confidencial serán perseguidos conforme a derecho. Este mensaje no c"
				+ "rea obligaciones para el remitente ni pretende ser usado como medio para celebrar convenios o contratos de cualquier tipo. Gracias"
				+ "<br/><br/>The information contained in this message is protected by professional privilege, is confidential and private, and is intended only for the use of the"
				+ " individual(s) named above. Any unauthorized dissemination, distribution, edition, print or copy of this communication is strictly prohibited. If by error, you "
				+ "have received this communication, or its copy, please delete it or destroy it immediately and contact the sender, or call (52) (55) 51332500. The unauthorized us"
				+ "e or dissemination of this confidential information will be sanctioned according to the applicable law. This data message does not"
				+ " create obligations for the sender and is not an instrument to agree on contracts of any kind. Thank You."
				+ "</div></footer>";
		String cuerpoCorreo = cabecera + "<br/>" + contenido + "<br/>" + footer;
		return cuerpoCorreo;
	}

	public static String mailAppWhere() {
		return "";
		// SMTPMessage m = new SMTPMessage(session);
		// MimeMultipart content = new MimeMultipart("related");
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// String date = sdf.format(new Date());
		//
		// // ContentID is used by both parts
		// String cid = ContentIdGenerator.getContentId();
		//
		// // HTML part
		// MimeBodyPart textPart = new MimeBodyPart();
		//
		//
		// if(nombreCliente.length() > 0 && nombreBeneficiario.length() > 0){
		// textPart.setText("<html><head>"
		// + "<title></title>"
		// + "</head>\n"
		// + "<body>"
		// + "<div><img src=\"cid:"
		// + cid
		// + "\" /></div><br>"
		// + "<span style='color: #000000; font-weight: bold;
		// font-family:arial;'>&Aacute;rea de Cumplimiento: Se comunica que
		// existen acciones pendientes de revisar en el proceso de
		// autorizaci&oacute;n</span>"
		// + "<p>&nbsp;</p>"
		// + "<p><span style='color: #4a0061; font-family:arial;'>Fecha de
		// B&uacute;squeda: " + date +".</span>"
		// + "</p>"
		// + "<p>&nbsp;</p>"
		// + "<p><span style='color: #4a0061; font-family:arial;'>Cliente
		// encontrado en listas: " + nombreCliente +".</span>"
		// + "</p>"
		// + "<p><span style='color: #4a0061; font-family:arial;'>ID Cliente: "
		// + sMensaje + ".</span>"
		// + "</p>"
		// + "<p><span style='color: #4a0061; font-family:arial;'>Status:
		// 1.</span>"
		// + "</p>"
		// + "<p>&nbsp;</p>"
		// + "<hr>"
		// + "<p>&nbsp;</p>"
		// + "<p><span style='color: #4a0061; font-family:arial;'>Beneficiario
		// encontrado en listas: " + nombreBeneficiario + ".</span>"
		// + "</p>"
		// + "<p><span style='color: #4a0061; font-family:arial;'>ID
		// Beneficiario: " + sMensaje2 + ".</span>"
		// + "</p>"
		// + "<p><span style='color: #4a0061; font-family:arial;'>Status:
		// 1.</span>"
		// + "</p>"
		// + "</p>"
		// + "<p><span style='color: #4a0061; font-family:arial;'></span></p>"
		// + "</body></html>",
		// "US-ASCII", "html");
		//
		//
		// }else{
		// if(nombreCliente.length() > 0){
		// textPart.setText("<html><head>"
		// + "<title></title>"
		// + "</head>\n"
		// + "<body>"
		// + "<div><img src=\"cid:"
		// + cid
		// + "\" /></div><br>"
		// + "<span style='color: #000000; font-weight: bold;
		// font-family:arial;'>&Aacute;rea de Cumplimiento: Se comunica que
		// existen acciones pendientes de revisar en el proceso de
		// autorizaci&oacute;n</span>"
		// + "<p>&nbsp;</p>"
		// + "<p><span style='color: #4a0061; font-family:arial;'>Fecha de
		// B&uacute;squeda: " + date +".</span>"
		// + "</p>"
		// + "<p>&nbsp;</p>"
		// + "<p><span style='color: #4a0061; font-family:arial;'>Cliente
		// encontrado en listas: " + nombreCliente +".</span>"
		// + "</p>"
		// + "<p><span style='color: #4a0061; font-family:arial;'>ID Cliente: "
		// + sMensaje + ".</span>"
		// + "</p>"
		// + "<p><span style='color: #4a0061; font-family:arial;'>Status:
		// 1.</span>"
		// + "</p>"
		//
		// + "<p><span style='color: #4a0061; font-family:arial;'></span></p>"
		// + "</body></html>",
		// "US-ASCII", "html");
		//
		// }else{
		// if(nombreBeneficiario.length() > 0){
		// textPart.setText("<html><head>"
		// + "<title></title>"
		// + "</head>\n"
		// + "<body>"
		// + "<div><img src=\"cid:"
		// + cid
		// + "\" /></div><br>"
		// + "<span style='color: #000000; font-weight: bold;
		// font-family:arial;'>&Aacute;rea de Cumplimiento: Se comunica que
		// existen acciones pendientes de revisar en el proceso de
		// autorizaci&oacute;n</span>"
		// + "<p>&nbsp;</p>"
		// + "<p><span style='color: #4a0061; font-family:arial;'>Fecha de
		// B&uacute;squeda: " + date +".</span>"
		// + "</p>"
		// + "<p>&nbsp;</p>"
		// + "<p><span style='color: #4a0061; font-family:arial;'>Beneficiario
		// encontrado en listas: " + nombreBeneficiario + ".</span>"
		// + "</p>"
		// + "<p><span style='color: #4a0061; font-family:arial;'>ID
		// Beneficiario: " + sMensaje2 + ".</span>"
		// + "</p>"
		// + "<p><span style='color: #4a0061; font-family:arial;'>Status:
		// 1.</span>"
		// + "</p>"
		//
		// + "<p><span style='color: #4a0061; font-family:arial;'></span></p>"
		// + "</body></html>",
		// "US-ASCII", "html");
		//
		//
		// }
		// }
		//
		// }
		//
		//
		//
		//
		//
		//
		// content.addBodyPart(textPart);
		//
		// // Image part
		// MimeBodyPart imagePart = new MimeBodyPart();
		// imagePart.attachFile(Propiedades.get("MAIL_IMG"));
		// imagePart.setContentID("<" + cid + ">");
		// imagePart.setDisposition(MimeBodyPart.INLINE);
		// content.addBodyPart(imagePart);
		//
		// m.setContent(content);
		// m.setSubject(sAsunto);
		// return m;

	}

	public static void main(String[] args) {
		System.out.println("");
	}
}
