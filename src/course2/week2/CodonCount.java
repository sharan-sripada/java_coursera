package course2.week2;
import java.util.*;
import edu.duke.*;
public class CodonCount {
    private HashMap<String, Integer> counts;

    public CodonCount() {
        counts = new HashMap<String, Integer>();
    }

    public void buildCodonMap (int start, String dna) {
        counts.clear();

        dna = dna.trim().toUpperCase();

        while (start + 3 <= dna.length()) {
            String codon = dna.substring(start, start + 3);
            if (3 == codon.length()) {
                counts.put(codon, counts.getOrDefault(codon, 0) + 1);
            }
            start += 3;
        }
    }

    public String getMostCommonCodon() {
        String max = "";
        for (String codon : counts.keySet()) {
            if (counts.get(codon) > counts.getOrDefault(max, -1))
                max = codon;
        }
        return max;
    }

    public void printCodonCounts (int start, int end) {
        System.out.println("Counts of codons between " + start + " and " + end);
        for (String codon : counts.keySet()) {
            int count = counts.get(codon);
            if (start <= count && count <= end) {
                System.out.println(codon + ": " + count);
            }
        }
        System.out.println();
    }
    public void tester() {

        FileResource DNA = new FileResource("src/course2/week2/data/smalldna.txt");
        String dna = DNA.asString();
        int start = 1;
        int end = 5;

        buildCodonMap(0, dna);
        System.out.println("Reading frame starting with 0 results in "+counts.size()+" unique codons"+"\t");
        String count = getMostCommonCodon();
        System.out.println("and most common codon is "+count+" with count "+counts.get(count)+"\t");
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
        printCodonCounts(start, end);

        buildCodonMap(1, dna);
        System.out.println("Reading frame starting with 1 results in "+counts.size()+" unique codons"+"\t");
        count = getMostCommonCodon();
        System.out.println("and most common codon is "+count+" with count "+counts.get(count)+"\t");
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
        printCodonCounts(start, end);

        buildCodonMap(2, dna);
        System.out.println("Reading frame starting with 2 results in "+counts.size()+" unique codons"+"\t");
        count = getMostCommonCodon();
        System.out.println("and most common codon is "+count+" with count "+counts.get(count)+"\t");
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
        printCodonCounts(start, end);
    }

    public static void main(String[] args) {
        CodonCount cc=new CodonCount();
        cc.tester();
    }
}
