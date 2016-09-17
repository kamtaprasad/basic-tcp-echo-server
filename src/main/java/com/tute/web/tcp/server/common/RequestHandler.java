package com.tute.web.tcp.server.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author kamta
 * Request handler is responsible for handling the socket returned by server
 * socket accept call. This mechanism allows thread per request. 
 */
public class RequestHandler extends Thread {

	private Socket socket;

	public RequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		System.out.println("Received a connection..");
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			outputStream =socket.getOutputStream();
			PrintStream printStream = new PrintStream(outputStream);

			printStream.println("Echo Server 1.0");
			printStream.flush();

			String line = bufferedReader.readLine();
			while(line != null) {
				printStream.println("Echo: " + line);
				printStream.flush();
				line = bufferedReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}