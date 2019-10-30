package com.land.back.jpa.tx.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

/**
 * @author LuisGlz
 */

@SuppressWarnings("serial")
public abstract class EntidadGenerica implements IQuerys,Serializable {
	
	protected String queryHql = "";
	protected Map<Object, Object> params = new HashMap<Object, Object>();
	protected String toString = "";
	protected Map<String, Object> bonusData = new HashMap<String, Object>(0);
	public abstract <T extends EntidadGenerica> EntidadGenerica cloneObj(T obj);

	//Datos CATALOGOS
	public int statusCat = 0;
	
	public void limpiarParametros(){
		params = new HashMap<Object, Object>();
	}
	
	@Transient
	public String getStatusToString(Boolean status){
		return status.booleanValue() ? "ACTIVO" : "INACTIVO";
	}
	
	@Transient
	public Map<String, Object> getBonusData() {
		return bonusData;
	}
	
	@Transient
	public void setBonusData(Map<String, Object> bonusData) {
		this.bonusData = bonusData;
	}
	
	@Transient
	public void putBonusData(String key, Object val){
		bonusData.put(key, val);
	}
	
	@Transient
	public Object getBonusDataAt(String key) {
		return bonusData.get(key);
	}
	
}
