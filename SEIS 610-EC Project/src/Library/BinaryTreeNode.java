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

	public abstract boolean mutate();
	public abstract boolean isOperator();
	
	public boolean hasChild()
	{
		return right!=null;
	}
	public abstract BinaryTreeNode clone();
	
	public boolean isVairable(){return false;}
	
	public boolean isEqual(BinaryTreeNode other)
	{
		boolean _result;
		_result = (this.data == other.data);		
		return _result;
	}
}
