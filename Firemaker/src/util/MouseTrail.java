package util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

import org.osbot.rs07.script.MethodProvider;

public class MouseTrail {

	private int r, g, b, duration;
	private LinkedList<MousePathPoint> mousePath  = new LinkedList<MousePathPoint>();;
	private MethodProvider api;


	public MouseTrail(int r, int g, int b, int duration, MethodProvider api) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.duration = duration;
		this.api = api;
	}
	
	public void nextRGB(){
		if(r == 255 && g < 255 & b == 0){
			g++;
		}
        if ( g == 255 && r > 0 && b == 0 )
        {
            r--;
        }
        if ( g == 255 && b < 255 && r == 0 )
        {
            b++;
        }
        if ( b == 255 && g > 0 && r == 0 )
        {
            g--;
        }
        if ( b == 255 && r < 255 && g == 0 )
        {
            r++;
        }
        if ( r == 255 && b > 0 && g == 0 )
        {
            b--;
        }
        
	}
	

	public Color nextColor(){
		nextRGB();
		return new Color(r,g,b);
	}

	public void paint(Graphics2D g){
		while (!mousePath.isEmpty() && mousePath.peek().isUp())
            mousePath.remove();
        Point clientCursor = api.getMouse().getPosition();
        MousePathPoint mpp = new MousePathPoint(clientCursor.x, clientCursor.y, duration);
        if (mousePath.isEmpty() || !mousePath.getLast().equals(mpp))
            mousePath.add(mpp);
        MousePathPoint lastPoint = null;
        for (MousePathPoint a : mousePath) {
            if (lastPoint != null) {
                g.setColor(nextColor());
                g.drawLine(a.x, a.y, lastPoint.x, lastPoint.y);
            }
            lastPoint = a;
        }
	}
	
}