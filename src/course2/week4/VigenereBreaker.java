package course2.week4;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker();
        for ( int i=0; i<klength; i++){
            String slice = sliceString(encrypted, i, klength);
            int newKey = cc.getKey(slice);
            key[i] = newKey;
        }
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String textToStr = fr.asString();
        int[] key = tryKeyLength(textToStr,5,'e');
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.decrypt(textToStr));
    }

    public static void main(String[] args) {
        VigenereBreaker vb=new VigenereBreaker();
        vb.breakVigenere();
    }
    
}
