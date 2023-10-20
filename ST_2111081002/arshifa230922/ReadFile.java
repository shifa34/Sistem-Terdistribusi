/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arshifa230915;
import java.io.*;

/**
 *
 * @author WIN - 10
 */
public class ReadFile {
    public static void main(String[] args) throws IOException
    {
        System.out.println("Nama File?");
        String filename;
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        filename = br.readLine();
        System.out.println("Baca File " + filename + "...");
        FileInputStream fis = null;     //nama objeknya fis
        try{
            fis = new FileInputStream(filename);        //utk mengikat filename
        }catch (FileNotFoundException ex){
            System.out.println("File not found.");
        }
        try{
            char data;                               //untuk membacanya
            int temp;                               //hasil dari fis berupa int yaitu bil. bulat
            do {
                temp = fis.read();                  //membaca file nya
                data = (char) temp;                 //hasil yg int itu diubahnjdi character
                if (temp != -1){                    //kalau datanya =-1 maka keluar data tapi jika tidak -1 maka keluar
                    System.out.print(data+" ("+temp+")");
                }
            } while (temp != -1);
        } catch (IOException ex){
            System.out.println("Problem in reading from the file.");
        }
        
        System.out.println("What is the name of to write to?");
        filename = br.readLine();
        System.out.println("Enter data to write to " + filename + "...");
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(filename);
        }catch (FileNotFoundException ex){
            System.out.println("File cannot be opened for writing.");
        }
    } 
}
