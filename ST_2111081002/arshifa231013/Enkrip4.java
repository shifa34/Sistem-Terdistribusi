/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arshifa231013;

/**
 *
 * @author WIN - 10
 */
public class Enkrip4 {
    private static String encrypt(String plainText, String secretKey){
        StringBuilder encryptedString = new StringBuilder();
        int encryptedInt;
        for (int i = 0; i < plainText.length(); i++) {
            int plainTextInt = (int) (plainText.charAt(i)-'A');
            int secretKeyInt = (int) (secretKey.charAt(i)-'A');
            encryptedInt = (plainTextInt + secretKeyInt) % 26;
            encryptedString.append((char) ((encryptedInt) + (int) 'A'));
        }
        return encryptedString.toString();
    }
    
    private static String decrypt(String decryptedText, String secretKey) {
        StringBuilder decryptedString = new StringBuilder();
        int decryptedInt;
        for (int i = 0; i < decryptedText.length(); i ++) {
            int decryptedTextInt = (int) (decryptedText.charAt(i)-'A');
            int secretKeyInt = (int) (secretKey.charAt(i)-'A');
            decryptedInt = decryptedTextInt - secretKeyInt;
            if (decryptedInt < 0) {
                decryptedInt += 26;
            }
            decryptedString.append((char) ((decryptedInt) + (int) 'A'));
        }
        return decryptedString.toString();
    }
}
