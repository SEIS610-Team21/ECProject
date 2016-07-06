package Library;

import java.util.Stack;

import Brokers.NodeCreationBroker;

public class BinaryTree {

	public BinaryTreeNode root;

	public BinaryTree() {
		//
	}

	private BinaryTreeNode _build(char expression[]) {
		BinaryTreeNode _result;

		Stack<BinaryTreeNode> st = new Stack();
		BinaryTreeNode node;

		for (int i = 0; i < expression.length; i++) {
			node = NodeCreationBroker.getInstance().createNodeFromChar(expression[i]);
			if (node != null) {
				if (node.isOperator()) {
					node.right = st.pop();
					node.left = st.pop();
				}
				st.push(node);
			}
		}
		_result = st.peek();
		st.pop();
		return _result;
	}

	private BinaryTreeNode _buildInfix(char expression[]) {
		BinaryTreeNode _result;

		Stack<BinaryTreeNode> st = new Stack();
		BinaryTreeNode node, tmp;

		for (int i = 0; i < expression.length; i++) {
			if (expression[i] == ')') {
				tmp = st.pop(); // Operand 2 : right child
				node = st.pop(); // Operator
				node.left = st.pop(); // Operand 1: left child
				node.right = tmp;
				st.push(node);
			} else {
				node = NodeCreationBroker.getInstance().createNodeFromChar(expression[i]);
				if (node != null)
					st.push(node);
			}
		}
		_result = st.peek();
		st.pop();
		return _result;
	}

	public void buildFromString(String expression) {
		char[] expArray = expression.toCharArray();
		this.root = _buildInfix(expArray);
	}

	public double evaluate(int x) {
		return root.evaluate(x);
	}

	public String inOrder(BinaryTreeNode node) {
		String _result = "";
		if (node != null) {
			if (node.hasChild())
				_result += "(";
			_result += inOrder(node.left);
			_result += node.data;
			_result += inOrder(node.right);
			if (node.hasChild())
				_result += ")";

		}
		return _result;
	}

}
