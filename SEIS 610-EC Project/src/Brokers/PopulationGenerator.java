package Brokers;

import java.util.ArrayList;
import java.util.Random;
import Library.BinaryTree;
import Modules.Context;

public class PopulationGenerator {
	Random rand;
	boolean hasPoly;

	public PopulationGenerator() {
		rand = new Random();
		rand.setSeed(System.currentTimeMillis());
	}

	public void generateFirstGeneration(ArrayList<BinaryTree> set, int populationSize) {
		for (int i = 0; i < populationSize; i++) {
			hasPoly = false;
			BinaryTree curTree = null;
			try {
				curTree = this.generateRandomExpression(Context.getInstance().treeDepth);
			} catch (StackOverflowError e) {
			}
			if ((curTree != null) && (!curTree.existsInList(set)) && curTree.hasVariable(curTree.root))
				set.add(i, curTree);
			else
				i--;
		}
	}

	private BinaryTree generateRandomExpression(int depth) throws StackOverflowError {
		BinaryTree _result = new BinaryTree();
		String expression = generateExpression(depth);
		_result.buildFromString(expression);
		return _result;
	}

	private String generateOperand() {
		String _result = "";
		char rVal = (char) (rand.nextInt(10) + 48);
		if (rVal == '0')
			_result = "X";
		else
			_result += rVal;
		return _result;
	}

	private String generateOperator() {
		String _result = "";
		char rVal = (char) (rand.nextInt(6) + 42);
		if (rVal == '.' || rVal == ',')
			_result = generateOperator();
		else
			_result += rVal;
		return _result;
	}

	private String generateSubExpression(int depth) {
		String _result = "";
		_result += generateExpression(depth);
		_result += generateOperator();
		_result += generateExpression(depth);
		return _result;
	}

	private String generateNthDegreeOperand(int degree) {
		String _result = "x";
		for (int i = 1; i < degree; i++) {
			_result += "*x";
		}
		return _result;
	}

	private String generateExpression(int depth) {
		String _result = "";
		if (depth <= 0)
			depth = 1;
		int rInt = rand.nextInt(depth);
		if (rInt == 0)
			_result = generateOperand();
		else if (rInt == (depth - 1) && !hasPoly) {
			_result = "(" + generateNthDegreeOperand(Context.getInstance().degree) + ")";
			hasPoly = true;
		} else
			_result = "(" + generateSubExpression(depth - 2) + ")";
		return _result;
	}
}
