package a01a.e2;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {
	
	private final int size;
	private final int shipSize;
	private int counter;
	//private final Player p;
	
	private Optional<Player> winner = Optional.empty();
	
	//Pair<Integer, Integer> ship = randomSpawn();
	
	private Set<Pair<Integer, Integer>> ship; // col Set posso avere più elementi composti da Pair, da coordinate x ed y.
	
	public LogicsImpl(int size, int shipSize) {
		this.size = size;
		this.shipSize = shipSize;
		this.ship = new HashSet<>(randomSpawn());
	}

	@Override
	public boolean hit(int x, int y) {
		var p = new Pair<>(x,y);
		if(this.ship.contains(p)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isOver() {
		System.out.println("counter" + counter);
		return this.winner.isPresent() || counter == 5;
	}

	@Override
	public Set<Pair<Integer, Integer>> randomSpawn() {
		final Random random = new Random();
		Set<Pair<Integer, Integer>> p = new HashSet<>();
		
		int max = this.shipSize <= 2 ? this.shipSize : 2;
		int min = 0;
		
		int x = random.nextInt((2 - 0) + 1) + 0; // ((max - min) + 1) + min)
		int y = random.nextInt((max - min) + 1) + min;
		System.out.println("shipSize: " + this.shipSize);
		var spawnPos = new Pair<>(x,y);
		System.out.println("spawnPos: " + spawnPos);
		p.add(spawnPos); // Prima posizione random, le altre devono essere adiacenti.
		
		for(int i = 0; i < (this.shipSize - 1); i++) {
			/*var temp = new Pair<>(x = x > i || x >= 2 ? x - 1 : x + 1, y = y > i || y >= 2 ? y + 1 : y - 1); // Se è maggiore a i, facciamo x o y - 1, altrimenti x o y + 1
			System.out.println("X: " + x + " Y: " + y);
			p.add(temp);*/
			if(x == 2) {
				var temp = new Pair<>(x - (i + 1),y);
				p.add(temp);
			}
			else if(y == 2) {
				p.add(new Pair<>(x,y - (i + 1)));
			}
			else if(x == 0 && y == 0){
				p.add(new Pair<>(x, y + (i + 1)));
			}
			else if(x == 0) {
				p.add(new Pair<>(x + (i+1), y));
			}
			else if(y == 0) {
				p.add(new Pair<>(x, y + (i+1)));
			}
			else if(x == 1 && y == 1) {
				//p.add(new Pair<>(x, y + 1)); // una volta più, una volta meno.
				//p.add(new Pair<>(x, y - 1));
				p.add(new Pair<>(x, ((-i + 1)^i) > 0 ? y + 1 : y - 1 )); // 1^0 = 1, 0^1 = 0
				//break; // non c'è manco bisogno di ripetere il loop (?)
			}
		}
		
		System.out.println("Set<Pair<Integer,Integer>> p: " + p);
		return p;
	}
	
	public int getCounter() {
		return this.counter;
	}
	
	public int incrementCounter() {
		System.out.println("counter" + counter);
		return this.counter++;
	}
	
	public int resetCounter() {
		return this.counter = 0;
	}

	@Override
	public Optional<Player> isWinner() {
		return this.winner = Optional.of(Player.X);
	}

}
