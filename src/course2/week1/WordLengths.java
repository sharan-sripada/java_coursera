package course2.week1;

import java.lang.*;
import edu.duke.*;

public class WordLengths {
    public static void main(String[] args) {
        WordLengths wl=new WordLengths();
        wl.testCountWordLengths();
    }


    public void countWordLengths(FileResource Resource, int[] counts) {

        for (String word : Resource.words()){
            int wl = word.length();
            for (int i=0; i<word.length();i++){
                char currChar = word.charAt(i);
                if ((i==0) || (i==word.length()-1)){
                    if (!Character.isLetter(currChar)) wl--;
                }
            }
            counts[wl]++;
            System.out.println( word +" "+wl );
        }

    }
    void testCountWordLengths(){
        FileResource Resource = new FileResource();
        int [] counts = new int[31];
        countWordLengths(Resource,counts);
        indexOfMax(counts);
    }

    public void indexOfMax(int[] values) {

        int max = 0;
        int position = 0;
        for (int i = 0; i <values.length;i++)
        {
            if (values[i] > max)
            {
                max = values[i];
                position = i;
            }
        }

        System.out.println(position);

    }


   

}
