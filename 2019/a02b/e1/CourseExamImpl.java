package a02b.e1;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class CourseExamImpl<A> implements CourseExam<A> {
	
	private Set<A> activities = new HashSet<>();
	
	/*public CourseExamImpl(Set<A> activities) {
		this.activities = activities;
	}*/

	@Override
	public Set<A> activities() {
		return this.activities;
	}

	@Override
	public Set<A> getPendingActivities() {
		return this.activities;
	}

	@Override
	public void completed(A a) {
		if(this.activities.contains(a)) {
			this.activities.remove(a);
		}
	}

	@Override
	public boolean examOver() {
		return this.activities.size() == 0;
	}

}
