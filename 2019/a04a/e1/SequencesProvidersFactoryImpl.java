package a04a.e1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SequencesProvidersFactoryImpl implements SequencesProvidersFactory {

	@Override
	public <E> SequencesProvider<E> iterative(E e) {
		return new SequencesProvider<>() {
			
			private List<E> list = new ArrayList<>();
			private int counter = 0;
			//private int i;

			@Override
			public List<E> nextSequence() {
				int i;
				List<E> p = new ArrayList<>();
				for(i = 0; i < this.counter; i++) {
					p.add(e);
					System.out.println("dentro al loop: " + i);
				}
				this.counter += 1;
				System.out.println("counter += 1: " + this.counter);
				System.out.println("list: " + this.list);
				this.list = p;
				return this.list;
			}

			@Override
			public boolean hasOtherSequences() {
				return this.list.size() < (int) e;
			}

			@Override
			public void reset() {
				this.list.removeAll(this.list); // oppure removeAll
				System.out.println("List dopo la remove: " + this.list);
				this.counter = 0;
				System.out.println("counter dopo la remove: " + this.counter);
			}
			
		};
	}

	@Override
	public <E> SequencesProvider<E> alternating(E e1, E e2) {
		return new SequencesProvider<>() {
			
			private List<Pair<E,E>> list = new ArrayList<>();
			private int counter = 0;
			private boolean turn_e2 = false;

			@Override
			public List<E> nextSequence() {
				int i;
				List<E> p = new ArrayList<>();
				if(this.turn_e2) {
					for(i = 0; i < this.counter; i++) {
						p.add(e2);
						this.list.add(new Pair<>(null,e2));
					}
					this.counter += 1;
					this.turn_e2 = false;
				} else {
					for(i = 0; i < this.counter; i++) {
						p.add(e1);
						this.list.add(new Pair<>(e1,null));
					}
					this.turn_e2 = true;
				}
				System.out.println("testAlternating list: " + this.list);
				System.out.println("testAlternating p: " + p);
				return p;
			}

			@Override
			public boolean hasOtherSequences() {
				return this.list.size() < 54;
			}

			@Override
			public void reset() {
				this.list.removeAll(this.list);
				this.counter = 0;
			}
			
		};
	}

	@Override
	public <E> SequencesProvider<E> iterativeBounded(E e, int bound) {
		return new SequencesProvider<>() {
			
			List<E> list = new ArrayList<>();
			private int counter = 0;

			@Override
			public List<E> nextSequence() {
				int i;
				List<E> p = new ArrayList<>();
				for(i = 0; i < this.counter; i++){//(this.counter >= bound ? this.counter = 0 : this.counter); i++) {
					p.add(e);
				}
				this.counter += 1;
				this.list = p;
				return this.list;
			}

			@Override
			public boolean hasOtherSequences() {
				//return this.list.size() < bound;
				return this.counter < bound;
			}

			@Override
			public void reset() {
				this.list.removeAll(this.list);
				System.out.println("list size: " + this.list.size());
				this.counter = 0;
			}
			
		};
	}

	@Override
	public <E> SequencesProvider<E> alternatingBounded(E e1, E e2, int bound) {
		return new SequencesProvider<>() {
			
			private List<Pair<E,E>> list = new ArrayList<>();
			private int counter = 0;
			private boolean e2Turn = false;

			@Override
			public List<E> nextSequence() {
				List<E> p = new ArrayList<>();
				int i;
				if(this.e2Turn) {
					for(i = 0; i < this.counter; i++) {
						p.add(e2);
						this.list.add(new Pair<>(null, e2));
					}
					this.counter += 1;
					this.e2Turn = false;
				} else {
					for(i = 0; i < this.counter; i++) {
						p.add(e1);
						this.list.add(new Pair<>(e1, null));
					}
					this.e2Turn = true;
				}
				
				return p;
			}

			@Override
			public boolean hasOtherSequences() {
				return this.list.size() < bound;
			}

			@Override
			public void reset() {
				this.list.removeAll(this.list);
				this.counter = 0;
			}
			
		};
	}

}
