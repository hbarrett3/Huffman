/*
 * Authors: 		Ben Yurek, Eli Schillinger, Harrison Barrett
 * Course:			CSC 345 FA 23 Melanie Lotz
 * File: 			huffmanEncoding.java
 * Class:			huffmanEncoding
 * Description:		This file contains functions that will work step-by-step through the process of Huffman encoding 
 * 					on a randomized string that has been generated in and passed by our main test function.
 * 					(See function comments and in line comments for additional detail)
 * 
 */

import java.util.HashMap;
import java.util.Scanner;

public class huffmanEncoding {

	
	public static void runHuffman(String testStr) {
		
		// get HashMap of frequencies and print data
		HashMap<Character, Integer> freqMap = countCharacters(testStr);
		
		// create an array of nodes to construct the tree
		Node[] nodes = makeNodes(freqMap);
		
		// construct the tree
		Node root = buildTree(nodes);
		
		// iterate through the tree to get encodings for each char
		HashMap<Character, String> codes = callGetCodes(root);
		
		// get the encoded string by substituting chars for their encodings
		String encodedStr = encode(testStr, codes);
		
		// reverse the HashMap of encodings for decoding purposes
		HashMap<String, Character> newCodes = reverseMap(codes);
		
		// decode the encoded string to verify no information was lost
		String decodedStr = decode(encodedStr, newCodes);
		
	}

	
	/*
	 * This function counts the number of times that each character appears in the input message. 
	 * 
	 *   --Parameters-- 
	 * String inputStr: input string 
	 * 
	 *   --Returns--
	 * frequencies: HashMap that contains character KEYs and their corresponding integer frequency VALs 
	 * 
	 *   --Runtime and Space--
	 *   O(N) time since we iterate over each character in the input string once and add to its HashMap frequency
	 *   N space since the HashMap has one key/value per character and each character could only appear once 
	 */
	private static HashMap<Character, Integer> countCharacters(String inputStr){
		
		System.out.println("--------------------------------------------------");
		System.out.println("   Starting the countCharacters function...\n");
		
		// create the HashMap 
		HashMap<Character, Integer> frequencies = new HashMap<Character, Integer>();
		
		for (int i = 0; i < inputStr.length(); i++) {
			
			// get the next character in the input
			char curChar = inputStr.charAt(i);
			
			// If the current character is already in the HashMap 
			// create a new key with the previous value + 1 
			if (frequencies.containsKey(curChar)) {
				int newVal = frequencies.remove(curChar) + 1;
				frequencies.put(curChar, newVal);
				System.out.println(" - Adding 1 instance to the frequency of " + curChar);
			}
			
			// If the current character is not in the HashMap
			// add it to the map with value 1
			else {
				System.out.println(" - Creating a HashMap Key/Value for " + curChar);
				frequencies.put(curChar, 1);
			}
		}
		
		System.out.println("\n   Concluding the countCharacters function.");
		System.out.println("--------------------------------------------------");
	
		return frequencies;
	}
	
	
	/*
	 * This function iterates over each character that the program supports and checks if it appears in
	 * the frequency HashMap. if it does, it creates a node with and adds it to an array
	 * 
	 *    --Parameters--
	 * frequencies: a HashMap of characters and the amount of times they appear in the input
	 * 
	 *    --Returns--
	 * nodes: an array of Node objects
	 * 
	 *    --Runtime and Space--
	 * O(1) time since we are iterating over a hard coded array that will always be the same size regardless of
	 * the input data. Constant space since we create an array of size 67. We are supporting only 67 different 
	 * characters so even if the input is thousands of characters long the array is always the same size.
	 */
	private static Node[] makeNodes(HashMap<Character, Integer> frequencies) {
		
		System.out.println("--------------------------------------------------");
		System.out.println("   Starting the makeNodes function...\n");
		
		// Create hard coded array of all supported characters
		char[] supportedChars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u',
		                   'v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P',
		                   'Q','R','S','T','U','V','W','X','Y','Z','.','!',',','?','1','2','3',
		                   '4','5','6','7','8','9','0',' '};
		
		// create return array to fill with created nodes
		Node[] nodes = new Node[67];
		
		// iterate through supported characters and if there is a provided
		// frequency, build a node and save it in the array
		int count = 0;
		for (char cur : supportedChars) {
			if (frequencies.containsKey(cur)) {
				Node newNode = new Node(frequencies.get(cur), cur);
				nodes[count] = newNode;
				count++;
				System.out.println(" - Creating a Node for   " + cur + "   with a frequency of   " + frequencies.get(cur));
			}
		}
		
		System.out.println("\n   Concluding the makeNodes function.");
		System.out.println("--------------------------------------------------");
		
