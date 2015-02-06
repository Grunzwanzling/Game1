package main;

public class PhysicsThread extends Thread{

	public void run() {
		
		while(true){
		for(int i=0;i<Spiel.id;i++){
			
			//Bewegung berechnen
			Spiel.positions[i][0]=Spiel.positions[i][0]+Spiel.movement[i][0];
			Spiel.positions[i][1]=Spiel.positions[i][1]+Spiel.movement[i][1];
			
			//Abprallen berechnen
			if(Spiel.positions[i][0]<=0) 							Spiel.movement[i][0]=Math.abs(Spiel.movement[i][0]);
			if(Spiel.positions[i][0]>=Spiel.width-Spiel.width2) 	Spiel.movement[i][0]=-Math.abs(Spiel.movement[i][0]);
			
			if(Spiel.positions[i][1]<=0) 							Spiel.movement[i][0]=Math.abs(Spiel.movement[i][1]);
			if(Spiel.positions[i][1]>=Spiel.heigth-Spiel.height2) 	Spiel.movement[i][1]=-Math.abs(Spiel.movement[i][1]);
			
		try {
			Thread.sleep(Spiel.tick);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		}
	}

}
