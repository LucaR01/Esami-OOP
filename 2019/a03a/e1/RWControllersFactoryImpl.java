package a03a.e1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RWControllersFactoryImpl implements RWControllersFactory {

	@Override
	public RWController createPermissive() {
		return new RWController() {
			
			private List<Integer> list = new ArrayList<>();
			private int idCounter;

			@Override
			public int enter() {
				for(int i = this.idCounter; i <= this.idCounter; i++) {
					this.list.add(i);
					this.idCounter += 1;
					return i;
				}
				return 0;
				//return idCounter;
			}

			@Override
			public void goRead(int id) {
				if(this.list.contains(id)) {
					System.out.println("id: " + id + " reads");
				}
				
			}

			@Override
			public void goWrite(int id) {
				if(this.list.contains(id)) {
					System.out.println("id: " + id + " writes");
				}
			}

			@Override
			public void exit(int id) {
				if(this.list.contains(id)) {
					list.remove(Integer.valueOf(id));
				}
			}
			
		};
	}

	@Override
	public RWController createOnlyRead() {
		return new RWController() {
			
			private List<Integer> list = new ArrayList<>();
			private int idCounter;

			@Override
			public int enter() {
				for(int i = this.idCounter; i <= this.idCounter; i++) {
					this.list.add(i);
					this.idCounter += 1;
					return i;
				}
				return 0;
			}

			@Override
			public void goRead(int id) {
				if(list.contains(id)) {
					System.out.println("TestOnlyRead id: " + id + " reads");
				}
			}

			@Override
			public void goWrite(int id) {
				final String msg = "TestOnlyRead id: " + id + " non può scrivere!";
				System.out.println(msg);
				throw new java.lang.IllegalStateException(msg);
			}

			@Override
			public void exit(int id) {
				if(list.contains(id)) { // list.contains
					list.remove(Integer.valueOf(id));
					// c'era un problema nell'uscita dei valori, me li cancella in base all'indice, io li voglio cancellare in base al valore.
					//Integer.valueOf(id)); // questa va bene
					//removeAll(Arrays.asList(id)); // questa anche va bene
					//new Integer(id)); //deprecata
				}
				System.out.println("TestOnlyRead list: " + list);
			}
			
		};
	}

	@Override
	public RWController createMutualExclusion() {
		return new RWController() {
			
			//private List<Integer> list = new ArrayList<>();
			private Map<Integer, Boolean> map = new HashMap<>();
			private int idCounter;
			//private boolean isReading = false;

			@Override
			public int enter() {
				for(int i = this.idCounter; i <= this.idCounter; i++) {
					//this.list.add(i);
					this.map.put(i, false);
					System.out.println("testMutualExclusion: " + map);
					this.idCounter += 1;
					return i;
				}
				return 0;
			}

			@Override
			public void goRead(int id) {
				map.forEach( (k,v) -> {
					if(v == false) {
						map.replace(id, true);
					} else {
						throw new java.lang.IllegalStateException();
					}
				});
				/*if(isReading == false) {
					if(list.contains(id)) {
						isReading = true;
						System.out.println("id: " + id + " is reading!");
					}
				} else {
					throw new java.lang.IllegalStateException();
				}*/
				/*
				int j = 0;
				while(j < map.size()) {
					if(map.entrySet().iterator().next().getValue() == true) {
						//map.replace(id, true);
						//throw new java.lang.IllegalStateException();
						System.out.println("here");
					} else {
						map.replace(id, true);
					}*/
					//j++;
					/*else {
						throw new java.lang.IllegalStateException();
					}*/
				//}
			}

			@Override
			public void goWrite(int id) {
				/*Set set = map.entrySet();
				System.out.println("Elements in HashMap...");
				// Get an iterator
				Iterator i = set.iterator();
				// Display elements
				while(i.hasNext()) {*/
					/*Map.Entry me = (Map.Entry)i.next();
					System.out.print(me.getKey() + ": ");
					System.out.println(me.getValue());*/
					/*if(map.get(id) == false) {
						map.replace(id, true);
					}*/
				
				/*Collection<Boolean> set = map.values(); // Anche questo va bene!
				for(boolean b : set) {
					if(b == false) {
						map.replace(id, true);
					}
					else {
						throw new java.lang.IllegalStateException();
					}
				}*/
				
				for(Map.Entry<Integer, Boolean> entry : map.entrySet()) {
					boolean b = entry.getValue();
					if(b == false) {
						map.replace(id, true);
					} else {
						throw new java.lang.IllegalStateException();
					}
				}
				
				/*int i = 0;
				while(i < map.size()) { //map.entrySet().iterator().hasNext()) {
					if(map.entrySet().iterator().next().getValue() == true) {
						throw new java.lang.IllegalStateException();
					}
					else {
						map.replace(id, true);
					}
					i++;
				}*/
			}

			@Override
			public void exit(int id) {
				if(map.containsKey(id)) {
					map.remove(Integer.valueOf(id));
					/*if(map.get(id) == true) { // Non serve controllare perchè tanto lo rimuovo
						
					}*/
				}
				System.out.println("testMutualExclusion " + map);
				System.out.println("testMutualExclusion map new size: " + map.size());
			}
			
		};
	}

	@Override
	public RWController createManyReadersOrOneWriter() {
		// TODO Auto-generated method stub
		return null;
	}

}
