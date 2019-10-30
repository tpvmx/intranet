package com.land.back.jpa.tx.config;

import java.util.Date;
import java.util.List;

import com.land.back.entities.Usuario;
import com.land.back.jpa.tx.util.EntidadGenerica;

public interface IEntityManagerJPA
{
	public Date getDate() throws Exception;
	public <T extends EntidadGenerica> List<T> searchGeneric(T objBuscar) throws Exception;
	public <T extends EntidadGenerica> T searchGenericOnly(T objBuscar) throws Exception;
	public <T extends EntidadGenerica> T searchGenericForId(T objBuscar) throws Exception;
	public <T extends EntidadGenerica> T saveUpdateGeneric(T objGuardar, Usuario userAct)throws Exception;
	public <T extends EntidadGenerica> T removeGeneric(T objRemove) throws Exception;
	public TransaccionDTO ejecutaTransaccion(TransaccionDTO transaccionDTO) throws Exception;
	public void sendEmail(String email, String subject, String bodyMessage) throws Exception;
}
