package course2.week1;

import edu.duke.*;
import java.io.*;
import java.lang.*;

public class CaesarBreaker {

    public int[] CountLetters(String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k < message.length();k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alphabet.indexOf(ch);
            if(dex!=-1) {
                counts[dex]++;
            }

        }
        return counts;
    }
    public int maxIndex(int[] values) {

        int max = 0;
        int index = 0;
        for(int i = 0;i<values.length;i++) {
            if (values[i]> max) {
                max = values[i];
                index=i;
            }

        }
        return index;
    }

    public String decrypt(String encrypted, int Key) {
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptUpdated(encrypted, 26 - Key);
        return decrypted;

    }
    public void testDecrypt() {
        int k = 23;
        FileResource fr = new FileResource();
        String message = fr.asString();
        //System.out.println( k+" "+decrypt(message,k));

    }
    public String halfOfString(String message, int start){
        StringBuilder result = new StringBuilder();
        for (int i = start; i< message.length();i+=2) {
            result.append(message.charAt(i));
        }
        return result.toString();
    }
    public int getKey(String s) {
        int[] freq = CountLetters(s);
        int index = maxIndex(freq);
        int dKey = index - 4;
        if (dKey < 0) {
            dKey += 26 ;
        }
        return dKey;

    }


    public String decryptTwoKeys(String encrypted){

        CaesarCipher cc = new CaesarCipher();
        String s1= halfOfString(encrypted,0);
        String s2 = halfOfString(encrypted,1);
        StringBuilder decrypt = new StringBuilder(encrypted);
        int key1= getKey(s1);
        int key2= getKey(s2);

        String encrypt1=cc.encryptUpdated(s1,(26-key1));
        String encrypt2=cc.encryptUpdated(s2,(26-key2));
        //System.out.println(key1+" "+key2);
        //System.out.println(encrypt1+"\n "+encrypt2);

        for (int i=0; i<encrypt1.length();i++) {
            decrypt.setCharAt((2*i), encrypt1.charAt(i) );
        }

        for (int i=0; i<encrypt2.length();i++) {
            decrypt.setCharAt((2*i+1), encrypt2.charAt(i) );
        }


        //System.out.println( decrypt.toString());
        return decrypt.toString();


    }


    public static void main(String[] args) {
        CaesarBreaker cb=new CaesarBreaker();
        cb.decryptTwoKeysTest();
        //cb.testDecrypt();
    }




    public void decryptTwoKeysTest() {

        FileResource resource = new FileResource("src/course2/week1/data/wordsLotsOfEsEncrypted.txt");
        String message = resource.asString();
        String decrypted_message = decryptTwoKeys(message);
        System.out.println(message);
        System.out.println(decrypted_message);
    }

}
