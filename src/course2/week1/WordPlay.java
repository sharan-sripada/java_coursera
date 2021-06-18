package course2.week1;

public class WordPlay {
    public boolean isVowel(char ch) {
     if (ch == 'a' || ch =='e' || ch =='i' || ch =='o'|| ch =='u' || ch == 'y')
         return true;
     else if (ch == 'A' || ch =='E' || ch =='I' || ch =='O'|| ch =='U' || ch =='Y')
         return true;
     else
         return false;

    }
    public String replaceVowels(String phrase, char ch) {

        StringBuilder string = new StringBuilder(phrase);

        for (int i = 0; i < string.length();i++) {

            char currChar = string.charAt(i);
            if (isVowel(currChar))
            {
                string.setCharAt(i, ch);
            }
        }
        return string.toString();
    }
    public String emphasize(String phrase, char ch ) {
        StringBuilder emphasize = new StringBuilder(phrase);
        for (int i = 0; i < emphasize.length(); i++) {
            char currChar = emphasize.charAt(i);
            if ((currChar == ch) && (i % 2 == 0)) {
                emphasize.setCharAt(i, '*');
            } else if ((currChar == ch) && (i % 2 != 0)) {
                emphasize.setCharAt(i, '+');
            }
        }
        return emphasize.toString();
    }
    public static  void main(String[] args){
        WordPlay wp =new WordPlay();
        System.out.println("a is a vowel:"+wp.isVowel('a'));
        System.out.println("F is a vowel:"+wp.isVowel('F'));
        System.out.println("replaceVowels in Hello World  with ‘*’ :"+wp.replaceVowels("Hello World",'*'));
        System.out.println(wp.emphasize("dna ctgaaactga", 'a'));
    }
}
