package Brokers;

import Library.BinaryTreeNode;
import Library.OperandNode;
import Library.OperatorNode;

public class NodeCreationBroker {
	private static NodeCreationBroker instance;

	private NodeCreationBroker() {
	}

	public static NodeCreationBroker getInstance() {
		if (instance == null)
			instance = new NodeCreationBroker();
		return instance;
	}

	private boolean isOperand(char c) {
		return ((c >= '0' && c <= '9') || (c=='x' || c=='X'));
	}

	private boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	public BinaryTreeNode createNodeFromChar(char c) {
		BinaryTreeNode _result = null;
		if (isOperand(c))
			_result = new OperandNode(c);
		else if (isOperator(c))
			_result = new OperatorNode(c);
		return _result;
	}
}
