package course1.week2.stringsthirdassignments;

import edu.duke.StorageResource;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int index = -1;
        while(true) {
            index = dna.indexOf(stopCodon, startIndex + 3);
            if (index == -1 || (index - startIndex) % 3 == 0) {
                break;
            }
            startIndex += 3;
        }
        if (index != -1) {
            return index;
        } else {
            return dna.length();
        }
    }
    public void testFindStopCodon() {
        String dna = "ATGGTAATCTAAATCGATTAAGG";

        int index = findStopCodon(dna, 0, "TAA");
        System.out.println("Index = " + index);

        index = findStopCodon(dna, 4, "TAA");
        System.out.println("Index = " + index);

        index = findStopCodon(dna, 0, "TAG");
        System.out.println("Index = " + index);
    }

    public void testFindGene() {
        String dna = "GTTAATGTAGCTTAAACCTTTAAAGCAAGGCACTGAAAATGCCTAGATGA";
        System.out.println("Gene: " + findGene(dna, 0));

        dna = "GTGAGCTCACTCCATAGACACAAAGGTTTGGTCCTGGCCTTCTTATTAGT";
        System.out.println("Gene: " + findGene(dna, 0));

        dna = "TTTCAGTGAGCTTACACATGCAAGTATCCGCGCGCCAGTGAAAATGCCCT";
        System.out.println("Gene: " + findGene(dna, 0));

    }
    public void printAllGenes(String dna) {
        int start = 0;
        while (true) {
            String gene = findGene(dna, start);
            if (gene.isEmpty()) {
                break;
            }
            System.out.println("Gene: " + gene);
            start = dna.indexOf(gene, start) + gene.length();
        }
    }
    public static void main(String[] args) {
     Part1 test = new Part1();
        test.testGetAllGenes();
    }
    public String findGene(String dna, int start) {
        final String START_CODON = "ATG";
        int startIndex = dna.indexOf(START_CODON, start);

        if (startIndex == -1) {
            return "";
        }

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));

        if (minIndex == dna.length()) {
            return "";
        } else {
            return dna.substring(startIndex, minIndex + 3);
        }
    }

    public StorageResource getAllGenes(String dna) {
        int start = 0;
        StorageResource genes = new StorageResource();
        while (true) {
            String gene = findGene(dna, start);
            if (gene.isEmpty()) {
                break;
            }
            genes.add(gene);
            start = dna.indexOf(gene, start) + gene.length();
        }

        return genes;
    }

    public void testGetAllGenes() {
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        StorageResource geneList = getAllGenes(dna);

        System.out.println("AllGenes in " + dna);
        for (String gene : geneList.data()) {
            System.out.println(gene);
        }

    }
}
