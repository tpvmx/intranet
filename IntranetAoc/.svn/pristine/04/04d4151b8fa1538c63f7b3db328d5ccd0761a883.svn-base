package mx.org.banxico.dgie.ws;

public class Prueba {
	public static void main(String[] args) {
		DgieWSLocator locator = new DgieWSLocator();
		locator.setDgieWSPortEndpointAddress(locator.getDgieWSPortAddress());
		try {
			DgieWSPortSoapBindingStub stub = (DgieWSPortSoapBindingStub) locator.getDgieWSPort();
			String response = stub.tiposDeCambioBanxico();
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
