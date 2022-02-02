package a04.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
    private final Logics logics;
    
    private static final String KING = "\u265A";
    private static final String PAWN = "\u2659";
    private static final String KNIGHT = "\u265E";
       
    public GUI(int size) {
    	this.logics = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	var button = (JButton)e.getSource();
        	var position = cells.get(button);
        	
        	if(this.logics.hit(position)) {
        		updateView(this.logics.getPieces());
        		
        		if(this.logics.isOver()) {
        			System.out.println("GAME OVER!");
        			System.exit(NORMAL);
        		}
        	}
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(""); 
                this.cells.put(jb, new Pair<>(j,i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        
        updateView(this.logics.getPieces());
        
        this.setVisible(true);
    }
    
    private void updateView(Set<Pair<Integer,Integer>> s) {
    	this.cells.forEach(  (b,p) -> {
    		s.forEach( n -> {
    			if(p.equals(n) && p.equals(this.logics.getKing())) {
    				if(this.logics.isKing()) {
        				b.setText(GUI.KING);
        			} else {
        				b.setText(GUI.KNIGHT);
        			}
    			} else if(p.equals(n) && this.logics.getPawns().contains(p)) {
    				b.setText(GUI.PAWN);
    			} else if(!(p.equals(this.logics.getKing()) || this.logics.getPawns().contains(p))){
    				b.setText("");
    			}
    		});
    	});
    }
    
}
