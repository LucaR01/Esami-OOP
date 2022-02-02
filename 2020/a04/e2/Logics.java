package a04.e2;

import java.util.Set;

public interface Logics {
	
	Pair<Integer,Integer> getKing();
	
	Set<Pair<Integer,Integer>> getPawns();
	
	boolean hit(Pair<Integer,Integer> p);
	
	boolean isKing();
	
	boolean isOver();
	
	Set<Pair<Integer,Integer>> getPieces();

}
