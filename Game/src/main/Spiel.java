package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Spiel {

	public ServerSocket server;

	public static int width = 10000;
	public static int heigth = 2800;
	public static int width2 = 500;
	public static int height2 = 500;
	public static int tick = 30;
	
	public boolean[] active;
	public static String[] names;
	public static int[][] positions;
	public static int[][] movement;
	public int maxSpeed;
	static int id;
	public static boolean[] shooting;
	public static int rotation[];

	public Spiel() throws IOException {

		names = new String[1000];
		positions = new int[1000][2];
		movement = new int[1000][2];
		active = new boolean[1000];
		rotation = new int[1000];
		shooting = new boolean[1000];

		PhysicsThread pt = new PhysicsThread();
		pt.start();
		
		connect();
	}
	

	public static void main(String[] args) throws IOException {
		new Spiel();

	}

	public void connect() throws IOException {

		
		
		ServerSocket m_ServerSocket = new ServerSocket(10000);	
		System.out.println("Waiting for Clients...");
		id = 0;
		while (true) {
			Socket clientSocket = m_ServerSocket.accept();
			ClientThread cliThread = new ClientThread(
					clientSocket, id++);
			cliThread.start();
		}
	}

}
