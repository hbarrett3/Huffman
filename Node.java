
public class Node {
	int numVal;
	char letterVal;
	Node leftChild;
	Node rightChild;
	
	public Node(int num, char letter)
	{
		numVal = num;
		letterVal = letter;
		leftChild = null;
		rightChild = null;
	}
	
	//Method to connect two pre-existing children to a Node
	public void connect(Node left, Node right)
	{
		leftChild = left;
		rightChild = right;
	}
}

