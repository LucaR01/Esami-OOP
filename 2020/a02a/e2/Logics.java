package a02a.e2;

import java.util.Set;

public interface Logics {
	
	Set<Pair<Integer,Integer>> getEnemies();
	
	Pair<Integer,Integer> getPawn();
	
	void isOver();
	
	/**
	 * 
	 * @param Pair of the current pawn position.
	 * @return Pair<Integer,Integer> of the next pawn position.
	 */
	
	Pair<Integer,Integer> movePawn();
	
	Set<Pair<Integer,Integer>> spawnEnemies();
	
	boolean canEatEnemy(Pair<Integer,Integer> p);

}
