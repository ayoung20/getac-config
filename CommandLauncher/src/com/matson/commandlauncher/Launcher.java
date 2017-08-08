package com.matson.commandlauncher;

import java.io.IOException;
import java.net.InetAddress;

/**
 *
 * @author mattdaniel
 */
public class Launcher {

	private InetAddress host;
	private String commandStr;
	private static final int WAIT_MILLIS = 2000;

	/*
	 * Execute a command on the operating system. Wait for the server to be
	 * online before doing so.
	 */
	public void launch(String server, String command, boolean showSplash) throws IOException {

		
		if (showSplash) {
			SplashScreen splashScreen = new SplashScreen(WAIT_MILLIS);
			splashScreen.showSplash();
		}
		
		// Wait for server to be available
		while (true) {
			try {
				host = InetAddress.getByName(server);
				if (host.isReachable(WAIT_MILLIS)) {
					System.out.println("Server is available, launching now!");
					break;
				} else {
					System.out.println("Waiting for server");
					Thread.sleep(WAIT_MILLIS);
				}
			} catch (InterruptedException e) {
                            e.printStackTrace();
			} catch (IOException e1) {
				System.out.println("Waiting for server");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		commandStr = command;

		try {
			System.out.println("Executing : " + command);
			new Thread() {
                                @Override
				public void run() {

					try {
						Runtime.getRuntime().exec(commandStr);

						// run again to
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
			
			Thread.sleep(2000);
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
