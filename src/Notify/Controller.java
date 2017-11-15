package Notify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * The Controller is the intermediary between the 
 * View and the Model, it handles all the events
 */
public class Controller {

	private Model model;
	private View view;
	
	private NoteFactory note;
	
	/**
	 * 
	 * @param model sets the model that will handle the data
	 * @param view sets the view that will interacte with the use
	 * 
	 * The constructor of the class allow us to set the
	 * initial values and initialize the model and view
	 * into the controller to listen for new events
	 * 
	 * @author diego zamora 
	 * 
	 */
	Controller(Model model, View view){
		this.model = model;
		this.view = view;
		
		this.note = new NoteFactory();
		
		this.view.addClickListener(new addListener());
		this.view.removeClickListener(new deleteListener());
		this.view.completeClickListener(new completeListener());				
	}
	
	
	/**
	 * Listens for click event in the new note button
	 * 
	 * @author diego11
	 *
	 */
	class addListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
//			this.note.
			Controller.this.view.newNote();
		}
				
	}
	
	/**
	 * 
	 * Listens for click event at the setNote complete event
	 * 
	 * @author user
	 *
	 */
	class completeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			view.setNoteComplete();
		}
		
	}
	
	/**
	 * 
	 * The delete listener for any click event on the view
	 * triggers the event in the model
	 * 
	 * @author user
	 *
	 */
	class deleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Controller.this.view.deleteNote();
		}
		
	}
	
}
