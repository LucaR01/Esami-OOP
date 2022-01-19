package a02c.e2;

import java.util.Set;

public interface Logics {
	
	Set<Pair<Integer,Integer>> getNumbers();
	
	void setNumber(Pair<Integer,Integer> p);
	
	boolean won();

}
