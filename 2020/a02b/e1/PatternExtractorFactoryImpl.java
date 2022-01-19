package a02b.e1;

import java.util.ArrayList;
import java.util.List;

public class PatternExtractorFactoryImpl implements PatternExtractorFactory {

	@Override
	public PatternExtractor<Integer, Integer> countConsecutiveZeros() {
		return new PatternExtractor<>() {
			
			private final List<Integer> list = new ArrayList<>();
			
			private int counter = 0;

			@Override
			public List<Integer> extract(List<Integer> input) {
				for(var n : input) {
					if(n == 0) {
						this.counter += 1;
					} else {
						if(this.counter != 0) {
							this.list.add(this.counter);
							this.counter = 0;
						}
					}
				}
				System.out.println("countConsecutiveZeros: " + this.list);
				return this.list;
			}
			
		};
	}

	@Override
	public PatternExtractor<Double, Double> averageConsecutiveInRange(double min, double max) {
		return new PatternExtractor<>() {
			
			private List<Double> list = new ArrayList<>();
			
			private int counter = 0;
			
			private double value = 0.0f;

			@Override
			public List<Double> extract(List<Double> input) {
				for(var n : input) {
					if(n >= min && n <= max) {
						this.counter++;
						this.value += n;
					} else {
						if(this.counter != 0) {
							double average = this.value / this.counter;
							this.list.add(average);
							// Azzero il contatore e il valore
							this.counter = 0;
							this.value = 0.0f;
						}
					}
				}
				System.out.println("averageConsecutiveInRange: " + this.list);
				return this.list;
			}
			
		};
	}

	@Override
	public PatternExtractor<String, String> concatenateBySeparator(String separator) {
		return new PatternExtractor<>() {
			
			private List<String> list = new ArrayList<>();
			
			private String stringa = "";

			@Override
			public List<String> extract(List<String> input) {
				for(var n : input) {
					if(n != separator) {
						stringa += n;
						if(n == input.get(input.size() - 1)) { // .get(-1) per ottenere l'ultimo elemento
							this.list.add(stringa);
							//stringa = "";
						}
					} else {
						if(stringa.length() != 0) {
							this.list.add(stringa);
							// Azzero la stringa
							stringa = "";
						}
					}
				}
				System.out.println("concatenateBySeparator: " + this.list);
				return this.list;
			}
			
		};
	}

	@Override
	public PatternExtractor<String, Double> sumNumericStrings() {
		return new PatternExtractor<>() {
			
			private List<Double> list = new ArrayList<>();

			@Override
			public List<Double> extract(List<String> input) {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}

}
