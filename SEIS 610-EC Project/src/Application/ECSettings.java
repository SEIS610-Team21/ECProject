package Application;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		add(txtPopSize);
		//
		JLabel lblTreeDepth = new JLabel("Tree Depth");
		add(lblTreeDepth);
		JTextField txtTreeDepth = new JTextField();
		txtTreeDepth.setPreferredSize(new Dimension(100, 25));
		add(txtTreeDepth);
		//
		JLabel lblSurThres = new JLabel("Survival Threshold");
		add(lblSurThres);
		JTextField txtSurThres = new JTextField();
		txtSurThres.setPreferredSize(new Dimension(100, 25));
		add(txtSurThres);
		//
		JLabel lblMProb = new JLabel("Mutation Probability");
		add(lblMProb);
		JTextField txtMProb = new JTextField();
		txtMProb.setPreferredSize(new Dimension(100, 25));
		add(txtMProb);
		//
		JLabel lblCProb = new JLabel("Crossover Probability");
		add(lblCProb);
		JTextField txtCProb = new JTextField();
		txtCProb.setPreferredSize(new Dimension(100, 25));
		add(txtCProb);
		//
		JLabel lblTDataLen = new JLabel("Training Data length");
		add(lblTDataLen);
		JTextField txtTDataLen = new JTextField();
		txtTDataLen.setPreferredSize(new Dimension(100, 25));
		add(txtTDataLen);
		setVisible(true);
		//
		JLabel lblTDataRange = new JLabel("Training Data Range");
		add(lblTDataRange);
		JPanel dataPanel = new JPanel();
		dataPanel.setPreferredSize(new Dimension(100,100));
		
		JTextField txtTDataFrom = new JTextField();
		txtTDataFrom.setPreferredSize(new Dimension(100, 25));
		dataPanel.add(txtTDataFrom);
		JLabel lblTo = new JLabel("To");
		dataPanel.add(lblTo);
		JTextField txtTDataTo = new JTextField();
		txtTDataTo.setPreferredSize(new Dimension(100, 25));
		dataPanel.add(txtTDataTo);
		add(dataPanel);
		//
		//
		setVisible(true);
		
	}

}
