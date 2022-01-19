package a02c.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ListSplitterFactoryImpl implements ListSplitterFactory {

	@Override
	public <X> ListSplitter<X> asPairs() {
		return new ListSplitter<>() {

			@Override
			public List<List<X>> split(List<X> list) {
				
				List<List<X>> lists = new ArrayList<>();
				
				System.out.println("list size: " + list.size()); //TODO: remove
				
				for(int i = 0; i < list.size(); i++) {
					if((i % 2) == 0) {
						
						if((i + 1) >= list.size()) {
							return lists;
						}
						
						List<X> listCouples = new ArrayList<>();
						listCouples.add(list.get(i));
						listCouples.add(list.get(i + 1));
						lists.add(listCouples);
						
						//this.lists.get(i).addAll(listCouples);
						//lists.get(0).add(listCouples.get(0));
						System.out.println("listCouples: " + listCouples); //TODO: remove
					}
				}
				System.out.println("lists: " + lists); //TODO: remove
				return lists;
			}
			
		};
	}

	@Override
	public <X> ListSplitter<X> asTriplets() {
		return new ListSplitter<>() {

			@Override
			public List<List<X>> split(List<X> list) {
				
				System.out.println("------------ TEST TRIPLETS -------------------"); //TODO: remove
				
				List<List<X>> lists = new ArrayList<>();
				
				int counter = 3;
				
				for(int i = 0; i <= list.size(); i++) {
					if(i == counter) {
						counter += 3;
						List<X> couples = new ArrayList<>(); //TODO: oppure pair
						couples.add(list.get(i - 3));
						couples.add(list.get(i - 2));
						couples.add(list.get(i - 1));
						
						System.out.println("couples: " + couples); //TODO: remove
						
						lists.add(couples);
					}
				}
				System.out.println("lists: " + lists); //TODO: remove
				return lists;
			}
			
		};
	}

	@Override
	public <X> ListSplitter<X> asTripletsWithRest() {
		return new ListSplitter<>() {

			@Override
			public List<List<X>> split(List<X> list) {
				
				System.out.println("------------- TEST TRIPLETS WITH RESET ------------------");
				
				List<List<X>> lists = new ArrayList<>();
				
				/*for(int i = 0; i < list.size(); i++) {
					List<X> pair = new ArrayList<>();
					
					if((i % 2) == 0 && (!(i + 1 > list.size()) ) && (i != 0) ) {
						pair.add(list.get(i + 1));
						pair.add(list.get(i));
						pair.add(list.get(i - 1));
						
						System.out.println("pair: " + pair); //TODO: remove
						
						lists.add(pair);
					} else {
						if((i % 2) == 0 && (i != 0)) {
							pair.add(list.get(i + 1));
							pair.add(list.get(i + 2));
							
							System.out.println("pair: " + pair); //TODO: remove
							
							lists.add(pair);
						} else if(i == 0) {
							pair.add(list.get(i));
							pair.add(list.get(i + 1));
							pair.add(list.get(i + 2));
							
							System.out.println("pair: " + pair); //TODO: remove
							
							lists.add(pair);
						}
					}
				}*/
				
				for(int i = 0; i < list.size(); i += 3) { //TODO: prima era i += 3
					if(((i % 3) == 0 || i == 0) && (!(i + 3 > list.size())) ) {
						List<X> pair = new ArrayList<>();
						
						pair.add(list.get(i));
						pair.add(list.get(i + 1));
						pair.add(list.get(i + 2));
						
						System.out.println("pair: " + pair);
						
						lists.add(pair);
					} else {
						System.out.println("i: " + i);
						
						List<X> pair = new ArrayList<>();
						
						pair.add(list.get(i));
						pair.add(list.get(i + 1));
						
						System.out.println("pair: " + pair);
						
						lists.add(pair);
					}
				}
				
				System.out.println("lists: " + lists); //TODO: remove
				return lists;
			}
			
		};
	}

	@Override
	public <X> ListSplitter<X> bySeparator(X separator) {
		return new ListSplitter<>() {
			
			private int separatorPos = 0;

			@Override
			public List<List<X>> split(List<X> list) {
				System.out.println("-------------- TEST DELIMETER STRINGS ------------------"); //TODO: remove
				List<List<X>> lists = new ArrayList<>();
				
				for(int i = 0; i < list.size(); i++) {
					System.out.println("list get: " + list.get(i)); //TODO: remove
					/*if(list.get(i) == separator) {
						List<X> listStrings = new ArrayList<>();
						for(int j = separatorPos; j < (i - separatorPos); j++) {
							System.out.println("j: " + j); //TODO: remove
							System.out.println("i - separatorPos: " + (i - separatorPos)); //TODO: remove
							listStrings.add(list.get(j));
						}
						
						separatorPos = i;
						
						System.out.println("listStrings: " + listStrings); //TODO: remove
						lists.add(listStrings);
						
						List<X> separatorList = new ArrayList<>();
						separatorList.add(separator);
						lists.add(separatorList);
					}*/
					
					if(list.get(i) == separator) {
						List<X> listStrings = new ArrayList<>();
						
						for(int j = (separatorPos == 0) ? 0 : separatorPos + 1; j < i; j++) {
							listStrings.add(list.get(j));
						}
						
						separatorPos = i;
						
						lists.add(listStrings);
						
						List<X> separatorList = new ArrayList<>();
						separatorList.add(separator);
						lists.add(separatorList);
					}
					
					/*if((i + 1) == list.size() && list.get(i + 1) != separator) {
						List<X> listLastChar = new ArrayList<>();
						listLastChar.add(list.get(i + 1));
						
						lists.add(listLastChar);
					}*/
					
					if((i == (list.size() - 1)) && (list.get(i) != separator)) {
						List<X> listLastChar = new ArrayList<>();
						listLastChar.add(list.get(i));
						
						lists.add(listLastChar);
					}
				}
				
				/*List<X> lastChar = new ArrayList<>();
				lastChar.add(list.get(list.size() - 1));
				
				System.out.println("last char: " + list.get(list.size() - 1));
				
				lists.add(lastChar);*/
				
				System.out.println("lists: " + lists); //TODO: remove
				
				return lists;
			}
			
		};
	}

	@Override
	public <X> ListSplitter<X> byPredicate(Predicate<X> predicate) {
		// TODO Auto-generated method stub
		return null;
	}

}
