package main;

import java.util.Random;

class LiftRequestor implements Runnable {


    private String name;
    private String direction;

    private short currentFloor;

    public LiftRequestor(String name, String direction, short currentFloor) {
        this.name = name;
        this.direction = direction;
        this.currentFloor = currentFloor;

        if(currentFloor == 0 && direction.equals("DOWN") || currentFloor == 9 && direction.equals("UP")){
            this.direction = direction.equals("DOWN")? "UP" : "DOWN";
        }

        System.out.println("Requester requesting a lift for " +this.name+ " from "+this.currentFloor+"th floor for direction  "+ this.direction);
    }


    @Override
    public void run() {
        Scheduler scheduler = new Scheduler();
        Lift acquiredLift = scheduler.requestLift(name, direction, currentFloor);

        if(acquiredLift == null){
            System.out.println("No available lifts");
            return;
        }

        boolean isUp = "UP".equals(direction);
        short sleepTime = (short) Math.abs(acquiredLift.currentFloor - this.currentFloor);
        try {
            for(short i=0; i<sleepTime; i++) {
                Thread.sleep( 1000);

                if(acquiredLift.currentFloor < this.currentFloor)
                    acquiredLift.setCurrentFloor((short) (++acquiredLift.currentFloor));
                else
                    acquiredLift.setCurrentFloor((short) (--acquiredLift.currentFloor));

                System.out.println(name + "--" + acquiredLift.getCurrentFloor());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        acquiredLift.setCurrentFloor(currentFloor);

        Random random = new Random();

        short destinationFloor = currentFloor;
        while(destinationFloor == currentFloor || (isUp && destinationFloor < currentFloor) || (!isUp && destinationFloor > currentFloor)){
             destinationFloor = Caller.getFloor();
        }

        System.out.print("Destination floor chosen is "+destinationFloor + " from current floor "+currentFloor+ " by "+name + "\n");
        sleepTime = (short) Math.abs(currentFloor - destinationFloor);
        try {
            for(short i=0; i<sleepTime; i++){
                Thread.sleep(1000);
                if(isUp)
                    acquiredLift.setCurrentFloor((short) (++acquiredLift.currentFloor));
                else
                    acquiredLift.setCurrentFloor((short) (--acquiredLift.currentFloor));

                System.out.println(name + "--" + acquiredLift.getCurrentFloor());
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(name + " is leaving the lift");
        if(!acquiredLift.isAlreadyInUse()){
            Scheduler.busyLifts.remove(acquiredLift);
            Scheduler.availableLifts.add(acquiredLift);
            System.out.println(name + "s lift is added to available lifts");
        }


    }
}


