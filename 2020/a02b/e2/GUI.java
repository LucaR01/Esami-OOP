package a02b.e2;

import javax.swing.*;

import a02b.e2.Logics.Direction;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
    private final Logics logics;
    
    public GUI(int size) {
    	this.logics = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        JButton north = new JButton("NORTH");
        JButton south = new JButton("SOUTH");
        JButton east = new JButton("EAST");
        JButton west = new JButton("WEST");
        this.getContentPane().add(BorderLayout.NORTH,north);
        this.getContentPane().add(BorderLayout.SOUTH,south);
        this.getContentPane().add(BorderLayout.EAST,east);
        this.getContentPane().add(BorderLayout.WEST,west);
        
        north.addActionListener(e -> {
        	//System.out.println("north");
        	//updateView(this.logics.move(Direction.UP));
        	this.logics.move(Direction.UP);
        	updateView2();
        });
        
        south.addActionListener(e -> {
        	this.logics.move(Direction.DOWN);
        	updateView2();
        });
        
        east.addActionListener(e -> {
        	this.logics.move(Direction.RIGHT);
        	updateView2();
        });
        
        west.addActionListener( e -> {
        	this.logics.move(Direction.LEFT);
        	updateView2();
        });
        
        ActionListener al = e -> {
        	var jb = (JButton)e.getSource();
        	var pos = cells.get(jb);
        	//jb.setText(""+cells.get(jb));
        	this.logics.setPlayers(pos);
        	jb.setText("*");
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<>(j,i));
                grid.add(jb);
                jb.addActionListener(al);
            }
        }
        this.setVisible(true);
    }
    
    private void updateView(Set<Pair<Integer,Integer>> s) {
    	this.cells.forEach( (k,v) -> {
    		/*s.forEach( n -> {
    			if(v.equals(n)) {
    				k.setText("*");
    			} else {
    				k.setText("");
    			}*/
    			
    		if(this.logics.getPlayers().contains(v)) {
    			k.setText("*");
    		} else {
    			k.setText("");
    		}
    		//});
    	});
    }
    
    private void updateView2() {
    	this.cells.forEach( (k,v) -> {
    			
    		if(this.logics.getPlayers().contains(v)) {
    			k.setText("*");
    		} else {
    			k.setText("");
    		}
    	});
    }
}
