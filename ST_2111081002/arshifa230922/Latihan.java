/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arshifa230922;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author WIN - 10
 */
public class Latihan {
    public static void main(String[] args) throws IOException
    {
        System.out.println("What is the name of the file to read from? ");
        String fileName;
        FileInputStream fis = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            fileName = br.readLine();
            System.out.println("Now reading from " + fileName+"....");
            fis = new FileInputStream(fileName);

        } catch (Exception e) {
            System.out.println("File Not Found!");
        }

        System.out.println("What is the name of the file to write to? ");
        String fileOutput;
        fileOutput = br.readLine();
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(fileOutput);
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be opened for writing");
        }

        try {
            char data;
            int temp;
            do {
                temp = fis.read();
                if (temp == 32) {
                    temp = 95;
                }
                data = (char) temp;
                fos.write(data);
            } while((temp != -1) );

        } catch (IOException e) {
            System.out.println("Problem Processing file!");
        }
    } 
}


