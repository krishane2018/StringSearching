import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CountSubstrings {

	//Determines the number of times a specified substring appears in a word in a specified text 
	public static String numSubstrings (Scanner input, String pattern, List<Character> characters, 
			List<Character> patternList, BufferedReader myBuffer) throws IOException {
				//declaration of variables
		String line;
		String[] words;
		int counter = 0;
		long startTime = System.currentTimeMillis();
		
		for (int i = 0; i < pattern.length(); i++) {		//adding substring characters to list
			patternList.add(pattern.charAt(i));
		}
		
		while ((line = myBuffer.readLine()) != null) {		//reading text file line by line 
			words = line.split(" ");						//spliting line into array of words
			
			for (int i = 0; i < words.length; i++) {			//looping through words and
																//determining whether they contain
				for (int j = 0; j < words[i].length(); j++) {	//substring
					characters.add(words[i].charAt(j));			
				}												
				
				if (findBrute(characters, patternList) != -1) {
					counter++;									//increment counter by 1 if
				}												//substring is in word
				
				characters.clear();								//clear list to add next word
			}
		}
		
		return String.format("%d matches, derived in %d milliseconds.", counter, 
				System.currentTimeMillis() - startTime);
	}
	
	/*
	 * Returns the lowest index at which substring pattern begins in text (or else
	 * -1).
	 */

	private static int findBrute(List<Character> text, List<Character> pattern) {
		int n = text.size();
		int m = pattern.size();
		for (int i = 0; i <= n - m; i++) { // try every starting index
			// within text
			int k = 0; // k is index into pattern
			while (k < m && text.get(i + k) == pattern.get(k))
				// kth character of pattern matches
				k++;
			if (k == m) // if we reach the end of the pattern,
				return i; // substring text[i..i+m-1] is a match
		}
		return -1; // search failed
	}

	public static void main(String[] args) throws IOException {
		System.out.print("Please enter the path for the input file: ");		
		Scanner input = new Scanner(System.in);							//getting file
		String file = input.nextLine();
		BufferedReader myBuffer = new BufferedReader(new FileReader(file));
		
		System.out.print("Enter the pattern to look for: ");			//getting pattern
		String pattern = input.nextLine();
		
		List<Character> patternList = new ArrayList<Character>();
		List<Character> characters = new ArrayList<Character>();
		System.out.println("Using ArrayLists: "+						//printing results
				numSubstrings(input, pattern, patternList, characters, myBuffer));
		
		myBuffer = new BufferedReader(new FileReader(file));
		
		patternList = new LinkedList<Character>();
		characters = new LinkedList<Character>();
		System.out.println("Using LinkedLists: "+						//printing results
				numSubstrings(input, pattern, patternList, characters, myBuffer));
		
		input.close();
		myBuffer.close();
	}

}
