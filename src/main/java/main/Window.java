package main;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public Window(int w, int h)
	{
		super();
		this.setTitle("Clock");
		this.setSize(w, h);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setDefaultLookAndFeelDecorated(true);
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		this.setLayout(new GridBagLayout());
		
		JPanel pan = new JPanel();
		pan.add(new Clock(new Dimension(w-100, h-100)));
		
		this.getContentPane().add(pan);
		
		this.setVisible(true);
	}

}
