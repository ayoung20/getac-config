package com.matson.commandlauncher;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.*; 

/**
 * Entry point to program for executing commands on the operating system. 
 * @author mattdaniel
 */
public class Main {
	
	private static final int PORT = 1243;

    
    public static void main(String[] args) throws Exception {

    	Options options = new Options();
    	Option command = new Option("c", "command", true, "Command to execute on host OS");
    	Option server = new Option("s", "server", true, "Server to wait for before executing command");
    	Option splash = new Option("l", "splash", false, "show a splash screen while waiting for server");
    	Option help = new Option("h", "help", false, "Print this help message");
    	options.addOption(command);
    	options.addOption(server);
    	options.addOption(help);
    	options.addOption(splash);
    	
    	List<Option> missingOptions = new ArrayList<>();
    	CommandLine cl = new DefaultParser().parse(options, args);
    	
    	String serverStr = null;
    	String commandStr = null;
    	
    	if (cl.hasOption('h')) {
    		help(options);
    		return;
    	}
    	
    	if (cl.hasOption('c')) {
    		commandStr = cl.getOptionValue('c');
    	} else {
    		missingOptions.add(command);
    	}
    	
    	if (cl.hasOption('s')) {
    		serverStr = cl.getOptionValue('s');
    	} else {
    		missingOptions.add(server);
    	}
    	
    	if (missingOptions.size() > 0) {
    		help(options);
    		throw new MissingOptionException(missingOptions);
    	} else {
    		/* make sure this program is not already running, will throw IOException if it is */
    		ServerSocket serverSocket = null;
    		try {
    			serverSocket = new ServerSocket(PORT);
    			new Launcher().launch(serverStr, commandStr, cl.hasOption('l'));
    		} finally {
    			serverSocket.close();
    		}
        		
    	}
    } 
    
    private static void help(Options options) {
    	HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("CommandRunner", "Run a command on the Operating system\n\n",options,"");
    }
    
}
