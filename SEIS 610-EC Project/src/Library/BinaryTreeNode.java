package Library;

public abstract class BinaryTreeNode {

	public BinaryTreeNode right; //Right Child node
	public BinaryTreeNode left; //Left Child node
	public char data; //Data element of type T
	public BinaryTreeNode() {
		
	}
	
	public double evaluate(int x)
	{
		return 0.0f;
	}
	public void crossOver(BinaryTreeNode otherNode)
	{
		//To be implemented
	}
	public void mutate()
	{
		//To be implemented
	}

	public abstract boolean isOperator();
	
	public boolean hasChild()
	{
		return right!=null;
	}
	
}
