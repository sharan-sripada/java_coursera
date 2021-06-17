package course1.week2.stringsfirstassignments;

public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int firstIndex = stringb.indexOf(stringa);
        int secondIndex = stringb.indexOf(stringa,firstIndex+1);

        if (firstIndex != secondIndex && firstIndex != -1 && secondIndex !=-1) {
            return true;
        } else {
            return false;
        }
    }

    public String lastPart(String stringa, String stringb) {
        int pos = stringb.indexOf(stringa);

        if (pos == -1) {
            return stringb;
        } else {
            return stringb.substring(pos + stringa.length());
        }
    }

    public void test() {
        String stringa;
        String stringb;

        System.out.println("twoOccurences:");
        stringa = "by";
        stringb = "A story by Abby Long";
        System.out.println(stringa + " appears at least twice in " + stringb + " = " + twoOccurrences(stringa, stringb));

        stringa = "a";
        stringb = "banana";
        System.out.println(stringa + " appears at least twice in " + stringb + " = " + twoOccurrences(stringa, stringb));

        stringa = "atg";
        stringb = "ctgtatgta";
        System.out.println(stringa + " appears at least twice in " + stringb + " = " + twoOccurrences(stringa, stringb));

        System.out.println("\nlastPart:");

        stringa = "an";
        stringb = "banana";
        System.out.println("The string after " + stringa + " in " + stringb + " is :" + lastPart(stringa, stringb));

        stringa = "zoo";
        stringb = "forest";
        System.out.println("The string after " + stringa + " in " + stringb + " is :" + lastPart(stringa, stringb));
    }
    public static void main(String[] args) {
        Part3 p3=new Part3();
        p3.test();
    }
}
