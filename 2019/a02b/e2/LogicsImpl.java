package a02b_2.e2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {
	
	private final int gridSize;
	private final Map<Pair<Integer,Integer>, Boolean> selected = new HashMap<>();
	private Pair<Integer,Integer> currentSelection;
	
	public LogicsImpl(final int gridSize) {
		this.currentSelection = new Pair<>(0,0);
		this.gridSize = gridSize;
	}

	@Override
	public void hit(Pair<Integer, Integer> p) {
		isDirectionUp(p);
		isDirectionDown(p);
		if(!hasToGoUp(p)) {
			this.selected.put(p, false);
		} else if(hasToGoUp(p)) {
			this.selected.put(this.currentSelection, true);
		}
	}

	private boolean hasToGoUp(Pair<Integer, Integer> p) {
		return this.selected.containsKey(p) && this.selected.get(p);
	}

	@Override
	public void hitBar() {
		Map<Pair<Integer,Integer>, Boolean> selectedCopy = new HashMap<>(selected);
		selectedCopy.entrySet().forEach(m -> {
			isDirectionUp(m.getKey());
			isDirectionDown(m.getKey());
			if(hasToGoUp(m.getKey())) {
				this.selected.put(new Pair<Integer,Integer>(m.getKey().getX() - 1, m.getKey().getY()), true);
				System.out.println(m.getValue());
				this.selected.remove(m.getKey());
			} else if(!hasToGoUp(m.getKey())) {
				this.selected.put(new Pair<Integer,Integer>(m.getKey().getX() + 1, m.getKey().getY()), false);
				this.selected.remove(m.getKey());
			}
		});
	}

	@Override
	public Set<Pair<Integer, Integer>> getSelected() {
		return this.selected.keySet().stream().collect(Collectors.toSet());
	}
	
	private boolean isDirectionUp(Pair<Integer,Integer> p) {
		if(p.getX().equals(this.gridSize - 1)) {
			this.selected.put(p, true);
			return true;
		}
		return false;
	}
	
	private boolean isDirectionDown(Pair<Integer,Integer> p) {
		if(p.getX().equals(0)) {
			this.selected.put(p, false);
			return true;
		}
		return false;
	}

}
