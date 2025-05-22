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
        while (true){
            try{
                //väntar på att en anslutning till socketet och acepterar den
                // metoden blockerar tills en ansluting är gjord till socketen
                System.out.println("Väntar på anslutnings förfrågan...");
                klient = server.accept();

                System.out.println("Anslutnings förfrågan är accepterad...");
                String klientHost = klient.getInetAddress().getHostAddress();
                int klientport = klient.getPort();
                System.out.println("Klient host = " + klientHost + "Klient port = " + klientport);

                //Läs in data
                InputStream klientIn = klient.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(klientIn));
                String medFrnKlient = br.readLine();
                System.out.println("Meddelande mottaget från klienten: " + medFrnKlient);

                //Skickar svar till klienten
                if (medFrnKlient != null && ! medFrnKlient.equalsIgnoreCase("bye")) {
                    OutputStream klientOut = klient.getOutputStream();
                    PrintWriter pw = new PrintWriter(klientOut, true);
                    String ansMsg = "Hello, " + medFrnKlient;
                    pw.println(ansMsg);
                }

                //Stänga ner sockets
                if (medFrnKlient != null && medFrnKlient.equalsIgnoreCase("bye")){
                    server.close();
                    klient.close();
                    break;
                }

            } catch (IOException ie){
                System.out.println("ERROR, Något gick FEL.");
            }
        }
    }
}