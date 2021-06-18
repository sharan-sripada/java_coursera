package course2.week1.oop;
import  edu.duke.*;
public class TestCaesarCipherTwo {
    public static void main(String[] args) {
        TestCaesarCipherTwo test=new TestCaesarCipherTwo();
        test.simpleTests();
    }
    public String halfOfString(String message, int start){
        StringBuilder result = new StringBuilder();
        for (int i = start; i< message.length();i+=2) {
            result.append(message.charAt(i));
        }
        return result.toString();
    }
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
    public void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
    CaesarCipherTwo cc2 = new CaesarCipherTwo(21,8);
    String encrypted = cc2.encryptUpdated(message);
    System.out.println(encrypted);
    String decrypted = cc2.decrypt(encrypted);
    System.out.println(decrypted);
        String breakCaesarCipher = breakCaesarCipher(message);
        System.out.println(breakCaesarCipher);
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
    private String breakCaesarCipher(String input) {

        String s1 = halfOfString(input,0);
        String s2 = halfOfString(input,1);
        

        int key1= getKey(s1);
        int key2= getKey(s2);

        CaesarCipherTwo cc = new CaesarCipherTwo(key1,key2);

        String decryption = cc.decrypt(input);

        
        return decryption;
    }

}



