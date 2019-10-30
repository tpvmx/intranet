package com.land.back.jpa.tx.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.land.back.jpa.SesionJPAIntranet;

public abstract class BusquedaComun extends EntidadGenerica {
	
	private static final long serialVersionUID = 1L;
	private Class<?> clase;
	protected Set<String[]> attConsulta;
	protected Map<String, String> paramBusqueda;
	

	public abstract Set<String[]> attConsulta();
	public abstract Map<String, String> paramBusqueda();
	public abstract String className();
	
	public BusquedaComun() {
		super();
		attConsulta();
		paramBusqueda();
	}
	
	public void queryBusquedaComun(Object cveVal, Object descVal){
		this.queryHql = "SELECT m FROM " + className() + " m "
				+ " WHERE m." + paramBusqueda.get("cve") + " = :cve "
				+ " OR m." + paramBusqueda.get("desc") + " LIKE :desc"
				+ " ORDER BY m." + paramBusqueda.get("desc") + " ASC";
		this.params.put("cve", cveVal);
		this.params.put("desc", "%" + descVal + "%");
	}
	
	public List<BusquedaComun> realizarBusqueda(SesionJPAIntranet session, Object cve, Object desc) {
		
		try {
			clase = Class.forName(className());
			String typeCve = clase.getDeclaredField(paramBusqueda.get("cve")).getType().getTypeName();
			cve = convertObject(cve, typeCve);
			
			BusquedaComun bc = (BusquedaComun) clase.newInstance();
			bc.queryBusquedaComun(cve, desc);
			List<BusquedaComun> l = session.searchGeneric(bc);
			return l;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Object convertObject(Object val, String typeName) {
		if(typeName.equals("java.lang.Long")){
			if(val.equals("")){
				val = new Long(0);
			}
			val = Long.valueOf(val.toString());
		} else if(typeName.equals("java.lang.Integer")){
			if(val.equals("")){
				val = new Integer(0);
			}
			val = Integer.valueOf(val.toString());
		}
		return val;
	}
}
