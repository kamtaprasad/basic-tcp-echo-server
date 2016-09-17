package com.tute.web.tcp.server.runner;

import com.tute.web.tcp.server.TinyEchoServer;

/**
 * @author kamta
 * This is the main class to start the tiny echo server. You need to set 
 * the port number in argument. 
 */
public class TinyEchoServerMultithreadedRunner {

	public static void main(String[] arr) {
		System.out.println("Starting the TCP server..");
		if(arr.length < 1) {
			System.err.println("No argument is provided.. Nothing to process. "
					+ "Terminating the TCP server process.");
			System.exit(0);
		}

		int portNumber = Integer.parseInt(arr[0]);
		System.out.println("Creating server socket using port number: " + portNumber);

		TinyEchoServer serverSocket = new TinyEchoServer(portNumber);
		serverSocket.startServer();
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		serverSocket.stopServer();
	}
}
