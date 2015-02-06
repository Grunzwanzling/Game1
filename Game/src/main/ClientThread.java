package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

public class ClientThread extends Thread {

	public Socket clientSocket;
	public int clientID;
	OutputStream out;
	InputStream in;
	public BufferedReader breader;

	public ClientThread(Socket s, int i) {
		clientSocket = s;
		clientID = i;
	}

	public void run() {

		System.out.println("Neuer Client : ID - " + clientID + " : IP - "
				+ clientSocket.getInetAddress().getHostName());
		int c;
		try {
			out = clientSocket.getOutputStream();
			in = clientSocket.getInputStream();
			ObjectOutputStream oop = new ObjectOutputStream(out);
			ObjectInputStream oip = new ObjectInputStream(in);

			BufferedReader buff = new BufferedReader(new InputStreamReader(in));

			try {
				Spiel.names[clientID] = (String) oip.readObject();
				System.out.println(Spiel.names[clientID]);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Spiel.positions[clientID][0] = (int) (Math.random() * (Spiel.width - Spiel.width2));
			Spiel.positions[clientID][1] = (int) (Math.random() * (Spiel.heigth - Spiel.height2));

			System.out.println(Spiel.positions[clientID][0]);
			System.out.println(Spiel.positions[clientID][1]);

			oop.writeObject(clientID);
			
			while(true){
				
				int[][] positions = new int[Spiel.id+1][2];
				int[][] movement = new int[Spiel.id+1][2];
				int[] 	rotation = new int[Spiel.id+1];
				boolean[]	shooting = new boolean[Spiel.id+1];
				
				for(int i = 0; i<Spiel.id; i++){
					
				positions[i][0] = Spiel.positions[i][0];
				positions[i][1] = Spiel.positions[i][1];
				movement[i][0] = Spiel.movement[i][0];
				movement[i][1] = Spiel.movement[i][1];	
					
				}	
				oop.writeObject(positions);
				oop.writeObject(movement);
				oop.writeObject(rotation);
				oop.writeObject(shooting);
				oop.writeObject(Spiel.id);
				
				Spiel.movement[clientID][0] = (int)oip.readObject();
				Spiel.movement[clientID][1] = (int)oip.readObject();
				Spiel.rotation[clientID] = (int)oip.readObject();
				Spiel.shooting[clientID] = (boolean)oip.readObject();
				
				System.out.println("Client: "+clientID+"    MoveX: "+movement[clientID][0]+"   MoveY: "+ movement[clientID][1]+"   PosX: "+positions[clientID][0]+"   PosY: "+ positions[clientID][1]+ "   Rot: "+rotation[clientID]+ "   isShooting: "+shooting[clientID]);
				
				Thread.sleep(Spiel.tick);
			}
			
			
		}catch (SocketException e){
			System.out.println("Client "+clientID+" getrennt");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
