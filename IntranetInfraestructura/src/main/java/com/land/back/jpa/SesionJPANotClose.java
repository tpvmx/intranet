package com.land.back.jpa;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import com.land.back.entities.Empleado;
import com.land.back.jpa.tx.util.EntidadGenerica;
import com.land.back.util.Constantes;

/**
 * @author LuisGlz
 */

@SuppressWarnings("unchecked")
public class SesionJPANotClose {

	private static SesionJPANotClose instance = null;
	private EntityManager entityManager = null;
	private Session mailSession = null;

	private static final Logger LOG = Logger.getLogger(SesionJPANotClose.class);

	private SesionJPANotClose(EntityManager entityManager, Session mailSession) {
		this.entityManager = entityManager;
		this.mailSession = mailSession;
	}

	public static SesionJPANotClose getInstance(EntityManager entityManager, Session mailSession) {
		instance = new SesionJPANotClose(entityManager, mailSession);
		return instance;
	}

	public Date getDate() throws Exception {
		try {
			Query qry = entityManager.createNativeQuery(" SELECT current_timestamp AS HOY ");
			Object fecha = qry.getSingleResult();
			if (fecha == null) {
				throw new Exception("Error al obtener la fecha.");
			}
			return (Date) fecha;
		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		} finally {
			System.gc();
		}

	}

	public <T extends EntidadGenerica> List<T> searchGeneric(T objBuscar) throws Exception {
		ArrayList<T> data = new ArrayList<T>();
		try {
			Query query = entityManager.createQuery(objBuscar.getQueryHql());
			Map<Object, Object> params = objBuscar.getParametros();
			if ((params != null) && (params.size() > 0)) {
				for (Object key : params.keySet()) {
					query.setParameter((String) key, params.get(key));
				}
			}
			query.setMaxResults(Integer.MAX_VALUE);

			// Linea para Refrescar objetos modificados en la DB en tiempo real
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			List<T> rs = query.getResultList();
			for (T obj : rs) {
				data.add(obj);
			}
			objBuscar.limpiarParametros();
		} catch (NoResultException e) {
			data = new ArrayList<T>();// Sin resultados
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error SesionJPANotClose searchGeneric: " + objBuscar.getClass().getCanonicalName(), e);
			throw e;
		} finally {
			System.gc();
		}
		return data;
	}

	public <T extends EntidadGenerica> T searchGenericOnly(T objBuscar) throws Exception {
		T data = null;
		try {
			Query query = entityManager.createQuery(objBuscar.getQueryHql());
			Map<Object, Object> params = objBuscar.getParametros();
			if ((params != null) && (params.size() > 0)) {
				for (Object key : params.keySet()) {
					query.setParameter((String) key, params.get(key));
				}
			}
			// Linea para Refrescar objetos modificados en la DB en tiempo real
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			Object rs = query.getSingleResult();
			data = (T) rs;
			objBuscar.limpiarParametros();
		} catch (NoResultException e) {
			data = null;// Sin resultados
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error SesionJPANotClose searchGenericOnly: " + objBuscar.getClass().getCanonicalName(), e);
			throw e;
		} finally {
			System.gc();
		}
		return data;
	}

	public <T extends EntidadGenerica> T searchGenericForId(T objBuscar) throws Exception {
		T data = null;
		try {
			data = (T) entityManager.find(objBuscar.getClass(), objBuscar.getId());
		} catch (NoResultException e) {
			data = null;// Sin resultados
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error SesionJPANotClose searchGenericForId: " + objBuscar.getClass().getCanonicalName(), e);
			throw e;
		} finally {
			System.gc();
		}
		return data;
	}

	public Object executeSQLNative(String sql, List<Object> params, boolean isResponse) throws Exception {
		try {
			Query query = entityManager.createNativeQuery(sql);
			int index = 1;
			for (Object obj : params) {
				query.setParameter(index, obj);
				index++;
			}
			return isResponse ? query.getResultList() : query.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}

	public <T extends EntidadGenerica> T saveUpdateGeneric(T objGuardar, Empleado empleadoActual) throws Exception {
		T obj = null;
		try {
			obj = (T) entityManager.find(objGuardar.getClass(), objGuardar.getId());
			if (obj == null) {
				// Guardamos
				entityManager.persist(objGuardar);
			} else {
				// Actualizacion
				obj = (T) obj.cloneObj(objGuardar);
				entityManager.merge(obj);
			}
			entityManager.flush();
		} catch (NoResultException e) {
			obj = null;// Sin resultados
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error SesionJPANotClose saveUpdateGeneric: " + objGuardar.getClass().getCanonicalName(), e);
			throw e;
		} finally {
			System.gc();
		}
		return objGuardar;
	}

	public <T extends EntidadGenerica> T saveGeneric(T objGuardar, Empleado empleadoActual) throws Exception {
		T obj = null;
		try {
			entityManager.persist(objGuardar);
		} catch (NoResultException e) {
			obj = null;// Sin resultados
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error SesionJPANotClose saveGeneric: " + objGuardar.getClass().getCanonicalName(), e);
			throw e;
		} finally {
			System.gc();
		}
		return objGuardar;
	}

	public <T extends EntidadGenerica> T saveUpdateGenericSinFlush(T objGuardar, Empleado empleadoActual) throws Exception {
		T obj = null;
		try {
			obj = (T) entityManager.find(objGuardar.getClass(), objGuardar.getId());
			if (obj == null) {
				// Guardamos
				entityManager.persist(objGuardar);
			} else {
				// Actualizacion
				obj = (T) obj.cloneObj(objGuardar);
				entityManager.merge(obj);
			}
		} catch (NoResultException e) {
			obj = null;// Sin resultados
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error SesionJPANotClose saveUpdateGenericSinFlush: " + objGuardar.getClass().getCanonicalName(),
					e);
			throw e;
		} finally {
			System.gc();
		}
		return objGuardar;
	}

	public <T extends EntidadGenerica> T removeGeneric(T objRemove) throws Exception {
		T obj = null;
		try {
			obj = (T) entityManager.find(objRemove.getClass(), objRemove.getId());
			entityManager.remove(obj);
			entityManager.flush();
		} catch (NoResultException e) {
			obj = null;// Sin resultados
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error SesionJPANotClose removeGeneric: " + objRemove.getClass().getCanonicalName(), e);
			throw e;
		} finally {
			System.gc();
		}
		return objRemove;
	}

	public void sendEmail(String email, String subject, String bodyMessage) throws Exception {

		MimeMessage message = new MimeMessage(mailSession);
		try {
			message.setSubject(subject, "utf-8");
			message.setRecipient(RecipientType.TO, new InternetAddress(email));

			message.setFrom(new InternetAddress(Constantes.EMAIL_CONTACTO_LANDSOFT, "Mensaje Aoc"));

			MimeBodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setContent(message, "text/html");

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messageBodyPart);
			message.setContent("Usuario: Text", "text/html; charset=utf-8");
			message.setContent(bodyMessage, "text/html; charset=utf-8");
			message.setHeader("X-Mailer", "MINIpay mailer www.minipay.eu");

			Transport.send(message);
		} catch (MessagingException ex) {
			throw ex;
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Conneccion Nativa de SQL para ejecutar querys con ResulSet,
	 * PreparetStatemen
	 * 
	 * @return
	 */
	public Connection getConnectionNative() {
		return entityManager.unwrap(Connection.class);
	}
}
