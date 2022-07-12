package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Clock extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private List<Needle> needles = new ArrayList<Needle>();
	
	private Timer tm = new Timer(5, this);
	
	public Clock(Dimension dim)
	{
		super();
		this.setPreferredSize(dim);
		needles.add(new Needle(this, 'h'));
		needles.add(new Needle(this, 'm'));
		needles.add(new Needle(this, 's'));
		this.setOpaque(false);
		tm.start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(41));
		g2d.fillOval(0, 0, getWidth(), getHeight());
		
		drawLines(g2d);
		
		for(Needle n : needles)
			n.draw(g2d);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}
	
	public void drawLines(Graphics2D g2d)
	{
		int w = (int)getPreferredSize().getWidth();
		int h = (int)getPreferredSize().getHeight();
		Rectangle rect1 = new Rectangle(w/2 - w/30/2, (int)(0.025*h), w/30, h/12);
		Rectangle rect2 = new Rectangle(w/2 - w/45/2, (int)(0.025*h), w/45, h/18);
		Rectangle rect3 = new Rectangle(w/2 - w/125/2, (int)(0.025*h), w/125, h/35);
		g2d.setColor(Color.WHITE);
		for(int i=1;i<=60;i++)
		{
			g2d.rotate(Math.toRadians(360/60), w/2, h/2);
			if(i%15 == 0)
				g2d.fill(rect1);
			else if(i%5 == 0)
				g2d.fill(rect2);
			else
				g2d.fill(rect3);
		}
	}
	
}
