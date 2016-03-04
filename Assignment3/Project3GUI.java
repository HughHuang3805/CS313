package Assignment3;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Project3GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JMenuItem[] menuItems = new JMenuItem[2];
	JTextArea textArea = new JTextArea();

	public Project3GUI(){	
		setTitle("Infix To Postfix Converter");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMenuItems();
		setDisplayPanel();
		setVisible(true);
	}

	public void setDisplayPanel(){
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		add(new JScrollPane(textArea));
	}

	public void setMenuItems(){
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("File");
		JMenuItem item1 = new JMenuItem("Open");
		JMenuItem item2 = new JMenuItem("Exit");
		menuItems[0] = item1;
		menuItems[1] = item2;
		menu1.add(item1);
		menu1.addSeparator();
		menu1.add(item2);
		menuBar.add(menu1);
		setJMenuBar(menuBar);
	}

	public void displayResult(String original, String modified, double result){
		textArea.append("The original infix expression: " + original + "\n");
		textArea.append("Postfix expression: " + modified + "\n");
		textArea.append("The value of the expression is: " + Double.toString(result) + "\n\n\n");
	}

	public void clearGUI(){
		textArea.setText(null);
	}

	public void setButtonListener(ActionListener a){
		for(JMenuItem x : menuItems){
			x.addActionListener(a);
		}
	}
}

