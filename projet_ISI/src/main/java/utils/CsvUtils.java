package utils;



public class CsvUtils {
	public static int indexOf(String element, String[] splittedLine) {
		for (int i = 0; i < splittedLine.length; i++) {
			if (splittedLine[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}
}
