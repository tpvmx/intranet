package comland.front.dao.noticias;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.land.back.entities.Empleado;
import com.land.back.entities.Noticias;
import com.land.back.entities.NoticiasImagenes;
import com.land.back.entities.TipoNoticia;
import com.land.back.jpa.tx.util.EntidadGenerica;
import com.land.front.dao.DAOComun;

public class InsertNewsDAO extends DAOComun {

	private List<TipoNoticia> listTipoNoticia;
	private LinkedHashMap<Integer, NoticiasImagenes> hmImagenesXNota = new LinkedHashMap<Integer, NoticiasImagenes>();
	Date date = new Date();

	public InsertNewsDAO(Empleado empleadoActual) throws Exception {
		super(empleadoActual);
	}

	private static final long serialVersionUID = 1L;

	public <T extends EntidadGenerica> T buscaXID(T obj) throws Exception {
		return session.searchGenericForId(obj);
	}

	public Noticias InsertarNoticia(Noticias myNoticia) throws Exception {

		if (myNoticia.getIdNoticias().intValue() == 0) {
			Integer res = ordenNoticia(myNoticia.getIdtiponoticia()) + 1;
			myNoticia.setOrden(res);
		}
		myNoticia.setFeccrea(date);
		return session.saveUpdateGeneric(myNoticia, empleadoActual);

	}

	public void insertarImagenes(String nombreArchivo, String contenttype, byte[] imagen, long idNoticia, int principal)
			throws Exception {

		NoticiasImagenes not = new NoticiasImagenes();
		not.setPrincipal(principal);
		not.setIdNoticia(idNoticia);
		not.queryXNoticiaTipo();
		not = session.searchGenericOnly(not);
		if (not == null)
			not = new NoticiasImagenes();

		not.setNombreArchivo(nombreArchivo);
		not.setContentType(contenttype);
		not.setImagen(imagen);
		not.setIdNoticia(idNoticia);
		not.setPrincipal(principal);
		session.saveUpdateGeneric(not, empleadoActual);
		hmImagenesXNota.put(principal, not);
	}

	public void buscaTipoNoticia() throws Exception {
		TipoNoticia unid = new TipoNoticia();
		unid.queryAll();
		listTipoNoticia = session.searchGeneric(unid);

	}

	public Integer ordenNoticia(Long idTipoNoticia) throws Exception {
		Noticias unid = new Noticias();
		unid.setIdtiponoticia(idTipoNoticia);
		unid.queryOrdenNoticia();
		List<Object> r = session.searchGenericObject(unid);

		return (Integer) r.get(0);

	}

	public List<NoticiasImagenes> buscaImagenNoticia(Long id) throws Exception {
		NoticiasImagenes unid = new NoticiasImagenes();
		unid.setIdNoticia(id);
		unid.queryImage();
		return session.searchGeneric(unid);

	}

	public List<NoticiasImagenes> buscaImagenNoticiaGaleria(Long id) throws Exception {
		NoticiasImagenes unid = new NoticiasImagenes();
		unid.setIdNoticia(id);
		unid.queryImageGaleria();
		return session.searchGeneric(unid);

	}

	public NoticiasImagenes buscaImagenNoticiaTipoGaleria(Long id) throws Exception {
		NoticiasImagenes unid = new NoticiasImagenes();
		unid.setIdNoticia(id);
		unid.queryImageGaleryTipo();
		return session.searchGenericOnly(unid);

	}

	public List<TipoNoticia> getListTipoNoticia() {
		return listTipoNoticia;
	}

	public void setListTipoNoticia(List<TipoNoticia> listTipoNoticia) {
		this.listTipoNoticia = listTipoNoticia;
	}

	public void buscaImagenesXNota(Long idNoticiaParam) throws Exception {
		hmImagenesXNota = new LinkedHashMap<Integer, NoticiasImagenes>();
		NoticiasImagenes noticiaImagen = new NoticiasImagenes();
		noticiaImagen.setIdNoticia(idNoticiaParam);
		noticiaImagen.queryXNoticia();
		List<NoticiasImagenes> list = session.searchGeneric(noticiaImagen);
		for (NoticiasImagenes imagen : list) {
			hmImagenesXNota.put(imagen.getPrincipal(), imagen);
		}
	}

	public LinkedHashMap<Integer, NoticiasImagenes> getHmImagenesXNota() {
		return hmImagenesXNota;
	}

	public void setHmImagenesXNota(LinkedHashMap<Integer, NoticiasImagenes> hmImagenesXNota) {
		this.hmImagenesXNota = hmImagenesXNota;
	}

}
