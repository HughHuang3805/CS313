package Assignment2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Project3Controller implements ActionListener {

	static Project2GUI gui;

	//create GUI
	public static Project3Controller createObject(){
		Project3Controller p = new Project3Controller(new Project2GUI());
		return p;
	}
		 
	public Project3Controller(Project2GUI g){
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
			String line = "";
			while(true){
				line = reader.readLine();
				if(line == null) break;
				gui.displayResult(line, InfixToPostfix.infixToPostfix(line));
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
