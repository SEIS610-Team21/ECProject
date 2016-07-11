package Modules;


import java.util.Random;

import Library.BinaryTree;

public class TrainingData {

	private static TrainingData myInstance;
	public double[][] dataSet;
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
		length=size;
		rand.setSeed(System.currentTimeMillis());
		dataSet = new double[size][2];
		int x;
		for(int i =0 ; i < size; i++)
		{
			x=rand.nextInt(100)-50;
			dataSet[i][0] = x;
			dataSet[i][1] = target.root.evaluate(x);
		}
	}
	
	
}
