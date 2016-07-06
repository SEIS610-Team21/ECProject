package Library;

public class OperatorNode extends BinaryTreeNode {

	public OperatorNode(char data) {
		this.data = data;
		this.left = this.right = null;
	}

	@Override
	public double evaluate(int x) {
		double _result = 0.0;
		switch (this.data) {
		case '+':
			_result = left.evaluate(x) + right.evaluate(x);
			break;
		case '-':
			_result = left.evaluate(x) - right.evaluate(x);
			break;
		case '*':
			_result = left.evaluate(x) * right.evaluate(x);
			break;
		case '/':
			_result = left.evaluate(x) / right.evaluate(x);
			break;
		}
		return _result;
	}

	@Override
	public void crossOver(BinaryTreeNode otherNode) {
		// TODO Auto-generated method stub
		super.crossOver(otherNode);
	}

	@Override
	public void mutate() {
		// TODO Auto-generated method stub
		super.mutate();
	}

	@Override
	public boolean isOperator() {
		return true;
	}

}
