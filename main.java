/*
 * Authors: 		Ben Yurek, Eli Schillinger, Harrison Barrett
 * Course:			CSC 345 FA 23 Melanie Lotz
 * File: 			main.java
 * Class:			main
 * Description:		Test code for our implementation of Huffman encoding. This file will ask the user to input an
 * 					integer that will act as the length of a randomized string to use Huffman encoding on. This file
 * 					will use functions that exist in our huffmanEncoding file which in turn uses the Node object we 
 * 					have built.
 */

import java.util.*;

public class main {

    public static void main(String[] args) {
    	
    	// Set up a scanner and get a string size 
    	Scanner keyInput = new Scanner(System.in);
   		System.out.println("--------------------------------------------------\n");
    	System.out.println("Please enter an integer that will correspond to the size of the randomized String "
    			+ "to perform Huffman encoding on, then press 'Enter'.");
   		System.out.println("\n--------------------------------------------------");
    	String userInput = keyInput.nextLine();
    	int stringSize = 0;
    	try {
    		// get the integer value of the input string
    		stringSize = Integer.valueOf(userInput);
    		
    		// generate a random string with the input length
            String test1 = generateRandomString(stringSize);
            System.out.println("--------------------------------------------------\n");
            System.out.println("   Running Huffman Encoding on the string: \n\n   " + test1 + "\n");
            System.out.println("--------------------------------------------------\n");
            
            //instantiate a Huffman encoding object from the huffmanEncoding file and encode the string 
            huffmanEncoding testEncoder = new huffmanEncoding();
            testEncoder.runHuffman(test1);
    	}
    	catch (java.lang.NumberFormatException exception) {
    		main(args);
    	}
    	keyInput.close();
    }

    // generates random character
    private static char generateRandomCharacter(){
        return (char)('A' + Math.random()*26);
    }

    // generates random string
    private static String generateRandomString(int length){
        String string = "";

        for (int i = 0; i < length; i++){
            string += generateRandomCharacter();
        }
        return string;
    }

}
