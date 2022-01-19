package a01a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    //private final List<JButton> cells = new ArrayList<>();
    private final Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
    
    private final Logics logics;
    
    private boolean turn = true; // TRUE = 0, FALSE = X
    
    public GUI(int size) {
    	this.logics = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	var button = (JButton)e.getSource();
        	//var position = cells.indexOf(button);
        	var position = cells.get(button);
        	//button.setText("X"+position);
        	if(!this.logics.getPlayers().contains(position) && this.logics.isBelow(position)) {
        		if(this.turn) {
            		button.setText("0");
            		//this.turn = false;
            	} else {
            		button.setText("X");
            		//this.turn = true;
            	}
        		this.logics.setPlayers(position);
        		if(this.logics.isOver((turn == true) ? this.logics.getO() : this.logics.getX() ,position)) {
        			System.out.println("GAME OVER!");
        			System.exit(0);
        		}
        		this.turn = !this.turn;
        	}
        	
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb,new Pair<>(j,i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
}
