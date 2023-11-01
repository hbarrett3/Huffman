package huffmanEncoding;

import java.util.HashMap;

public class main {

	public static void main(String[] args) {
		

	}

	/*
	 * This function counts the number of times that each character appears in the input message. 
	 * 
	 *   --Parameters-- 
	 * String message: input string 
	 * 
	 *   --Returns--
	 * retval: HashMap that contains string KEYs and their corresponding integer frequency VALs 
	 */
	private HashMap<String, Integer> countCharacters(String message){
		
		
		HashMap<String, Integer> retval = new HashMap<String, Integer>();
		return retval;
	}
	
	
	
	/*
	 * This function fills in the Tree object that will be used to determine the encoding for each 
	 * character. 
	 * 
	 *    --Parameters--
	 * frequencies: HashMap of characters and their number of appearances in the input string
	 * 
	 *    --Returns--
	 * retval: head Node
	 */
	private Node buildTree(HashMap<String, Integer> frequencies) {
		
		
		Node retval = new Node();
		return retval;
	}
	
	
	
	/*
	 * This function traverses the tree to determine the path to reach each character thus
	 * giving us the encoding for each character.
	 * 
	 *    --Parameters--
	 * root: the root node of the binary tree
	 * 
	 *    -- Returns--
	 * retval: a HashMap of characters and their respective encodings
	 */
	private HashMap<String, Integer> getCodes(Node root){
		

		HashMap<String, Integer> retval = new HashMap<String, Integer>();
		return retval;
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
	 * retval: encoded bit string 
	 */
	private String encode(String message, HashMap<String, Integer> codes) {
		
		
		String retval = "temp";
		return retval;
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
	 * retval: decoded string 
	 */
	private String decode(String codedMessage, HashMap<String, Integer> codes) {
		
		
		String retval = "temp";
		return retval;
	}

}















