package Application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import javax.swing.JTextArea;

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
	
	private void sortTree(ArrayList<BinaryTree> trees)
	{
		trees.sort(new Comparator<BinaryTree>() {
			@Override
			public int compare(BinaryTree o1, BinaryTree o2) {
				return Double.compare(o1.fitnessValue, o2.fitnessValue);
			}

		});
	}

	private void processTerminationOfUnfit() {
		ArrayList<BinaryTree> trees = (ArrayList<BinaryTree>) currentGeneration.clone();
		this.currentGeneration.clear();
		BinaryTree curTree;
		int index = 0;
		this.sortTree(trees);
		int cutOffRange = Math.round(trees.size() * ((float) (context.survivalThreshold) / 100));
		for (int i = 0; i < cutOffRange; i++) {
			curTree = trees.get(i);
			if (!expressionExists(curTree)) {
				this.currentGeneration.add(index, curTree);
				index++;
			}
		}
	}

	private void processCrossOver() {
		BinaryTree curTree, secondTree, newTree;
		Random rand = new Random();
		Random probability = new Random();
		rand.setSeed(System.currentTimeMillis());
		int firstTreeNode, secondTreeNode, size;
		boolean retry = false;
		size = currentGeneration.size();
		if (size == 1)
			return;// no crossover for single parent
		for (int i = 0; i < size - 1; i++) {
			if (probability.nextDouble() < context.crossoverProbability || retry) {
				if (retry) {
					i--;
					retry = false;
				}
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
				} else if (i > 0)
					retry = true;// Retry for same index in case of failed
									// crossover
			}
		}

	}

	private void processMutation() {
		BinaryTree curTree, newTree;
		Random rand = new Random();
		int nodeIndex, size;
		boolean retry = false;

		size = currentGeneration.size();

		for (int i = 0; i < size; i++) {
			if (rand.nextDouble() <= context.mutationProbability || retry) {
				if (retry) {
					i--;
					retry = false;
				}
				curTree = currentGeneration.get(i);
				newTree = curTree.clone();
				nodeIndex = rand.nextInt(context.treeDepth);
				if (newTree.mutate(nodeIndex)) {
					if (!expressionExists(newTree)) {
						newTree.evaluate();
						currentGeneration.add(newTree);
					}
				} else
					retry = true;// Retry for same index incase of failed
									// mutation
			}
		}
	}

	private void regenerateTrainingData() {
		TrainingData.getInstance().regenerate();
	}

	public void processNaturalSelection(JTextArea txtOutput) {
		String output = "";
		int passIndex = 0;
		while (currentGeneration.size() > 1 && passIndex < context.maxIterations) {
			regenerateTrainingData();
			processTerminationOfUnfit();
			processCrossOver();
			processMutation();
			output += "\nIteration # " + (passIndex + 1) + "\n";
			passIndex++;
			output += StringifyCurrentGeneration();
			txtOutput.append(output);
		}
	}
	public BinaryTree getResultExpression()
	{
		this.sortTree(this.currentGeneration);
		return this.currentGeneration.get(0);
	}
}
