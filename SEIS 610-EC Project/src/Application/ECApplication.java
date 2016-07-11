package Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Brokers.PopulationGenerator;
import Library.BinaryTree;
import Modules.Context;
import Modules.TrainingData;

public class ECApplication extends JFrame {

	BinaryTree tree;
	BinaryTree[] trees;
	Context context;
	PopulationGenerator generator;
	JTextArea textOutput;

	public ECApplication() {
		tree = new BinaryTree();
		context = Context.getInstance();
		generator = new PopulationGenerator();
		trees = new BinaryTree[10];
		generator.generateFirstGeneration(trees, 10);
		
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
		JButton btnExecute = new JButton("Evaluate");
		btnExecute.setLocation(460, 15);
		btnExecute.setSize(100, 60);
		topPanel.add(btnExecute);
		//
		frame.add(topPanel);
		//
		JPanel centerPanel = new JPanel(null);
		centerPanel.setLocation(10, 100);
		centerPanel.setSize(560, 380);
		centerPanel.setBorder(new TitledBorder(new EtchedBorder(),""));
		//
		textOutput = new JTextArea();
		textOutput.setLocation(10, 10);
		textOutput.setSize(540, 480);
		textOutput.setEditable(false);
		JScrollPane scroll = new JScrollPane(textOutput);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		centerPanel.add(scroll);
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
				Evaluate('(' + txtTargetExp.getText() + ')');
			}
		});
		//Menu Bar
		JMenuBar mBar = new JMenuBar();
		JMenu menu = new JMenu("Config");
		menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mBar.add(menu);
		this.setJMenuBar(mBar);
		//
		this.add(frame);
		this.setTitle("EC System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public String StringifyTreeArray() {
		String _result = "";
		for (int i = 0; i < trees.length; i++) {
			_result += trees[i].toString() + " ==>> " + trees[i].evaluate() + "\n";
		}
		return _result;
	}

	private void Evaluate(String inputExp) {
		String output;
		tree=new BinaryTree();
		tree.buildFromString(inputExp);
		TrainingData.getInstance().Generate(100, tree);
		//
		output = "The Target Expression : ";
		output += tree.inOrder(tree.root) + "\n\n";
		output += "First generation expressions" + "\n";
		output += StringifyTreeArray();
		textOutput.setText(output);
	}

	public static void main(String[] args) {
		ECApplication app = new ECApplication();
	}

}
