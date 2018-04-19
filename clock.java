

/*
	simple program to design analog clock syncronised to system clock 
	
											designed by Ashish
*/




import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
/*
<applet code="clock.class" width="500" height="500"></applet>
*/

public class clock extends Applet implements Runnable
{
	Date da;
	int sx,sy,mx,my,hx,hy;
	
	double theata,r;
	double th1=(double)(6*Calendar.getInstance().get(Calendar.SECOND));				//initial point of second hand
	double th2=(double)(6*Calendar.getInstance().get(Calendar.MINUTE));				//initial point of minute hand
	double th3=(double)(30*Calendar.getInstance().get(Calendar.HOUR));				//initial point of hour hand
	int x1,x2,y1,y2;
	SimpleDateFormat sf=new SimpleDateFormat("hh:mm:ss");
	
	public void init()
	{
		Thread t=new Thread(this);
		t.start();
	}	  
		 
		 
		
	
	public void run()
	{					
			try
			{	
				while(true)
				{
					repaint();						//to call paint() function
					da=new Date();				
					Thread.sleep(1000);				//calls paint() function after every 1 second
				}
			}
			catch(Exception ex){}
	}
	

	public void paint(Graphics g)
	{
		theata=0;
		g.setColor(Color.red);
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString(sf.format(da),160,90);					// draw date of font arial and red color
		
		g.drawOval(145,145,130,130);						//draw outer circle of clock
		g.drawOval(150,150,120,120);						//draw inner circle of clock
		
		while(theata<=360)
		{
			g.setColor(Color.blue);
			r=Math.toRadians(theata);
			x1=(int)(60*Math.cos(r));
			y1=(int)(60*Math.sin(r));
			x2=(int)(50*Math.cos(r));
			y2=(int)(50*Math.sin(r));	
			g.drawLine(210+x1,210+y1,210+x2,210+y2);		// to draw minutes dashes
			theata = theata+6;
		}
		theata=0;		
		while(theata<=360)
		{
			g.setColor(Color.red);
			r=Math.toRadians(theata);
			x1=(int)(60*Math.cos(r));
			y1=(int)(60*Math.sin(r));
			x2=(int)(40*Math.cos(r));
			y2=(int)(40*Math.sin(r));	
			g.drawLine(210+x1,210+y1,210+x2,210+y2);		// to draw hour dashes
			theata = theata+30;
		}
			
			double a=Math.toRadians(th1);
			sx=(int)(40*Math.sin(a));	
			sy=(int)(40*Math.cos(a));	
			g.setColor(Color.cyan);			
			g.drawLine(210,210,210+sx,210-sy);		//seconds Needle
			
			double b=Math.toRadians(th2);
			mx=(int)(30*Math.sin(b));
			my=(int)(30*Math.cos(b));
			g.setColor(Color.yellow);
			g.drawLine(210,210,210+mx,210-my);
			double c=Math.toRadians(th3);			//minute Needle
			hx=(int)(20*Math.sin(c));
			hy=(int)(20*Math.cos(c));
			g.setColor(Color.pink);
			g.drawLine(210,210,210+hx,210-hy);		//hour needle
			g.setColor(Color.black);
			g.fillOval(206,208,7,7);
			th1+=6;									//increase th1 by 6 degree per second
			th2+=0.1;								//increase th2 by 0.1 degree per second
			th3+=0.001;								//increase th1 by 0.001 degree per second
			
	}
}