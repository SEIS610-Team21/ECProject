package Application;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import Modules.Context;

public class ECSettings extends JFrame {
	Context context;

	public ECSettings() {
		context = Context.getInstance();
		InitUI();
	}

	private void InitUI() {
		setSize(300, 500);
		setLayout(new GridLayout(20,4));
		//
		JLabel lblPopSize = new JLabel("Population Size");
		add(lblPopSize);
		JTextField txtPopSize = new JTextField();
		txtPopSize.setPreferredSize(new Dimension(100, 25));
		txtPopSize.setText(""+context.populationSize);
		add(txtPopSize);
		//
		JLabel lblTreeDepth = new JLabel("Tree Depth");
		add(lblTreeDepth);
		JTextField txtTreeDepth = new JTextField();
		txtTreeDepth.setPreferredSize(new Dimension(100, 25));
		txtTreeDepth.setText(""+context.treeDepth);
		add(txtTreeDepth);
		//
		JLabel lblSurThres = new JLabel("Survival Threshold");
		add(lblSurThres);
		JTextField txtSurThres = new JTextField();
		txtSurThres.setPreferredSize(new Dimension(100, 25));
		txtSurThres.setText(""+context.survivalThreshold);
		add(txtSurThres);
		//
		JLabel lblMProb = new JLabel("Mutation Probability");
		add(lblMProb);
		JTextField txtMProb = new JTextField();
		txtMProb.setPreferredSize(new Dimension(100, 25));
		txtMProb.setText(""+context.mutationProbability);
		add(txtMProb);
		//
		JLabel lblCProb = new JLabel("Crossover Probability");
		add(lblCProb);
		JTextField txtCProb = new JTextField();
		txtCProb.setPreferredSize(new Dimension(100, 25));
		txtCProb.setText(""+context.crossoverProbability);
		add(txtCProb);
		//
		JLabel lblTDataLen = new JLabel("Training Data length");
		add(lblTDataLen);
		JTextField txtTDataLen = new JTextField();
		txtTDataLen.setPreferredSize(new Dimension(100, 25));
		txtTDataLen.setText(""+context.trainingDataCount);
		add(txtTDataLen);
		setVisible(true);
		//
		JLabel lblTDataRange = new JLabel("Training Data Range");
		add(lblTDataRange);
		JPanel dataPanel = new JPanel();
		dataPanel.setPreferredSize(new Dimension(100,100));
		
		JTextField txtTDataFrom = new JTextField();
		txtTDataFrom.setPreferredSize(new Dimension(100, 25));
		txtTDataFrom.setText(""+context.trainingDataFrom);
		dataPanel.add(txtTDataFrom);
		JLabel lblTo = new JLabel("To");
		dataPanel.add(lblTo);
		JTextField txtTDataTo = new JTextField();
		txtTDataTo.setPreferredSize(new Dimension(100, 25));
		txtTDataTo.setText(""+context.trainingDataTo);
		dataPanel.add(txtTDataTo);
		add(dataPanel);
		//
		JButton btnOK = new JButton("Apply");
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				context.populationSize=Integer.parseInt(txtPopSize.getText());
				context.treeDepth = Integer.parseInt(txtTreeDepth.getText());
				context.crossoverProbability= Double.parseDouble(txtCProb.getText());
				context.mutationProbability=Double.parseDouble(txtMProb.getText());
				context.survivalThreshold=Integer.parseInt(txtSurThres.getText());
				context.trainingDataCount=Integer.parseInt(txtTDataLen.getText());
				context.trainingDataFrom=Integer.parseInt(txtTDataFrom.getText());
				context.trainingDataTo=Integer.parseInt(txtTDataTo.getText());
				
				try {
					context.saveConfig();
				} catch (TransformerFactoryConfigurationError | TransformerException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(btnOK);
		//
		setVisible(true);
		
	}

}
