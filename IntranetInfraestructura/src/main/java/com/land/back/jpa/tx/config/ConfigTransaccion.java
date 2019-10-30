package com.land.back.jpa.tx.config;


/**
 * @author LuisGlz
 */
public class ConfigTransaccion
{
	private static ConfigTransaccion instance = null;

	private ConfigTransaccion()
	{
		
	}
	
	public static ConfigTransaccion getInstance() 
	{
		if (instance == null)
		{
			instance = new ConfigTransaccion();
		}
		return instance;
	}

	@SuppressWarnings({ "unchecked"})
	public TransaccionLauncher<TransaccionDTO> creaTransaccion(String miTx)throws Exception
	{
		String className = miTx;
		try
		{
			if(className == null)
			{
				throw new Exception("AMIGO!!! te falta agregarlo en el NamesTransaccion WEY");
			}
			
			return (TransaccionLauncher<TransaccionDTO>) Class.forName(className).newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}
}
