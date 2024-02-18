package main;

public class Main {

     static final short floors = 10;
     static final short lifts = 2;
     static short f2lratio = 0;

    public static void main(String[] args){

        System.out.println("Floors:: "+floors);
        System.out.println("Lifts available:: "+lifts);

        if(floors != 0 && lifts != 0 && floors > lifts){
            f2lratio = (short) (floors/lifts);
        }else{
            System.out.println("Please provide valid i/p");
            System.exit(0);
        }

        System.out.println("Lifts are placed every "+f2lratio + " floors");

        for(short i=0, baseFloor = 0; i<lifts; i++, baseFloor += f2lratio){
            Lift obj = new Lift(baseFloor);
            Scheduler.availableLifts.add(obj);
        }

        System.out.println("Total available lifts:: "+Scheduler.availableLifts.size());

        System.out.println("Invoke passengers...");

        Caller caller = new Caller();
        caller.run();
    }
}