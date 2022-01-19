package a02a.e1;

import java.util.HashSet;
import java.util.Set;

public class WorkflowImpl<T> implements Workflow<T> {
	
	private Set<T> tasks = new HashSet<>();
	//private Set<T> nextTasks = new HashSet<>();
	private Set<T> completedTasks = new HashSet<>();

	@Override
	public Set<T> getTasks() {
		return this.tasks;
	}

	@Override
	public Set<T> getNextTasksToDo() {
		return null;
	}

	@Override
	public void doTask(T t) {
		if(this.tasks.contains(t)) {
			this.completedTasks.add(t);
		}
	}

	@Override
	public boolean isCompleted() {
		return completedTasks.size() == tasks.size();
	}

}
