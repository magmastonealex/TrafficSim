package net.magmastone.traffic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.Random;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import java.util.ArrayList;

class Surface extends JPanel {
	public TrafficController tc;
	public CarController cc;

	private void drawRoad(int roadNumber, Graphics g, int x1, int x2 float scale){
		Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(150, 150, 150));
        int roadLength = 10000; // 10k

        int roadWidth=2*scale;

        Dimension size = getSize();
        
        int hgt=
        
        g2d.fillRect(x, 0, roadWidth, );

        ArrayList<TrafficLight> tls=tc.lightsOnRoad(1);
        for (TrafficLight tl : tls) {
        	if(tl.color==0){
        		g2d.setColor(new Color(255,255,0));
        	}else if(tl.color==-1){
        		g2d.setColor(new Color(255,0,0));
        	}else if (tl.color==1){
        		g2d.setColor(new Color(0,255,0));
        	}
        	g2d.fillOval(90,900-tl.roadMetre, 20,20);
        }
        g2d.setColor(new Color(0, 150, 150));
    	ArrayList<Car> cs = cc.carsOnRoad(1);
    	for (Car c : cs) {
    		g2d.fillRect(85,900-(int)c.roadMetre,30,30);
    	}


	}

    private void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(150, 150, 150));

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);
        Dimension size = getSize();
        drawRoad(1,g,80,40);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class CarGraphics extends JFrame {
	public TrafficController tc;
	public CarController cc;
	public Surface s;
    public CarGraphics(TrafficController t, CarController c) {
    	tc=t;
    	cc=c;
        initUI();
    }
    private void initUI() {

        setTitle("Simple Java 2D example");
        s = new Surface();
        s.tc=tc;
        s.cc=cc;
        add(s);
        setSize(200, 1500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

  
}