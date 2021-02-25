import java.rmi.*;
import java.rmi.server.*;

public class SprinklerSystem extends UnicastRemoteObject implements SprinklerRemote {
    final static int OFF = 0;
    final static int ON = 1;
    final static int IS_RAINING = 2;
    final static int LIMIT_REACHED = 3;

    String location;
    int state = OFF;
    int limit = 0; // limit is number of hours per day according to "city ordinance"
    int initialLimit = 0;

    public SprinklerSystem(String location, int limit) throws RemoteException {
        this.limit = limit;
        this.location = location;
        initialLimit = limit;
        if (limit > 0) {
            state = OFF;
        }
    }

    public String getLocation(){
        return location;
    }

    public int getLimit(){
        return limit;
    }

    public String getState(){
        if (state == OFF){
            return "Off";
        } else if (state == ON){
            return "On";
        } else if (state == IS_RAINING){
            return  "Due to rain, sprinkler is off";
        } else{
            return "Sprinkler is off due to reaching daily limit";
        }
    }

    public void turnOn(){
        if (state == ON){
            System.out.println("Sprinklers are already on!");
        } else if (state == OFF){
            state = ON;
            System.out.println("Sprinkler system is now watering the grass.");
        }else if (state == IS_RAINING){
            System.out.println("It is raining, why waste water with sprinklers?");
        }else if (state == LIMIT_REACHED){
            System.out.printf("You have reached the daily watering limit of %d hours set by local ordinance.\n", initialLimit);
        }
    }

    public void turnOff(){
        if (state == ON){
            state = OFF;
            System.out.println("Sprinklers have been turned off");
        } else if (state == OFF){
            System.out.println("Sprinklers are already off!");
        }else if (state == IS_RAINING){
            System.out.println("Sprinklers have already been turned off due to rain!");
        }else if (state == LIMIT_REACHED){
            System.out.printf("Sprinklers have already been turned off because you have reached the daily watering limit of %d hours set by local ordinance.\n", initialLimit);
        }
    }

    public void passOneHour(){
        if (state == ON){
            limit -= 1;
            System.out.printf("Sprinklers have used 1 hour of available limit. Available time left: %d\n", limit);
            if (limit < 1){
                state = LIMIT_REACHED;
                System.out.printf("You have reached the daily watering limit of %d hours set by local ordinance. Sprinklers have been shut off.\n", initialLimit);
            }
        } else if (state == OFF){
            System.out.println("Sprinklers have been off for the past hour.");
        } else if (state == IS_RAINING){
            System.out.println("It has been raining for the past hour");
        } else if (state == LIMIT_REACHED){
            System.out.println("Daily limit has been reached");
        }

    }

    public void toggleRain(){
        if (state == ON){
            state = IS_RAINING;
            System.out.println("Sprinklers have been shut off due to rain.");
        } else if (state == OFF){
            state = IS_RAINING;
            System.out.println("Sprinklers will remain off due to rain.");
        } else if (state == IS_RAINING){
            state = OFF;
            System.out.println("It has stopped raining, you may turn on the sprinklers if you wish to do so.");
        } else if (state == LIMIT_REACHED){
            System.out.println("The rain helped you out because you can't water the grass even if you wanted to because you use your daily limit!");
        }
    }

    public String toString(){
        return "\nSprinkler System\nTotal daily limit: " + initialLimit + "\nDaily limit remaining: " + limit + "\n";
    }
}
