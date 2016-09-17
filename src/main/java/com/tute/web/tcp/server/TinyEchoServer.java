package com.tute.web.tcp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.tute.web.tcp.server.common.RequestHandler;

/**
 * @author kamta
 * This is tiny echo server built using socket. This echo server process each
 * request in separate thread. You need to set the port number while initializing 
 * the tiny echo server.  
 */
public class TinyEchoServer extends Thread {
	private ServerSocket serverSocket;
	private boolean isRunning = Boolean.FALSE;
	private int portNumber;

	public TinyEchoServer(int portNumber) {
		this.portNumber = portNumber;
	}

	public void startServer()  {
		try {
			serverSocket = new ServerSocket(portNumber);
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void stopServer() {
		isRunning = false;
		this.interrupt();
	}

	@Override
	public void run() {
		isRunning = true;
		while(isRunning) {
			try {
				System.out.println("Listening for a connection..");
				Socket socket = serverSocket.accept();
				RequestHandler handler = new RequestHandler(socket);
				handler.start();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
	}

}
