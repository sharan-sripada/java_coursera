package course2.week2;
import edu.duke.*;

import java.io.File;
import java.util.*;
public class WordsInFile {
    private HashMap<String, ArrayList<String>> words;
    public WordsInFile() {
        words = new HashMap<String, ArrayList<String>>();
    }
    private void addWordsFromFile (File f) {
        FileResource fr = new FileResource(f);
        String filename = f.getName();
        for (String word : fr.words()) {
            if (words.containsKey(word)) {
                ArrayList<String> fileList = words.get(word);
                if (! fileList.contains(filename)) {
                    fileList.add(filename);
                    words.put(word, fileList);
                }
            } else {
                ArrayList<String> fileList = new ArrayList<String>();
                fileList.add(filename);
                words.put(word, fileList);
            }
        }
    }

    public void buildWordFileMap() {
        words.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    public int maxNumber() {
        if (words.isEmpty()) return -1;
        int max = 0;
        for (String word : words.keySet()) {
            int size = words.get(word).size();
            if (size > max) max = size;
        }
        return max;
    }
    public ArrayList<String> wordsInNumFiles (int number) {
        ArrayList<String> list = new ArrayList<String>();

        if (number > 0) {
            for (String word : words.keySet()) {
                if (words.get(word).size() == number) {
                    list.add(word);
                }
            }
        }

        return list;
    }
    public void printFilesIn (String word) {
            System.out.print( word + " appears in:");
            for (String filename : words.get(word))
                System.out.print("  " + filename);

        System.out.println();

    }
    public void tester(){
        buildWordFileMap();
        int maximum = maxNumber();
        ArrayList<String> TestList = wordsInNumFiles(maximum);
        System.out.println("The greatest number of files a word appears in is "+maximum +" and there are "+TestList.size()+" such words: ");
        for (int k =0;k< TestList.size(); k++)
        {
            System.out.print(TestList.get(k)+"  ");
        }
        System.out.println();

        for (int k =0;k <TestList.size();k++){
            //System.out.println(TestList.get(k)+" appears in the files: ");
            printFilesIn(TestList.get(k));
        }

    }

    public static void main(String[] args) {
        WordsInFile wf=new WordsInFile();
        wf.tester();
    }

}
