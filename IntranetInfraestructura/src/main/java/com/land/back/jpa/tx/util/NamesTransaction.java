package com.land.back.jpa.tx.util;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.land.back.tx.TxEliminaNoticia;
import com.land.back.tx.TxEliminaViatico;
import com.land.back.tx.TxGuardaViatico;
import com.land.back.tx.TxVacacion;

@SuppressWarnings("serial")
public class NamesTransaction implements Serializable {
	public static NamesTransaction obj = null;
	private LinkedHashMap<NamesTX, String> hmIds = new LinkedHashMap<NamesTX, String>();

	public NamesTransaction() {
		hmIds.put(NamesTX.TxEliminaNoticia, TxEliminaNoticia.class.getCanonicalName());
		hmIds.put(NamesTX.TxGuardaViatico, TxGuardaViatico.class.getCanonicalName());
		hmIds.put(NamesTX.TxVacacion, TxVacacion.class.getCanonicalName());
		hmIds.put(NamesTX.TxEliminaViatico, TxEliminaViatico.class.getCanonicalName());
	}

	public static NamesTransaction getInstance() {
		if (obj == null) {
			obj = new NamesTransaction();
		}
		return obj;
	}

	public LinkedHashMap<NamesTX, String> getHmIds() {
		return hmIds;
	}
}
