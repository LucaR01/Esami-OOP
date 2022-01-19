package a05a.e2;

import java.util.Set;

public interface Logics {

	Set<Pair<Integer,Integer>> getNeighbours(Pair<Integer,Integer> p);
	
	Set<Pair<Integer,Integer>> remove(Pair<Integer,Integer> p);
	
	void setPlayers(Pair<Integer,Integer> p);
	
	Set<Pair<Integer,Integer>> getPlayers();
	
	boolean isOver();
}
