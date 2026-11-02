package ClientPackage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client3 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Client connecté au serveur.");

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        Scanner scanner = new Scanner(System.in);

        int code;
        do {
            System.out.println("\n MENU CALCULATRICE ");
            System.out.println("1. Addition        (x + 5)");
            System.out.println("2. Soustraction    (x - 5)");
            System.out.println("3. Multiplication  (x * 5)");
            System.out.println("4. Division        (x / 5)");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            code = scanner.nextInt();

            dos.writeInt(code);
            dos.flush();

            if (code == 0) {
                System.out.println("Fin de la communication.");
                break;
            }

            if (code < 1 || code > 4) {
                System.out.println("Choix invalide !");
                continue;
            }

            System.out.print("Entrez la valeur de x : ");
            int x = scanner.nextInt();

            dos.writeInt(x);
            dos.flush();

            int resultat = dis.readInt();

            if (resultat == Integer.MIN_VALUE) {
                System.out.println("Erreur de calcul (division par zéro ?)");
            } else {
                System.out.println(">>> Résultat = " + resultat);
            }

        } while (code != 0);

        dis.close();
        dos.close();
        socket.close();
        scanner.close();
    }
}