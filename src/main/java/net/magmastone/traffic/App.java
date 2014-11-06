package net.magmastone.traffic;

import java.lang.Thread;
import javax.swing.SwingUtilities;
/**
 * Hello world!
 *
 */
public class App
{

    public static void main( String[] args ) throws Exception
    {

    	TrafficLight tl = new TrafficLight();
    	tl.roadNumber=1;
    	tl.roadMetre=10;
    	tl.id=1020;
    	tl.color=1;

    	final TrafficController cont = new TrafficController();
    	cont.addLight(tl,1);

    	TrafficLight tl2 = new TrafficLight();
    	tl2.roadNumber=1;
    	tl2.roadMetre=150;
    	tl2.id=2010;
    	tl2.color=-1;

    	cont.addLight(tl2,1);

    	TrafficLight tl3 = new TrafficLight();
    	tl3.roadNumber=1;
    	tl3.roadMetre=500;
    	tl3.id=2011;
    	tl3.color=-1;

    	cont.addLight(tl3,1);

    	Car c=new Car(cont,null);
    	c.accelleration=(float)1;
    	c.goalSpeed=(float)20;
    	c.speedMod=0;
    	c.roadNumber=1;
    	c.roadMetre=9;
    	c.reactionTime=0;
    	c.state=-3;

    	final CarController cc = new CarController();
    	cc.addCar(c,1);
    	final CarGraphics sk = new CarGraphics(cont,cc);

 SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                
                sk.setVisible(true);
            }
        });

    	while(true){
    	sk.repaint();
        cc.tick();
		cont.tick();
		sk.repaint();
		if(c.roadMetre>800){
			c.roadMetre=0;
		}
		Thread.sleep(500);
		}
    }
}
