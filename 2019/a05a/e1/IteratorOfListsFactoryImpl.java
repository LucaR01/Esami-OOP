package a05a.e1;

import java.util.ArrayList;
import java.util.List;

public class IteratorOfListsFactoryImpl implements IteratorOfListsFactory {

	@Override
	public <E> IteratorOfLists<E> iterative(E e) {
		return new IteratorOfLists<>() {
			
			private List<E> list = new ArrayList<>();
			private boolean isFirst = true;

			@Override
			public List<E> nextList() {
				if(isFirst) {
					System.out.println("Lista vuota: " + list);
					isFirst = false;
					return list;
				}
				list.add(e);
				return list;
			}

			@Override
			public boolean hasOtherLists() {
				return true;
			}

			@Override
			public void reset() {
				list.removeAll(list);
				isFirst = true;
				
			}
			
		};
	}

	@Override
	public <E> IteratorOfLists<E> iterativeOnList(List<E> list) {
		return new IteratorOfLists<>() {
			
			private List<E> list2 = new ArrayList<>();
			private boolean isFirst = true;
			private int counter = 0;

			@Override
			public List<E> nextList() {
				if(this.isFirst)
				{
					this.isFirst = false;
					return this.list2;
				}
				var temp = this.counter;
				temp += 1;
				for(int i = counter; i < temp; i++){
					if(i >= list.size())
					{
						this.counter = 0;
						i = 0;
						temp = counter + 1;
					}
					this.list2.add(list.get(i));
				}
				this.counter++;
				System.out.println("list: " + this.list2);
				return this.list2;
				
			}

			@Override
			public boolean hasOtherLists() {
				return true;
			}

			@Override
			public void reset() {
				this.list2.removeAll(this.list2);
				this.isFirst = true;
				this.counter = 0;
				
			}
			
		};
	}

	@Override
	public <E> IteratorOfLists<E> iterativeWithPreamble(E e, List<E> preamble) {
		return new IteratorOfLists<>() {
			
			private List<E> list2 = new ArrayList<>();
			private boolean isFirst = true;
			private int counter = 0;

			@Override
			public List<E> nextList() {
				if(this.isFirst) {
					this.list2.addAll(preamble);
					this.isFirst = false;
					return this.list2;
				}
				this.list2.add(e);
				System.out.println("list2: " + this.list2);
				return this.list2;
			}

			@Override
			public boolean hasOtherLists() {
				return true;
			}

			@Override
			public void reset() {
				this.isFirst = true;
				this.list2.removeAll(this.list2);
			}
			
		};
	}

}
