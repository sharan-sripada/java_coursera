package course2.week2;

import edu.duke.*;
import java.util.*;

public class MapVersionOfGladLibs{
    private HashMap<String, ArrayList<String>> myMap;
    private int wordcount;
    private ArrayList<String> alreadyList;


    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "src/course2/week2/data";

    public MapVersionOfGladLibs(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        alreadyList = new ArrayList<String>();
        wordcount = 0;
    }

    public MapVersionOfGladLibs(String source){
        initializeFromSource(source);
        myRandom = new Random();
        wordcount = 0;
    }

    private void initializeFromSource(String source) {
        String[] labels = {"country", "noun", "animal",
                "adjective", "name", "color",
                "timeframe", "verb", "fruit"};
        for(String s : labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s,list);
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub=getSubstitute(w.substring(first+1,last));
        if (!alreadyList.contains(sub)){
            alreadyList.add(sub);
        }
        else{
            wordcount += 1;
            return processWord(w);
        }

        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
        System.out.println("\n numbers of words replaced: "+ wordcount);
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }

        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    private int totalWordsInMap(){
        int size = 0;
        for(ArrayList v : myMap.values()){
            size = size + v.size();
        }
        return size;
    }

    public void makeStory(){
        System.out.println("\n");
        alreadyList.clear();
        wordcount=0;
        String story = fromTemplate("src/course2/week2/data/madtemplate2.txt");
        printOut(story, 60);


    }
    public static void main(String[] args) {
        GladLib gl=new GladLib();
        gl.makeStory();
    }


}
