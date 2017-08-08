package com.matson.commandlauncher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * Matson splash screen
 * @author mattdaniel
 *
 */
public class SplashScreen extends JWindow {
	private static final long serialVersionUID = 1L;
	private int duration;

	public SplashScreen(int d) {
		duration = d;
	}

	// A simple little method to show a title screen in the center
	// of the screen for the amount of time given in the constructor
	public void showSplash() {
		JPanel content = (JPanel) getContentPane();
		content.setBackground(Color.white);

		// Set the window's bounds, centering the window
		int width = 486;
		int height = 118;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(50, 50, width, height);

		// Build the splash screen

		JLabel label = null;
		try {
			label = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Images/MATSON_LO_rgb.jpg")));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JLabel copyrt = new JLabel("Waiting for network", JLabel.CENTER);
		Font font = new Font("Sans-Serif", Font.BOLD, 18);
		Color oraRed = new Color(156, 20, 20, 255);

		copyrt.setFont(font);
		copyrt.setForeground(oraRed);

		content.add(label, BorderLayout.CENTER);
		content.add(copyrt, BorderLayout.SOUTH);

		// Display it
		setVisible(true);

		// Wait a little while, maybe while loading resources
		try {
			Thread.sleep(duration);
		} catch (Exception e) {
		}

	}

	public void showSplashAndExit() {
		showSplash();
		System.exit(0);
	}

/**
 * Standalone testing for this class
 */
	public static void main(String[] args) {
		// Throw a nice little title page up on the screen first
		SplashScreen splash = new SplashScreen(5000);
		// Normally, we'd call splash.showSplash() and get on with the program.
		// But, since this is only a test...
		splash.showSplashAndExit();
	}
}