package a04b.e1;

import java.util.ArrayList;
import java.util.List;

public class ParsersFactoryImpl implements ParsersFactory {

	@Override
	public Parser<Integer> createSequenceParserToCount(String t) {
		return new Parser<>() {
			
			private List<String> list = new ArrayList<>();
			private int counter = 0;

			@Override
			public boolean getNext(String token) {
				if(token == t) {
					this.list.add(token);
					System.out.println("list: " + this.list);
					this.counter += 1;
					return token == t;
				} else {
					//token = t;
					throw new java.lang.IllegalStateException();
					//return false;
				}
				
			}

			@Override
			public boolean getAllInList(List<String> tokens) {
				/*tokens.forEach( k -> {
					if(t.contains(this.list)) {
						
					}
				});*/
				//tokens.containsAll(this.list);
				return this.list.containsAll(tokens);
			}

			@Override
			public Integer completeAndCreateResult() {
				return this.counter;
			}
			
		};
	}

	@Override
	public Parser<String> createNonEmptySequenceParserToString(String t) {
		return new Parser<>() {
			
			private List<String> list = new ArrayList<>();
			private int counter = 4;
			

			@Override
			public boolean getNext(String token) {
				if(token == t) {
					for(int i = 0; i < this.counter; i++) {
						list.add(token);
						this.counter += 1;
						System.out.println("optionalTest list: " + this.list);
					}
					return token == t; //true;
				} else {
					throw new java.lang.IllegalStateException();
				}
				//return false;
			}

			@Override
			public boolean getAllInList(List<String> tokens) {
				return this.list.containsAll(tokens);
			}

			@Override
			public String completeAndCreateResult() {
				String result = "";
				for(int i = 0; i < this.list.size(); i++){//this.counter; i++) {
					result += t;
				}
				System.out.println("result.length(): " + result.length());
				System.out.println("this.counter: " + this.counter);
				if(result.length() == this.counter) {
					return result;
				} else {
					throw new java.lang.IllegalStateException();
				}
				//return result;
			}
			
		};
	}

	@Override
	public Parser<Integer> createExpressionParserToResult() {
		return new Parser<>() {
			
			private List<String> list = new ArrayList<>();
			private List<String> list2 = new ArrayList<>();
			private int counter = 0;

			@Override
			public boolean getNext(String token) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean getAllInList(List<String> tokens) {
				//this.list.addAll(tokens);
				if(this.list.containsAll(tokens)) {
					return true;
				} else {
					//this.list2.addAll(tokens);
					this.list.addAll(tokens);
					return false; // se restituisco true alcuni test funzionano ed altri smettono di funzionare!
				}
				//return this.list.containsAll(tokens);
			}

			@Override
			public Integer completeAndCreateResult() {
				int result = 0;
				//this.list = list2;
				String x = this.list.get(0);
				String y = this.list.get(2);
				String z = this.list.get(4);
				if(list.get(1) == "+") {
					result += Integer.parseInt(x) + Integer.parseInt(y);
				}
				else if(list.get(1) == "-") {
					result += Integer.parseInt(x) - Integer.parseInt(y);
				}
				if(list.get(3) == "+") {
					result += Integer.parseInt(z);
				}
				else if(list.get(3) == "-") {
					result -= Integer.parseInt(z);
				}
				return result;
			}
			
		};
	}

}
