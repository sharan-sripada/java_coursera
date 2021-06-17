package course1.week2.stringsfirstassignments;

public class Part1 {
    public String findSimpleGene(String dna) {

        int startIndex = dna.indexOf("atg");
        int stopIndex = dna.indexOf("taa", startIndex + 3);

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
        System.out.println("Gene in attagtatc:"+findSimpleGene("attagtatc"));
        System.out.println("Gene in aatgggtagatta:"+findSimpleGene("aatgggtagatta"));
        System.out.println("Gene in attagtgtaatc:"+findSimpleGene("attagtgtaatc"));
        System.out.println("Gene in agatgaatggatctaa:"+findSimpleGene("agatgaatggatctaa"));
        System.out.println("Gene in taaatgaattggtaaatc: "+findSimpleGene("taaatgaattggtaaatc"));

    }

    public static void main(String[] args) {
        Part1 p1=new Part1();
        p1.testSimpleGene();
    }

}
