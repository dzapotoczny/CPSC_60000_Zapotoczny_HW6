import java.rmi.Naming;

// Sprinkler Test

public class ZapotocznyHW5 {
    public static void main(String [] args){
        SprinklerRemote sprinklerSystem = null;
        int count;

        if (args.length < 2){
            System.out.println("Sprinkler system <location> <limit>");
            System.exit(1);
        }
        try {
            count = Integer.parseInt(args[1]);

            sprinklerSystem = new SprinklerSystem(args[0], count);
            Naming.rebind("//" + args[0] + "/sprinklersystem", sprinklerSystem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
