package a02a.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {
	
	private final int size;
	//private final static int ENEMIES_NUMBER = 3;
	private final int enemiesNumber;
	private Pair<Integer,Integer> pawn;
	private Random rand = new Random();
	
	private Set<Pair<Integer,Integer>> enemies = new HashSet<>();
	
	public LogicsImpl(int size) {
		this.size = size;
		this.enemiesNumber = size;
		pawn = new Pair<>(rand.nextInt(this.size - 1 - 0) + 1, this.size - 1); // x tra this.size e 0 , y fissa.
	}

	@Override
	public Set<Pair<Integer, Integer>> getEnemies() {
		return this.enemies;
	}

	@Override
	public Pair<Integer, Integer> getPawn() {
		return this.pawn;
	}

	@Override
	public void isOver() {
		System.out.println("GAME OVER!");
		System.exit(0);
	}

	@Override
	public Pair<Integer,Integer> movePawn() {
		// Guardare una casella in diagonale a destra ed a sinistra, se no andare avanti, ma solo se non c'è un nemico.
		System.out.println("pawn: " + this.pawn);
		if(canEatEnemy(this.pawn)) { // potrei passare semplicemente this.pawn
			return eatEnemy();
		} else {
			Pair<Integer,Integer> pos = new Pair<>(this.pawn.getX(), this.pawn.getY() - 1);
			if(pos.getY() == -1) {
				isOver();
			}
			if(!this.enemies.contains(pos)) {
				System.out.println("MOVED");
				this.pawn = pos;
				return pos;
			}
		}
		isOver();
		return null;
	}

	@Override
	public Set<Pair<Integer, Integer>> spawnEnemies() {
		Pair<Integer,Integer> temp;
		for(int i = 0; i < this.enemiesNumber; i++) {
			temp = new Pair<>(rand.nextInt(this.size - 1 - 0 ) + 1, rand.nextInt(this.size - 2) + 1);
			this.enemies.add(temp);
		}
		return this.enemies;
	}
	
	private boolean inBoundaries(Pair<Integer,Integer> p) {
		return p.getX() >= 0 && p.getX() <= this.size - 1 
				&& p.getY() >= 0 && p.getY() <= this.size - 1;
	}

	@Override
	public boolean canEatEnemy(Pair<Integer, Integer> p) {
		Pair<Integer,Integer> temp = new Pair<>(p.getX() - 1, p.getY() - 1); // diagonale sinistra
		
		if(this.enemies.contains(temp)) {
			return true;
		} 
		temp = new Pair<>(p.getX() + 1, p.getY() - 1); // diagonale destra
		if(this.enemies.contains(temp)) {
			return true;
		} 
		
		return false;
	}
	
	private Pair<Integer,Integer> eatEnemy(){
		Pair<Integer,Integer> temp = new Pair<>(this.pawn.getX() - 1, this.pawn.getY() - 1); // diagonale sinistra
		
		System.out.println("Mangiato un pezzo");
		
		if(this.enemies.contains(temp)) {
			// Salvo la nuova posizione del pawn
			this.pawn = temp;
			// Teoricamente dovrei rimuovere quel pawn
			this.enemies.remove(temp);
			return temp;
		} 
		temp = new Pair<>(this.pawn.getX() + 1, this.pawn.getY() - 1); // diagonale destra
		if(this.enemies.contains(temp)) {
			// Salvo la nuova posizione del pawn
			this.pawn = temp;
			// Teoricamente dovrei rimuovere quel pawn
			this.enemies.remove(temp);
			return temp;
		} 
		
		return null;
	}

}
