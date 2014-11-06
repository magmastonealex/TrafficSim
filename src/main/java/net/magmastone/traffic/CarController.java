package net.magmastone.traffic;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class CarController implements Tickable{
	
public HashMap<Integer,ArrayList<Car>> cars;

public ArrayList<Car> carsOnRoad(int rd){
ArrayList<Car> onRoad=cars.get(Integer.valueOf(rd));
return onRoad;
}

public CarController(){
	cars = new HashMap<Integer,ArrayList<Car>>();
}

public void tick(){
	for (ArrayList<Car> tls : cars.values()) {
    	for (Car tl : tls) {
    		tl.tick();
    	}
	}
}

public void addCar(Car c, int rd){

if(cars.containsKey(rd)){
	ArrayList<Car> tls=cars.get(Integer.valueOf(rd));
	tls.add(c);
	cars.put(Integer.valueOf(rd),tls);
}else{
	ArrayList<Car> tls = new ArrayList<Car>();
	tls.add(c);
	cars.put(Integer.valueOf(rd),tls);
}

}

}