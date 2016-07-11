package Application;

import Brokers.PopulationGenerator;
import Library.BinaryTree;
import Modules.Context;
import Modules.TrainingData;

public class ECOperations {
	Context context;
	PopulationGenerator generator;
	BinaryTree[] currentGeneration;

	public ECOperations() {
		context = Context.getInstance();
		generator = new PopulationGenerator();
	}

	public void generateFirstGeneration() {
		currentGeneration = new BinaryTree[context.populationSize];
		generator.generateFirstGeneration(currentGeneration, context.populationSize);
	}

	private String StringifyCurrentGeneration() {
		String _result = "";
		for (int i = 0; i < currentGeneration.length; i++) {
			_result += currentGeneration[i].toString() + " ==>> " + currentGeneration[i].evaluate() + "\n";
		}
		return _result;
	}

	public String evaluateFirstGeneration(String inputExp, BinaryTree targetTree) {
		String output;
		targetTree = new BinaryTree();
		targetTree.buildFromString(inputExp);
		TrainingData.getInstance().Generate(context.populationSize, targetTree);
		//
		output = "The Target Expression : ";
		output += targetTree.inOrder(targetTree.root) + "\n\n";
		output += "First generation expressions" + "\n";
		output += StringifyCurrentGeneration();
		return output;
	}

	public String processNaturalSelection() {
		String output = "";
		//
		return output;
	}
}
