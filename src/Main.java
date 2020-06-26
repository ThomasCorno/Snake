import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame obj = new JFrame();//creation of an empty frame
		Gameplay gameplay = new Gameplay();
		
		obj.setBounds(10, 10, 905, 700);//setting of the x and y coordinates, width and height
		obj.setBackground(Color.DARK_GRAY);//setting the background color to darkgray
		obj.setResizable(false);// theframe won't be resizable
		obj.setVisible(true);// the frame is set to visible
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// the application shuts down on close
		obj.add(gameplay);// Adding the object of gameplay to the object of JFrame
		
	}

}
