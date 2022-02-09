package a02b.e1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UniversityProgramFactoryImpl implements UniversityProgramFactory {

	@Override
	public UniversityProgram flexible() {
		return new UniversityProgram() {
			
			private static final int CREDITS = 60;
			
			private Map<String, Integer> map = new HashMap<>();
			
			private int sum = 0;

			@Override
			public void addCourse(String name, Sector sector, int credits) {
				this.map.put(name, credits);
			}

			@Override
			public boolean isValid(Set<String> courseNames) {
				
				this.sum = 0;
				
				for(var c : courseNames) {
					this.sum += this.map.get(c);
				}
				
				return this.sum == CREDITS;
			}
			
		};
	}

	@Override
	public UniversityProgram scientific() {
		return new UniversityProgram() {
			
			private static final int CREDITS = 60;
			private static final int SCIENTIFIC_CREDITS = 12;
			
			private Map<Pair<String, Sector>, Integer> map = new HashMap<>();
			
			private int sum = 0;
			
			private int math = 0;
			private int physics = 0;
			private int computerScience = 0;

			@Override
			public void addCourse(String name, Sector sector, int credits) {
				this.map.put(new Pair<>(name, sector),  credits);
			}

			@Override
			public boolean isValid(Set<String> courseNames) {
				
				this.sum = 0;
				this.math = 0;
				this.physics = 0;
				this.computerScience = 0;
				
				for(var c : courseNames) {
					for(var s : this.map.keySet()) {
						if(c.equals(s.get1()) && s.get2() == Sector.COMPUTER_SCIENCE) {
							this.computerScience += this.map.get(s);
						}
						
						if(c.equals(s.get1()) && s.get2() == Sector.MATHEMATICS) {
							this.math += this.map.get(s);
						}
						
						if(c.equals(s.get1()) && s.get2() == Sector.PHYSICS) {
							this.physics += this.map.get(s);
						}
						
						if(c.equals(s.get1())) {
							this.sum += this.map.get(s);
						}
					}
				}
				
				return this.sum == CREDITS && this.computerScience >= SCIENTIFIC_CREDITS && this.math >= SCIENTIFIC_CREDITS && this.physics >= SCIENTIFIC_CREDITS;
			}
			
		};
	}

	@Override
	public UniversityProgram shortComputerScience() {
		return new UniversityProgram() {
			
			private final static int CREDITS = 48;
			private static final int COMPUTER_CREDITS = 30;
			
			private Map<Pair<String, Sector>, Integer> map = new HashMap<>();
			
			private int sum = 0;
			private int sumComputerScience = 0;

			@Override
			public void addCourse(String name, Sector sector, int credits) {
				this.map.put(new Pair<>(name, sector), credits);
			}

			@Override
			public boolean isValid(Set<String> courseNames) {
				this.sum = 0;
				this.sumComputerScience = 0;
				
				for(var c : courseNames) {
					for(var s : this.map.keySet()) {
						if(c.equals(s.get1()) && (s.get2() == Sector.COMPUTER_SCIENCE || s.get2() == Sector.COMPUTER_ENGINEERING)) {
							this.sumComputerScience += this.map.get(s);
						}
						
						if(c.equals(s.get1())) {
							this.sum += this.map.get(s);
						}
					}
				}
				
				return this.sum >= CREDITS && this.sumComputerScience >= COMPUTER_CREDITS;
			}
			
		};
	}

	@Override
	public UniversityProgram realistic() {
		// TODO Auto-generated method stub
		return null;
	}

}
