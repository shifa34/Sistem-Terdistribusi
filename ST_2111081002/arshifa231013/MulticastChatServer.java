/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arshifa231013;

/**
 *
 * @author WIN - 10
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class MulticastChatServer {
    private static final String MULTICAST_ADDRESS = "230.0.0.1";
    private static final int MULTICAST_PORT = 12345;
    private static final int MAX_USERS = 10;

    private static Map<String, InetAddress> users = new HashMap<>();

    // Fungsi untuk mengenkripsi pesan
    public static String encrypt(String text, int key) {
        String temp = "";
        for (int i = 0; i < text.length(); i++) {
            int h = (int) (text.charAt(i));
            char t = (char) (h ^ key);
            temp += t;
        }
        return temp;
    }

    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
            MulticastSocket multicastSocket = new MulticastSocket(MULTICAST_PORT);
            multicastSocket.joinGroup(group);

            System.out.println("Multicast Chat Server is running..");
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                multicastSocket.receive(packet);

                String message = new String(packet.getData(), 0, packet.getLength());

                String[] parts = message.split(":", 2);
                if (parts.length == 2) {
                    String username = parts[0].trim();
                    String content = parts[1].trim();
                    if (!users.containsKey(username)) {
                        if (users.size() >= MAX_USERS) {
                            System.out.println("User limit reached. " + username + " cannot join.");
                            continue;
                        }
                        users.put(username, packet.getAddress());
                        System.out.println(username + " joined the chat.");
                    }
                    for (Map.Entry<String, InetAddress> entry : users.entrySet()) {
                        if (!entry.getKey().equals(username)) {
                            InetAddress userAddress = entry.getValue();
                            String encryptedMessage = encrypt(message, 15); // Mengenkripsi pesan
                            DatagramPacket sendPacket = new DatagramPacket(
                                    encryptedMessage.getBytes(), encryptedMessage.length(), userAddress, MULTICAST_PORT);
                            multicastSocket.send(sendPacket);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

