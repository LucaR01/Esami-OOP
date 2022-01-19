package a05a.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
	
	private final Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
	private Logics logics;
	
	public GUI(int size) {
		this.logics = new LogicsImpl(size);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(100*size, 100*size);
		this.setVisible(true);

		int cols = size; // {1,2,3,4,5}
		JPanel panel = new JPanel(new GridLayout(cols,cols));
		this.getContentPane().add(BorderLayout.CENTER,panel);
		/*JButton south = new JButton(">");
		this.getContentPane().add(BorderLayout.SOUTH, south);
		
		south.addActionListener( e -> {
			
		});*/
		
		ActionListener al = (e)->{
			final JButton bt = (JButton)e.getSource();
			
			var pos = cells.get(bt);
			
			if(bt.getText() == "") {
				this.logics.setPlayers(pos);
				bt.setText("X");
			} else if(bt.getText() == "X") {
				this.logics.remove(pos);
				//bt.setText("");
			}
			
			updateView(this.logics.getPlayers());
		};
		
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++) {
				final JButton jb = new JButton("");
				cells.put(jb, new Pair<>(j,i));
				jb.addActionListener(al);
				panel.add(jb);
			}
		}
		
	}
	
	// così non funzionava
	/*void updateView(Set<Pair<Integer,Integer>> s) {
		s.forEach( n -> {
			cells.forEach( (k,v) -> {
				if(v.equals(n)) {
					k.setText("X");
				}
				
			});
		});
	}*/
	
	void updateView(Set<Pair<Integer,Integer>> s) {
		cells.forEach( (k,v) -> {
			if(s.contains(v)) {
				k.setText("X");
			} else {
				k.setText("");
			}
		});
		if(this.logics.isOver()) {
			System.out.println("GAME OVER!");
			System.exit(0);
		}
	}
    
}
