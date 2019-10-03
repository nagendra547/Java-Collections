package map;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortFileBySecondField {

	public static void main(String[] args) {
		try {
			List<String> lines = Files
					.readAllLines(new File("file.txt").toPath());
			System.out.println(lines);
			Comparator<String> comp = new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o1.split(" ")[1].compareTo(o2.split(" ")[1]);
				}
				
			};
			Collections.sort(lines, comp);
			System.out.println(lines);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
