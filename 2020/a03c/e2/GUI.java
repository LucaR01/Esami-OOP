package a03c.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer,Integer>> cells = new HashMap<>();
    private final JButton next = new JButton(">");
    private final Logics logics;
    
    private int counter = 0;
    
    public GUI(int size) {
    	this.logics = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        this.getContentPane().add(BorderLayout.SOUTH,next);
        
        //next.addActionListener(e -> cells.forEach((b,p)-> {if(p.getX()==0) b.setText("*");}));
        
        next.addActionListener( e -> {
        	if(this.logics.hit(new Pair<>(this.logics.getHead().getX(), this.logics.getHead().getY() + 1))) {
        		updateHead(new Pair<>(this.logics.getHead().getX(), this.logics.getHead().getY() + 1));
        	} else {
        		if((this.logics.hit(new Pair<>(this.logics.getHead().getX(), this.logics.getHead().getY() - 1)))) {
        			updateHead(new Pair<>(this.logics.getHead().getX(), this.logics.getHead().getY() - 1));
        		}
        		
        	}
        });
        
        ActionListener el = e -> {
        	var button = (JButton)e.getSource();
        	var position = this.cells.get(button);
        	
        	if(this.logics.hit(position)) {
        		updateHead(position);
        	}
        	
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<>(j,i));
                grid.add(jb);
                jb.addActionListener(el);
            }
        }
        var random = new Random();
    	var randomPosition = new Pair<>(random.nextInt(size),random.nextInt(size));
    	//cells.forEach( (button,position) -> { if (position.equals(randomPosition)) {button.setText("*");}});
    	
    	updateView(this.logics.getRandomNumbers());
    	
        this.setVisible(true);
    }
    
    private void updateView(Set<Pair<Integer,Integer>> s) {
    	this.cells.forEach( (b,p) -> {
    		s.forEach( n -> {
    			if(p.equals(n)) {
    				b.setText(String.valueOf(this.counter));
    				this.counter++;
    			}
    		});
    	});
    }
    
    private void updateHead(Pair<Integer,Integer> p) {
    	this.cells.forEach( (b,c) -> {
    		if(c.equals(p)) {
    			b.setText(String.valueOf(this.counter));
    			this.counter++;
    		}
    	});
    }
    
}
