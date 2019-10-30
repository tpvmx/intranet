package comland.front.dao.noticias;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.land.back.entities.Empleado;
import com.land.back.entities.Noticias;
import com.land.back.entities.NoticiasImagenes;
import com.land.back.entities.Permiso;
import com.land.back.entities.TipoNoticia;
import com.land.back.jpa.tx.config.TransaccionDTO;
import com.land.back.jpa.tx.util.EntidadGenerica;
import com.land.back.jpa.tx.util.NamesTX;
import com.land.back.util.Constantes;
import com.land.front.dao.DAOComun;
import com.land.front.util.UtilTimestamp;

public class SummaryNewsDAO extends DAOComun {

	public SummaryNewsDAO(Empleado empleado) throws Exception {
		super(empleado);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;
	private List<Noticias> listNoticiasResumen = new ArrayList<Noticias>();
	private LinkedHashMap<Long, NoticiasImagenes> hmBase64Images = new LinkedHashMap<Long, NoticiasImagenes>();
	private List<Noticias> listNoticiasBanner = new ArrayList<Noticias>();
	private List<TipoNoticia> listTipoNoticia;
	Date date = new Date();

	public void buscaNoticias() throws Exception {
		Noticias ga = new Noticias();
		ga.queryAllNoticias();
		listNoticiasResumen = session.searchGeneric(ga);
	}

	public void buscaTipoNoticia() throws Exception {
		TipoNoticia unid = new TipoNoticia();
		unid.queryAll();
		listTipoNoticia = session.searchGeneric(unid);

		TipoNoticia tipo = new TipoNoticia();
		tipo.setIdTipoNoticia(-1L);
		tipo.setDescripcion(getTexto("cboSeleccione"));
		listTipoNoticia.add(0, tipo);
	}

	public void buscaNoticiasInicio() throws Exception {
		Noticias ga = new Noticias();
		ga.setFecact(UtilTimestamp.getDateCeroHoras(new Date()));
		ga.setIdtiponoticia(Constantes.TIPO_PRINCIPAL);
		ga.queryNoticiasFechaTipo();
		ArrayList<Noticias> listNt = (ArrayList<Noticias>) session.searchGeneric(ga);
		int contador = 1;
		listNoticiasResumen = new ArrayList<Noticias>();
		for (Noticias noticias : listNt) {
			if (contador > 8)
				break;
			NoticiasImagenes ima = new NoticiasImagenes();
			ima.setIdNoticia(noticias.getIdNoticias());
			ima.setPrincipal(Constantes.IMAGEN_PRINCIPAL);
			ima.queryXNoticiaTipo();
			ima = session.searchGenericOnly(ima);
			if (ima != null) {
				hmBase64Images.put(noticias.getIdNoticias(), ima);
			}
			listNoticiasResumen.add(noticias);
			contador++;
		}
	}

	public Boolean eliminaNoticia(Noticias myNoticia) throws Exception {
		LinkedHashMap<String, Object> hmParams = new LinkedHashMap<String, Object>();
		hmParams.put("not", myNoticia);
		TransaccionDTO dto = new TransaccionDTO();
		dto.setClassTx(NamesTX.TxEliminaNoticia);
		dto.setHmParametrosTx(hmParams);
		dto = session.ejecutaTransaccion(dto, empleadoActual);
		return dto.isComplete();
	}

	public void buscaNoticiasResumen(long idTipoNoticia) throws Exception {
		Noticias ga = new Noticias();
		ga.setIdtiponoticia(idTipoNoticia);
		ga.queryNoticiasTipoOrden();
		listNoticiasResumen = session.searchGeneric(ga);
	}

	public void buscaNoticiasBanner() throws Exception {
		Noticias ga = new Noticias();
		ga.setFecact(UtilTimestamp.getDateCeroHoras(new Date()));
		ga.setIdtiponoticia(Constantes.TIPO_INTERES);
		ga.queryNoticiasFechaTipo();
		listNoticiasResumen = session.searchGeneric(ga);

	}

	public void ActualizaOrden() throws Exception {
		int a = 1;
		for (Noticias noticias : listNoticiasResumen) {
			noticias.setOrden(a);
			session.saveUpdateGeneric(noticias, empleadoActual);
			a++;
		}
	}

	public void buscaNoticiasInicioBanner() throws Exception {
		Noticias ga = new Noticias();
		ga.setFecact(UtilTimestamp.getDateCeroHoras(new Date()));
		ga.setIdtiponoticia(Constantes.TIPO_INTERES);
		ga.queryNoticiasFechaTipo();
		listNoticiasBanner = session.searchGeneric(ga);
	}

	public <T extends EntidadGenerica> T buscaXID(T obj) throws Exception {
		return session.searchGenericForId(obj);
	}

	public List<Noticias> getListNoticiasResumen() {
		return listNoticiasResumen;
	}

	public void setListNoticiasResumen(List<Noticias> listNoticiasResumen) {
		this.listNoticiasResumen = listNoticiasResumen;
	}

	public List<Noticias> getListNoticiasBanner() {
		return listNoticiasBanner;
	}

	public void setListNoticiasBanner(List<Noticias> listNoticiasBanner) {
		this.listNoticiasBanner = listNoticiasBanner;
	}

	public LinkedHashMap<Long, NoticiasImagenes> getHmBase64Images() {
		return hmBase64Images;
	}

	public void setHmBase64Images(LinkedHashMap<Long, NoticiasImagenes> hmBase64Images) {
		this.hmBase64Images = hmBase64Images;
	}

	public List<TipoNoticia> getListTipoNoticia() {
		return listTipoNoticia;
	}

	public void setListTipoNoticia(List<TipoNoticia> listTipoNoticia) {
		this.listTipoNoticia = listTipoNoticia;
	}

	public Permiso buscaPermisoIP(String ipAddress) throws Exception {

		Permiso per = new Permiso();
		per.setIp(ipAddress);
		per.queryXIP();
		per = session.searchGenericOnly(per);
		return per;
	}

	public String buscaCumpleaneros() throws Exception {
		String cadena = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int mesActual = cal.get(Calendar.MONTH) + 1;
		int diaActual = cal.get(Calendar.DAY_OF_MONTH);

		List<Object> listaCumpleaños = buscaCumpleanerosXMes(false);
		for (Object dat : listaCumpleaños) {
			Object[] datos = (Object[]) dat;
			String nombre = (String) datos[0];
			int mes = ((Double) datos[1]).intValue();
			int dia = ((Double) datos[2]).intValue();
			if (mesActual == mes) {
				if (diaActual == dia) {
					cadena += "  !!" + getTexto("hoyCumple") + " " + nombre.toUpperCase() + "!!  ";
					continue;
				}
			}
			String proximos = getTexto("felizCumpleanios");
			if (cadena.indexOf(proximos) < 0) {
				cadena += proximos;
			}
			cadena += "  " + nombre + "  " + dia + "-" + UtilTimestamp.getInstance().getMesLetra(mes - 1) + "   ";

		}
		return cadena;
	}

	private boolean proximoMes = false;
	private List<Object> response;

	private List<Object> buscaCumpleanerosXMes(boolean nextMes) throws Exception {

		String sqlCumpleañeros = "SELECT " + "	 a1.nombre, " + "    a1.mes, " + "    a1.dia " + "FROM " + "    ( "
				+ "        SELECT "
				+ "          nombre_empleado || ' ' || apellido_paterno || ' ' || apellido_materno AS nombre,"
				+ "            EXTRACT(MONTH FROM fecha_nacimiento) AS mes, "
				+ "            EXTRACT(DAY FROM fecha_nacimiento)   AS dia " + "        FROM "
				+ "            public.tb_empleado) a1 " + "WHERE " + "      a1.mes = ? "
				+ " AND a1.dia BETWEEN ? AND ? ORDER BY a1.dia";

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int mesActual = 0;
		if (nextMes) {
			mesActual = cal.get(Calendar.MONTH) + 2;
			if (mesActual > 12) {
				mesActual = 1;// Colocamos Enero
			}
		} else {
			mesActual = cal.get(Calendar.MONTH) + 1;
		}
		int diaActual = cal.get(Calendar.DAY_OF_MONTH);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		cal2.add(Calendar.DAY_OF_MONTH, 4);
		cal2.getTime();
		int diaFinal = cal2.get(Calendar.DAY_OF_MONTH);
		if (!nextMes) {
			if (diaFinal < diaActual) {// -2
				proximoMes = true;
				cal2.add(Calendar.DAY_OF_MONTH, -1);
				diaFinal = cal2.get(Calendar.DAY_OF_MONTH);
				if (diaFinal < diaActual) {// -1
					cal2.add(Calendar.DAY_OF_MONTH, -1);
					diaFinal = cal2.get(Calendar.DAY_OF_MONTH);
					if (diaFinal < diaActual) {
						cal2.add(Calendar.DAY_OF_MONTH, -1);
						diaFinal = cal2.get(Calendar.DAY_OF_MONTH);
						if (diaFinal < diaActual) {
							cal2.add(Calendar.DAY_OF_MONTH, -1);
							diaFinal = cal2.get(Calendar.DAY_OF_MONTH);
						}
					}
				}
			}
		}
		List<Object> params = new ArrayList<Object>();
		params.add(mesActual);
		if (nextMes)
			params.add(1);
		else
			params.add(diaActual);
		params.add(diaFinal);

		List<Object> res = session.searchSQLNative(sqlCumpleañeros, params);
		if (response != null) {
			for (Object object : res) {
				response.add(object);
			}
		} else
			response = res;
		if (proximoMes) {
			proximoMes = false;
			buscaCumpleanerosXMes(true);
		}
		return this.response;
	}

}
