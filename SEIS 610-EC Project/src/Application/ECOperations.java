package Application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import Brokers.PopulationGenerator;
import Library.BinaryTree;
import Modules.Context;
import Modules.TrainingData;

public class ECOperations {
	Context context;
	PopulationGenerator generator;
	ArrayList<BinaryTree> currentGeneration;
	// BinaryTree[] currentGeneration;

	public ECOperations() {
		context = Context.getInstance();
		generator = new PopulationGenerator();
	}

	public void generateFirstGeneration() {
		currentGeneration = new ArrayList<BinaryTree>();
		generator.generateFirstGeneration(currentGeneration, context.populationSize);
	}

	private String StringifyCurrentGeneration() {
		String _result = "";
		for (int i = 0; i < currentGeneration.size(); i++) {
			_result += currentGeneration.get(i).toString() + " ==>> " + currentGeneration.get(i).evaluate() + "\n";
		}
		return _result;
	}

	private boolean expressionExists(BinaryTree exp) {
		for (int i = 0; i < currentGeneration.size(); i++) {
			if (exp.root.isEqual(currentGeneration.get(i).root))
				return true;
		}
		return false;
	}

	public String evaluateFirstGeneration(String inputExp, BinaryTree targetTree) {
		String output;
		targetTree = new BinaryTree();
		targetTree.buildFromString(inputExp);
		TrainingData.getInstance().Generate(context.trainingDataCount, targetTree);
		//
		output = "The Target Expression : ";
		output += targetTree.inOrder(targetTree.root) + "\n\n";
		output += "First generation expressions" + "\n";
		output += StringifyCurrentGeneration();
		return output;
	}

	private void processTerminationOfUnfit() {
		ArrayList<BinaryTree> trees = (ArrayList<BinaryTree>) currentGeneration.clone();
		this.currentGeneration.clear();
		BinaryTree curTree;
		int index = 0;
		trees.sort(new Comparator<BinaryTree>() {
			@Override
			public int compare(BinaryTree o1, BinaryTree o2) {
				if (o1.fitnessValue > o2.fitnessValue)
					return 1;
				else if (o1.fitnessValue == o2.fitnessValue)
					return 0;
				else
					return -1;
			}

		});
		for (int i = 0; i < trees.size() / 2; i++) {
			curTree = trees.get(i);
			if (curTree.fitnessValue <= context.survivalThreshold) {
				if (!expressionExists(curTree)) {
					this.currentGeneration.add(index, curTree);
					index++;
				}
			}
		}
		//this.currentGeneration = trees;
	}

	private void processCrossOver() {
		BinaryTree curTree, secondTree, newTree;
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		int firstTreeNode, secondTreeNode, size;
		size = currentGeneration.size();
		if (size % 2 != 0)
			size -= 1;
		if (size == 1)
			return;// no crossover for single tree
		for (int i = 0; i < size; i += 2) {
			curTree = currentGeneration.get(i);
			newTree = curTree;
			secondTree = currentGeneration.get(i + 1);
			firstTreeNode = rand.nextInt(context.treeDepth / 2) + 1;
			secondTreeNode = rand.nextInt(context.treeDepth / 2) + 1;
			if ((newTree.crossOver(secondTree, secondTreeNode, firstTreeNode))) {
				if (!expressionExists(newTree)) {
					newTree.evaluate();
					currentGeneration.add(newTree);
				}
			} else
				i -= 2;// Retry for same index in case of failed crossover
		}

	}

	private void processMutation() {
		BinaryTree curTree, newTree;
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		int nodeIndex, size, pickThis;
		size = currentGeneration.size();

		for (int i = 0; i < size; i++) {
			pickThis = rand.nextInt(100);
			if (pickThis % 3 == 0) { // Only mutate some trees
				curTree = currentGeneration.get(i);
				newTree = curTree.clone();
				nodeIndex = rand.nextInt(context.treeDepth);
				if (newTree.mutate(nodeIndex)) {
					if (!expressionExists(newTree)) {
						newTree.evaluate();
						currentGeneration.add(newTree);
					}
				} else
					i--;// Retry for same index incase of failed mutation
			}
		}
	}

	private void regenerateTrainingData() {
		TrainingData.getInstance().regenerate();
	}

	public String processNaturalSelection() {
		String output = "";
		int passIndex = 0;
		while (currentGeneration.size() > 1) {
			regenerateTrainingData();
			processTerminationOfUnfit();
			processCrossOver();
			processMutation();
			output += "\nPass Index :" + passIndex + "\n";
			passIndex++;
			output += StringifyCurrentGeneration();
		}
		return output;
	}
}
