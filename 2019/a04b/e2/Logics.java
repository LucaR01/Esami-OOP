package a04b.e2;

import java.util.Set;

public interface Logics {

	//boolean isOver();
	
	//boolean hit();
	
	boolean checkAll(Set<Pair<Integer,Integer>> p);
	
	Set<Pair<Integer,Integer>> randomSpawn();
	
	Set<Pair<Integer,Integer>> spread();
}
