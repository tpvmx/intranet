package com.land.back.tx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.land.back.entities.Empleado;
import com.land.back.entities.Noticias;
import com.land.back.entities.NoticiasImagenes;
import com.land.back.jpa.SesionJPANotClose;
import com.land.back.jpa.tx.config.TransaccionComun;
import com.land.back.jpa.tx.config.TransaccionDTO;

public class TxEliminaNoticia extends TransaccionComun {
	private Noticias myNoticia;
	private NoticiasImagenes myNoticiaImagen = new NoticiasImagenes();
	

	@Override
	public TransaccionDTO ejecutaTransaccion(SesionJPANotClose sesion, Empleado empleadoActual) throws Exception {
		session = sesion;
		List<NoticiasImagenes> ListnoticiaImagenes = new ArrayList<NoticiasImagenes>();
		
		myNoticiaImagen.setIdNoticia(myNoticia.getIdNoticias());
		myNoticiaImagen.queryXNoticia();
		ListnoticiaImagenes = session.searchGeneric(myNoticiaImagen);
        for(NoticiasImagenes var : 	ListnoticiaImagenes) {
        	session.removeGeneric(var);
        }
        session.removeGeneric(myNoticia);
		return dtoResponse;
	}

	@Override
	public void inicializaParametros(HashMap<String, Object> hmParametrosTX) {
		myNoticia = (Noticias)hmParametrosTX.get("not");
		
	}

}
