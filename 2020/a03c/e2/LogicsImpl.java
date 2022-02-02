package a03c.e2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {
	
	private final int size;
	
	private Pair<Integer,Integer> head;
	private Set<Pair<Integer,Integer>> snake = new HashSet<>();
	
	public LogicsImpl(int size) {
		this.size = size;
	}
	
	@Override
	public Pair<Integer,Integer> getHead(){
		return this.head;
	}
	
	@Override
	public Set<Pair<Integer,Integer>> getSnake(){
		return this.snake;
	}
	
	@Override
	public Set<Pair<Integer, Integer>> getRandomNumbers(){
		final Random rand = new Random();
		
		final var p1 = new Pair<>(rand.nextInt(this.size), 0);
		
		final var p2 = new Pair<>(p1.getX(), 1);
		
		this.head = p2;
		
		var temp = new HashSet<Pair<Integer,Integer>>();
		
		temp.add(p2);
		temp.add(p1);
		
		this.snake.add(p1);
		this.snake.add(p2);
		
		return Collections.unmodifiableSet(temp);
	}

	@Override
	public boolean hit(Pair<Integer, Integer> p) {
		
		for(var i : Set.of(-1, 0, 1)) {
			for(var j : Set.of(-1, 0, 1)) {
				var temp = new Pair<>(this.head.getX() + (i), this.head.getY() + (j));
				if(inBoundaries(temp) && p.equals(temp) && notInSnake(p)) {
					this.head = p;
					this.snake.add(p);
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean inBoundaries(Pair<Integer,Integer> p) {
		return p.getX() >= 0 && p.getX() < this.size 
				&& p.getY() >= 0 && p.getY() < this.size;
	}
	
	private boolean notInSnake(Pair<Integer,Integer> p) {
		return !this.snake.contains(p);
	}

}
