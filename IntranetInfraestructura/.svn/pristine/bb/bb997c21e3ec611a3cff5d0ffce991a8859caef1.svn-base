package com.land.back.jpa.tx.config;

import java.io.Serializable;
import java.util.HashMap;

import com.land.back.jpa.tx.util.NamesTX;

/**
 * @author LuisGlz
 */

@SuppressWarnings("serial")
public class TransaccionDTO implements Serializable {
	protected HashMap<String, Object> hmParametrosTx = new HashMap<String, Object>();
	protected HashMap<String, Object> hmResultadoTx = null;
	protected Boolean complete = Boolean.FALSE;
	protected NamesTX classTx = null;

	public TransaccionDTO() {
	}

	public TransaccionDTO(HashMap<String, Object> hmResultadoTX) {
		this.hmResultadoTx = hmResultadoTX;
	}

	public NamesTX getClassTx() {
		return classTx;
	}

	public void setClassTx(NamesTX classTx) {
		this.classTx = classTx;
	}

	public HashMap<String, Object> getHmParametrosTx() {
		return hmParametrosTx;
	}

	public void setHmParametrosTx(HashMap<String, Object> hmParametrosTx) {
		this.hmParametrosTx = hmParametrosTx;
	}

	public HashMap<String, Object> getHmResultadoTx() {
		return hmResultadoTx;
	}

	public void setHmResultadoTx(HashMap<String, Object> hmResultadoTx) {
		this.hmResultadoTx = hmResultadoTx;
	}

	public Boolean isComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
}
