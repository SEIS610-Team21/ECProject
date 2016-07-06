package Library;

public class OperandNode extends BinaryTreeNode {

	public OperandNode(char data) {
		this.data = data;
		this.left=this.right=null;
	}

	@Override
	public double evaluate(int x) {
		if(data=='x' || data=='X')
		{
			return x;
		}
		return Double.parseDouble(String.valueOf(data));
	}

	@Override
	public void crossOver(BinaryTreeNode otherNode) {
		
	}

	@Override
	public void mutate() {
		
	}

	@Override
	public boolean isOperator() {
		return false;
	}

	

	
}
