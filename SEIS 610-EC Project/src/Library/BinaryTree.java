package Library;

import java.util.EmptyStackException;
import java.util.Stack;

import Brokers.NodeCreationBroker;
import Modules.TrainingData;

public class BinaryTree {

	public BinaryTreeNode root;
	public double[] eValues;
	public double fitnessValue;

	public BinaryTree() {
		//
	}

	private BinaryTreeNode _build(char expression[]) {
		BinaryTreeNode _result;

		Stack<BinaryTreeNode> st = new Stack<BinaryTreeNode>();
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

	public double evaluate() {
		TrainingData data = TrainingData.getInstance();
		double total = 0.0;
		this.eValues = new double[data.length];
		for (int i = 0; i < data.length; i++) {
			this.eValues[i] =Math.abs((root.evaluate((int) (data.dataSet[i][0]))-(data.dataSet[i][1])));
			total += this.eValues[i];
		}
		this.fitnessValue = total / data.length;
		return this.fitnessValue;
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

	public int getDepth(BinaryTreeNode node) {
		int _result = 0;
		if (node != null)
			_result = 1 + Math.max(getDepth(node.left), getDepth(node.right));
		else
			_result = 1;
		return _result;
	}

	@Override
	public String toString() {
		return inOrder(root);
	}

}
