/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arshifa230922;
import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author WIN - 10
 */

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            String nama = "";

            System.out.print("Input Nama anda ? ");
            nama = scanner.nextLine();

            System.out.println("Connect ke Server. Kirim Pesan ('exit' untuk keluar):");
            new PesanServer(socket, in).start();

            String message;
            while (true) {
                message = scanner.nextLine();
                out.println(nama + ":" + message);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            // Tutup koneksi
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class PesanServer extends Thread {
        Socket socket;
        BufferedReader in;
        String message;

        public PesanServer(Socket a, BufferedReader in) {
            this.socket = a;
            this.in = in;
        }

        @Override
        public void run() {
            try {
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        
        private String encrypt(String message) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
 }
           public static String encrypt(String plainText, String secretKey){
               StringBuilder encryptedString = new StringBuilder();
               int encryptedInt;
               for (int i = 0; i < plainText.length(); i++){
                   int plainTextInt = (int) (plainText.charAt(i) - 'A');
                   int secretKeyInt = (int) (secretKey.charAt(i) - 'A');
                   encryptedInt = (plainTextInt + secretKeyInt) % 26;
                   encryptedString.append((char) ((encryptedInt) + (int) 'A'));
               }
               return encryptedString.toString();
        
        
    }
}

