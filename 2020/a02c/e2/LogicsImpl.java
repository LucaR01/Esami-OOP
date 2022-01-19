package a02c.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class LogicsImpl implements Logics {
	
	private Set<Pair<Integer,Integer>> numbers = new HashSet<>();
	
	private final int size;
	
	private boolean end;
	
	public LogicsImpl(int size) {
		this.size = size;
	}

	@Override
	public Set<Pair<Integer, Integer>> getNumbers() {
		return this.numbers;
	}

	@Override
	public void setNumber(Pair<Integer, Integer> p) {
		this.numbers.add(p);
		
		/*if(inBoundaries(p)) {
			this.numbers.add(p);
		}*/
	}
	
	private boolean checkSquare() {
		this.numbers.forEach( p -> {
			if(!atBorder(p)) {
				boolean endGame = hasNeighbours(p);
				if(endGame) {
					this.end = true;
					System.out.println("this.end inside: " + this.end); //TODO: remove
				}
			}
		});
		System.out.println("this.end: " + this.end); //TODO: remove
		return this.end;
	}

	@Override
	public boolean won() {
		return checkSquare();
	}
	
	private boolean inBoundaries(Pair<Integer,Integer> p) {
		return p.getX() >= 0 && p.getX() <= this.size - 1 &&
				p.getY() >= 0 && p.getY() <= this.size - 1;
	}
	
	private boolean atBorder(Pair<Integer,Integer> p) {
		
		// verso destra in alto
		for(int x = 0; x <= this.size - 1; x++) {
			if(p.getX() == x && p.getY() == 0) {
				return true;
			}
		}
		
		// verso il basso a sinistra
		for(int y = 0; y <= this.size - 1; y++) {
			if(p.getX() == 0 && p.getY() == y) {
				return true;
			}
		}
		
		// verso destra in basso
		for(int x = 0; x <= this.size - 1; x++) {
			if(p.getX() == x && p.getY() == this.size - 1) {
				return true;
			}
		}
		
		// verso il basso a destra
		for(int y = 0; y <= this.size - 1; y++) {
			if(p.getX() == this.size - 1 && p.getY() == y) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean hasNeighbours(Pair<Integer,Integer> p) {
		
		ArrayList<Boolean> hasNeighbours = new ArrayList<>();
		
		// X + 1
		var temp = new Pair<>(p.getX() + 1, p.getY() + 1);
		if(this.numbers.contains(temp)) { // non c'è bisogno di controllare che sia nei boundaries perchè già controlliamo che non si trovi ai bordi
			// e in più se è fuori dai boundaries non dovrebbe trovarsi nel set.
			hasNeighbours.add(true);
		}
		
		temp = new Pair<>(p.getX() + 1, p.getY());
		if(this.numbers.contains(temp)) {
			hasNeighbours.add(true);
		}
		
		temp = new Pair<>(p.getX() + 1, p.getY() - 1);
		if(this.numbers.contains(temp)) {
			hasNeighbours.add(true);
		}
		
		// X - 1
		temp = new Pair<>(p.getX() - 1, p.getY());
		if(this.numbers.contains(temp)) {
			hasNeighbours.add(true);
		}
		
		temp = new Pair<>(p.getX() - 1, p.getY() + 1);
		if(this.numbers.contains(temp)) {
			hasNeighbours.add(true);
		}
		
		temp = new Pair<>(p.getX() - 1, p.getY() - 1);
		if(this.numbers.contains(temp)) {
			hasNeighbours.add(true);
		}
		
		// Y + 1
		temp = new Pair<>(p.getX(), p.getY() + 1);
		if(this.numbers.contains(temp)) {
			hasNeighbours.add(true);
		}
		
		// Y - 1
		temp = new Pair<>(p.getX(), p.getY() - 1);
		if(this.numbers.contains(temp)) {
			hasNeighbours.add(true);
		}
		
		if(hasNeighbours.size() >= 3) {
			return true;
		}
		
		System.out.println("hasNeighbours: " + hasNeighbours); //TODO: remove
		System.out.println("hasNeighbours: " + hasNeighbours.size()); //TODO: remove
		
		return false;
	}
	
	

}
