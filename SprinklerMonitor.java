import java.rmi.*;

public class SprinklerMonitor {
    SprinklerRemote sprinkler;

    public SprinklerMonitor(SprinklerRemote sprinkler){
        this.sprinkler = sprinkler;
    }

    public void statusReport(){
        try {
            System.out.println("Sprinkler System: " + sprinkler.getLocation());
            System.out.println("Hours of daily watering limit remaining: " + sprinkler.getLimit());
            System.out.println("Current state: " + sprinkler.getState());
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }
}
