/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arshifa230922;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author WIN - 10
 */
public class ChatServer {
    private static final int PORT = 12345;
    private static Set<PrintWriter> clientWriters = new HashSet<>();
    
    public static void main(String[] args){
        System.out.println("Server running...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)){
            while (true){
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(),true);
                synchronized (clientWriters) {
                    clientWriters.add(out);
                }
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received: " + message);
                    broadcastMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
        } finally {
                if (out != null) {
                    synchronized (clientWriters) {
                        clientWriters.remove(out);
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        private void broadcastMessage(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                    writer.flush();
                }
            }
        }
        
        
         private String decrypt(String message) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
           public static String decrypt(String decryptedText, String secretKey) {
           StringBuilder decryptedString = new StringBuilder();
           int decryptedInt;
           for (int i = 0; i < decryptedText.length(); i++){
               int decryptedTextInt = (int) (decryptedText.charAt(i) - 'A');
               int secretKeyInt = (int) (secretKey.charAt(i) - 'A');
               decryptedInt = decryptedTextInt - secretKeyInt;
               if (decryptedInt < 0){
                   decryptedInt += 26;
               }
               decryptedString.append((char) ((decryptedInt) + (int) 'A'));                            
           }
           return decryptedString.toString();
           
        
    }
}

