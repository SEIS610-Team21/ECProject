package Brokers;

import java.util.Random;
import Library.BinaryTree;

public class PopulationGenerator {
	Random rand;

	public PopulationGenerator() {
		rand = new Random();
		rand.setSeed(System.currentTimeMillis());
	}

	public void generateFirstGeneration(BinaryTree[] set, int populationSize) {
		for (int i = 0; i < populationSize; i++) {
			BinaryTree curTree = null;
			try {
				curTree = this.generateRandomExpression(10);
			} catch (StackOverflowError e) {
			}
			if (curTree != null)
				set[i] = curTree;
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

	private String generateExpression(int depth) {
		String _result = "";
		if (depth <= 0) depth = 1;
		int rInt = rand.nextInt(depth);
		if (rInt == 0)
			_result = generateOperand();
		else
			_result = "(" + generateSubExpression(depth - 2) + ")";
		return _result;
	}
}
