package main;

public class Lift {
    short currentFloor;
    String direction;
    short destinationFloor;

    public boolean isAlreadyInUse() {
        return alreadyInUse;
    }

    public void setAlreadyInUse(boolean alreadyInUse) {
        this.alreadyInUse = alreadyInUse;
    }

    boolean alreadyInUse;

    public short getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(short currentFloor) {
        this.currentFloor = currentFloor;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public short getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(short destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    Lift(short currentFloor){
        this.currentFloor = currentFloor;
    }

}
