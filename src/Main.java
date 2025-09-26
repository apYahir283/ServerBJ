import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(3000);
        System.out.println("Servidor iniciado");
        System.out.println("Leyendo modo (2 o alt)");
        //hacer sistema que detecte si es que se ejecuto uno de los 2 modos
        //leer cual fue el que se ejecuto con una señal automatica y esa señal sera solo del que crea la partida

        Socket jugador1 = serverSocket.accept();
        if ( jugador1.isConnected()){
            System.out.println("Jugador 1 conectado");
        }

        InputStreamReader putJ1 = new InputStreamReader(jugador1.getInputStream());
        BufferedReader inj1 = new BufferedReader(putJ1);
        PrintWriter outj1 = new PrintWriter(jugador1.getOutputStream(), true);

        String modo = inj1.readLine();

        if (modo.equalsIgnoreCase("dos")){
            System.out.println("Servidor en modo 2");

            Socket jugador2 = serverSocket.accept();
            if (jugador2.isConnected()){
                System.out.println("Jugador 2 conectado");
            }

            InputStreamReader putJ2 = new InputStreamReader(jugador2.getInputStream());

            BufferedReader inj2 = new BufferedReader(putJ2);

            PrintWriter outj2 = new PrintWriter(jugador2.getOutputStream(), true);

            do {

            //Utilidades
            String continuaj1 = inj1.readLine(),
                    continuaj2;
            int ncarta1,ncarta2;
            int sumaj1, sumaj2;

            System.out.println("Inicio de partida, comienza j1");

            if (continuaj1.equals("si")) {
                while (continuaj1.equals("si")) {

                    ncarta1 = Integer.parseInt(inj1.readLine());
                    System.out.println("Se recibio el numero de la carta del j1");
                    outj2.println(ncarta1);
                    System.out.println("Se envio el numero de la carta a j2");
                    if (jugador1.isClosed() || jugador2.isClosed()) {
                        System.out.println("Un jugador se desconecto Terminando partida.");
                        break;
                    }

                    System.out.println("Pregunta si quiere otra carta j1");
                    continuaj1 = inj1.readLine();
                    if (continuaj1.equalsIgnoreCase("no")){
                        System.out.println("Continuando al turno j2");
                    }
                }
            }
            continuaj2 = inj2.readLine();
            if (continuaj2.equals("si")) {
                while (continuaj2.equals("si")) {

                    ncarta2 = Integer.parseInt(inj2.readLine());
                    System.out.println("Se recibio el numero de la carta del j2");
                    outj1.println(ncarta2);
                    System.out.println("Se envio el numero de la carta a j1");
                    if (jugador1.isClosed() || jugador2.isClosed()) {
                        System.out.println("Un jugador se desconecto Terminando partida.");
                        break;
                    }

                    System.out.println("Pregunta si quiere otra carta j2");
                    continuaj2 = inj2.readLine();
                    if (continuaj2.equalsIgnoreCase("no")){
                        System.out.println("Evaluacion");
                    }
                }
            }
            //evaluacion

            System.out.println("si quiere continuar");
            inj1.readLine();
            inj2.readLine();
                } while (inj1.readLine().equalsIgnoreCase(inj2.readLine()));

        } else if (modo.equalsIgnoreCase("alt")) {

        }


    }
}