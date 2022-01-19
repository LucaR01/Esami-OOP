package a02c.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
    private final Logics logics;
    
    private int counter = 0;
    
    public GUI(int size) {
    	this.logics = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	var button = (JButton)e.getSource();
        	//var position = cells.indexOf(button);
        	
        	var pos = cells.get(button);
        	
        	if(!this.logics.getNumbers().contains(pos)) {
        		
        		button.setText(String.valueOf(counter));
            	this.counter++;
            	this.logics.setNumber(pos);
            	
            	if(this.logics.won()) {
            		System.out.println("Vittoria!");
            		System.exit(0);
            	}
        	}
        	
        	System.out.println("numbers: " + this.logics.getNumbers()); //TODO: remove
        	
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                //this.cells.add(jb);
                this.cells.put(jb,new Pair<>(j,i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
}
