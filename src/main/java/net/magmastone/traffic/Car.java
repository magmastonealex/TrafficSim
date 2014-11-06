package net.magmastone.traffic;
//Car needs to be able to 
public class Car implements Tickable{
	//Randomly assigned characteristics of a driver
	public int speedMod; // How much will a driver speed/underspeed? (tiles per tick)
	public int reactionTime; // What's the reaction time of the driver (in ticks)
	public TrafficController control;
	public Car behindCar; //Allow signals to another car

	//Randomly assigned characteristics of the car


	public float accelleration; // tiles per tick per tick
	
	public float brakes; // tiles per tick per tick

	//State information
	public float curSpeed; // tiles per tick

/*
	-3 = waiting for light
	-2 = stopped
	-1 = slowing
	0 = Maintaining speed
	1 = Waiting to accellerate
	2 = Accellerating
*/

	public int waitNumber=0;
	public int maxDecel=2;
	public int state = -2;

// Each road is assigned a number
	public int roadNumber;


// roadMetre is the position.
	public float roadMetre;

	public int stopMetre;
	private int lastLightStopped;
	public float goalSpeed;
public Car(TrafficController tc, Car bc){
control=tc;
behindCar=bc;
}
public void tick(){

if(state==0){
	roadMetre+=curSpeed;
}else if(state ==-2){

}
else if(state==1){
	if(waitNumber>=reactionTime){
		state=2;
	}else{
		waitNumber++;
	}
}
else if(state==2){
	if(curSpeed < goalSpeed+speedMod){
	curSpeed+=accelleration;
	roadMetre+=curSpeed;
	}else{
		state=0;
		curSpeed=goalSpeed+speedMod;
		roadMetre+=curSpeed;
	}
}else if (state==0){
	roadMetre+=curSpeed;

}else if (state==-3){
	int col=checkTrafficLightsStopped();
	System.out.println("state3"+String.valueOf(col));
	if(col==1){
		System.out.println("statr");
		carAheadMoving();
	}
}else if(state==-1){
	if(curSpeed > 0){
		curSpeed+=brakes;
		roadMetre+=curSpeed;
		System.out.println("Slowing still..."+String.valueOf(curSpeed));
	}
	if(curSpeed <=0 || roadMetre > stopMetre){
		System.out.println("Ending...");
		curSpeed=0;
		roadMetre=stopMetre;
		brakes=0;
		state=-3;
	}
}

int col=checkTrafficLights();
if(state==-1 && col==1){
carAheadMoving();
}
if(state !=-1 && state != -2 && state != -3){
	TrafficLight l= control.findClosestLight(roadNumber, (int)roadMetre);

	if(col==0){
		//Yellow light.
		float acl=calcAccel();
		if(acl<=maxDecel){

			state=-1;

			brakes=acl;
			
			stopMetre=l.roadMetre-2;
		}
	}else if(col==-1){
		//Must stop for the light.
		float acl=calcAccel();
		state=-1;
		brakes=acl;
		stopMetre=l.roadMetre-2;
	}

}

}

/*
Finds closest light, returns -1,0,1

-1 = Red light, must stop
0 = Yellow, stop if decel < maxDecel
1 = Green, go!
*/
public int checkTrafficLightsStopped(){
TrafficLight l= control.findClosestLight(roadNumber, (int)roadMetre);
System.out.println("Closest light: "+String.valueOf(l.id)+" dist: "+String.valueOf(l.roadMetre-roadMetre)+" color:"+String.valueOf(l.color));

System.out.println("Color: "+String.valueOf(l.color));
return l.color;
}

public int checkTrafficLights(){
TrafficLight l= control.findClosestLight(roadNumber, (int)roadMetre);
System.out.println("Closest light: "+String.valueOf(l.id)+" dist: "+String.valueOf(l.roadMetre-roadMetre)+" color:"+String.valueOf(l.color));
if(l.roadMetre-roadMetre > (curSpeed*5)){
	return 1;
}
System.out.println("Color: "+String.valueOf(l.color));
return l.color;
}

public float calcAccel(){
TrafficLight l= control.findClosestLight(roadNumber, (int)roadMetre);
float accl=(curSpeed*curSpeed)/(-2*(l.roadMetre-roadMetre));
//System.out.println("need to slow at:"+String.valueOf(accl));
return accl;
}


public void carAheadMoving(){
System.out.println("Going");
waitNumber=0;
state=1;
}
public void carAheadSlowing(){
	state=-2;
	curSpeed=0;
}

}