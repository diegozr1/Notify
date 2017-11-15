package Notify;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class View {
	
	protected static String currentPriority;
	
	JFrame frame = new JFrame();    
	
    Object[] row = new Object[4];
    JTable table = new JTable(); 
    
    ArrayList<Integer> groupTimer = new ArrayList<>();
    
    JTextField textContent = new JTextField();
    JTextField textTimeLeft = new JTextField();
    
    JButton btnAdd      = new JButton("New Note");
    JButton btnDelete   = new JButton("Delete");
    JButton btnComplete = new JButton("Done");
    
    DefaultTableModel model = new DefaultTableModel()
    {
        @Override 
        public boolean isCellEditable(int row, int column)
        {
            // add your code here
        	return column == 1 ? true : false;
        }
    };

    Model m = new Model();
    
	private Component pane;
    
	View (){
              
        
        Object[] columns = {"Elapsed Time","Content","Priority","Time left"};
        model.setColumnIdentifiers(columns);
    	
        table.setModel(model); // agregar modelo a la tabla
        
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        
        
        String[] priorities  = { "Low", "Medium", "High"};
        currentPriority = "Low";

	      JComboBox priorityBox = new JComboBox(priorities);
	      priorityBox.setSelectedIndex(0);
	      priorityBox.addActionListener(new ActionListener(){

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	  JComboBox cb = (JComboBox)e.getSource();
	                  String priority = (String)cb.getSelectedItem();
	                  View.currentPriority = priority;
	                  System.out.println(currentPriority);
	            }
	        });
        
       
        textContent.setText("Write your note");
        textContent.setBounds(20, 250, 100, 25);
        textTimeLeft.setBounds(20, 280, 100, 25);
        textTimeLeft.setText("Set time in sec");
        priorityBox.setBounds(20, 310, 100, 25);
        
        btnAdd.setBounds(150, 220, 100, 25);
        btnDelete.setBounds(150, 310, 100, 25);
        btnComplete.setBounds(150, 265, 100,25);
        
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);
        
        frame.setLayout(null);
        
        frame.add(pane);
        
        frame.add(textContent);
        frame.add(textTimeLeft);
        frame.add(priorityBox);
    
        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnComplete);      
               
        table.addMouseListener(new MouseAdapter(){
        
	        @Override
	        public void mouseClicked(MouseEvent e){
	            
	            int i = table.getSelectedRow();
	            
	            textContent.setText(model.getValueAt(i, 1).toString());
	            textTimeLeft.setText(model.getValueAt(i, 3).toString());
	        }
        });
        
        // button update row
//        btnUpdate.addActionListener(new ActionListener(){
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//             
//                // i = the index of the selected row
//                int i = table.getSelectedRow();
//                
//                if(i >= 0) 
//                {
//                   model.setValueAt(textId.getText(), i, 0);
//                   model.setValueAt(textFname.getText(), i, 1);
//                   model.setValueAt(textLname.getText(), i, 2);
//                   model.setValueAt(textAge.getText(), i, 3);
//                }else{
//                    System.out.println("Update Error");
//                }
//            }
//        });        
        
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                updateTableData(table);
            }
        });
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = table.rowAtPoint(e.getPoint());
                if (r >= 0 && r < table.getRowCount()) {
                    table.setRowSelectionInterval(r, r);
                } else {
                    table.clearSelection();
                }

                int rowindex = table.getSelectedRow();
                if (rowindex < 0)
                    return;
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                	VisualizeTimer(rowindex);
                }
            }
        });
        
        this.preLoadNotes();
       
        
        frame.setSize(900,400);
        frame.setTitle("Notify");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
	
	private void updateTableData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();
        
        String[][] datos = new String[numRows][numCols];

//        System.out.println("--------------------------");
        for (int i=0; i < numRows; i++) {
//            System.out.print("row" + i + ":");
            for (int j=0; j < numCols; j++) {
//                System.out.print(model.getValueAt(i, j)+",");
                datos[i][j] = (String) model.getValueAt(i, j).toString();
            }
//            System.out.println();
        }
//        System.out.println("--------------------------");
        this.m.setList(datos);
//        return datos;
    }
	
	void preLoadNotes() {
		
		ArrayList<String[]> aux = this.m.getList();
		
		for(int i = 0; i<aux.size();i++) {
			String[] notas = aux.get(i);			
			row[0] = notas[0];
	        row[1] = notas[1];                
	        row[2] = notas[2];
	        row[3] = notas[3];
	        
	        this.model.addRow(row);
		}
						
	}

	void addClickListener(ActionListener listenClick) {		
		this.btnAdd.addActionListener(listenClick);
	}
	
	protected void AddRow() {
		
		row[0] = (0);
        row[1] = (textContent.getText().equals("")?"A note":textContent.getText());                
        row[2] = currentPriority;
        
        try {
        	row[3] = textTimeLeft.getText().equals("Set time in sec")?setTime(60):setTime(Integer.valueOf(textTimeLeft.getText()));
        }catch(NumberFormatException e) {
        	//   e.printStackTrace();
       	 	JOptionPane.showMessageDialog(pane, "Please enter the value in seconds", "Error", 0);
        }        
        
        this.model.addRow(row);
	}
	
	void removeClickListener(ActionListener listenClick) {
		this.btnDelete.addActionListener(listenClick);
	}
	
	protected void DeleteRow() {
     	int i = table.getSelectedRow();
         if(i >= 0){
         	if(model.getValueAt(i, 3).toString().equals("Completed")) {
         		model.removeRow(i);
         	}else {
         		JOptionPane.showMessageDialog(pane, "You can't delete a message is not completed yet", "Error", 0);
         	}                    
         }else{
//             System.out.println("Delete Error");
        	 JOptionPane.showMessageDialog(pane, "Please select a note before delete", "Error", 0);
         }
     }
	
	void completeClickListener(ActionListener completeClick) {
		this.btnComplete.addActionListener(completeClick);
	}
	
	protected void completeRow() {
		 int i = table.getSelectedRow();
         
         if(i >= 0) 
         {                  
            model.setValueAt("Completed", i, 3);
         }else{
//            System.out.println("No task selected");
            JOptionPane.showMessageDialog(pane, "Please select a note to complete", "Error", 0);
         }
	}	

	
	public String setTime(int totalSecs) {
        int hours = totalSecs / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return timeString;
    }
	
	private void VisualizeTimer(int rowindex) {
    	JLabel label1 = new JLabel();
    	JButton play = new JButton("Play");
    	JButton pause = new JButton("Pause");
    	JButton reset = new JButton("Reset");
//    	long startTime = System.currentTimeMillis();
    	JLabel label2 = new JLabel();
    	
    	new Thread() {
            int counter = 100;
            public void run() {
                while(counter >= 0) {
//                	label1.setText(setTime(counter++));
//                	System.out.println(1);
                    label2.setText(View.this.setTime(counter--));
                    try{
                        Thread.sleep(1000);
                    } catch(Exception e) {}
                }
            }
        }.start();
    		
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        
        frame.add(label1);
        frame.add(play);
        frame.add(pause);
        frame.add(reset);
        frame.add(label2);

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        
//        popupmenu.show(e.getComponent(), e.getX(), e.getY());
    	System.out.println("row"+ rowindex);
	}
	
}