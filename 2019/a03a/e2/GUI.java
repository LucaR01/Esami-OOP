package a03a.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
	
	private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
	final private Logics logics;
    
    public GUI(int size) {
    	this.logics = new LogicsImpl(size);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        int cols = size; // {1,2,3,4,5}
        JPanel panel = new JPanel(new GridLayout(cols,cols)); // size, size
        
        this.getContentPane().add(BorderLayout.CENTER,panel);
        JButton south = new JButton(">");
        
        this.getContentPane().add(BorderLayout.SOUTH,south);
        south.addActionListener(e -> {
        	updateView(this.logics.move());
        });
        
        ActionListener al = (e)->{
            final JButton bt = (JButton)e.getSource();
            var pos = cells.get(bt);
            this.logics.createSet(pos);
            if(pos.getX() == 0 && pos.getY() == 0) {
            	System.out.println("Non si può mettere la O sulla X!");
            } else {
            	bt.setText("O");
            }
            //bt.setText(""+list.indexOf(bt));
        };
        /*for (int i=0;i<3*3;i++){
            final JButton jb = new JButton("a");
            jb.addActionListener(al);
            //list.add(jb);
            panel.add(jb);
        } */
        
        for(int i = 0; i < size; i++) {
        	for(int j = 0; j < size; j++) {
        		final JButton jb = new JButton(" ");
        		jb.addActionListener(al);
        		cells.put(jb, new Pair<>(j,i)); // Qui è molto importante mettere prima la j e poi la i altrimenti le coordinate (x,y) si rovesciano!
        		//((i == 0) && (j == 0)) ? jb.setText("X") : jb.setText(" ");
        		jb.setText(i == 0 && j == 0 ? "X" : " ");
        		/*if(i == 0 && j == 0) {
        			jb.setText("X");
        		} else {
        			jb.setText(" ");
        		}*/
        		panel.add(jb);
        	}
        }
        this.setVisible(true);
    }
    
    private void updateView(Pair<Integer,Integer> p) {
    	cells.forEach( (k,v) -> {
    		//Pair<Integer,Integer> temp = cells.get(k);
    		//System.out.println("updateView temp: " + temp);
    		System.out.println("updateView cells.get(k): " + cells.get(k));
    		System.out.println("updateView p: " + p);
    		if(p.equals(cells.get(k))) { 
    			k.setText("X");
    		} 
    		else if(this.logics.getSet().contains(cells.get(k))) {
    			k.setText("O");
    		}
    		else {
    			k.setText(" ");
    		}
    	});
    }
    
    
}
