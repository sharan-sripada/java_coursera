package course2.week1.oop;

public class CaesarCipher {
    private String alphabet="abcdefghijklmnopqrstuvwxyz";
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher (int key) {
        mainKey = key;
        shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
    }
    public String encrypt (String input) {
        StringBuilder encrypted = new StringBuilder(input);

        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }

        return encrypted.toString();
    }
    public String decrypt (String input) {
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }

}
