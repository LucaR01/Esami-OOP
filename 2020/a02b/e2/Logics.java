package a02b.e2;

import java.util.Set;

public interface Logics {
	
	public enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
	
	Set<Pair<Integer,Integer>> getPlayers();
	
	void move(Direction direction);
	
	void setPlayers(Pair<Integer,Integer> p);

}
