package map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * @author nagendra
 *
 */
public class SortMapByValueCustom {
	public static void main(String a[]) {
		Map<String, Integer> wordCounts = new HashMap<>();
		wordCounts.put("abc", 10);
		wordCounts.put("a", 15);
		wordCounts.put("b", 20);
		wordCounts.put("c", 5);
		
		Map<String, Integer> wordCounts2 = sortByValueCustomCompare(wordCounts);
		System.out.println(wordCounts2);
	}

	public static Map<String, Integer> sortByValueCustomCompare(final Map<String, Integer> wordCounts) {

		Comparator<Map.Entry<String, Integer>> myComp = new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		};

		return wordCounts.entrySet().stream().sorted(myComp)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

}
