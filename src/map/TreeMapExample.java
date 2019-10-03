/**
 * 
 */
package map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;


/**
 * @author nagendra
 *
 */
public class TreeMapExample {

	public static void main(String[] args) {
		/**
		 * Sample example to Create a TreeMap based on different sorting approach.
		 */
		Map<Person, Integer> map = new TreeMap<>();
		map.put(new Person("A", 12, 1240), 1);
		map.put(new Person("B", 11, 1239), 2);
		map.put(new Person("C", 15, 1259), 11);
		map.put(new Person("D", 13, 1200), 7);
		map.put(new Person("E", 12, 1300), 15);
		
		System.out.println("Printing Items based on Comparable implemented by Person class");
		for (Map.Entry<Person, Integer>  entry: map.entrySet()) {
			System.out.println(entry);
		}
		
		System.out.println();
		
		/**
		 * Sort based on Comparator classes
		 */
		Comparator<Person> myComp = new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				int value = o1.age - o2.age ;
				return value == 0 ? 1 : value;
			}
		};
		
		Map<Person, Integer> mapWithComp = new TreeMap<>(myComp);
		mapWithComp.put(new Person("A", 12, 1240), 1);
		mapWithComp.put(new Person("B", 11, 1239), 2);
		mapWithComp.put(new Person("C", 15, 1259), 11);
		mapWithComp.put(new Person("D", 13, 1200), 7);
		mapWithComp.put(new Person("E", 12, 1300), 15);
		
		System.out.println("Printing Items based on Comparator implemented manually");
		for (Map.Entry<Person, Integer>  entry: mapWithComp.entrySet()) {
			System.out.println(entry);
		}
		System.out.println();
		
		//Sort the hashmap based on values now
		/**
		 * Sort based on Comparator classes
		 */
		Comparator<Map.Entry<Person, Integer>> myValueComp = new Comparator<Map.Entry<Person, Integer>>() {

			@Override
			public int compare(Map.Entry<Person, Integer> o1, Map.Entry<Person, Integer> o2) {
				int value = o1.getValue()-o2.getValue();
				return value == 0 ? 1 : value;
			}
		};
		
		System.out.println("Printing Items based on Comparator, sorted by value");
		// Approach 1 using Java8 and use the linkedHashMap to maintain item insertion
		Map<Person, Integer> sortedMap =map.entrySet().stream().sorted(myValueComp).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new ));
		for (Map.Entry<Person, Integer>  entry: sortedMap.entrySet()) {
			System.out.println(entry);
		}
		
		// Approach 2 - Directly used the comparator and using TreeSet
		System.out.println("\nApproach 2, Printing Items based on Comparator, sorted by value");
		Set<Map.Entry<Person, Integer>> sortedEntrySet = new TreeSet<>(myValueComp);
		
		sortedEntrySet.addAll(map.entrySet());
		for (Map.Entry<Person, Integer>  entry: sortedEntrySet) {
			System.out.println(entry);
		}
		
		
		//Approach 3- Using a value Comparator (Best Approach so far)
		System.out.println("\nApproach 3, Printing Items based on Comparator, sorted by value");
		Map<Person, Integer> mymap = new HashMap<>();
		mymap.put(new Person("A", 12, 1240), 1);
		mymap.put(new Person("B", 11, 1239), 2);
		mymap.put(new Person("C", 15, 1259), 11);
		mymap.put(new Person("D", 13, 1200), 7);
		mymap.put(new Person("E", 12, 1300), 15);
		
		Map<Person, Integer> sortedMapByValue = new TreeMap<>(new ValueComparator(mymap));
		sortedMapByValue.putAll(mymap);
		print(sortedMapByValue);
		

	}
	
	public static void print(Map<Person, Integer> map) {
		for (Map.Entry<Person, Integer>  entry: map.entrySet()) {
			System.out.println(entry);
		}
	}
}

class ValueComparator implements Comparator<Person>{
	Map<Person, Integer> map;
	
	public ValueComparator(Map<Person, Integer> map){
		this.map = map;
	}
	
	@Override
	public int compare(Person o1, Person o2) {
		int v1 = map.get(o1);
		int v2 = map.get(o2);
		return v1-v2 ==0 ? 1 : v1-v2;
	}
	
}

class Person implements Comparable<Person> {
	public Person(String name, int age, int salary) {
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((Person)obj).name.equals(this.name) ;
	}
	

	String name;
	int age;
	int salary;

	public String toString() {
		return "Name: " + name + ", age: " + age + ", salary: " + salary;
	}

	// Sort on Name
	public int compareToName(Person o) {
		return this.name.compareTo(o.name);
	}

	// Sort on age
	// @Override
	public int compareToAge(Person o) {
		int value = this.age - (o.age);
		return value == 0 ? 1 : value;
	}

	// Salary
	public int compareTo(Person o) {
		int value = this.salary - (o.salary);
		return value == 0 ? 1 : value;
	}

}
