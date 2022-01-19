package a04b.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {
	
	private final int size;
	private final static int NUM_RAND_SPAWN = 3;
	Set<Pair<Integer,Integer>> total = new HashSet<>();
	
	public LogicsImpl(int size) {
		this.size = size;
	}

	/*@Override
	public boolean isOver() {
		// TODO Auto-generated method stub
		return false;
	}*/

	/*@Override
	public boolean hit() {
		// TODO Auto-generated method stub
		return false;
	}*/

	@Override
	public Set<Pair<Integer, Integer>> randomSpawn() {
		Random rand = new Random();
		int max = this.size - 1;
		int min = 0;
		Set<Pair<Integer,Integer>> p = new HashSet<>();
		for(int i = 0; i < NUM_RAND_SPAWN; i++) {
			int x = rand.nextInt(((max - min) + 1) + min);
			int y = rand.nextInt(((max - min) + 1) + min);
			p.add(new Pair<>(x,y));
		}
		total.addAll(p);
		return p;
	}
	
	private Set<Pair<Integer,Integer>> getNeighbours(Pair<Integer,Integer> p) {
		Set<Pair<Integer,Integer>> pos = new HashSet<>();
		pos.add(p);
		/*for(int i = 0; i < 8; i++) { // this.size / 2 + 2
			
			pos.add(new Pair<>(p.getX(), p.getY()));
		}*/
		var temp = new Pair<>(p.getX() + 1, p.getY());
		if(inBoundaries(temp)) {
			pos.add(temp);
		}
		temp = new Pair<>(p.getX() - 1, p.getY());
		if(inBoundaries(temp)) {
			pos.add(temp);
		}
		temp = new Pair<>(p.getX(), p.getY() + 1);
		if(inBoundaries(temp)) {
			pos.add(temp);
		}
		temp = new Pair<>(p.getX(), p.getY() - 1);
		if(inBoundaries(temp)) {
			pos.add(temp);
		}
		temp = new Pair<>(p.getX() + 1, p.getY() + 1);
		if(inBoundaries(temp)) {
			pos.add(temp);
		}
		temp = new Pair<>(p.getX() - 1, p.getY() - 1);
		if(inBoundaries(temp)) {
			pos.add(temp);
		}
		temp = new Pair<>(p.getX() + 1, p.getY() - 1);
		if(inBoundaries(temp)) {
			pos.add(temp);
		}
		temp = new Pair<>(p.getX() - 1, p.getY() + 1);
		if(inBoundaries(temp)) {
			pos.add(temp);
		}
		/*
		pos.add(new Pair<>(p.getX() + 1, p.getY()));
		pos.add(new Pair<>(p.getX() - 1, p.getY()));
		
		pos.add(new Pair<>(p.getX(), p.getY() + 1));
		pos.add(new Pair<>(p.getX(), p.getY() - 1));
		
		pos.add(new Pair<>(p.getX() + 1, p.getY() + 1));
		pos.add(new Pair<>(p.getX() - 1, p.getY() - 1));
		pos.add(new Pair<>(p.getX() + 1, p.getY() - 1));
		pos.add(new Pair<>(p.getX() - 1, p.getY() + 1));*/
		return pos;
	}

	@Override
	public Set<Pair<Integer, Integer>> spread() {
		Set<Pair<Integer,Integer>> spread = new HashSet<>();
		this.total.forEach( p -> {
			spread.addAll(getNeighbours(p));
		});
		this.total = spread;
		return spread;
	}
	
	public boolean checkAll(Set<Pair<Integer,Integer>> p) {
		// 10 * 10 la griglia (this.size * this.size)
		System.out.println("this.size * this.size (superficie della griglia): " + this.size * this.size);
		System.out.println("p.size(): " + p.size());
		return this.total.size() >= (this.size * this.size); // Controllo se gli elementi sono maggiori del numero totale di celle della griglia.
		// Facciamo per 2 perchè il Pair contiene
	}
	
	private boolean inBoundaries(Pair<Integer,Integer> p) {
		return p.getX() >= 0 && p.getX() <= this.size - 1 && p.getY() >= 0 && p.getY() <= this.size - 1;
	}

}
