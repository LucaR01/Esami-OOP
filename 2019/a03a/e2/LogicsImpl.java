package a03a.e2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LogicsImpl implements Logics {
	
	//private Map<Pair<Integer,Integer>, Boolean> map = new HashMap<>();
	private Pair<Integer,Integer> x = new Pair<>(0,0);
	private final int size;
	
	private Set<Pair<Integer,Integer>> o = new HashSet<>();
	
	public LogicsImpl(int size) {
		this.size = size;
	}
	
	public void createSet(Pair<Integer,Integer> p) {
		this.o.add(p);
	}
	
	// move mi sposta la X se non c'è una O nella cella in cui deve andare.
	public Pair<Integer,Integer> move() {
		/*if(directionRight(this.x)) {
			Pair<Integer,Integer> temp = new Pair<>(this.x.getX() + 1, this.x.getY());
			this.x = temp;
		} else {
			Pair<Integer,Integer> temp = new Pair<>(this.x.getX(), this.x.getY() + 1);
			this.x = temp;
		}*/
		
		if(direction(this.x) == Direction.RIGHT) {
			Pair<Integer,Integer> temp = new Pair<>(this.x.getX() + 1, this.x.getY());
			this.x = temp;
		}
		else if(direction(this.x) == Direction.DOWN) {
			Pair<Integer,Integer> temp = new Pair<>(this.x.getX(), this.x.getY() + 1);
			this.x = temp;

		}
		else if(direction(this.x) == Direction.LEFT) {
			Pair<Integer,Integer> temp = new Pair<>(this.x.getX() - 1, this.x.getY());
			this.x = temp;

		}
		else if(direction(this.x) == Direction.UP) {
			Pair<Integer,Integer> temp = new Pair<>(this.x.getX(), this.x.getY() - 1);
			this.x = temp;
			
		}
		
		//if(inBoundaries(this.x)) {
		if(hit(this.x.getX(), this.x.getY())) { // dovrei capire se ci troviamo al bordo, così possiamo scendere verso il basso con la y.
			System.out.println("Game Over!");
			System.exit(0);
		} else {
			System.out.println("x: " + this.x);
			return this.x;
		}
		//}
		//this.x = new Pair<>(temp.getX() - 1, temp.getY());
		return this.x;
	}

	@Override
	// Devo controllare se la prossima posizione di x coincide con una O.
	public boolean hit(int x, int y) {
		var p = new Pair<>(x,y);
		/*if(this.x.equals(p)) {
			System.out.println("Hit!");
			return true;
		}
		return false;*/
		int i = 0;
		while(i < o.size()) {
			if(o.contains(p)) {
				System.out.println("Colpito!");
				return true;
			} else {
				return false;
			}
			/*if(o.iterator().next().getX() == p.getX() && o.iterator().next().getY() == p.getY()) {
				System.out.println("Colpito!");
				return true;
			}*/ 
		}
		return false;
	}

	@Override
	public boolean isOver() {
		return false;
	}
	
	public Set<Pair<Integer,Integer>> getSet(){
		return this.o;
	}
	
	/*private boolean inBoundaries(Pair<Integer,Integer> p) {
		return p.getX() >= 0 && p.getX() <= this.size - 1 && p.getY() >= 0 && p.getY() <= this.size - 1;
	}*/
	
	/*private boolean directionRight(Pair<Integer,Integer> p) {
		if(p.getX() >= this.size - 1) {
			return false;
		}
		return true;
	}*/
	
	/*private boolean directionDown(Pair<Integer,Integer> p) {
		if(p.getX() >= this.size - 1 && getY() >= this.size - 1) {
			return false;
		}
		return true;
	}*/
	
	private Direction direction(Pair<Integer,Integer> p) {
		if(p.getX() >= 0 && !(p.getX() == this.size - 1) && p.getY() == 0) {
			return Direction.RIGHT;
		}
		else if(p.getX() == this.size - 1 && p.getY() >= 0 && !(p.getY() == this.size - 1)) { // anche p.getX() == this.size - 1
			return Direction.DOWN;
		}
		else if(p.getX() <= this.size - 1 && !(p.getX() == 0) && p.getY() == this.size - 1) { // anche p.getY() == this.size - 1
			return Direction.LEFT;
		}
		else if(p.getX() == 0 && p.getY() <= this.size - 1 && !(p.getY() == 0)) { // se la x è uguale a 0 e la y è uguale al size - 1 e NON è uguale a 0.
			return Direction.UP;
		}
		return null;
	}

}
