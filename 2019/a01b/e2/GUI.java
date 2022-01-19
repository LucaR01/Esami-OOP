package a01b.e2_2;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;

public class GUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1175139189322139326L;
	private final Map<JButton, Pair<Integer, Integer>> grid = new HashMap<>();
	private Logics logics;
	
	public GUI(final int size, final int mines) {
		logics = new LogicsImpl(size, mines);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        int cols = size; // {1,2,3,4,5}
        JPanel panel = new JPanel(new GridLayout(size,size)); // cols, cols
        this.getContentPane().add(BorderLayout.CENTER,panel);
        ActionListener al = (e)->{
            final JButton bt = (JButton)e.getSource();
            int hit = this.logics.hit(this.grid.get(bt));
            if (this.logics.won()) {
            	System.exit(0);
            }
            bt.setText(hit + "");
            bt.setEnabled(false);
            System.out.println(this.grid.get(bt));
        };
        
        for (int i = 0; i < size;i++){ // cols
        	for (int j = 0; j < size; j++) { // cols
	            final JButton jb = new JButton(" ");
	            this.grid.put(jb, new Pair<>(i, j));
	            jb.addActionListener(al);
	            panel.add(jb);
        	}
        }
        this.setVisible(true);   
    }
}
