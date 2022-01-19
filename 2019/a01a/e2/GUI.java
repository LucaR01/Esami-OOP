package a01a.e2;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
	
	private final Logics logics;
	private int xCounter;
    
	private final Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
    
    public GUI(int size, int boat) {
    	this.logics = new LogicsImpl(size, boat);
    	
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300 * size, 300 * size);
        
        int cols = 3; // {1,2,3,4,5}
        
        JPanel panel = new JPanel(new GridLayout(cols,cols));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        
        ActionListener al = (e)->{
            final JButton bt = (JButton)e.getSource();
            var pos = cells.get(bt);
            //System.out.println("pos del button clickato: " + pos);
            if(this.logics.isOver()) {
            	System.out.println("isOver!");
            	System.exit(0);
            }
            else if(logics.hit(pos.getX(), pos.getY())) {
            	bt.setText("X");
            	this.logics.resetCounter(); // se prendiamo una parte della nave dobbiamo resettare il counter dei tentativi.
            	xCounter++;
            	System.out.println("X counter: " + xCounter);
            	bt.setEnabled(false);
            	if(xCounter == boat) {
            		System.out.println("Vinto!");
            		System.out.println("isPresent winner: " + this.logics.isWinner());
            		if(this.logics.isOver()) {
            			System.out.println("isOver!");
            			System.exit(0);
            		}
            	}
            } else {
            	/*if(logics.getCounter() >= 5) {
            		if(logics.isOver()) {
            			System.exit(0);
            		}
            	}*/
            	if(logics.isOver()) { // equivalente a scrivere this.logics.getCounter() >= 5
            		System.exit(0);
            		System.out.println("isOver!");
            	}
            	bt.setText("O");
            	logics.incrementCounter();
            	if(logics.isOver()) { // equivalente a scrivere this.logics.getCounter() >= 5
            		System.exit(0);
            		System.out.println("isOver!");
            	}
            }
            //System.exit(1);
        };
        /*
        for (int i=0;i<3*3;i++){
            final JButton jb = new JButton("");
            this.cells.put(jb, new Pair<>(i,i));
            jb.addActionListener(al);
            panel.add(jb);
        } */
        for(int i = 0; i < 3; i++) {
        	for(int j = 0; j < 3; j++) {
        		final JButton jb = new JButton("");
        		this.cells.put(jb, new Pair<>(j,i));
        		jb.addActionListener(al);
        		panel.add(jb);
        	}
        }
    	this.setVisible(true);
    }
    
}
