package Library;

import java.util.Random;

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
	public boolean mutate() {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		char rVal = (char) (rand.nextInt(6) + 42);
		if (rVal == '.' || rVal == ',')
			return false;
		else {
			this.data = rVal;
			return true;
		}
	}

	@Override
	public boolean isOperator() {
		return true;
	}

	@Override
	public BinaryTreeNode clone() {
		OperatorNode _result = new OperatorNode(data);
		_result.left = this.left.clone();
		_result.right = this.right.clone();
		_result.data = this.data;
		return _result;
	}

	@Override
	public boolean isEqual(BinaryTreeNode other) {
		boolean _result; 		
		_result = super.isEqual(other);
		_result = _result && (this.left.isEqual(other.left));
		_result =_result && (this.right.isEqual(other.right));
		return _result;
	}
	
	

}
