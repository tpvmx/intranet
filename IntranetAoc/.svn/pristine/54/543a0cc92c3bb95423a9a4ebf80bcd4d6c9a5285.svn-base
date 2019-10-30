package com.land.front.beans.calendario;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.land.back.entities.Evento;
import com.land.back.entities.TipoEvento;
import com.land.front.beans.BeanComun;
import com.land.front.beans.BeanSession;
import com.land.front.dao.CalendarioDAO;
import com.land.front.util.UtilMac;

@ManagedBean
@ViewScoped
public class BeanCalendario extends BeanComun {

	/**
	 * Daniel
	 */
	private static final long serialVersionUID = 1L;

	private ScheduleModel eventModel;
	private ScheduleModel lazyEventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();
	private CalendarioDAO dao;
	private Evento myEvento = new Evento();
	private TipoEvento myTipoEvento = new TipoEvento();
	private String mensajeEvento = "";

	@Override
	public void datosIniciales() {
		eventModel = new DefaultScheduleModel();
		buscaEventos();
		buscaTiposEventos();
		try {
			dao.buscaTipoEventos1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void init() {
		try {
			BeanSession beanLogin = (BeanSession) getSessionObj("beanSession");
			dao = new CalendarioDAO(beanLogin.getPermisoActual().getEmpleado());
			datosIniciales();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buscaTiposEventos() {
		try {
			dao.buscaTipoEventos();
		} catch (Exception e) {
			e.printStackTrace();
			alertError("ERROR " + e);
		}
	}

	public String macAddress() {
		try {
			String mac = UtilMac.getInstance().getMacAddres();
			alertInfo("MAC ACTUAL:" + mac);
			if (mac == null || mac.isEmpty()) {
				return "/template/500.xhtml?faces-redirect=true";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/inicio";
	}

	public void guardaEvento() {
		try {
			if (myEvento.getDescripcion().isEmpty()) {
				alertAlerta(getTexto("msgRequerido").replaceAll("&&&&", getTexto("calenTitulo")));
				return;
			}

			if (myEvento.getFecha_inicio() == null) {
				alertAlerta(getTexto("msgRequerido").replaceAll("&&&&", getTexto("calenFechaIni")));
				return;
			}

			if (myEvento.getFecha_termino() == null) {
				alertAlerta(getTexto("msgRequerido").replaceAll("&&&&", getTexto("calenFechaTerm")));
				return;
			}
			if (myEvento.getTipoevento().getId_tipoevento().intValue() == -1) {
				alertAlerta(getTexto("msgRequerido").replaceAll("&&&&", getTexto("calenTipoEvento")));
				return;
			}
			dao.guardaEvento(myEvento);
			alertInfo(getTexto("dicMsgExitoso"));
			buscaEventos();
			myEvento = new Evento();
		} catch (Exception e) {
			e.printStackTrace();
			alertError(getTexto("beanCalendarioERROR"));
		}
	}

	public void buscaEventos() {
		try {
			eventModel = new DefaultScheduleModel();
			dao.buscaEventos();
			for (Evento var : getListEventos()) {
				DefaultScheduleEvent event = new DefaultScheduleEvent();
				event.setTitle(var.getDescripcion());
				event.setStartDate(var.getFecha_inicio());
				event.setEndDate(var.getFecha_termino());
				event.setStyleClass(var.getTipoevento().getStyle());
				event.setData(var);
				eventModel.addEvent(event);
			}

		} catch (Exception e) {
			e.printStackTrace();
			alertError("ERROR ");
		}
	}

	public void filtraEventos() {
		try {
			String filtro = "";
			for (TipoEvento item : getListTiposEventos1()) {
				if (item.isSeleccion()) {
					filtro += item.getId_tipoevento() + ",";
				}
			}
			eventModel = new DefaultScheduleModel();
			if (filtro.isEmpty()) {
				datosIniciales();
			} else {
				dao.buscaEventosFiltrado(filtro.substring(0, filtro.length() - 1));
				for (Evento var : getListEventos()) {
					DefaultScheduleEvent event = new DefaultScheduleEvent();
					event.setTitle(var.getDescripcion());
					event.setStartDate(var.getFecha_inicio());
					event.setEndDate(var.getFecha_termino());
					event.setStyleClass(var.getTipoevento().getStyle());
					event.setData(var);
					eventModel.addEvent(event);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void eliminaEvento() {
		try {
			dao.eliminaEvento(myEvento);
			buscaEventos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}

	private Calendar today() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

		return calendar;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public void addEvent(ActionEvent actionEvent) {
		if (event.getId() == null)
			eventModel.addEvent(event);
		else
			eventModel.updateEvent(event);

		event = new DefaultScheduleEvent();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		try {
			mensajeEvento = getTexto("calenMod");
			event = (ScheduleEvent) selectEvent.getObject();
			myEvento = (Evento) event.getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onDateSelect(SelectEvent selectEvent) {
		mensajeEvento = getTexto("calenEvent");
		myEvento = new Evento();
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved",
				"Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized",
				"Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public Evento getMyEvento() {
		return myEvento;
	}

	public void setMyEvento(Evento myEvento) {
		this.myEvento = myEvento;
	}

	public List<Evento> getListEventos() {
		return dao.getListEventos();
	}

	public List<TipoEvento> getListTiposEventos() {
		return dao.getListTiposEventos();
	}

	public List<TipoEvento> getListTiposEventos1() {
		return dao.getListTiposEventos1();
	}

	public TipoEvento getMyTipoEvento() {
		return myTipoEvento;
	}

	public void setMyTipoEvento(TipoEvento myTipoEvento) {
		this.myTipoEvento = myTipoEvento;
	}

	public String getMensajeEvento() {
		return mensajeEvento;
	}

	public void setMensajeEvento(String mensajeEvento) {
		this.mensajeEvento = mensajeEvento;
	}

}