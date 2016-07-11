package Modules;


import java.util.Random;

import Library.BinaryTree;

public class TrainingData {

	private static TrainingData myInstance;
	public double[][] dataSet;
	public BinaryTree targetExp;
	public int length;
	
	private TrainingData(){}
	public static TrainingData getInstance()
	{
		if(myInstance==null)
			myInstance = new TrainingData();
		return myInstance;
	}
	
	public void Generate(int size,BinaryTree target)
	{
		Random rand = new Random();
		int range = Context.getInstance().trainingDataTo - Context.getInstance().trainingDataFrom;
		length=size; this.targetExp=target;
		rand.setSeed(System.currentTimeMillis());
		dataSet = new double[size][2];
		int x;
		for(int i =0 ; i < size; i++)
		{
			x=rand.nextInt(range)+Context.getInstance().trainingDataFrom;
			dataSet[i][0] = x;
			dataSet[i][1] = target.root.evaluate(x);
		}
	}
	
	public void regenerate()
	{
		this.Generate(this.length, this.targetExp);
	}
	
	
}
