package net.magmastone.traffic;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class TrafficController implements Tickable{
	
public HashMap<Integer,ArrayList<TrafficLight>> lights;

public ArrayList<TrafficLight> lightsOnRoad(int rd){
ArrayList<TrafficLight> onRoad=lights.get(Integer.valueOf(rd));
return onRoad;
}

public TrafficLight findClosestLight(int rd, int mtr){
ArrayList<TrafficLight> onRoad=lights.get(Integer.valueOf(rd));
TrafficLight closest = new TrafficLight();
closest.color=-1;
int closestDistance=100000000;
for (TrafficLight tl : onRoad) {
	if(tl.roadMetre > mtr){

		if(tl.roadMetre+mtr < closestDistance){
			closest=tl;
			closestDistance=tl.roadMetre;
		}
	}
}

return closest;
}
public TrafficController(){
	lights = new HashMap<Integer,ArrayList<TrafficLight>>();
}

public void tick(){
	for (ArrayList<TrafficLight> tls : lights.values()) {
    	for (TrafficLight tl : tls) {
    		tl.tick();
    	}
	}
}

public void addLight(TrafficLight tl, int rd){

if(lights.containsKey(rd)){
	ArrayList<TrafficLight> tls=lights.get(Integer.valueOf(rd));
	tls.add(tl);
	lights.put(Integer.valueOf(rd),tls);
}else{
ArrayList<TrafficLight> tls = new ArrayList<TrafficLight>();
tls.add(tl);
lights.put(Integer.valueOf(rd),tls);
}

}

}