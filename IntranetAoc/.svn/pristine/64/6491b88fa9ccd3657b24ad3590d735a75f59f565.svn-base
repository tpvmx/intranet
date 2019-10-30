package com.land.front.beans;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.land.back.entities.Empleado;
import com.land.back.entities.GastoDetalle;
import com.land.back.entities.GastoViaje;
import com.land.back.nomapping.ConceptoDinamico;
import com.land.back.nomapping.DiaDinamico;
import com.land.back.util.Constantes;
import com.land.front.dao.GastoViajeDAO;
import com.land.front.util.UtilSesion;
import com.land.front.util.UtilTimestamp;

@ManagedBean
@ViewScoped
public class BeanGastoViaje extends BeanComun {
	/**
	 * DANIEL
	 */
	private static final long serialVersionUID = 1L;
	private GastoViajeDAO dao;
	private GastoViaje myGastoViaje = new GastoViaje();
	private Boolean checktableta = false;
	private Boolean checktelefono = false;
	private Boolean checkmonitor = false;
	private Boolean tableta = true;
	private Boolean telefono = true;
	private Boolean monitor = true;
	private GastoDetalle myGastoDetalle = new GastoDetalle();
	private List<GastoDetalle> listGastos = new ArrayList<GastoDetalle>();
	private List<ConceptoDinamico> listConcepto = new ArrayList<ConceptoDinamico>();
	private List<DiaDinamico> listDiasTemp = new ArrayList<DiaDinamico>();
	private List<GastoDetalle> listGastoTemp = new ArrayList<GastoDetalle>();
	int ordenActual = 1;
	private boolean activaAdd = false;
	private Boolean save = true;

