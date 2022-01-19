package a01a.e2;

import java.util.HashSet;
import java.util.Set;

public class LogicsImpl implements Logics {
	
	private final int size;
	private Set<Pair<Integer,Integer>> players = new HashSet<>();
	
	private Set<Pair<Integer,Integer>> o = new HashSet<>();	
	private Set<Pair<Integer,Integer>> x = new HashSet<>();
	
	private boolean turn = true; // TRUE = 0 , FALSE = X
	
	public LogicsImpl(int size) {
		this.size = size;
	}

	@Override
	public Set<Pair<Integer, Integer>> getPlayers() {
		return this.players;
	}

	@Override
	public boolean isOver(Set<Pair<Integer,Integer>> s, Pair<Integer,Integer> p) {
		// devo controllare se ci sono almeno 3 vicini o in diagonale, o in orizzontale, o in verticale e concludere il gioco.
		// p rappresenta l'ultimo aggiunto, da quello controllo se i suoi 2 altri vicini in diagonale, in orizzontale, in verticale sono contenuti nel set rispettivo.
		Set<Pair<Integer,Integer>> temp = new HashSet<>();
		
		// così controlliamo orizzontalmente x+2 p x+1 è inutile guardare quello sopra, meglio guardare quello sotto e quello sotto ancora.
		temp.add(p);
		temp.add(new Pair<>(p.getX() + 1, p.getY()));
		temp.add(new Pair<>(p.getX() + 2, p.getY()));
		
		System.out.println("temp orizzontale: " + temp);
		
		if(s.containsAll(temp)) {
			System.out.println("IN ORIZZONTALE!");
			return true;
		}
		
		temp.removeAll(temp); // cancelliamo quelli orizzontali.
		
		// così controlliamo verticalmente y - 1 | p | y + 1
		temp.add(p);
		temp.add(new Pair<>(p.getX(), p.getY() + 1));
		temp.add(new Pair<>(p.getX(), p.getY() - 1));
		
		System.out.println("temp verticale: " + temp);
		
		if(s.containsAll(temp)) {
			System.out.println("IN VERTICALE!");
			return true;
		}
		
		temp.removeAll(temp); // cancelliamo quelli verticali.
		
		// così controlliamo diagonale destra (in giù e a sinistra) (x + 1, y + 1)	| p | (x - 1, y - 1) (a destra e in su)
		temp.add(p);
		temp.add(new Pair<>(p.getX() + 1, p.getY() + 1));
		temp.add(new Pair<>(p.getX() - 1, p.getY() - 1));
		
		System.out.println("temp diagonale destra: " + temp);
		
		if(s.containsAll(temp)) {
			System.out.println("DIAGONALE DESTRA!");
			return true;
		}
		
		temp.removeAll(temp); // cancelliamo quelli della diagonale destra.
		
		// così controlliamo diagonale sinistra (in giù e a sinistra) (x - 1, y - 1)	| p | (x + 1, y + 1) (a destra e in su)
		temp.add(p);
		temp.add(new Pair<>(p.getX() - 1, p.getY() - 1));
		temp.add(new Pair<>(p.getX() + 1, p.getY() + 1));
		
		System.out.println("temp diagonale sinistra: " + temp);
		
		if(s.containsAll(temp)) {
			System.out.println("DIAGONALE SINISTRA!");
			return true;
		}
		return false;
	}

	@Override
	public void setPlayers(Pair<Integer,Integer> p) {
		this.players.add(p);
		if(this.turn) {
			o.add(p);
		} else {
			x.add(p);
		}
		this.turn = !this.turn;
		
		System.out.println("players: " + this.players);
		System.out.println("o: " + this.o);
		System.out.println("x: " + this.x);
	}

	@Override
	public boolean isBelow(Pair<Integer, Integer> p) { // isThereBelow
		if(inBoundaries(p)) { //&& p.getY() != 0) {
			var temp = new Pair<>(p.getX(), p.getY() + 1); // + 1 verso il basso, - 1 verso l'alto, perchè (0,0) nell'angolo a sinistra.
			return this.players.contains(temp) || p.getY() == this.size - 1; // y - 1 fuori dalla mappa non è contenuto in players allora col or
			// lo facciamo uscire true.
		}
		return false;
	}
	
	private boolean inBoundaries(Pair<Integer,Integer> p) {
		return p.getX() >= 0 && p.getX() <= this.size - 1 && 
				p.getY() >= 0 && p.getY() <= this.size - 1;
	}

	@Override
	public Set<Pair<Integer, Integer>> getO() {
		return this.o;
	}

	@Override
	public Set<Pair<Integer, Integer>> getX() {
		return this.x;
	}

}
