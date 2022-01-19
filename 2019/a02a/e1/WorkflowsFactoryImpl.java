package a02a.e1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkflowsFactoryImpl implements WorkflowsFactory {

	@Override
	public <T> Workflow<T> singleTask(T task) {
		return new Workflow<>() {
			
			private boolean isCompleted = false;

			@Override
			public Set<T> getTasks() {
				return Set.of(task);
			}

			@Override
			public Set<T> getNextTasksToDo() {
				return this.isCompleted ? new HashSet<>() : Set.of(task);
			}

			@Override
			public void doTask(T t) {
				this.isCompleted = true;
			}

			@Override
			public boolean isCompleted() {
				return this.isCompleted;
			}
			
		};
	}

	@Override
	public <T> Workflow<T> tasksSequence(List<T> tasks) {
		return new Workflow<T>() {
			
			Set<T> set = new HashSet<>(tasks);
			private int current = 0;

			@Override
			public Set<T> getTasks() {
				return tasks.stream().collect(Collectors.toSet()); // fa la stessa cosa di Set<T> set = new HashSet<>(tasks); (?)
			}

			@Override
			public Set<T> getNextTasksToDo() {
				if(this.isCompleted()) {
					return Set.of();
				}
				return Set.of(tasks.get(current++));
			}

			@Override
			public void doTask(T t) {
				this.set.remove(t);
			}

			@Override
			public boolean isCompleted() {
				return this.set.size() == 0;
			}
			
		};
	}

	@Override
	public <T> Workflow<T> tasksJoin(Set<T> initialTasks, T finalTask) {
		return new Workflow<T>() {
			
			private boolean isFinalDone;
			
			List<T> tasks = new ArrayList<>(initialTasks);
			// Set<T> tasks = new HashSet<>(initialTasks); // mia variazione, ma non si può fare perchè poi rimuoverei degli elementi

			@Override
			public Set<T> getTasks() {
				Set<T> allTasks = new HashSet<>(initialTasks);
				allTasks.add(finalTask);
				return allTasks;
			}

			@Override
			public Set<T> getNextTasksToDo() {
				if(!isCompleted()) {
					if(tasks.size() == 0) {
						this.isFinalDone = true;
						return Set.of(finalTask);
					} else {
						return tasks.stream().collect(Collectors.toSet());
					}
				} else {
					return new HashSet<>();
				}
			}

			@Override
			public void doTask(T t) {
				this.tasks.remove(t); // si potrebbe aggiungere un controllo, se quella task è presente nel set
			}

			@Override
			public boolean isCompleted() {
				return tasks.size() == 0 && isFinalDone;
			}
			
		};
	}

	@Override
	public <T> Workflow<T> tasksFork(T initialTask, Set<T> finalTasks) {
		return new Workflow<T>() {
			
			List<T> tasks = new ArrayList<>(finalTasks);
			boolean isFirstIteration = true;

			@Override
			public Set<T> getTasks() {
				Set<T> set = new HashSet<>();
				set.add(initialTask);
				set.addAll(finalTasks);
				return set;
			}

			@Override
			public Set<T> getNextTasksToDo() {
				if(isFirstIteration) {
					this.isFirstIteration = false;
					return Set.of(initialTask);
				}
				if(isCompleted()) {
					return Set.of();
				} else {
					return tasks.stream().collect(Collectors.toSet());
				}
			}

			@Override
			public void doTask(T t) {
				if(finalTasks.contains(t)) {
					tasks.remove(t);
				}
			}

			@Override
			public boolean isCompleted() {
				return this.tasks.size() == 0;
			}
			
		};
	}

	@Override
	public <T> Workflow<T> concat(Workflow<T> first, Workflow<T> second) {
		// TODO Auto-generated method stub
		return new Workflow<>() {
			
			Set<T> set1 = first.getTasks();
			Set<T> set2 = second.getTasks();

			@Override
			public Set<T> getTasks() {
				Set<T> allTasks = new HashSet<>();
				allTasks.addAll(set1);
				allTasks.addAll(set2);
				return allTasks;
			}

			@Override
			public Set<T> getNextTasksToDo() {
				if(!isCompleted()) {
					if(!isFirstDone()) {
						return first.getNextTasksToDo();
					} else {
						return second.getNextTasksToDo();
					}
				} else {
					return Set.of();
				}
			}

			@Override
			public void doTask(T t) {
				if(!isCompleted()) {
					if(!isFirstDone()) {
						set1.remove(t);
					} else {
						set2.remove(t);
					}
				}
			}

			@Override
			public boolean isCompleted() {
				return this.set2.size() == 0;
			}
			
			private boolean isFirstDone() {
				return this.set1.size() == 0;
			}
			
		};
	}

}
