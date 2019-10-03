package map;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class MFU {
	
	static TreeSet<URL> treeSet = new TreeSet<>(Collections.reverseOrder());


	public static void main(String[] args) {
		
		printTop2("A");
		printTop2("A");
		printTop2("B");
		printTop2("B");
		printTop2("C");
		printTop2("A");
		printTop2("B");
		printTop2("A");
		printTop2("D");
		printTop2("D");
		printTop2("D");
	}
	
	static Map<String, Integer> map = new HashMap<>();
	static PriorityQueue<URL> pq = new PriorityQueue<>();
	static final int size = 2;
	
	private static void printTop2(String s) {
		map.put(s, map.getOrDefault(s, 0) + 1);
		int count = map.get(s);
		URL my = new URL(s, count);
		if(pq.contains(my)) {
			pq.remove(my);
			pq.offer(my);
		} else {
			if (pq.size() == size) {
				if (pq.peek().count < count) {
					pq.remove();
					pq.add(my);
				}
			}else {
				pq.add(my);
			}
			
		}
		URL [] array1 = new URL [1];
		URL [] array = pq.toArray(array1 );
		Arrays.sort(array, Collections.reverseOrder());
		
		System.out.println(Arrays.toString(array));
	}

	private static void printTop51(String s) {
		map.put(s, map.getOrDefault(s, 0) + 1);
		int count = map.get(s);
		URL my = new URL(s, count);

		if (treeSet.contains(my)) {
			treeSet.remove(my);
			treeSet.add(my);
		} else if (treeSet.size() == size) {
			if (treeSet.first().count >= count) {
				// ignore
			} else {
				URL first = treeSet.first();
				treeSet.remove(first);
				treeSet.add(my);
			}
		} else {
			treeSet.add(my);
		}

		System.out.println(treeSet);

	}
	

}

class URL implements Comparable<URL>{
	String url;
	int count;
	
	public URL(String s, int count2) {
		this.url =s;
		this.count = count2;
	}

	@Override
	public int compareTo( URL o2) {
		if (this.url.equals(o2.url)) {
			return 0;
		}
		return this.count -o2.count ;
	}
	
	@Override
	public int hashCode() {
		return url.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return url.equals(((URL)obj).url);
	}
//	
	@Override
	public String toString() {
		return "URL : "+url+", count: "+count+" \t";
	}
	
	
}
