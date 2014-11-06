package net.magmastone.traffic;

public class TrafficLight implements Tickable{

public int roadMetre;
public int roadNumber;
public int id;
/*
-1 = red
0 = yellow
1 = green
*/
public int color;

public int count;


public void tick(){

if(count > 20 && (color==1 || color==-1) ){
	count=0;
	if(color==1){
		color=0;
	}else{
		color=1;
	}
}else if(color==0 && count > 5){
	color=-1;
	count=0;
}
count++;
}

}