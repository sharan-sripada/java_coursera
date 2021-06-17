package course1.week2.stringssecondassignments;

public class Part2 {
    int howMany(String stra,String strb){
        int occurrences=0,index=0;
        while(true){
            index=strb.indexOf(stra,index);
            if(index==-1) break;
            occurrences++;
            index+=stra.length();
        }
        return  occurrences;

    }
    public void testHowMany() {
        String stringa = "GAA";
        String stringb = "ATGAACGAATTGAATC";
        System.out.println("occurences of " + stringa + " in " + stringb + " = " + howMany(stringa, stringb));

        stringa = "AA";
        stringb = "ATAAAA";
        System.out.println("occurences of " + stringa + " in " + stringb + " = " + howMany(stringa, stringb));
    }
    public static void main(String[] args) {
        Part2 p2 = new Part2();
        p2.testHowMany();
    }
}
