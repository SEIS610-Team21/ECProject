package Library;

import java.util.ArrayList;
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
	
	public boolean isEmpty()
	{
		return (root==null);
	}

	private BinaryTreeNode _buildInfix(char expression[]) throws EmptyStackException {
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

	public boolean buildFromString(String expression) {
		char[] expArray = expression.toCharArray();
		try {
			this.root = _buildInfix(expArray);
			return true;
		} catch (EmptyStackException e) {
			return false;
		}

	}

	public double evaluate() {
		TrainingData data = TrainingData.getInstance();
		double total = 0.0;
		this.eValues = new double[data.length];
		for (int i = 0; i < data.length; i++) {
			this.eValues[i] = Math.abs((root.evaluate((int) (data.dataSet[i][0])) - (data.dataSet[i][1])));
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

	public BinaryTreeNode getNodeAtIndex(int index, BinaryTreeNode node) {

		if (index == 0 || (!node.hasChild()))
			return node;
		if (node.left != null) {
			BinaryTreeNode curNode = getNodeAtIndex(index - 1, node.left);
			if (curNode != null)
				return curNode;
		}
		return (getNodeAtIndex(index - 1, node.right));
	}

	private void _crossOver(BinaryTreeNode myNode, BinaryTreeNode withNode) {
		if (withNode.hasChild()) {
			myNode.left = withNode.left.clone();
			myNode.right = withNode.right.clone();
		} else {
			myNode.left = null;
			myNode.right = null;
		}
		myNode.data = withNode.data;
	}

	public boolean crossOver(BinaryTree otherTree, int otherNodeIndex, int myNodeIndex) {
		BinaryTreeNode myNode = getNodeAtIndex(myNodeIndex, root);
		BinaryTreeNode otherNode = getNodeAtIndex(otherNodeIndex, otherTree.root);
		if ((myNode != null && otherNode != null) && (myNode.getClass() == otherNode.getClass())) {
			_crossOver(myNode, otherNode);
			return true;
		} else
			return false;
	}

	public boolean hasVariable(BinaryTreeNode node) {
		Boolean _result = false;

		if (node != null) {
			if (node.isVairable())
				return true;
			else {
				return (hasVariable(node.right) || hasVariable(node.left));
			}
		}
		return _result;
	}

	public boolean mutate(int atIndex) {
		BinaryTreeNode myNode = getNodeAtIndex(atIndex, root);
		if (myNode != null) {
			myNode.mutate();
			return true;
		} else
			return false;
	}

	public BinaryTree clone() {
		BinaryTree _result = new BinaryTree();
		_result.root = this.root.clone();
		_result.evaluate();
		return _result;
	}
	
	public boolean existsInList(ArrayList<BinaryTree> list)
	{
		for (int i = 0; i < list.size(); i++) {
			if (this.root.isEqual(list.get(i).root))
				return true;
		}
		return false;
	}
}
