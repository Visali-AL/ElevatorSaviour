package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scheduler {

    public static List<Lift> availableLifts = Collections.synchronizedList(new ArrayList<>());
    public static List<Lift> busyLifts = Collections.synchronizedList(new ArrayList<>());


    public Lift requestLift(String name, String direction, short requestFloor) {

        if(!busyLifts.isEmpty()){

            for(Lift lift: busyLifts){
                if(direction.equals(lift.direction)){
                    System.out.println(" Trying to re-use busy lifts");
                    short diff = (short) Math.abs(lift.currentFloor - requestFloor);
                    if(diff > 3){
                        lift.setAlreadyInUse(true);
                        System.out.println(" Using a busy lift. Sharing with "+name+"\n");
                        return lift;
                    }
                }
            }
        }

        if(!availableLifts.isEmpty()){
            System.out.println("Getting one from available lifts");
            short min = Main.floors;
            Lift nearestLift = null;

            System.out.print("Available Lifts are ");
            for(Lift lift: availableLifts){
                short diff = (short) Math.abs(lift.getCurrentFloor() - requestFloor);
                System.out.print(lift.getCurrentFloor() + ",");
                if(diff < min){
                    min = diff;
                    nearestLift = lift;
                }
            }

            if(nearestLift != null){
                System.out.println("\nChosen nearest lift is at:: "+nearestLift.getCurrentFloor());
                availableLifts.remove(nearestLift);
                busyLifts.add(nearestLift);
            }else{
                System.out.println("Something wrong");
            }

            return nearestLift;
        }
    return null;

    }
}
