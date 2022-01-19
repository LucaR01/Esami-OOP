package a02b_2.e2;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6218820567019985015L;
	private final Map<JButton, Pair<Integer,Integer>> grid = new HashMap<>();
	private final Logics logics;
	
	public GUI(int size) {
		logics = new LogicsImpl(size);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        int cols = size;
        JPanel panel = new JPanel(new GridLayout(cols,cols)); // size, size
        this.getContentPane().add(BorderLayout.CENTER,panel);
        JButton south = new JButton(">");
        this.getContentPane().add(BorderLayout.SOUTH,south);
        south.addActionListener(e -> {
        	 final JButton bt = (JButton)e.getSource();
        	 this.logics.hitBar();
        	 updateView();
        });
        ActionListener al = (e)->{
            final JButton bt = (JButton)e.getSource();
            bt.setText("X");
            this.logics.hit(grid.get(bt));
            System.out.println(grid.get(bt));
        };
        for (int i=0;i<size;i++){
        	for (int j=0;j<size;j++) {
        		final JButton jb = new JButton(" ");
        		jb.addActionListener(al);
        		grid.put(jb, new Pair<>(i, j));
        		panel.add(jb);
        	}
        } 
        this.setVisible(true);
        updateView();
	}

	private void updateView() {
		this.grid.forEach((b,p) -> {
			if(this.logics.getSelected().contains(p)) {
				b.setText("X");
			} else {
				b.setText(" ");
			}
		});
	}

}
