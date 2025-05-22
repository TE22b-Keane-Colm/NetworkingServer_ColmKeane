import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        ServerSocket server = null;
        Socket klient;

        //port nummer vi ska använda
        int portnummer = 55000;
        if (args.length >= 1){
            portnummer = Integer.parseInt(args[0]);
        }

        //Skapa socket för servern
        try {
            server = new ServerSocket(portnummer);
        } catch (IOException ie){
            System.out.println("Kan inte öppna socket." + ie);
            System.exit(1);
        }

        //vänta på data från klienten.
    }
}