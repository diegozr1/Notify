package Notify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller {

	private Model model;
	private View view;
	
	private NoteFactory note;
	
	Controller(Model model, View view){
		this.model = model;
		this.view = view;
		
		this.note = new NoteFactory();
		
		this.view.addClickListener(new addListener());
		this.view.removeClickListener(new deleteListener());
		this.view.completeClickListener(new completeListener());				
	}
	
	class addListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
//			this.note.
			Controller.this.view.AddRow();
		}
				
	}
	
	class completeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			view.completeRow();
		}
		
	}
	
	class deleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Controller.this.view.DeleteRow();
		}
		
	}
	
}
