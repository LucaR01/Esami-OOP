package a03b.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
	
	private final Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
	private final Logics logics;
    
   public GUI(int size) {
	   this.logics = new LogicsImpl(size);
	   this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       this.setSize(500, 500);
       int cols = size; // {1,2,3,4,5}
       JPanel panel = new JPanel(new GridLayout(cols,cols));
       this.getContentPane().add(BorderLayout.CENTER,panel);
       //this.getContentPane().add(BorderLayout.SOUTH,new JButton("South"));
       
       final JButton south = new JButton(">");
       this.getContentPane().add(BorderLayout.SOUTH,south);
       south.addActionListener(e -> {
    	   updateView2(this.logics.move());
    	   
       });
       ActionListener al = (e)->{
           final JButton bt = (JButton)e.getSource();
           //var pos = cells.get(bt);
           updateView(this.logics.randSpawnPosition());
           
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
    		   cells.put(jb, new Pair<>(j,i));
    		   panel.add(jb);
    	   }
       }
       this.setVisible(true);
   }
   
   private void updateView(Set<Pair<Integer,Integer>> p) {
	   //this.logics.getPlayer().add(p);
	   //Iterator<Pair<Integer, Integer>> i = this.logics.getPlayer().iterator();
	   int m = 0;
	   List<Pair<Integer,Integer>> list = new ArrayList<>(this.logics.getPlayer());
	   cells.forEach( (k,v) -> {
		   /*while(this.logics.getPlayer().iterator().hasNext()) {
			   if(v.equals(new Pair<>(this.logics.getPlayer().iterator().next().getX(), this.logics.getPlayer().iterator().next().getY()))){
				   k.setText( "X" );
			   } else {
				   k.setText(" ");
			   }
		   }*/
		   
		   //k.setText(this.logics.getPlayer().forEach( (x) -> v.equals(new Pair<>(x.getX(), x.getY())) ? "X" : " "));
		   /*if(this.logics.getPlayer().forEach(e -> v.equals(new Pair<>(e.getX(), e.getY())))) {
			   
		   }*/
		   
		   /*while(i.hasNext()) {
			   int x = i.next().getX();
			   int y = i.next().getY();
			   if(v.equals(new Pair<>(x,y))) {
				   k.setText("X");
			   } else {
				   k.setText(" ");
			   }
		   }*/
		   
		   /*int j = 0;
		   while(j < this.logics.getPlayer().size()) {
			   if(v.equals(new Pair<>(this.logics.getPlayer().iterator().next().getX(), this.logics.getPlayer().iterator().next().getY()))) {
				   k.setText("X");
			   } else {
				   k.setText(" ");
			   }
		   }*/
		   //int m = 0;
		   //k.setText(v.equals(list.get(m)) ? "X" : " ");
		   //System.out.println("list.get(m): " + list.get(m));
		   //m += 1;
		   
		   //this.logics.getPlayer().forEach(h -> v.equals(h));
		   this.logics.getPlayer().forEach(h -> {
			   if(v.equals(h)) {
				   k.setText("X");
			   }
		   });
		   /*int z = 0;
		   while(z < list.size()) {
			   if(v.equals(list.get(z))) {
				   k.setText("X");
			   } else {
				   k.setText(" ");
			   }
		   }*/
       });
   }
   
   private void updateView2(Set<Pair<Integer,Integer>> p) {
	   Set<Pair<Integer,Integer>> buttonsSet = new HashSet<>();
	   List<Pair<Integer,Integer>> list = new ArrayList<>(p);
	   /*cells.forEach( (k,v) -> {
		   //k.setText( v.equals(new Pair<>(p.iterator().next().getX(), p.iterator().next().getY())) ? "X" : " ");
		   buttonsSet.add(v);
	   });*/
	   //int j = 0;
	   cells.forEach( (k,v) -> {
		   /*buttonsSet.forEach( z -> {
			   if(z.equals(list))
		   });*/
		   //k.setText(buttonsSet.containsAll(p) ? "X" : " ");
		   
		   /*buttonsSet.forEach( z -> {
			  p.forEach( n -> {
				 if(z.equals(n)) {
					 k.setText("X");
				 } else {
					 k.setText("");
				 }
			  });
		   });*/
		   
		   p.forEach(n -> {
			  if(v.equals(n)) {
				  k.setText("X");
			  }
			  else if(v.equals(new Pair<>(n.getX(), n.getY() - 1))) {
				  k.setText(" ");
			  }
			  /*else {
				  k.setText(" ");
			  }*/
		   });
		   
		   /*p.forEach( h -> {
		   });*/
		   
	   });
	   
	   /*p.forEach(n -> {
		   cells.forEach( (k,v) -> {
			   if(v.equals(n)) {
				   k.setText("X");
			   } else {
				   k.setText("");
			   }
		   });
	   });*/
   }
    
    
}
