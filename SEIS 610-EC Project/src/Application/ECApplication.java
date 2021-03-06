package Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import Library.BinaryTree;

/*This is the main Launcher class, with main method. 
 * The main UI of EC system is built in this class
 * The menu help access the Config settings
 * */
public class ECApplication extends JFrame {

	BinaryTree targetExp;
	ECOperations operations;
	JTextArea textOutput;
	JTextField txtResult;
	public ECApplication() {
		targetExp = new BinaryTree();
		initUI();
	}

	// Generation of all UI Agents is done in the initUI method
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
		centerPanel.setLocation(10, 110);
		centerPanel.setSize(560, 380);
		centerPanel.setBorder(new TitledBorder(new EtchedBorder(), ""));
		//
		textOutput = new JTextArea();
		// textOutput.setLocation(10, 10);
		// textOutput.setSize(540, 480);
		textOutput.setEditable(false);
		JScrollPane scroll = new JScrollPane(textOutput);
		scroll.setLocation(10, 10);
		scroll.setSize(540, 360);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		centerPanel.add(scroll);
		// centerPanel.add(textOutput);
		//
		frame.add(centerPanel);
		//
		JPanel bottomPanel = new JPanel(null);
		bottomPanel.setLocation(10, 500);
		bottomPanel.setSize(560, 50);
		//
		JLabel lblResult = new JLabel("Resultant Approx. Expression : ");
		lblResult.setLocation(0, 10);
		lblResult.setSize(180, 25);
		bottomPanel.add(lblResult);
		txtResult = new JTextField();
		txtResult.setLocation(185, 10);
		txtResult.setSize(375, 25);
		bottomPanel.add(txtResult);
		frame.add(bottomPanel);
		//
		btnExecute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Evaluate('(' + txtTargetExp.getText() + ')');				
			}
		});
		// Menu Bar
		JMenuBar mBar = new JMenuBar();
		JMenuItem menu = new JMenuItem("Config");
		menu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ECSettings settings = new ECSettings();
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

	private void evaluateFirstGeneration(String inputExp) {
		String output;
		output = operations.evaluateFirstGeneration(inputExp, targetExp);
		textOutput.setText(output);
	}

	private void processNaturalSelection() {
		textOutput.append("\n" + "After Process:" + "\n");
		operations.processNaturalSelection(textOutput);
	}

	// Evaluate method will take the given Target expression as input
	// And call separate operations on ECOperations class, sequentially
	private void Evaluate(String inputExp) {
		textOutput.setText("");// Reset output
		operations = new ECOperations();
		operations.generateFirstGeneration(); // Generate first generation
		evaluateFirstGeneration(inputExp);
		//
		if (!targetExp.isEmpty()) {
			// This method will loop multiple times to perform selection of
			// expression after multiple generations
			processNaturalSelection();
			//
			//get the resultant approximate expression and display output
			BinaryTree result = operations.getResultExpression();
			txtResult.setText(result.inOrder(result.root));
		}
	}

	public static void main(String[] args) {
		ECApplication app = new ECApplication();
	}

}
