package Assignment3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Project3Controller implements ActionListener {

	static Project3GUI gui;

	//create GUI
	public static Project3Controller createObject(){
		Project3Controller p = new Project3Controller(new Project3GUI());
		return p;
	}

	public Project3Controller(Project3GUI g){
		gui = g;
		gui.setButtonListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		String buttonName = e.getActionCommand();
		if(buttonName.equals("Open")){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			FileNameExtensionFilter filter1 = new FileNameExtensionFilter("text file","txt");
			fileChooser.setFileFilter(filter1);
			int result = fileChooser.showOpenDialog(gui);
			if(result == JFileChooser.APPROVE_OPTION){
				File selectedFile = fileChooser.getSelectedFile();
				//parse in the data
				parseData(selectedFile);
			}
		}
		if(buttonName.equals("Exit")){
			System.exit(0);
		}
		if(buttonName.equals("Words")){
			gui.clearGUI();
		}
	}

	//parse in the expressions
	public static void parseData(File x){
		try{
			BufferedReader reader = new BufferedReader(new FileReader(x));
			LinkedQueue<Character> linkedQueue = new LinkedQueue<>();
			LinkedStack<BinaryTree> linkedStack = new LinkedStack<>();
			String line = "";
			
			while(true){
				line = reader.readLine();
				if(line == null) break;
				String modified = InfixToPostfix2.infixToPostfix2(line).replaceAll("\\s+","");
				System.out.println(line);
				System.out.println(modified);
				for(int i = 0; i < modified.length(); i++){
					linkedQueue.enqueue(modified.charAt(i));
				}
				//keep dequeueing until the queue is empty
				while(!linkedQueue.isEmpty()){
					if(!InfixToPostfix2.isOperator(linkedQueue.first())){//finds operand
						linkedStack.push(new BinaryTree<Character>(linkedQueue.dequeue()));
					} else{//finds operator
						BinaryTree<Character> tree = new BinaryTree<>(linkedQueue.dequeue());
						@SuppressWarnings("unchecked")
						BinaryTree<Character> rightChild = linkedStack.pop();
						@SuppressWarnings("unchecked")
						BinaryTree<Character> leftChild = linkedStack.pop();
						tree.attach(leftChild, rightChild);
						linkedStack.push(tree);
					}
				}
				@SuppressWarnings("unchecked")
				//pops the only element left in the stack, which is the entire binary tree
				BinaryTree<Character> newTree = linkedStack.pop();
				LinkedStack<Double> newStack = new LinkedStack<>();
				Iterator<Character> itr = newTree.iterator();
				double b;
				while(itr.hasNext()){
					char current = itr.next(); 
					//checks if the character returned by the iterator is a digit, letter, or an operator
					if(Character.isDigit(current)){
						int a = Character.getNumericValue(current);
						newStack.push((double)a);
					} else if(Character.isLetter(current)){
						Scanner sc1 = new Scanner(System.in);
						System.out.println("Enter a number for the variable " + current);
						String input = sc1.nextLine().toLowerCase();
						b = Double.parseDouble(input);
						newStack.push((double)b);
					} else{
						double c, d, result;
						switch(current){
						case '+':
							c = newStack.pop();
							d = newStack.pop();
							result = d + c;
							newStack.push(result);
							break;
						case '-':
							c = newStack.pop();
							d = newStack.pop();
							result = d - c;
							newStack.push(result);
							break;
						case '*':
							c = newStack.pop();
							d = newStack.pop();
							result = d * c;
							newStack.push(result);
							break;
						case '/':
							c = newStack.pop();
							d = newStack.pop();
							result = d / c;
							newStack.push(result);
							break;
						}
					}
				}
				double result2 = newStack.pop();
				System.out.println(result2 + "\n\n\n");
				gui.displayResult(line, modified, result2);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

