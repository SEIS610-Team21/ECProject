package Application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Library.BinaryTree;

public class ECApplication extends JFrame {
	BinaryTree tree;
	JTextArea textOutput;

	public ECApplication() {
		tree = new BinaryTree();
		initUI();
	}

	private void initUI() {
		this.setSize(600, 600);
		JPanel frame = new JPanel(null);
		//
		JPanel topPanel = new JPanel(null);
		topPanel.setLocation(10, 10);
		topPanel.setSize(560, 100);
		//
		JLabel lblTargetExp = new JLabel("Enter Target Expression");
		lblTargetExp.setLocation(20, 15);
		lblTargetExp.setSize(200, 25);
		topPanel.add(lblTargetExp);
		//
		JTextField txtTargetExp = new JTextField();
		txtTargetExp.setLocation(240, 15);
		txtTargetExp.setSize(200, 25);
		topPanel.add(txtTargetExp);
		//
		JLabel lblXValue = new JLabel("Enter X Value");
		lblXValue.setLocation(20, 50);
		lblXValue.setSize(200, 25);
		topPanel.add(lblXValue);
		//
		JTextField txtXValue = new JTextField();
		txtXValue.setLocation(240, 50);
		txtXValue.setSize(200, 25);
		topPanel.add(txtXValue);
		//
		JButton btnExecute = new JButton("Evaluate");
		btnExecute.setLocation(460, 15);
		btnExecute.setSize(100, 60);
		topPanel.add(btnExecute);
		//
		frame.add(topPanel);
		//
		JPanel centerPanel = new JPanel(null);
		centerPanel.setLocation(10, 100);
		centerPanel.setSize(560, 400);
		//
		textOutput = new JTextArea();
		textOutput.setLocation(10, 10);
		textOutput.setSize(540, 480);
		centerPanel.add(textOutput);
		//
		frame.add(centerPanel);
		//
		JPanel bottomPanel = new JPanel(null);
		bottomPanel.setLocation(10, 500);
		bottomPanel.setSize(560, 50);
		frame.add(bottomPanel);
		//
		btnExecute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Evaluate('(' + txtTargetExp.getText() + ')', Integer.parseInt(txtXValue.getText()));
			}
		});
		//
		this.add(frame);
		this.setTitle("EC System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void Evaluate(String inputExp, int xValue) {
		String output;
		tree.buildFromString(inputExp);
		double _result = tree.evaluate(xValue);
		output = "The Expression is : ";
		output += tree.inOrder(tree.root)+"\n";
		output += "The Value of expression is " + _result;
		textOutput.setText(output);
	}

	public static void main(String[] args) {

		ECApplication app = new ECApplication();

		// Scanner scan = new Scanner(System.in);

		// System.out.println("Enter equation in infix form");
		// tree.buildFromString(scan.next());

		// System.out.println("\n\nEvaluated Result : " +
		// tree.evaluate(scan.nextInt()));
		// scan.close();
	}

}
