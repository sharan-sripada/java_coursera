package course1.week2.stringsthirdassignments;

public class Part2 {
    public double cgRatio(String dna) {
        int occurrences = 0;

        for (int i = 0; i < dna.length(); i++) {
            if (dna.toUpperCase().charAt(i) == 'C' || dna.toUpperCase().charAt(i) == 'G') {
                occurrences++;
            }
        }

        return (double) occurrences / dna.length();
    }

    public int countCTG(String dna) {
        int totalOccurences = 0;
        int start = 0;

        while(true) {
            int index = dna.toUpperCase().indexOf("CTG", start);

            if (index == -1) {
                break;
            }

            totalOccurences++;

            start = index + 3;
        }

        return totalOccurences;
    }
    public static void main(String[] args){
        Part2  p2=new Part2();
        System.out.println(p2.cgRatio("ATGCCATAG"));
        System.out.println(p2.countCTG("ATGCTGCCACTGTAG"));
    }
}
