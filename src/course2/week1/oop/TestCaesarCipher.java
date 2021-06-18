package course2.week1.oop;
import edu.duke.*;
public class TestCaesarCipher {
    public static void main(String[] args) {
        TestCaesarCipher tcc=new TestCaesarCipher();
        tcc.simpleTests();
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
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(message);
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
        String breakCaesarCipher = breakCaesarCipher(encrypted);
        System.out.println(breakCaesarCipher);
    }
    public String breakCaesarCipher(String input) {

        int [] freqs = CountLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (dkey < 0)
        { dkey += 26 ;}
        CaesarCipher cc = new CaesarCipher(dkey);

        String message = cc.decrypt(input);
        return message;
    }

}
