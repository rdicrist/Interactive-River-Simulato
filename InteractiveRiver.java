
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class InteractiveRiver extends JFrame{
	
	public InteractiveRiver(int length, String title, River r) {
		
		//creates a Grid Bag Layout, with no resizable
		GridBagConstraints layout = null;
		setTitle(title);
		setLayout(new GridBagLayout());
		layout = new GridBagConstraints();
		setResizable (false);
		
		for (int i = 0; i < length; i++) {
			
			ImageIcon icon2 = new ImageIcon("Images/bear.jpg");
			JLabel bear = new JLabel (icon2);
			
			ImageIcon icon3 = new ImageIcon("Images/fishy.jpg");
			JLabel fish = new JLabel (icon3);
						
			
			String type = r.type(i);
			if (type.equals("B")) {
				
				layout.gridx = i;
				layout.gridy = 1;
				layout.gridwidth = 1;
				layout.gridheight = 1;
				add(bear,layout);
			}
			else if (type.equals("F")) {
				
				layout.gridx = i;
				layout.gridy = 3;
				layout.gridwidth = 1;
				layout.gridheight = 1;
				add(fish,layout);
			}
			else {
				
			}
		}
		
		for (int i = 0; i < length; i++) {
			
			ImageIcon icon1 = new ImageIcon("Images/wave.jpg");
			JLabel wave = new JLabel (icon1, JLabel.CENTER);
			
			ImageIcon icon4 = new ImageIcon("Images/forest.jpg");
			JLabel forest = new JLabel (icon4);
			
			layout.gridx = i;
			layout.gridy = 2;
			layout.gridwidth = 1;
			layout.gridheight = 1;
			add(wave,layout);
			
			String type = r.type(i);
			if(!(type.equals("B"))){
				layout.gridx = i;
				layout.gridy = 1;
				layout.gridwidth = 1;
				layout.gridheight = 1;
				add(forest,layout);
			}
			
		}
		for (int i = 0; i < length; i++) {			
			ImageIcon icon5 = new ImageIcon("Images/rwater.jpg");
			JLabel water = new JLabel (icon5);
	
			
			String type = r.type(i);
			if(!(type.equals("F"))){
				layout.gridx = i;
				layout.gridy = 3;
				layout.gridwidth = 1;
				layout.gridheight = 1;
				add(water,layout);
			}
		}
		
		for (int i = 0; i < length; i++) {
			String info = r.cellInfo(i).substring(1);
			JLabel cell = new JLabel (info);
			
			String type = r.type(i);
			if (type.equals("B")) {
				layout.gridx = i;
				layout.gridy = 0;
				add(cell,layout);
			}
			else if (type.equals("F")) {
				layout.gridx = i;
				layout.gridy = 4;
				add(cell,layout);
			}
			
		}
	}
	
	
	
	public static void main (String [] args) {
		
		River r = new River(8);
		System.out.println(r);
		
		InteractiveRiver river = new InteractiveRiver(8, "River", r); 
		river.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		river.setLocationRelativeTo(null);
		river.pack();
		river.setVisible(true);
	}

}
