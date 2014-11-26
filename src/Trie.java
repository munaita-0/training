import java.util.HashMap;

public class Trie {
	public HashMap<String, HashMap> nodess = new HashMap<String, HashMap>();
	public Trie() {
	}
	
	public void add(String word) {
		String[] spliteds = new String[1000];
		char[] charArray = word.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			spliteds[i] = String.valueOf(charArray[i]);
		}
		
		for (String c: spliteds) {
			if (nodess.get(c) == null) {
				nodess = new HashMap<String, HashMap>();
				nodess.put(c, new HashMap());
			} else {
				nodess = nodess.get(c);
			}
		}
	}
}