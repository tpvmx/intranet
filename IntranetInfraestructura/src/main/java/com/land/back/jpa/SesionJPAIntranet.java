package com.land.back.jpa;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import com.land.back.entities.ConstantesDB;
import com.land.back.entities.Empleado;
import com.land.back.jpa.tx.config.ConfigTransaccion;
import com.land.back.jpa.tx.config.TransaccionDTO;
import com.land.back.jpa.tx.config.TransaccionLauncher;
import com.land.back.jpa.tx.util.EntidadGenerica;
import com.land.back.jpa.tx.util.NamesTransaction;
import com.land.back.util.AOCException;
import com.land.back.util.Constantes;

@SuppressWarnings("unchecked")
@Stateless
public class SesionJPAIntranet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String DS_POSTGRES = "intranetjpa";

	// @PersistenceUnit(unitName = "intranetjpa")
	// private EntityManagerFactory entityFactory;

	private static SesionJPAIntranet instance = null;

	@Resource(name = "mail/AocMail")
	private Session mailSession;

	private static final Logger LOG = Logger.getLogger(SesionJPAIntranet.class);

	// public static SesionJPA getInstance() {
	// if (instance == null) {
	// instance = new SesionJPA();
	// }
	// return instance;
	// }

	public static SesionJPAIntranet getInstanceIndependiente() throws Exception {
		if (instance == null) {
			instance = new SesionJPAIntranet();
		}
		return instance;
	}

	// public Date getDate() throws Exception {
	// EntityManager entityManager = entityFactory.createEntityManager();
	// try {
	// Query qry = entityManager.createNativeQuery(" SELECT current_timestamp AS HOY
	// ");
	// Object fecha = qry.getSingleResult();
	//
	// if (fecha == null) {
	// throw new Exception("Error al obtener la fecha.");
	// }
	// return (Date) fecha;
	// } catch (SQLException e) {
	// System.out.println(e);
	// throw e;
	// } finally {
	// System.gc();
	// entityManager.close();
	// // entityFactory.close();
	// }
	//
	// }

	public Date getDateIndependiente() throws Exception {
		EntityManagerFactory entityFactoryIndependiente = Persistence.createEntityManagerFactory(DS_POSTGRES,
				new Properties());
		EntityManager entityManager = entityFactoryIndependiente.createEntityManager();
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
			entityManager.close();
			entityFactoryIndependiente.close();
		}

	}

	public List<Object> searchSQLNative(String sql, List<Object> params) throws Exception {
		EntityManagerFactory entityFactoryIndependiente = Persistence.createEntityManagerFactory(DS_POSTGRES,
				new Properties());
		EntityManager entityManager = entityFactoryIndependiente.createEntityManager();
		List<Object> result;
		Query query = entityManager.createNativeQuery(sql);
		try {
			int index = 1;
			for (Object obj : params) {
				query.setParameter(index, obj);
				index++;
			}
			result = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error searchSQLNative: ", e);
			System.out.println();
			throw e;
		} finally {
			System.gc();
			entityManager.close();
			entityFactoryIndependiente.close();
		}
		return result;
	}

	public <T extends EntidadGenerica> List<T> searchGeneric(T objBuscar) throws Exception {
		ArrayList<T> data = new ArrayList<T>();
		EntityManagerFactory entityFactoryIndependiente = Persistence.createEntityManagerFactory(DS_POSTGRES,
				new Properties());
		EntityManager entityManager = entityFactoryIndependiente.createEntityManager();
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
			LOG.error("Error searchGeneric: " + objBuscar.getClass().getCanonicalName(), e);
			System.out.println();
			throw e;
		} finally {
			System.gc();
			entityManager.close();
			entityFactoryIndependiente.close();
		}
		return data;
	}

	public <T extends EntidadGenerica> T searchGenericOnly(T objBuscar) throws Exception {
		T data = null;
		EntityManagerFactory entityFactoryIndependiente = Persistence.createEntityManagerFactory(DS_POSTGRES,
				new Properties());
		EntityManager entityManager = entityFactoryIndependiente.createEntityManager();
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
			List<Object> rs = query.getResultList();
			if (rs.isEmpty()) {
				data = null;
			} else {
				data = (T) rs.get(0);
			}
			objBuscar.limpiarParametros();
		} catch (NoResultException e) {
			data = null;// Sin resultados
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error searchGenericOnly: " + objBuscar.getClass().getCanonicalName(), e);
			throw e;
		} finally {
			System.gc();
			entityManager.close();
			entityFactoryIndependiente.close();
		}
		return data;
	}

	public <T extends EntidadGenerica> T searchGenericForId(T objBuscar) throws Exception {
		T data = null;
		EntityManagerFactory entityFactoryIndependiente = Persistence.createEntityManagerFactory(DS_POSTGRES,
				new Properties());
		EntityManager entityManager = entityFactoryIndependiente.createEntityManager();
		try {
			data = (T) entityManager.find(objBuscar.getClass(), objBuscar.getId());
		} catch (NoResultException e) {
			data = null;// Sin resultados
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error searchGenericForId: " + objBuscar.getClass().getCanonicalName(), e);
			throw e;
		} finally {
			System.gc();
			entityManager.close();
			entityFactoryIndependiente.close();
		}
		return data;
	}

	public <T extends EntidadGenerica> List<Object> searchGenericObject(T objBuscar) throws Exception {
		ArrayList<Object> data = new ArrayList<Object>();
		EntityManagerFactory entityFactoryIndependiente = Persistence.createEntityManagerFactory(DS_POSTGRES,
				new Properties());
		EntityManager entityManager = entityFactoryIndependiente.createEntityManager();
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
			List<Object> rs = query.getResultList();
			for (Object obj : rs) {
				data.add(obj);
			}
			objBuscar.limpiarParametros();
		} catch (NoResultException e) {
			data = new ArrayList<Object>();// Sin resultados
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error searchGeneric: " + objBuscar.getClass().getCanonicalName(), e);
			System.out.println();
			throw e;
		} finally {
			System.gc();
			entityManager.close();
			entityFactoryIndependiente.close();
		}
		return data;
	}

	public <T extends EntidadGenerica> T saveUpdateGeneric(T objGuardar, Empleado empleadoAct) throws Exception {
		T obj = null;
		EntityManagerFactory entityFactoryIndependiente = Persistence.createEntityManagerFactory(DS_POSTGRES,
				new Properties());
		EntityManager entityManager = entityFactoryIndependiente.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			obj = (T) entityManager.find(objGuardar.getClass(), objGuardar.getId());

			if (obj == null) {
				// Guardamos
				entityManager.persist(objGuardar);
			} else {
				// Actualizacin
				obj = (T) obj.cloneObj(objGuardar);
				entityManager.merge(obj);
			}
			tx.commit();
			// entityManager.flush();
		} catch (NoResultException e) {
			obj = null;// Sin resultados
			entityManager.getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			LOG.error("Error saveUpdateGeneric: " + objGuardar.getClass().getCanonicalName(), e);
			throw e;
		} finally {
			System.gc();
			entityManager.close();
			entityFactoryIndependiente.close();
		}
		return objGuardar;
	}

	public <T extends EntidadGenerica> T removeGeneric(T objRemove) throws Exception {
		T obj = null;
		EntityManagerFactory entityFactoryIndependiente = Persistence.createEntityManagerFactory(DS_POSTGRES,
				new Properties());
		EntityManager entityManager = entityFactoryIndependiente.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			obj = (T) entityManager.find(objRemove.getClass(), objRemove.getId());
			entityManager.remove(obj);
			entityManager.getTransaction().commit();
		} catch (NoResultException e) {
			obj = null;// Sin resultados
			entityManager.getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			LOG.error("Error removeGeneric: " + objRemove.getClass().getCanonicalName(), e);
			throw e;
		} finally {
			System.gc();
			entityManager.close();
			entityFactoryIndependiente.close();
		}
		return objRemove;
	}

	public TransaccionDTO ejecutaTransaccion(TransaccionDTO transaccionDTO, Empleado empleadoAct) throws Exception {
		EntityManagerFactory entityFactory2 = Persistence.createEntityManagerFactory(DS_POSTGRES, new Properties());
		EntityManager entityManager = entityFactory2.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			String canonicalName = NamesTransaction.getInstance().getHmIds().get(transaccionDTO.getClassTx());
			TransaccionLauncher<TransaccionDTO> objTx = ConfigTransaccion.getInstance().creaTransaccion(canonicalName);
			objTx.inicializaParametros(transaccionDTO.getHmParametrosTx());
			SesionJPANotClose sessionNotClose = SesionJPANotClose.getInstance(entityManager, mailSession);
			transaccionDTO = (TransaccionDTO) objTx.ejecutaTransaccion(sessionNotClose, empleadoAct);
			transaccionDTO.setClassTx(null);
			transaccionDTO.setHmParametrosTx(null);
			transaccionDTO.setComplete(Boolean.TRUE);
			tx.commit();

			return transaccionDTO;
		} catch (AOCException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error ejecutaTransaccion: " + transaccionDTO.getClass().getCanonicalName(), e);
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			System.out.println();
			System.gc();
			try {
				if (entityManager != null) {
					entityManager.close();
					entityFactory2.close();
				}
			} catch (Exception ex) {
				throw ex;
			}
		}
	}

	public void initConstants() throws Exception {
		Properties unaLista = null;
		EntityManagerFactory entityFactoryIndependiente = Persistence.createEntityManagerFactory(DS_POSTGRES,
				new Properties());
		EntityManager entityManager = entityFactoryIndependiente.createEntityManager();
		try {
			ConstantesDB unaConstante = new ConstantesDB();
			unaConstante.queryBuscaTodas();
			Query query = entityManager.createQuery(unaConstante.getQueryHql());

			// Linea para Refrescar objetos modificados en la DB en tiempo real
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			List<ConstantesDB> results = query.getResultList();
			if (results.isEmpty()) {
				System.out.println("NO SE ECONTRARON CONSTANTES");
				LOG.error("NO SE ECONTRARON CONSTANTES");
				return;
			}
			unaLista = new Properties();
			for (ConstantesDB con : results) {
				unaLista.setProperty(con.getDescripcion(), con.getValor());
			}
			Constantes.setConstantes(unaLista);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error initConstants: ", e);
			throw e;
		} finally {
			System.gc();
			entityManager.close();
			entityFactoryIndependiente.close();
		}
	}

	public void sendEmail(String email, String subject, String bodyMessage) throws Exception {
		InitialContext ic = new InitialContext();
		String snName = "mail/AocMail";
		try {
			Session mailSession = (Session) ic.lookup(snName);
			MimeMessage message = new MimeMessage(mailSession);
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

}
