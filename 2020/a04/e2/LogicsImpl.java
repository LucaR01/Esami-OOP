package a04.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {
	
	private final int size;
	
	private Set<Pair<Integer,Integer>> pieces = new HashSet<>();
	
	private static final int PAWNS = 3;
	
	private Pair<Integer,Integer> king;
	
	private Set<Pair<Integer,Integer>> pawns = new HashSet<>();
	
	private final Random rand = new Random();
	
	private boolean isKing = true;
	
	public LogicsImpl(int size) {
		this.size = size;
		this.king = new Pair<>(rand.nextInt(this.size), rand.nextInt(this.size));
		
		for(int i = 0; i < LogicsImpl.PAWNS; i++) {
			var temp = new Pair<>(rand.nextInt(this.size), rand.nextInt(this.size));
			
			// Metto questo while per assicurarmi che non ci siano overlap con la posizione del king oppure con le posizioni dei pawns.
			while(temp.equals(this.king) || this.pawns.contains(temp)) {
				temp = new Pair<>(rand.nextInt(this.size), rand.nextInt(this.size));
			}
			this.pawns.add(temp);
		}
		
		this.pieces.addAll(pawns);
		this.pieces.add(king);
	}

	@Override
	public Pair<Integer, Integer> getKing() {
		return this.king;
	}

	@Override
	public Set<Pair<Integer, Integer>> getPawns() {
		return this.pawns;
	}

	@Override
	public boolean hit(Pair<Integer, Integer> p) {
		// Forse quel inBoundaries non mi serve perchè tanto non possiamo cliccare fuori dalla griglia e sui neighbours già facciamo quel controllo.
		if(inBoundaries(p) && getNeighbours(this.king).contains(p)) {
			this.king = p;
			this.pieces.add(p);
			if(this.pawns.contains(this.king)) {
				this.isKing = !this.isKing;
				// Abbiamo mangiato un pedone quindi la rimuoviamo dal set pawns e dal set pieces
				this.pawns.remove(p);
				
				//this.pieces.remove(p); //TODO: non serve rimuoverlo da qui, ma solo dai pawns.
			}
			return true;
		}
		return false;
	}
	
	private Set<Pair<Integer,Integer>> getNeighbours(Pair<Integer,Integer> p){
		Set<Pair<Integer,Integer>> neighbours = new HashSet<>();
		
		for(var i : Set.of(-1, 0, 1)) {
			for(var j : Set.of(-1, 0, 1)) {
				var temp = new Pair<>(p.getX() + (i),p.getY() + (j));
				if(inBoundaries(temp)) {
					neighbours.add(temp);
				}
			}
		}
		System.out.println("Neighbours: " + neighbours); //TODO: remove
		return neighbours;
	}
	
	private boolean inBoundaries(Pair<Integer,Integer> p) {
		return p.getX() >= 0 && p.getX() < this.size 
				&& p.getY() >= 0 && p.getY() < this.size;
	}

	@Override
	public boolean isKing() {
		return this.isKing;
	}

	@Override
	public boolean isOver() {
		return this.pawns.isEmpty();
	}

	@Override
	public Set<Pair<Integer, Integer>> getPieces() {
		return this.pieces;
	}

}
