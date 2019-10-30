package com.land.back.jpa.tx.config;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.land.back.entities.Empleado;
import com.land.back.jpa.SesionJPANotClose;

/**
 * @author LuisGlz
 */

public abstract class TransaccionComun implements TransaccionLauncher<TransaccionDTO>
{
	protected SesionJPANotClose session = null;//load_Manualy
	protected Empleado empleadoActual;
	
	protected HashMap<String, Object> hmPramasTX = null;
	protected HashMap<String, Object> hmResultadoTX = null;
	
	protected TransaccionDTO dtoResponse = null;
	
	protected SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
	protected SimpleDateFormat formatFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	protected DecimalFormat formatDecimal2 = new DecimalFormat("##.00"); 
	
	public TransaccionComun() 
	{
		this.hmResultadoTX = new HashMap<String,Object>();
		dtoResponse = new TransaccionDTO(hmResultadoTX);
	}
	
	public void closeStatement(Statement st) {
		try {
			if(st != null) {
				st.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void closeResultSet(ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Double getDoubleScale2(double valor)
	{
		return Math.round(valor * 100.0) / 100.0;
	}

}
