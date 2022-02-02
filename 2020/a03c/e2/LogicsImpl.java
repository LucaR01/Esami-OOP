package a03c.e2;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
	public Set<Pair<Integer,Integer>> getRandomNumbers(){
		final Random rand = new Random();
		
		final var p1 = new Pair<>(rand.nextInt(this.size), 0);
		
		final var p2 = new Pair<>(p1.getX(), 1);
		
		this.head = p2;
		
		System.out.println("head: " + this.head); //TODO: remove
		
		var temp = new HashSet<Pair<Integer,Integer>>();
		
		temp.add(p2);
		temp.add(p1);
		
		this.snake.add(p1);
		this.snake.add(p2);
		
		return Collections.unmodifiableSet(temp);
	}

	@Override
	public boolean hit(Pair<Integer, Integer> p) {
		/*for(int i = 0; i < ((this.size - 1) * 2); i++) {
			//Set.of(1, -1);
			//List.of(1, -1).get(0);
			
		}*/
		
		//TODO: neighbour per neighbour e controllare se si trova in boundaries
		//TODO: se è adiacente alla testa allora ritornare true
		
		var temp = new Pair<>(this.head.getX() + 1, this.head.getY());
		if(inBoundaries(temp) && p.equals(temp) && notInSnake(p)) {
			this.head = p;
			this.snake.add(this.head);
			return true;
		}
		
		temp = new Pair<>(this.head.getX() + 1, this.head.getY() + 1);
		if(inBoundaries(temp) && p.equals(temp) && notInSnake(p)) {
			this.head = p;
			this.snake.add(this.head);
			return true;
		}
		
		temp = new Pair<>(this.head.getX() + 1, this.head.getY() - 1);
		if(inBoundaries(temp) && p.equals(temp) && notInSnake(p)) {
			this.head = p;
			this.snake.add(this.head);
			return true;
		}
		
		temp = new Pair<>(this.head.getX() - 1, this.head.getY());
		if(inBoundaries(temp) && p.equals(temp) && notInSnake(p)) {
			this.head = p;
			this.snake.add(this.head);
			return true;
		}
		
		temp = new Pair<>(this.head.getX() - 1, this.head.getY() + 1);
		if(inBoundaries(temp) && p.equals(temp) && notInSnake(p)) {
			this.head = p;
			this.snake.add(this.head);
			return true;
		}
		
		temp = new Pair<>(this.head.getX() - 1, this.head.getY() - 1);
		if(inBoundaries(temp) && p.equals(temp) && notInSnake(p)) {
			this.head = p;
			this.snake.add(this.head);
			return true;
		}
		
		temp = new Pair<>(this.head.getX(), this.head.getY() + 1);
		if(inBoundaries(temp) && p.equals(temp) && notInSnake(p)) {
			this.head = p;
			this.snake.add(this.head);
			return true;
		}
		
		temp = new Pair<>(this.head.getX(), this.head.getY() - 1);
		if(inBoundaries(temp) && p.equals(temp) && notInSnake(p)) {
			this.head = p;
			this.snake.add(this.head);
			return true;
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