		// return the array of created nodes
		return nodes;
	}
	
	
	
	/*
	 * This function fills in the tree that will be used to determine the encoding for each 
	 * character. 
	 * 
	 *    --Parameters--
	 * nodes: the array of nodes created in the makeNodes function
	 * 
	 *    --Returns--
	 * root: root Node of the tree
	 * 
	 *    --Runtime and Space--
	 * O(N^2) time because each time we join two nodes with a shared parent node the number of nodes in the 
	 * array only decreases by one. this means it will take O(N) time for the outside loop. also each time 
	 * we iterate through the array to find the smallest nodes we check each node once. so we are doing N work
	 * a total of N times. there is no extra space used in this function since we are just linking Nodes together
	 */
	private static Node buildTree(Node[] nodes) {
		
		System.out.println("--------------------------------------------------");
		System.out.println("   Starting the buildTree function...\n");
		
		// get the number of unique characters in the input string
		int leafCount = 0;
		int i = 0;
		while (nodes[i] != null) {
			leafCount++;
			i++;
		}
		
		// until there is only one Node remaining in the array of Nodes, find the two smallest frequencies
		// and join them with a shared parent Node. repeat this until there is one parent node as the root
		
		int nodeCount = leafCount;
		while (nodeCount > 1) {
			
			//create variables for the smallest and second smallest Nodes and their indices
			Node smallest = null;
			int smallestIndex = 0;
			Node second = null;
			int secondIndex = 0;
			
			// iterate through the indices of the array of Nodes and put the proper Nodes into 
			// the smallest and second smallest variables
			int j = 0;
			while (j < leafCount) {
				if (nodes[j] == null) {
				}
				else if (smallest == null) {
					smallest = nodes[j];
					smallestIndex = j;
				}
				else if (nodes[j].numVal < smallest.numVal) {
					second = smallest;
					secondIndex = smallestIndex;
					smallest = nodes[j];
					smallestIndex = j;
				}
				else if (second == null) {
					second = nodes[j];
					secondIndex = j;
				}
				else if (nodes[j].numVal < second.numVal){
					second = nodes[j];
					secondIndex = j;
				}
				j++;
			}
			
			
			System.out.println(" - The current two smallest frequencies are " + smallest.numVal +
					" and " + second.numVal);
			System.out.println("   They will be linked by a parent Node");
			
			// link the smallest nodes with a parent node, add the parent node to the array of nodes
			// and remove the two children nodes from the array of nodes
			Node newNode = new Node(smallest.numVal + second.numVal);
			newNode.connect(smallest, second);
			nodes[smallestIndex] = newNode;
			nodes[secondIndex] = null;
			nodeCount--;
		}
		
		//iterate through the array a final time to locate the root node
		Node root = new Node(0, '#');
		for (int k=0; k<leafCount; k++) {
			if (nodes[k] != null) {
				root = nodes[k];
			}
		}
		
		System.out.println("\n   Concluding the buildTree function.");
		System.out.println("--------------------------------------------------");
		
		return root;
	}
	
	
	
	/*
	 * This function calls the function that traverses the tree to determine the path to 
	 * reach each character thus giving us the encoding for each character.
	 * 
	 *    --Parameters--
	 * root: the root node of the binary tree
	 * 
	 *    --Returns--
	 * encodings: a HashMap of characters and their respective encodings
	 * 
	 *    --Runtime and Space--
	 * O(N) time complexity. we are traversing the tree to the bottom (in getCodes) a total number of N times in the 
	 * worst case when each character has a frequency of one. N space since in the worst case each character has its
	 * own key in the HashMap 
	 */
	private static HashMap<Character, String> callGetCodes(Node root){
		
		System.out.println("--------------------------------------------------");
		System.out.println("   Starting the callGetCodes function...\n");
		
		// create map to store characters and their encodings 
		HashMap<Character, String> encodings = new HashMap<Character, String>();
		
		// run a post order traversal of the tree to generate the encodings for each character. check
		// the left child, then the right child, then the root
		while (!root.checked) {
			String cur = getCodes(root);
			char character = cur.charAt(cur.length()-1);
			if (character != '*') {
				cur = cur.substring(0, cur.length()-1);
				encodings.put(character, cur);
				
				System.out.println(" - A tree traversal has resulted in an encoding for the character:     " + character 
						+ "     " + cur);
			}
		}
		
		System.out.println("\n   Concluding the callGetCodes function.");
		System.out.println("--------------------------------------------------");
		
		return encodings;
	}
	
	
	/*
	 * This function recursively traverses the tree to determine the path to reach each letter.
	 * each time the function recurses on a left or right child, a '0' or '1' is added to the 
	 * character encoding (respectively).
	 * 
	 *    --Parameters-- 
	 * root: the current root of the tree or sub tree
	 * 
	 *    --Returns--
	 * a string of 0's and 1's following the path to the letter. the letter itself is included at the
	 * end of the string. it will be removed in the callGetCodes function. this was only done to easily 
	 * return both the numerical encoding and the alphabet character.
	 * 
	 *    --Runtime and Space--
	 * O(Log N) runtime since we are recursing until we reach the bottom of a binary tree. this function requires 
	 * no extra space to store data
	 */
	private static String getCodes(Node root) {
		
		// if the letter value of the node is one of our supported characters this executes
		if (root.letterVal != '#') {
			root.checked = true;
			return String.valueOf(root.letterVal);
		}
		// if the left child of the current node has not been covered we call this recursion
		else if (!root.leftChild.checked) {
			return "0" + getCodes(root.leftChild);
		}
		// if the right child of the current node has not been covered we call this recursion
		else if (!root.rightChild.checked) {
			return "1" + getCodes(root.rightChild);
		}
		else {
			root.checked = true;
			return "*";
		}
	}
	
	
	
	/*
	 * This function iterates through each character of the input string and appends
	 * the encoding value of the current character to the return string.
	 * 
	 *    --Parameters--
	 * message: input string
	 * codes: HaspMap of characters and their encoding values
	 * 
	 *    --Returns-- 
	 * encodedStr: encoded bit string 
	 * 
	 *    --Runtime and Space--
	 * O(N) runtime since we are iterating over every character in the input and doing a O(1)
	 * encoding substitution. the only additional storage used in this function is for the new string
	 * generated after the characters are substituted for their encodings
	 */
	private static String encode(String message, HashMap<Character, String> codes) {
		
		String encodedStr = "";
		
		//iterate over each character and swap it for its encoding
		for (int i=0; i<message.length(); i++) {
			String curCode = codes.get(message.charAt(i));
			encodedStr = encodedStr + curCode;
		}
		
		System.out.println("--------------------------------------------------\n");
		System.out.println("   After the final encoding substitutions the encoded string is \n");
		
		int count = 0;
		for (int i=0; i<encodedStr.length(); i++) {
			if (i%100 == 0) {
				System.out.print("\n  ");
				count++;
				if (count%2 == 0) {
					System.out.print(" ");
				}
			}
			if (i%8 == 0) {
				System.out.print(" ");
			}
			System.out.print(encodedStr.charAt(i));
		}
		
		System.out.println("\n\n   Without Huffman encoding the length of the bit string would have been:\n");
		System.out.println("   Number of characters   x   8 bits   =   " + 
		message.length() + "   x   8   =   " + 8*message.length() + " bits.");
		System.out.println("\n\n   With Huffman encoding the length of the encoded bit string is:\n");
		System.out.println("   " + encodedStr.length());
		System.out.println("\n\n--------------------------------------------------");
		
		
		return encodedStr;
	}
	
	
	/*
	 * this method iterates over the HashMap of characters and their encodings and reverses the 
	 * key/value order so the decoding process is easier.
	 * 
	 *    --Parameters--
	 * codes: the original HashMap
	 * 
	 *    --Returns-- 
	 * newCodes: a reversed HashMap
	 * 
	 *    --Runtime and Space--
	 * constant runtime since we are checking every index of a hard coded array. N additional space used since
	 * we are using a new HashMap with the same size as the one being passes as a parameter
	 */
	public static HashMap<String, Character> reverseMap(HashMap<Character, String> codes) {
		
		HashMap<String, Character> newCodes = new HashMap<String, Character>();
		char[] supportedChars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u',
                'v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P',
                'Q','R','S','T','U','V','W','X','Y','Z','.','!',',','?','1','2','3',
                '4','5','6','7','8','9','0',' '};
		
		// for each character if the character was used in the input then swap the key/value order and 
		// put it into the new HashMap
		for (char character : supportedChars) {
			if (codes.get(character) != null) {
				newCodes.put(codes.get(character), character);
			}
		}
		
		return newCodes;
	}
	
	
	
	/*
	 * This function iterates through each segment of the encoded bit string and appends
	 * the decoded character value to the return string.
	 * 
	 *    --Parameters--
	 * codedMessage: input string
	 * codes: HaspMap of characters and their encoding values
	 * 
	 *    --Returns-- 
	 * decodedStr: decoded string 
	 * 
	 *    --Runtime and Space--
	 * O(N) runtime since we are iterating over every character in the input and doing a O(1)
	 * decoding substitution. the only additional storage used in this function is for the new string
	 * generated after the characters are substituted for their encodings
	 */
	private static String decode(String codedMessage, HashMap<String, Character> codes) {
		
		String decodedStr = "";
		int curLen = 1;
		
		while (codedMessage.length() != 0) {
			String tryStr = codedMessage.substring(0, curLen);
			if (codes.get(tryStr) != null) {
				codedMessage = codedMessage.substring(curLen);
				decodedStr = decodedStr + codes.get(tryStr);
				curLen = 1;
			}
			else {
				curLen++;
			}
		}
		
		System.out.println("--------------------------------------------------\n");
		System.out.println("   By running the encoding substitutions in reverse we can see that the original");
		System.out.println("   input string was not altered in the encoding/decoding process\n");
		System.out.println("   " + decodedStr);
		System.out.println("\n--------------------------------------------------");
		
		return decodedStr;
	}

}

