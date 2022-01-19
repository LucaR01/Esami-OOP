package a04b.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
	
	private Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
	private Logics logics;
	private boolean clicked = false;
    
    
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
        	updateView(this.logics.spread());
        });
        ActionListener al = (e)->{
            final JButton bt = (JButton)e.getSource();
            if(clicked == false) {
            	updateView(this.logics.randomSpawn());
            	this.clicked = true;
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
        		cells.put(jb, new Pair<>(j,i));
        		jb.addActionListener(al);
        		panel.add(jb);
        	}
        }
        this.setVisible(true);
    }
    
    private void updateView(Set<Pair<Integer,Integer>> p) {
    	cells.forEach( (k,v) -> {
    		p.forEach( n -> {
    			if(v.equals(n)) {
    				k.setText("*");
    			}
    			if(this.logics.checkAll(p)){
    				System.out.println("GAME OVER!");
    				System.exit(0);
    			}
    				/*else {
    			}
    				k.setText(" ");
    			}*/
    			
    			//k.setText( v.equals(n) ? "*" : " ");
    		});
    	});
    }
    
}
