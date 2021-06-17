package course1.week2.stringsfirstassignments;

import java.util.Locale;

public class Part2 {
    public String findSimpleGene(String dna,String startCodon,String stopCodon) {
        dna=dna.toLowerCase();
        int startIndex = dna.indexOf(startCodon);
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);

        if (startIndex == -1 || stopIndex == -1) {
            return "";
        }
        //System.out.println(stopIndex +" "+ startIndex);
        if ((stopIndex - startIndex) % 3 == 0) {

            return  dna.substring(startIndex, stopIndex + 3);
        }

        return "";
    }
    public void testSimpleGene(){
        String startCodon = "atg";
        String stopCodon = "taa";

        System.out.println("Gene in attagtatc:"+findSimpleGene("attagtatc",startCodon,stopCodon));
        System.out.println("Gene in aatgggtagatta:"+findSimpleGene("aatgggtagatta",startCodon,stopCodon));
        System.out.println("Gene in attagtgtaatc:"+findSimpleGene("attagtgtaatc",startCodon,stopCodon));
        System.out.println("Gene in agatgaatggatctaa:"+findSimpleGene("agatgaatggatctaa",startCodon,stopCodon));
        System.out.println("Gene in taaatgaattggtaaatc: "+findSimpleGene("taaatgaattggtaaatc",startCodon,stopCodon));

    }

    public static void main(String[] args) {
        Part2 p2=new Part2();
        p2.testSimpleGene();
    }
}
