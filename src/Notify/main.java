package Notify;

public class main {
	
	public static void main(String[] args) {				
		Model m = new Model();
		View v = new View();
		
		Controller c = new Controller(m, v);
	}
	
}
