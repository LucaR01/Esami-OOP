package a01a.e1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TreeFactoryImpl implements TreeFactory {

	@Override
	public <E> Tree<E> singleValue(E root) {
		return new Tree<>() {
			
			private List<Tree<E>> list = new ArrayList<>();
			private Set<E> set = new HashSet<>();

			@Override
			public E getRoot() {
				return root;
			}

			@Override
			public List<Tree<E>> getChildren() {
				return this.list;
			}

			@Override
			public Set<E> getLeafs() {
				this.set.add(root);
				return this.set;
			}

			@Override
			public Set<E> getAll() {
				this.set.add(root);
				return this.set;
			}
			
		};
	}

	@Override
	public <E> Tree<E> twoChildren(E root, Tree<E> child1, Tree<E> child2) {
		return new Tree<E>() {
			
			private List<Tree<E>> list = Arrays.asList( child1, child2 );

			@Override
			public E getRoot() {
				return null;
			}

			@Override
			public List<Tree<E>> getChildren() {
				return this.list;
			}

			@Override
			public Set<E> getLeafs() {
				return null;
			}

			@Override
			public Set<E> getAll() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}

	@Override
	public <E> Tree<E> oneLevel(E root, List<E> children) {
		return new Tree<E>() {
			
			private List<E> list = new ArrayList<>();

			@Override
			public E getRoot() {
				return root;
			}

			@Override
			public List<Tree<E>> getChildren() {
				this.list.addAll(children);
				return (List<Tree<E>>) this.list;
			}

			@Override
			public Set<E> getLeafs() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Set<E> getAll() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}

	@Override
	public <E> Tree<E> chain(E root, List<E> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
