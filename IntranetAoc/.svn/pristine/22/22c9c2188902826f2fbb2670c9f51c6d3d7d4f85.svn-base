package com.land.front.cliente;

import java.applet.Applet;
import java.awt.Graphics;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * LGONZALEZ
 */
public class AppletMacAddress extends Applet implements Serializable {

	private static final long serialVersionUID = 1L;

	public void init() {
		/**
		 * INIT WORK
		 */
		try {
			InetAddress ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());

			NetworkInterface network = NetworkInterface.getByInetAddress(ip);

			byte[] mac = network.getHardwareAddress();

			System.out.print("Current MAC address : ");

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("Hello World - Applet", 40, 100);
	}

}
