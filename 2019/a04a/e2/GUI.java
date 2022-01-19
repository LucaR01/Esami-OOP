package a04a.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
	
	private final Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
	private final Logics logics;
	private boolean turn;
   
    public GUI(int size) {
    	this.logics = new LogicsImpl(size);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        int cols = size; // {1,2,3,4,5}
        JPanel panel = new JPanel(new GridLayout(cols,cols));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        JButton south = new JButton(">");
        this.getContentPane().add(BorderLayout.SOUTH,south);
        south.addActionListener( e -> {
        	System.out.println("turn: " + this.turn);
        	//System.out.println("turn: " + turn(this.turn));
        	updateView(this.logics.randomMove(), this.turn);
        });
        ActionListener al = (e)->{
            final JButton bt = (JButton)e.getSource();
            //bt.setText(""+list.indexOf(bt));
        };
        
        for (int i=0;i<size;i++){
        	for(int j = 0; j < size; j++) {
        		final JButton jb = new JButton("a");
                jb.addActionListener(al);
                //list.add(jb);
                cells.put(jb, new Pair<>(j,i));
                jb.setText( (i == size / 2 && j == size / 2) ? "1" : " ");
                panel.add(jb);
        	}
        } 
        this.setVisible(true);
    }
    
    private void updateView(Pair<Integer,Integer> p, boolean b) {
    	cells.forEach( (k,v) -> {
    		//k.setText( v.equals(p) ? "" : " ");
    		if(b == false) {
    			k.setText(v.equals(p) ? "0" : " ");
    			this.turn = true;
    		} else {
    			k.setText(v.equals(p) ? "1" : " ");
    			this.turn = false;
    		}
    	});
    }
    
    /*private boolean turn(boolean b) {
    	if(b == true) {
    		this.turn = true;
    		return false;
    	} else {
    		this.turn = false;
    		return true;
    	}
    	//return b == true ? false : true;
    }*/
    
    
}
