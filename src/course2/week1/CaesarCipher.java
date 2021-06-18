package course2.week1;

import edu.duke.*;
import java.io.*;

public class CaesarCipher {
    public String encrypt (String input, int key) {
        // Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder (input);
        // Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+ alphabet.substring(0,key);

        // Count from 0 to < length of encrypted,(call it i)
        for ( int i = 0; i < encrypted.length();i++){
            // Look at the ith character of encrypted ( call it currchar)
            char currChar = encrypted.charAt(i);
            // Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            // If currChar is in the alphabet
            if (idx !=-1){
                // Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                // Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i,newChar);
            }
            //otherwise : do nothing
        }
        // Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    public String encryptUpdated(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        String uAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lAlphabet = "abcdefghijklmnopqrstuvwxyz";
        
        String shiftedUAlphabet = uAlphabet.substring(key)+
        uAlphabet.substring(0,key);
        String shiftedLAlphabet = lAlphabet.substring(key)+
                lAlphabet.substring(0,key);

        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (Character.isLowerCase(currChar)) {

                int idx = lAlphabet.indexOf(currChar);

                if (idx !=-1){
                    char newChar = shiftedLAlphabet.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }

            }

            else {
                int idx = uAlphabet.indexOf(currChar);
                if (idx !=-1){
                    char newChar = shiftedUAlphabet.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }

    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
        System.out.println( encryptUpdated("First Legion", 23));
        System.out.println( encryptUpdated("First Legion", 17));

        System.out.println(encryptTwoKeys("First Legion", 23, 17) );
    }
    public static void main(String[] args){
        CaesarCipher cc=new CaesarCipher();
        cc.testCaesar();
    }
    public String encryptTwoKeys (String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String uAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lAlphabet = "abcdefghijklmnopqrstuvwxyz";

        String shiftedUAlphabet1 = uAlphabet.substring(key1)+
                uAlphabet.substring(0,key1);
        String shiftedLAlphabet1 = lAlphabet.substring(key1)+
                lAlphabet.substring(0,key1);

        String shiftedUAlphabet2 = uAlphabet.substring(key2)+
                uAlphabet.substring(0,key2);
        String shiftedLAlphabet2 = lAlphabet.substring(key2)+
                lAlphabet.substring(0,key2);

        for (int i = 0; i < encrypted.length(); i++) {
            int l=i%2==0?0:1;
            char currChar = encrypted.charAt(i);
            if (Character.isLowerCase(currChar)) {

                int idx = lAlphabet.indexOf(currChar);

                if (idx !=-1){
                    char newChar = l==0?shiftedLAlphabet1.charAt(idx):shiftedLAlphabet2.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }

            }

            else {
                int idx = uAlphabet.indexOf(currChar);
                if (idx !=-1){
                    char newChar = l==0?shiftedUAlphabet1.charAt(idx):shiftedUAlphabet2.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }
            }
        }

        return encrypted.toString();
    }

}

