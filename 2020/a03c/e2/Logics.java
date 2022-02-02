package a03c.e2;

import java.util.Set;

public interface Logics {

	Pair<Integer,Integer> getHead();
	
	Set<Pair<Integer,Integer>> getSnake();
	
	Set<Pair<Integer,Integer>> getRandomNumbers();
	
	boolean hit(Pair<Integer,Integer> p);
}
