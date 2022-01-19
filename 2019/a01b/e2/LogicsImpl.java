package a01b.e2_2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics{

	private final int gridSize;
	private final int nMines;
	private int countMinesSpots;
	private boolean isOver = false;
	private final Random r = new Random();
	private Pair<Integer, Integer> currentSelection;
	private final Map<Pair<Integer, Integer>, Integer> minesInGrid = new HashMap<>();
	private final Set<Pair<Integer, Integer>> hits = new HashSet<>();
	
	
	public LogicsImpl(final int gridSize, final int nMines) {
		this.currentSelection = new Pair<>(0, 0);
		this.gridSize = gridSize;
		this.nMines = nMines;
		for (int i = 0; i < this.nMines; i++) {
			Pair<Integer, Integer> p = new Pair<>(r.nextInt(this.gridSize), r.nextInt(this.gridSize));
			if (this.minesInGrid.containsKey(p)) {
				int sel = this.minesInGrid.get(p);
				this.minesInGrid.put(p, sel + 1);
			} else {
				this.countMinesSpots++;
				this.minesInGrid.put(p, 1);
			}
		}
	}
	
	@Override
	public int hit(Pair<Integer, Integer> p) {
		this.currentSelection = p;
		if (this.minesInGrid.containsKey(p)) {
			this.isOver = true;
		}
		this.minesInGrid.remove(p);
		this.hits.add(p);
		return this.countNeighbors(p);
	}
	
	private int countNeighbors(Pair<Integer, Integer> pair) {
		int x = pair.getX();
		int y = pair.getY();
		int res = 0;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (isInBound(pair) && exists(new Pair<>(i, j))) {
					res = res + this.minesInGrid.get(new Pair<>(i, j));
				}
			}
		}
		System.out.println(res);
		return res;
	}

	private boolean exists(Pair<Integer, Integer> p) {
		return this.minesInGrid.containsKey(p);
	}
	
	private boolean isInBound(Pair<Integer, Integer> p) {
		return p.getX() >= 0 && p.getX() < this.gridSize
				&& p.getY() >= 0 && p.getY() < this.gridSize;
	}
	
	@Override
	public boolean won() {
		if (this.hits.size() == (this.gridSize * this.gridSize) - this.countMinesSpots) {
			System.out.println("YOU WON!");
			return true;
		} else if (this.isOver) {
			System.out.println("YOU LOST");
			return true;
		}
		return false;
	}

}
