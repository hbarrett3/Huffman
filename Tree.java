
public class Tree {
	public static class Node 
	{
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
		
		public void connect(Node left, Node right)
		{
			leftChild = left;
			rightChild = right;
		}
	}
	
	Node root;
	
	public Tree()
	{	
		root = null;
	}
	
	public void setRoot(Node r)
	{
		root = r;
	}
	
	public char traverse(String code, Node node)
	{
		if(code.length() <= 0)
		{
			return node.letterVal;
		}
		else
		{
			if(code.charAt(0) == 0)
			{
				return traverse(code.substring(1), node.leftChild);
			}
			else
			{
				return traverse(code.substring(1), node.rightChild);
			}
		}
	}
}
