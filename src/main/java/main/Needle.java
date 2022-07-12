package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.time.LocalDateTime;

public class Needle
{

	private Clock clock;
	private int[] size;
	private Position pos, circPos;
	private int circSize = 24, style = 0;
	private char type;
	private Color color = Color.GRAY;
	
	public Needle(Clock clock, char type, int style)
	{
		this.type = type;
		this.clock = clock;
		this.style = style;
		setPosAndStyle();
	}
	
	public Needle(Clock clock, char type)
	{
		this.type = type;
		this.clock = clock;
		switch(type)
		{
		case 's':
			style = 0;
			break;
		case 'm':
			style = 1;
			break;
		case 'h':
			style = 2;
			break;
		}
		setPosAndStyle();
	}
	
	public void setPosAndStyle()
	{
		size = new int[] {circSize/4, 0};
		int w = (int)clock.getPreferredSize().getWidth();
		int h = (int)clock.getPreferredSize().getHeight();
		circPos = new Position(w/2 - circSize/2, h/2 - circSize/2);
		switch(style)
		{
		case 0:
			color = Color.RED;
			size[1] = (int)(h*0.47);
			pos = new Position(w/2 - size[0]/2, h/2  - (int)(size[1]*0.8));
			break;
		case 1:
			size[1] = (int)(h*0.28);
			pos = new Position(w/2 - size[0]/2, h/2 - size[1]);
			break;
		case 2:
			size[1] = (int)(h*0.17);
			pos = new Position(w/2 - size[0]/2, h/2 - size[1]);
			break;
		}
	}
	
	public void draw(Graphics2D g2d)
	{
		double rota = getRotation();
		g2d.rotate(Math.toRadians(rota), clock.getWidth()/2, clock.getHeight()/2);
		g2d.setColor(color);
		g2d.fillRect(pos.getX(), pos.getY(), size[0], size[1]);
		g2d.fillOval(circPos.getX(), circPos.getY(), circSize, circSize);
		int littleCirc = circSize/2 - circSize/3/2;
		g2d.setColor(Color.BLACK);
		g2d.fillOval(circPos.getX() + littleCirc, circPos.getY()+ littleCirc, circSize/3, circSize/3);
		g2d.rotate(Math.toRadians(-rota), clock.getWidth()/2, clock.getHeight()/2);
	}
	
	public double getRotation()
	{
		return getRotation(type);
	}
	
	public static double getRotation(char type)
	{
		LocalDateTime now = LocalDateTime.now();
		double r = 360;
		switch(type)
		{
		case 's':
			r /= 60;
			return now.getSecond()*r;
		case 'm':
			r /= 60;
			return now.getMinute()*r;
		case 'h':
			r/=12;
			return now.getHour()*r;
		}
		return 0.0;
	}
	
	
	public int[] getSize()
	{
		return size;
	}
	
}
