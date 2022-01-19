package a03a.e2;

import java.util.Set;

public interface Logics {
	
	public enum Direction {
		UP,
		DOWN,
		RIGHT,
		LEFT
	}

	boolean hit(int x, int y);
	
	boolean isOver();
	
	void createSet(Pair<Integer,Integer> p);
	
	Pair<Integer,Integer> move();
	
	public Set<Pair<Integer,Integer>> getSet();
}
