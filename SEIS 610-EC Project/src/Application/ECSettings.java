package Application;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		setLayout(new GridLayout(20, 4));
		//
		JLabel lblPopSize = new JLabel("Population Size");
		add(lblPopSize);
		JTextField txtPopSize = new JTextField();
		txtPopSize.setPreferredSize(new Dimension(100, 25));
		txtPopSize.setText("" + context.populationSize);
		add(txtPopSize);
		//
		JLabel lblTreeDepth = new JLabel("Tree Depth");
		add(lblTreeDepth);
		JTextField txtTreeDepth = new JTextField();
		txtTreeDepth.setPreferredSize(new Dimension(100, 25));
		txtTreeDepth.setText("" + context.treeDepth);
		add(txtTreeDepth);
		//
		JLabel lblSurThres = new JLabel("Survival Threshold");
		add(lblSurThres);
		JTextField txtSurThres = new JTextField();
		txtSurThres.setPreferredSize(new Dimension(100, 25));
		txtSurThres.setText("" + context.survivalThreshold);
		add(txtSurThres);
		//
		JLabel lblMaxIt = new JLabel("Maximum Iterations");
		add(lblMaxIt);
		JTextField txtMaxIt = new JTextField();
		txtMaxIt.setPreferredSize(new Dimension(100, 25));
		txtMaxIt.setText("" + context.maxIterations);
		add(txtMaxIt);
		//
		JLabel lblMProb = new JLabel("Mutation Probability");
		add(lblMProb);
		JTextField txtMProb = new JTextField();
		txtMProb.setPreferredSize(new Dimension(100, 25));
		txtMProb.setText("" + context.mutationProbability);
		add(txtMProb);
		//
		JLabel lblCProb = new JLabel("Crossover Probability");
		add(lblCProb);
		JTextField txtCProb = new JTextField();
		txtCProb.setPreferredSize(new Dimension(100, 25));
		txtCProb.setText("" + context.crossoverProbability);
		add(txtCProb);
		//
		JLabel lblTDataLen = new JLabel("Number of Training Data");
		add(lblTDataLen);
		JTextField txtTDataLen = new JTextField();
		txtTDataLen.setPreferredSize(new Dimension(100, 25));
		txtTDataLen.setText("" + context.trainingDataCount);
		add(txtTDataLen);
		setVisible(true);
		//
		JLabel lblTDataRange = new JLabel("Training Data Range");
		add(lblTDataRange);
		JPanel dataPanel = new JPanel();
		dataPanel.setPreferredSize(new Dimension(100, 100));

		JTextField txtTDataFrom = new JTextField();
		txtTDataFrom.setPreferredSize(new Dimension(100, 25));
		txtTDataFrom.setText("" + context.trainingDataFrom);
		dataPanel.add(txtTDataFrom);
		JLabel lblTo = new JLabel("To");
		dataPanel.add(lblTo);
		JTextField txtTDataTo = new JTextField();
		txtTDataTo.setPreferredSize(new Dimension(100, 25));
		txtTDataTo.setText("" + context.trainingDataTo);
		dataPanel.add(txtTDataTo);
		add(dataPanel);
		//
		JButton btnOK = new JButton("Apply");
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					context.populationSize = Integer.parseInt(txtPopSize.getText());
					context.treeDepth = Integer.parseInt(txtTreeDepth.getText());
					context.crossoverProbability = Double.parseDouble(txtCProb.getText());
					context.mutationProbability = Double.parseDouble(txtMProb.getText());
					context.survivalThreshold = Integer.parseInt(txtSurThres.getText());
					context.maxIterations = Integer.parseInt(txtMaxIt.getText());
					context.trainingDataCount = Integer.parseInt(txtTDataLen.getText());
					context.trainingDataFrom = Integer.parseInt(txtTDataFrom.getText());
					context.trainingDataTo = Integer.parseInt(txtTDataTo.getText());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getContentPane(), "Invalid Input : " + ex.getMessage(),
							"Invalid Input", JOptionPane.ERROR_MESSAGE);
				}
				if (context.crossoverProbability > 1.0) {
					JOptionPane.showMessageDialog(getContentPane(),
							"Invalid Input : Crossover Probability cannot exceed 1.0", "Invalid Input",
							JOptionPane.ERROR_MESSAGE);
					context.crossoverProbability=1.0;
				}
				if (context.mutationProbability > 1.0) {
					JOptionPane.showMessageDialog(getContentPane(),
							"Invalid Input : Mutation Probability cannot exceed 1.0", "Invalid Input",
							JOptionPane.ERROR_MESSAGE);
					context.mutationProbability=1.0;

				}

				try {
					context.saveConfig();
				} catch (TransformerFactoryConfigurationError | TransformerException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(btnOK);
		//
		this.setLocationRelativeTo(null);
		setVisible(true);

	}

}
