package a03b.e2;

import java.util.Set;

public interface Logics {
	
	Set<Pair<Integer,Integer>> randSpawnPosition();
	
	Set<Pair<Integer,Integer>> getPlayer();
	
	Set<Pair<Integer,Integer>> move();
}
