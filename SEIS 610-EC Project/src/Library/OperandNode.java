package Library;

import java.util.Random;

public class OperandNode extends BinaryTreeNode {

	public OperandNode(char data) {
		this.data = data;
		this.left = this.right = null;
	}

	@Override
	public double evaluate(int x) {
		if (data == 'x' || data == 'X') {
			return x;
		}
		return Double.parseDouble(String.valueOf(data));
	}

	@Override
	public boolean mutate() {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		char rVal = (char) (rand.nextInt(10) + 48);
		if (rVal == this.data)
			return false;
		else {
			if (rVal == '0')
				this.data = 'X';
			else
				this.data = rVal;
			return true;
		}
	}

	@Override
	public boolean isOperator() {
		return false;
	}

	@Override
	public BinaryTreeNode clone() {
		OperandNode _result = new OperandNode(data);
		_result.data = this.data;
		return _result;
	}

}
