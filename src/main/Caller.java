package main;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Caller implements Runnable{


    @Override
    public void run() {

        ExecutorService pool = Executors.newFixedThreadPool(3);
        try{
            System.out.println("Creating 5 requesters for lift...");

            for(int i=0; i<5; i++){
                System.out.println("\n\n\nA new passenger is invoking...");
                pool.execute(new LiftRequestor( "P"+i, getDirection(), getFloor()));
                Thread.sleep(5000);
            }

        }catch (Exception e){
            System.out.println("Exception creating objects");
        }finally {
            pool.shutdown();
        }

    }

    public static short getFloor() {
        Random random = new Random();
        return (short) random.nextInt(10);
    }

    private String getDirection(){
        return randomDirectionGenerator();
    }

    private String randomDirectionGenerator(){
        String[] directions = new String[]{"UP", "DOWN"};
        Random rand = new Random();
        return directions[rand.nextInt(directions.length)];
    }
}