	@Override
	public void datosIniciales() {
		try {
			dao.buscaXEstatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void init() {
		try {
			BeanSession beanLogin = (BeanSession) getSessionObj("beanSession");
			empleadoActual = beanLogin.getPermisoActual().getEmpleado();
			dao = new GastoViajeDAO(empleadoActual);
			datosIniciales();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validaCampos() {
		if (checktableta) {
			tableta = false;
		} else {
			tableta = true;
		}
		if (checktelefono) {
			telefono = false;
		} else {
			telefono = true;
		}
		if (checkmonitor) {
			monitor = false;
		} else {
			monitor = true;
		}
	}
	
	public void eliminaRegistro(GastoViaje gv) {
		try {
			dao.eliminaGastoViaje(gv);
			datosIniciales();
			alertInfo(getTexto("beanGastoViajeEliminarExitoso"));
		} catch (Exception e) {
			e.printStackTrace();
			alertError(getTexto("beanGastoViajeEliminarFallido"));
		}
	}

	public void addConcepto() {
		cambiaListaDias();
		GastoDetalle gato = new GastoDetalle();
		gato.setOrden(ordenActual++);

		// for (DiaDinamico dia : listDiasTemp) {
		// cadenaFinal += dia.getDia() + "|" + dia.getValor() + ",";
		// }
		gato.setListDias(listDiasTemp);
		listGastos.add(gato);
	}

	public List<DiaDinamico> obtenListaDias(ConceptoDinamico gas) {
		if (gas != null) {
			return gas.getList();
		}
		return null;
	}

	public String obtenConcepto(ConceptoDinamico gas) {
		if (gas != null) {
			return gas.getConcepto();
		}
		return null;
	}

	public void cambiaListaDias() {
		if (myGastoViaje.getFecha_inicioviaje() == null) {
			return;
		}
		if (myGastoViaje.getFecha_terminoviaje() == null) {
			return;
		}

		activaAdd = true;

		long diasDiferecia = UtilTimestamp.getInstance().calculaDiasDeDiff(myGastoViaje.getFecha_inicioviaje(),
				myGastoViaje.getFecha_terminoviaje());
		if (diasDiferecia < 0) {
			alertAlerta("definir mensaje");
			return;
		}
		List<DiaDinamico> listDia = new ArrayList<DiaDinamico>();
		Calendar calIni = Calendar.getInstance();
		calIni.setTime(myGastoViaje.getFecha_inicioviaje());

		Calendar calTer = Calendar.getInstance();
		calTer.setTime(myGastoViaje.getFecha_terminoviaje());
		int diaTer = calTer.get(Calendar.DAY_OF_MONTH);

		for (int i = 0; i <= diasDiferecia; i++) {
			int diaDelMes = calIni.get(Calendar.DAY_OF_MONTH);
			// int dayOfWeek = calIni.get(Calendar.DAY_OF_WEEK);

			// if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
			// calIni.add(Calendar.DAY_OF_MONTH, 1);
			// continue;
			// }
			if (diaDelMes != diaTer) {
				DiaDinamico dia = new DiaDinamico(cerosIzquierda(diaDelMes, 2));
				listDia.add(dia);
			} else {
				DiaDinamico dia = new DiaDinamico(cerosIzquierda(diaDelMes, 2));
				listDia.add(dia);
				break;
			}
			calIni.add(Calendar.DAY_OF_MONTH, 1);
		}
		listDiasTemp = listDia;
		// GastoDetalle det = new GastoDetalle();
		// det.setListDias(listDiasTemp);
		// listGastoTemp.add(det);
		// }
	}

	public void nuevo() {
		myGastoViaje = new GastoViaje();
		myGastoDetalle = new GastoDetalle();
		ordenActual = 1;
		listGastos.clear();
		listDiasTemp.clear();
		checktableta = false;
		checktelefono = false;
		checkmonitor = false;
		tableta = true;
		telefono = true;
		monitor = true;
		activaAdd = false;
	}

	public void eliminarConcepto(int orden) {
		List<GastoDetalle> nueva = new ArrayList<GastoDetalle>();
		int ordennuevo = 1;
		for (GastoDetalle gasto : listGastos) {
			if (gasto.getOrden().intValue() == orden) {
				continue;
			} else {
				gasto.setOrden(ordennuevo);
				nueva.add(gasto);
				ordennuevo++;
			}
		}
		listGastos = nueva;
		ordenActual = ordennuevo;
	}

	public void guardaGasto() {
		try {
			if (myGastoViaje.getFecha_solicitud() == null) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("gastoViajeFechaSoli")));
				return;
			}
			// if (myGastoViaje.getNivel() == null) {
			// alertAlerta(getTexto("msgRequerido").replace("&&&&",
			// getTexto("gastoViajeNivel")));
			// return;
			// }
			// if (myGastoViaje.getTarjeta().isEmpty()) {
			// alertAlerta(getTexto("msgRequerido").replace("&&&&",
			// getTexto("gastoViajeTarjeta")));
			// return;
			// }
			// if (myGastoViaje.getBanco().isEmpty()) {
			// alertAlerta(getTexto("msgRequerido").replace("&&&&",
			// getTexto("gastoViajeBanco")));
			// return;
			// }
			// if (myGastoViaje.getClabe() == null) {
			// alertAlerta(getTexto("msgRequerido").replace("&&&&",
			// getTexto("gastoViajeClabe")));
			// return;
			// }
			// if (myGastoViaje.getCuenta() == null) {
			// alertAlerta(getTexto("msgRequerido").replace("&&&&",
			// getTexto("gastoViajeCuenta")));
			// return;
			// }
			if (myGastoViaje.getDestino().isEmpty()) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("gastoViajeDestino")));
				return;
			}
			if (myGastoViaje.getMotivo().isEmpty()) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("gastoViajeMotivo")));
				return;
			}
			if (myGastoViaje.getFecha_inicioviaje() == null) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("gastosViajeFechaInicio")));
				return;
			}
			if (myGastoViaje.getFecha_terminoviaje() == null) {
				alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("gastosViajeFechaTermino")));
				return;
			}
			int porcentaje = 0;
			if (checktableta) {
				if (myGastoViaje.getPortableta() != null) {
					myGastoViaje.setTableta(1);
					porcentaje += myGastoViaje.getPortableta();
				} else {
					alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("gastoViajeTableta")));
					return;
				}
			} else {
				myGastoViaje.setTableta(0);
			}
			if (checktelefono) {
				if (myGastoViaje.getPortelefono() != null) {
					myGastoViaje.setTelefono(1);
					porcentaje += myGastoViaje.getPortelefono();
				} else {
					alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("gastoViajeTelefono")));
					return;
				}
			} else {
				myGastoViaje.setTelefono(0);
			}
			if (checkmonitor) {
				if (myGastoViaje.getPormonitor() != null) {
					myGastoViaje.setMonitor(1);
					porcentaje += myGastoViaje.getPormonitor();
				} else {
					alertAlerta(getTexto("msgRequerido").replace("&&&&", getTexto("gastoViajeMonitor")));
					return;
				}
			} else {
				myGastoViaje.setMonitor(0);
			}
			if (porcentaje > 100) {
				alertAlerta(getTexto("beanGastoViajePorcentaje100"));
				return;
			}

			myGastoViaje.setNumEmpleado(empleadoActual);
			dao.guardaViatico(myGastoViaje, listGastos);
			alertInfo(getTexto("dicMsgExitoso"));
			nuevo();
			dao.buscaXEstatus();
		} catch (Exception e) {
			e.printStackTrace();
			alertAlerta(getTexto("beanGastoViajeERROR"));
		}
	}

	public void validaFechaInicio() {
		try {
			if (myGastoViaje.getFecha_inicioviaje() == null) {
				return;
			}
			Calendar fechaInicio = Calendar.getInstance();
			fechaInicio.setTime(myGastoViaje.getFecha_inicioviaje());
			Calendar fechaActual = Calendar.getInstance();
			long diasDiferecia = UtilTimestamp.getInstance().calculaDiasDeDiff(fechaActual, fechaInicio);
			fechaActual.add(Calendar.DAY_OF_MONTH, 2);
			int contador = 0;
			for (int i = 0; i <= diasDiferecia; i++) {
				int diaofWeek = fechaInicio.get(Calendar.DAY_OF_WEEK);
				if (diaofWeek == Calendar.SATURDAY || diaofWeek == Calendar.SUNDAY) {
					fechaInicio.add(Calendar.DAY_OF_MONTH, -1);
				} else {
					fechaInicio.add(Calendar.DAY_OF_MONTH, -1);
					contador++;
				}
			}
			fechaInicio.add(Calendar.DAY_OF_MONTH, contador);
			if (fechaInicio.after(fechaActual) || fechaInicio == fechaActual) {
				save = false;
			} else {
				alertError(getTexto("beanGastoViajeDiasHabiles"));
				save = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			alertAlerta(getTexto("ERROR"));
		}
	}

	public void calculaTotal() {
		try {
			System.out.println("HOLA");
		} catch (Exception e) {
			e.printStackTrace();
			alertAlerta(getTexto("ERROR"));
		}
	}

	@SuppressWarnings("rawtypes")
	public void gastoReporte() {
		Map<String, Object> params = new HashMap<String, Object>();
		List listaDetails = new ArrayList();
		String dias = "";
		String valores = "";
		try {
			GastoViaje gastoViaje = dao.buscaUltimoGastoViaje();
			if (gastoViaje == null) {
				alertAlerta("No se encontro gasto de viaje pendiente.");
				return;
			}
			List<GastoDetalle> listDet = dao.buscaDetallesGasto(gastoViaje);
			Empleado rh = dao.buscaEncargadoRH();
			double totalfinal = 0D;
			double subtotal = 0D;
			for (GastoDetalle gasto : listDet) {
				dias = "";
				valores = "";
				HashMap<String, Object> hm = new HashMap<String, Object>(0);
				hm.put("orden", gasto.getOrden().toString());
				hm.put("concepto", gasto.getConcepto());
				String[] separarDias = gasto.getDias().split("\\|");
				for (String separado : separarDias) {
					String[] separarValores = separado.split(",");
					String dia = separarValores[0];
					String valor = separarValores[1];
					if (valor.length() <= 6) {
						if (valor.length() <= 3) {
							valores += espaciosIzquierdaDecimal(new Double(valor), 8) + "|";
							dias += espaciosIzquierda(dia, 8) + "|";
						} else {
							valores += espaciosIzquierdaDecimal(new Double(valor), 6) + "|";
							dias += espaciosIzquierda(dia, 8) + "|";
						}
					} else {
						int longitudvalor = valor.length();
						valores += espaciosIzquierdaDecimal(new Double(valor), longitudvalor) + "|";
						dias += espaciosIzquierda(dia, longitudvalor + 2) + "|";
					}
					subtotal += new Double(valor);
					totalfinal += new Double(valor);
					hm.put("total", formatDecimal2.format(subtotal));
				}
				hm.put("dias", valores);
				subtotal = 0D;
				hm.put("total1", formatDecimal2.format(totalfinal));
				listaDetails.add(hm);
			}
			// params.put("dias", valores);
			params.put("diasDinamico", dias);
			params.put("titulo", Constantes.TITULO);
			params.put("nombre", empleadoActual.getNombre());
			params.put("fechasolicitud", formatFecha.format(gastoViaje.getFecha_solicitud()));
			if (gastoViaje.getPortableta() == null) {
				params.put("segmentotableta", " ");
				params.put("porcentajetableta", " ");
			} else {
				params.put("segmentotableta", "Tableta");
				params.put("porcentajetableta", gastoViaje.getPortableta().toString());
			}
			if (gastoViaje.getPortelefono() == null) {
				params.put("segmentotelefono", " ");
				params.put("porcentajetelefono", " ");
			} else {
				params.put("segmentotelefono", "Telefono");
				params.put("porcentajetelefono", gastoViaje.getPortelefono().toString());
			}
			if (gastoViaje.getPormonitor() == null) {
				params.put("segmentomonitor", " ");
				params.put("porcentajemonitor", " ");
			} else {
				params.put("segmentomonitor", "Monitor");
				params.put("porcentajemonitor", gastoViaje.getPormonitor().toString());
			}
			params.put("puesto", empleadoActual.getPuesto());
			// params.put("nivel", gastoViaje.getNivel().toString());
			params.put("numempleado", empleadoActual.getNum_empleado().toString());
			params.put("tarjeta", empleadoActual.getTarjeta());
			params.put("banco", empleadoActual.getBanco());
			params.put("clabe", empleadoActual.getClabe().toString());
			params.put("cuenta", empleadoActual.getCuenta().toString());
			params.put("destino", gastoViaje.getDestino());
			params.put("motivodelviaje", gastoViaje.getMotivo());
			params.put("fechainicio", formatFecha.format(gastoViaje.getFecha_inicioviaje()));
			params.put("fechatermino", formatFecha.format(gastoViaje.getFecha_terminoviaje()));
			params.put("nombresolicitante", empleadoActual.getNombreCompleto());
			params.put("puestosolicitante", "SOLICITANTE");
			if (rh != null) {
				params.put("nombreautorizacionrh", rh.getNombreCompleto());
				params.put("puestoautorizacionrh", "REVISIÓN");
			}
			params.put("foliousoarea", gastoViaje.getFolio().toString());
			if (empleadoActual.getJefe() == null) {
				params.put("nombreautorizacionjefe", " ");
				params.put("puestoautorizacionjefe", " ");
			} else {
				params.put("nombreautorizacionjefe", empleadoActual.getJefe().getNombreCompleto());
				params.put("puestoautorizacionjefe", "AUTORIZACIÓN");
			}
			params.put("imagenaoc",
					new File(UtilSesion.getExternalContext().getRealPath("/resources/images/aoc_reporte.jpg")));
			// Para Imprimir PDF
			lanzaReportePDF("/resources/reportes/gastoViaje.jasper", params, listaDetails, "Viaticos");
		} catch (Exception e) {
			e.printStackTrace();
			alertError("Error al construir el reporte");
		}
	}

	public GastoViajeDAO getDao() {
		return dao;
	}

	public void setDao(GastoViajeDAO dao) {
		this.dao = dao;
	}

	public GastoViaje getMyGastoViaje() {
		return myGastoViaje;
	}

	public List<GastoViaje> getListGastosInactivos() {
		return dao.getListGastosInactivos();
	}

	public void setMyGastoViaje(GastoViaje myGastoViaje) {
		this.myGastoViaje = myGastoViaje;
	}

	public Boolean getTableta() {
		return tableta;
	}

	public void setTableta(Boolean tableta) {
		this.tableta = tableta;
	}

	public Boolean getTelefono() {
		return telefono;
	}

	public void setTelefono(Boolean telefono) {
		this.telefono = telefono;
	}

	public Boolean getMonitor() {
		return monitor;
	}

	public void setMonitor(Boolean monitor) {
		this.monitor = monitor;
	}

	public Boolean getChecktableta() {
		return checktableta;
	}

	public void setChecktableta(Boolean checktableta) {
		this.checktableta = checktableta;
	}

	public Boolean getChecktelefono() {
		return checktelefono;
	}

	public void setChecktelefono(Boolean checktelefono) {
		this.checktelefono = checktelefono;
	}

	public Boolean getCheckmonitor() {
		return checkmonitor;
	}

	public void setCheckmonitor(Boolean checkmonitor) {
		this.checkmonitor = checkmonitor;
	}

	public List<GastoDetalle> getListGastos() {
		return listGastos;
	}

	public void setListGastos(List<GastoDetalle> listGastos) {
		this.listGastos = listGastos;
	}

	public GastoDetalle getMyGastoDetalle() {
		return myGastoDetalle;
	}

	public void setMyGastoDetalle(GastoDetalle myGastoDetalle) {
		this.myGastoDetalle = myGastoDetalle;
	}

	public List<ConceptoDinamico> getListConcepto() {
		return listConcepto;
	}

	public void setListConcepto(List<ConceptoDinamico> listConcepto) {
		this.listConcepto = listConcepto;
	}

	public List<GastoDetalle> getListGastoTemp() {
		return listGastoTemp;
	}

	public void setListGastoTemp(List<GastoDetalle> listGastoTemp) {
		this.listGastoTemp = listGastoTemp;
	}

	public boolean isActivaAdd() {
		return activaAdd;
	}

	public void setActivaAdd(boolean activaAdd) {
		this.activaAdd = activaAdd;
	}

	public Boolean getSave() {
		return save;
	}

	public void setSave(Boolean save) {
		this.save = save;
	}

}
