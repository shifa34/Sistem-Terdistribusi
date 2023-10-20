/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arshifa231013;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

/**
 *
 * @author WIN - 10
 */

public class MulticastChatClientB {
    private static final String MULTICAST_ADDRESS = "230.0.0.1";
    private static final int MULTICAST_PORT = 12345;

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

            System.out.print("Enter your username: ");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine().trim();
            String joinMessage = username + " has joined the chat.";
            byte[] joinData = joinMessage.getBytes();
            DatagramPacket joinPacket = new DatagramPacket(joinData, joinData.length, group, MULTICAST_PORT);

            multicastSocket.send(joinPacket);

            System.out.println("Multicast Chat Client is running...");

            Thread readThread = new Thread(() -> {
                try {
                    while (true) {
                        byte[] buffer = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        multicastSocket.receive(packet);

                        String encryptedMessage = new String(packet.getData(), 0, packet.getLength());
                        String decryptedMessage = encrypt(encryptedMessage, 15); // Mendekripsi pesan
                        System.out.println(decryptedMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            readThread.start();

            while (true) {
                String message = scanner.nextLine();
                String encryptedMessage = encrypt(username + ": " + message, 15); // Mengenkripsi pesan
                byte[] sendData = encryptedMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, group, MULTICAST_PORT);

                multicastSocket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
