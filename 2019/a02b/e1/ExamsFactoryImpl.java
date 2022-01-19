package a02b.e1;

/*import static a02b.e1.ExamsFactory.OOPExamActivities.FINAL_EVALUATION;
import static a02b.e1.ExamsFactory.OOPExamActivities.LAB_EXAM;
import static a02b.e1.ExamsFactory.OOPExamActivities.LAB_REGISTER;
import static a02b.e1.ExamsFactory.OOPExamActivities.PROJ_EXAM;
import static a02b.e1.ExamsFactory.OOPExamActivities.PROJ_PROPOSE;
import static a02b.e1.ExamsFactory.OOPExamActivities.PROJ_SUBMIT;*/

import java.util.ArrayList;
import java.util.Arrays;
//import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ExamsFactoryImpl implements ExamsFactory {

	@Override
	public CourseExam<SimpleExamActivities> simpleExam() {
		return new CourseExam<>() {
			
			private Set<SimpleExamActivities> set = new LinkedHashSet<>(); // l'HashSet mantiene l'ordine (alfabetico) quindi REGISTER sarebbe il primo elemento.
			//private List<SimpleExamActivities> activities = new ArrayList<>(Arrays.asList(SimpleExamActivities.WRITTEN, SimpleExamActivities.ORAL, SimpleExamActivities.REGISTER));
			private int currentActivity = 0;

			@Override
			public Set<SimpleExamActivities> activities() {
				this.set.add(SimpleExamActivities.WRITTEN);
				this.set.add(SimpleExamActivities.ORAL);
				this.set.add(SimpleExamActivities.REGISTER);
				Iterator<SimpleExamActivities> i = set.iterator();
				while(i.hasNext()) {
					System.out.println(i.next());
				}
				System.out.println("set: " + set);
				return this.set;
				//return activities.stream().collect(Collectors.toSet());
			}

			@Override
			public Set<SimpleExamActivities> getPendingActivities() {
				List<SimpleExamActivities> list = new ArrayList<SimpleExamActivities>(set);
				if(this.currentActivity < this.set.size()) {
					if(this.set.size() == 3) {
						System.out.println("currentActivity: " + list.get(currentActivity));
						return Set.of(list.get(currentActivity));
					}
					/*else if(this.set.size() == 2) {
						return Set.of(list.get(currentActivity));
					}
					else if(this.set.size() == 1) {
						return Set.of(list.get(currentActivity));
					}*/
					else {
						return Set.of(list.get(currentActivity));
					}
				}
				return Set.of();
				/*if(!examOver()) {
					if(this.currentActivity < this.set.size()) {
						List<SimpleExamActivities> list = new ArrayList<SimpleExamActivities>(set); // lo trasformo in un ArrayList per poter ottenere il secondo elemento.
						return Set.of(list.get(currentActivity));
					}
				}
				return Set.of();*/ // Set vuoto oppure null
			}

			@Override
			public void completed(SimpleExamActivities a) {
				if(this.set.contains(a)) {
					this.set.remove(a);
					System.out.println("set after completion: " + set);
				}
				System.out.println("set size after completion: " + set.size());
			}

			@Override
			public boolean examOver() {
				return this.set.size() == 0;
			}
			
		};
	}

	@Override
	public CourseExam<OOPExamActivities> simpleOopExam() {
		return new CourseExam<OOPExamActivities>() {
			
			private Set<OOPExamActivities> activities = new LinkedHashSet<>();
			private int currentActivity = 0;

			@Override
			public Set<OOPExamActivities> activities() {
				this.activities.add(OOPExamActivities.LAB_REGISTER);
				this.activities.add(OOPExamActivities.LAB_EXAM);
				this.activities.add(OOPExamActivities.PROJ_PROPOSE);
				this.activities.add(OOPExamActivities.PROJ_SUBMIT);
				this.activities.add(OOPExamActivities.PROJ_EXAM);
				this.activities.add(OOPExamActivities.FINAL_EVALUATION);
				System.out.println("Set OOPExamActivities: " + this.activities);
				return this.activities;
			}

			@Override
			public Set<OOPExamActivities> getPendingActivities() {
				List<OOPExamActivities> list = new ArrayList<>(activities);
				if(this.activities.size() == 6) {
					return Set.of(list.get(currentActivity), list.get(currentActivity = 2));
				} 
				else if(this.activities.size() == 3){//!this.activities.contains(OOPExamActivities.LAB_EXAM)) {
					this.currentActivity = 0;
					System.out.println("LAB_EXAM Completed: " + (this.currentActivity));
					System.out.println("LAB_EXAM Completed: " + list.get(this.currentActivity));
					return Set.of(list.get(this.currentActivity)); 
				}
				else if(this.activities.size() != 6 && this.activities.size() != 3 && this.activities.size() > 0){
					if(this.activities.size() <= 2) {
						this.currentActivity = 0;
						return Set.of(list.get(this.currentActivity));
					}
					this.currentActivity = 0;//this.currentActivity += 1;
					System.out.println("CurentActivity e CurrentActivity++" + this.currentActivity + " " + (++this.currentActivity) + " ");
					System.out.println("list.get(--currentActivity), list.get(++currentActivity)" + list.get(--this.currentActivity) + " " + list.get(++this.currentActivity));
					return Set.of(list.get(--this.currentActivity), list.get(++this.currentActivity));
				}
				else if(this.activities.size() == 0){
					return Set.of();
				}
				return Set.of();
			}

			@Override
			public void completed(OOPExamActivities a) {
				if(this.activities.contains(a)) {
					this.activities.remove(a);
				}
				System.out.println("Nuovo size OOPExamActivities: " + this.activities.size());
				System.out.println("Elementi rimanenti OOPExamActivities: " + this.activities);
			}

			@Override
			public boolean examOver() {
				return this.activities.size() == 0;
			}
			
		};
	}

	@Override
	public CourseExam<OOPExamActivities> fullOopExam() {
		// TODO Auto-generated method stub
		return null;
	}

}
