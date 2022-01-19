package a01a.e2;

import java.util.Set;

public interface Logics {
	
    /*public enum player {
	O, X;
	}*/
	
	Set<Pair<Integer,Integer>> getPlayers();
	
	Set<Pair<Integer,Integer>> getO();
	
	Set<Pair<Integer,Integer>> getX();
	
	boolean isOver(Set<Pair<Integer,Integer>> s, Pair<Integer,Integer> p);
	
	void setPlayers(Pair<Integer,Integer> p);
	
	boolean isBelow(Pair<Integer,Integer> p);

}
