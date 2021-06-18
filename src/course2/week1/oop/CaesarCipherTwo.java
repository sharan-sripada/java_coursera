package course2.week1.oop;

public class CaesarCipherTwo {
    private int key1;
    private int key2;
    private String alphabet="abcdefghijklmnopqrstuvwxyz";;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    public CaesarCipherTwo(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
        shiftedAlphabet1 = alphabet.substring(key1)+alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2)+alphabet.substring(0,key2);
    }


    public String encryptUpdated(String input) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);


        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);


                int idx = alphabet.indexOf(currChar);

                if (idx !=-1){
                    char newChar =i%2==0? shiftedAlphabet1.charAt(idx):shiftedAlphabet2.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }


            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    public String decrypt(String encrypted)
    {

        CaesarCipherTwo cc = new CaesarCipherTwo(26 - key1,26 - key2);
        String decrypted = cc.encryptUpdated(encrypted);
        return decrypted;

    }

}
