package a01a.e2;

import java.util.Optional;
import java.util.Set;

public interface Logics {
	public enum Player{
		O, X;
	}
	
	Set<Pair<Integer,Integer>> randomSpawn();
	
	boolean hit(int x, int y);
	
	boolean isOver();
	
	int getCounter();
	
	int incrementCounter();
	
	int resetCounter();
	
	Optional<Player> isWinner();
}
