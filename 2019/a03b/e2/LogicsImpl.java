package a03b.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {
	
	private Set<Pair<Integer,Integer>> player = new HashSet<>();
	private final int size;
	
	public LogicsImpl(int size) {
		this.size = size;
	}

	@Override
	public Set<Pair<Integer, Integer>> randSpawnPosition() {
		Random rand = new Random();
		Pair<Integer,Integer> pos = new Pair<>(rand.nextInt(0 + (this.size - 1) + 1), 0);
		int i = 0;
		/*while(i < player.size()) {
			if(player.iterator().next().equals(pos)) {
				pos = new Pair<>(rand.nextInt(0 + (this.size - 1) + 1), 0);
			}
		}*/
		System.out.println("randSpawnPosition(): " + pos);
		player.add(pos);
		System.out.println("set dopo randSpawnPosition: " + this.player);
		return this.player;
	}
	
	public Set<Pair<Integer,Integer>> move(){
		Set<Pair<Integer,Integer>> temp = new HashSet<>();
		//int i = 0;
		/*while(i < player.size()) {
			if(inBoundaries(new Pair<>(this.player.iterator().next().getX(), this.player.iterator().next().getY() + 1))) {
				temp.add(new Pair<>(this.player.iterator().next().getX(), this.player.iterator().next().getY() + 1));
			}	
		}*/
		this.player.forEach(p -> {
			if(inBoundaries(new Pair<>(p.getX(), p.getY() + 1))) {
				temp.add(new Pair<>(p.getX(), p.getY() + 1));
			} else {
				System.out.println("Fuori boundaries!");
				System.exit(0);
			}
		});
		//int j = 0;
		/*while(j  < player.size()) {
			this.player.remove(player.iterator().next());
			this.player.removeAll(player);
		}*/
		this.player.removeAll(this.player);
		System.out.println("set dopo la remove: " + this.player);
		this.player = temp;
		System.out.println("set dopo l'aggiunta di temp: " + this.player);
		return this.player;
	}

	@Override
	public Set<Pair<Integer, Integer>> getPlayer() {
		return this.player;
	}
	
	private boolean inBoundaries(Pair<Integer,Integer> p) {
		return p.getX() >= 0 && p.getX() <= this.size - 1 && p.getY() >= 0 && p.getY() <= this.size - 1;
	}

}
