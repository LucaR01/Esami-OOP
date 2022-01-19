package a02a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
    private final JButton next = new JButton(">");
    private final Logics logics;
    
    private boolean isFirst = true;
    
    public GUI(int size) {
    	this.logics = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        this.getContentPane().add(BorderLayout.SOUTH,next);
        
        next.addActionListener(e -> {
        	//System.out.println("NEXT");
        	//cells.get(0).setText("*");
        	//this.next.setEnabled(false);
        	if(this.isFirst) { // Avrei anche potuto avere una singola updateView con parametri i nemici, la nuova posizione del pawn e la vecchia da cancellare.
        		updateView(this.logics.spawnEnemies());
        		update2(this.logics.getPawn(), null); // altrimenti chiamo semplicemente la prima update.
        		this.isFirst = !this.isFirst;
        	} else {
        		var temp = this.logics.getPawn(); // salvo temporaneamente le vecchie coordinate del pedone, così da poter cancellare sullo schermo
        		// la sua vecchia posizione.
        		update2(this.logics.movePawn(), temp);
        	}   	
        });
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<>(j,i));
                grid.add(jb);
            }
        }
        this.setVisible(true);
    }    
    
    private void updateView(Set<Pair<Integer,Integer>> s) {
    	cells.forEach( (k,v) -> {
    		s.forEach( n -> {
    			if(n.equals(v)) {
    				k.setText("*");
    			}
    		});
    	});
    }
    
    private void update(Pair<Integer,Integer> p) {
    	cells.forEach( (k,v) -> {
    		if(v.equals(p)) {
    			k.setText("p");
    		}
    	});
    }
    
    private void update2(Pair<Integer,Integer> p, Pair<Integer,Integer> toRemove) {
    	cells.forEach( (k,v) -> {
    		if(v.equals(p)) {
    			k.setText("p");
    		}
    		if(v.equals(toRemove)) {
    			k.setText("");
    		}
    	});
    }
}
