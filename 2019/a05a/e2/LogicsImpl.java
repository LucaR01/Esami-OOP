package a05a.e2;

import java.util.HashSet;
import java.util.Set;

public class LogicsImpl implements Logics {
	
	private final int size;
	
	private Set<Pair<Integer,Integer>> players = new HashSet<>();
	
	public LogicsImpl(int size) {
		this.size = size;
	}

	@Override
	public Set<Pair<Integer, Integer>> getNeighbours(Pair<Integer, Integer> p) {
		Set<Pair<Integer,Integer>> pos = new HashSet<>();
		
		// Salvo i neghbours, ma controllo anche se sono in players (ovvero sono stati cliccati, quindi delle X).
		var temp = new Pair<>(p.getX() + 1, p.getY());
		if(inBoundaries(temp) && this.players.contains(temp)) {
			pos.add(temp);
		}
		temp = new Pair<>(p.getX() - 1, p.getY());
		if(inBoundaries(temp) && this.players.contains(temp)) {
			pos.add(temp);
		}
		temp = new Pair<>(p.getX(), p.getY() + 1);
		if(inBoundaries(temp) && this.players.contains(temp)) {
			pos.add(temp);
		}
		temp = new Pair<>(p.getX(), p.getY() - 1);
		if(inBoundaries(temp) && this.players.contains(temp)) {
			pos.add(temp);
		}
		temp = new Pair<>(p.getX() + 1, p.getY() + 1);
		if(inBoundaries(temp) && this.players.contains(temp)) {
			pos.add(temp);
		}
		temp = new Pair<>(p.getX() - 1, p.getY() - 1);
		if(inBoundaries(temp) && this.players.contains(temp)) {
			pos.add(temp);
		}
		temp = new Pair<>(p.getX() + 1, p.getY() - 1);
		if(inBoundaries(temp) && this.players.contains(temp)) {
			pos.add(temp);
		}
		temp = new Pair<>(p.getX() - 1, p.getY() + 1);
		if(inBoundaries(temp) && this.players.contains(temp)) {
			pos.add(temp);
		}
		
		pos.add(p);
		
		System.out.println("getNeighbours: " + pos);
		return pos;
	}

	@Override
	public Set<Pair<Integer, Integer>> remove(Pair<Integer, Integer> p) {
		Set<Pair<Integer,Integer>> temp = getNeighbours(p);
		for(var n : temp) { // Lo chiamo ricorsivamente sui neighbours dei neighbours.
			temp.addAll(getNeighbours(n)); // Aggiungo anche i neghbours dei miei neighbours
		}
		System.out.println("to remove: " + temp);
		// Posso sia restituire quelli che sono da eliminare sia posso eliminarli dai players e poi restituire players
		this.players.removeAll(temp);
		System.out.println("players dopo la remove: " + this.players);
		return this.players;
	}
	
	private boolean inBoundaries(Pair<Integer,Integer> p) {
		return p.getX() >= 0 && p.getX() <= this.size - 1 && p.getY() >= 0 && p.getY() <= this.size - 1;
	}

	@Override
	public boolean isOver() {
		return this.players.size() == 0;
	}

	@Override
	public void setPlayers(Pair<Integer, Integer> p) {
		this.players.add(p);
		System.out.println("setPlayers: " + this.players);
	}

	@Override
	public Set<Pair<Integer, Integer>> getPlayers() {
		return this.players;
	}

}
