package a04a.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {
	
	//private Set<Pair<Integer,Integer>> set = new HashSet<>();
	private final int size;
	private Pair<Integer,Integer> player;
	
	
	public LogicsImpl(int size) {
		this.size = size;
		this.player = new Pair<>(this.size / 2, this.size / 2);
	}

	/*@Override
	public boolean hit(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}*/

	/*@Override
	public boolean isOver() {
		// TODO Auto-generated method stub
		return false;
	}*/

	@Override
	public Pair<Integer, Integer> getPlayer() {
		return this.player;
	}
	
	private boolean inBoundaries(Pair<Integer,Integer> p) {
		return p.getX() >= 0 && p.getX() <= this.size - 1 && p.getY() >= 0 && p.getY() <= this.size - 1;
	}

	@Override
	public Pair<Integer, Integer> randomMove() {
		System.out.println("this.player: " + this.player);
		Random rand = new Random();
		int max = 1;
		int min = -1;
		double xCoord = ((Math.random() * 2) - 1);
		double yCoord = ((Math.random() * 2) - 1);
		System.out.println("xCoord: " + xCoord);
		System.out.println("yCoord: " + yCoord);
		int testX;
		int testY;
		if(xCoord >= 0.5) {
			testX = 1;
		}
		else if(xCoord <= -0.5) {
			testX = -1;
		}
		else {
			testX = 0;
		}
		if(yCoord >= 0.5) {
			testY = 1;
		}
		else if(yCoord <= -0.5) {
			testY = -1;
		}
		else {
			testY = 0;
		}
		System.out.println("testX: " + testX);
		System.out.println("testY: " + testY);
		int randX = testX; //Math.random() >= 0.5 ? 1 : -1; //rand.nextInt(((max - min) + 1) + min); //== 0 ? -1 : 1; // senza lo zero non può andare ne su ne giu ne a destra ne a sinistra
		int randY = testY; //Math.random() >= 0.5 ? 1 : -1; //rand.nextInt(((max - min) + 1) + min); //== 0 ? -1 : 1;
		System.out.println("randX: " + randX);
		System.out.println("randY: " + randY);
		var randPos = new Pair<>(this.player.getX() + randX, this.player.getY() + randY);
		if(inBoundaries(randPos)) {
			this.player = new Pair<>(randPos.getX(), randPos.getY());
			System.out.println("randPos: " + randPos);
			return randPos;
		} else {
			System.out.println("END GAME!");
			System.exit(0);
		}
		// random range = ((max - min) + 1) + min)
		
		// destra = x + 1; sinistra = x - 1; su = y + 1; giù = y - 1;
		// diagonale su sinistra = (x - 1, y + 1); diagonale su destra = (x + 1, y + 1);
		// diagonale giù sinistra = (x - 1, y - 1); diagonale giù destra = (x + 1, y - 1);
		
		System.out.println("randPos: " + randPos);
		return randPos;
	}

}
