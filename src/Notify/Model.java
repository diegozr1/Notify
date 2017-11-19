package Notify;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class Model {

	private static final String FILENAME = "C:\\notify\\notify.txt";
	
	/**
	 * Is the method dedicated to read the file containing the notes and returns the
	 * notes as an arraylists  
	 * @return ArrayList
	 */
	private ArrayList readFile() {
		ArrayList<String[]> notesList = new ArrayList<String[]>();
		BufferedReader br = null;
		FileReader fr = null;
		try {
			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			String sCurrentLine;
			int i = 0;
			while ((sCurrentLine = br.readLine()) != null) {
//				String[] aux = {"0", };
				String[] tokens = sCurrentLine.split("\\|");
//				System.out.println(Arrays.toString(tokens));
				notesList.add(tokens);
//				System.out.println(sCurrentLine);
			}
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("No file found, create a task to create it! ");
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return notesList;
	}
	
	/**
	 * This method is used to save the notes to 
	 * the text file under c:/notify/notify.txt
	 * @param lines
	 */
	private void saveFile(String[][] lines) {		
		try {
			PrintWriter writer = new PrintWriter(FILENAME, "UTF-8");
			for(int i = 0; i < lines.length; i++) {
				writer.println(lines[i][0]+"|"+lines[i][1]+"|"+lines[i][2]+"|"+lines[i][3]+"|");
			}			
			writer.close();
		}catch(FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}				
	}
	/**
	 * The protected method that triggers the read from the notes
	 * this is used so that the real one is note directly accessed
	 * @return
	 */
	protected ArrayList getList(){				
		return this.readFile();
	}
		
	/**
	 * The protected method that receives the multidimensional array with notes from the user
	 * to be saved in a text file at C:/notify/notify.txt
	 * @param notes
	 */
	protected void setList(String[][] notes) {
		this.saveFile(notes);
	}
	
}
