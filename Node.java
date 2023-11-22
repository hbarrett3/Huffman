/*
 * Authors: 		Ben Yurek, Eli Schillinger, Harrison Barrett
 * Course:			CSC 345 FA 23 Melanie Lotz
 * File: 			Node.java
 * Class:			Node
 * Description:		This file contains the code for the node object used in the Huffman encoding functions in 
 * 					the huffmanEncoding file. The object is essentially a basic binary tree node but this 
 *                  implementation allows the node to have connections to two children, a letter value, a 
 *                  number value, and a boolean that tells whether the node has been covered in a tree traversal.
 */

public class Node {
	int numVal;
	char letterVal;
	Node leftChild;
	Node rightChild;
	boolean checked;
	
	public Node(int num, char letter)
	{
		numVal = num;
		letterVal = letter;
		leftChild = null;
		rightChild = null;
		checked = false;
	}
	
	public Node(int num)
	{
		numVal = num;
		letterVal = '#';
		leftChild = null;
		rightChild = null;
		checked = false;
		
	}
	
	//Method to connect two pre-existing children to a Node
	public void connect(Node left, Node right)
	{
		leftChild = left;
		rightChild = right;
	}
}

