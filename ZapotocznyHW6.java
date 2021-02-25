////////////////////////////////////////////////////////////////////////////////////////////////////
// Name:            Daniel Zapotoczny
// Date:            26-Feb-2021
// Course Name:		CPSC 60000 | Object Oriented Development
// Semester:		Spring I 2021
// Assignment Name:	PROGRAMMING ASSIGNMENT #6 - Proxy Pattern
// Program Name:	ZapotocznyHW6
////////////////////////////////////////////////////////////////////////////////////////////////////

import java.rmi.*;

// Monitor test

public class ZapotocznyHW6 {
    public static void main(String[] args){
        String [] location = {"rmi://home.sprinkler.com/sprinklersystem",
                "rmi://lakehouse.sprinkler.com/sprinklersystem",
                "rmi://skicabin.sprinkler.com/sprinklersystem"};

        SprinklerMonitor[] monitor = new SprinklerMonitor[location.length];

        for (int i = 0; i < location.length; i++){
            try {
                SprinklerRemote sprinkler = (SprinklerRemote) Naming.lookup(location[i]);
                monitor[i] = new SprinklerMonitor(sprinkler);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        for (int i = 0; i < monitor.length; i++){
            monitor[i].statusReport();
        }
    }


}
