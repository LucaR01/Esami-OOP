package a02b.e2;

import java.util.HashSet;
import java.util.Set;

public class LogicsImpl implements Logics {
	
	private final int size;
	
	private Set<Pair<Integer,Integer>> players = new HashSet<>();
	
	public LogicsImpl(int size) {
		this.size = size;
	}

	@Override
	public Set<Pair<Integer, Integer>> getPlayers() {
		return this.players;
	}

	@Override
	public void move(Direction direction) {
		Set<Pair<Integer,Integer>> temp = new HashSet<>();
		System.out.println("Direction: " + direction);
		
		switch(direction) {
		case UP:
			for(var n : this.players) {
				temp.add(new Pair<>(n.getX(), n.getY() - 1));
			}
			this.players = temp;
			System.out.println("players move up: " + this.players);
			//return temp;
			break;
		case DOWN:
			for(var n : this.players) {
				temp.add(new Pair<>(n.getX(), n.getY() + 1));
			}
			this.players = temp;
			System.out.println("players move down: " + this.players);
			//return temp;
			break;
		case RIGHT:
			for(var n : this.players) {
				temp.add(new Pair<>(n.getX() + 1, n.getY()));
			}
			this.players = temp;
			System.out.println("players move right: " + this.players);
			//return temp;
			break;
		case LEFT:
			for(var n : this.players) {
				temp.add(new Pair<>(n.getX() - 1, n.getY()));
			}
			this.players = temp;
			System.out.println("players move left: " + this.players);
			//return temp;
			break;
		}
		//return this.players; // null
	}

	@Override
	public void setPlayers(Pair<Integer, Integer> p) {
		this.players.add(p);
		System.out.println("setPlayers: " + this.players);
	}

}
