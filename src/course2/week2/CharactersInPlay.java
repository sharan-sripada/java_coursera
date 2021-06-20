package course2.week2;
import java.util.*;
import edu.duke.*;
public class CharactersInPlay {
    private ArrayList<String> character_name=new ArrayList<String>();
    private ArrayList<Integer> count = new ArrayList<Integer>();

    public void update(String person){
        int index = character_name.indexOf(person);
        if (index == -1) {
            character_name.add(person);
            count.add(1);
        }
        else {
            int freq = count.get(index);
            count.set(index,freq+1);
        }
    }
    public void findAllCharacters() {
        character_name.clear();
        count.clear();

        FileResource Resource = new FileResource("src/course2/week2/data/macbethSmall.txt");

        for (String line: Resource.lines()){
            if (line.contains(".")) {

                int idx = line.indexOf(".");
                String possible_name = line.substring(0,idx);
                update(possible_name);

            }
        }
    }
    public void charactersWithNumParts(int num1, int num2) {
        for (int i =0; i < count.size();i++) {

            if (count.get(i) >= num1 && count.get(i)<= num2) {

                System.out.println( character_name.get(i) +"  "+count.get(i));

            }

        }

    }

    public static void main(String[] args) {
        CharactersInPlay test=new CharactersInPlay();
        test.tester();
    }
    public void tester() {
        findAllCharacters();

        for (int i=0; i < count.size();i++) {
            if (count.get(i) > 1) {
                System.out.println( character_name.get(i) +"  "+count.get(i));
            }
        }

        int num1 = 1;
        int num2 = 3;
        charactersWithNumParts(num1, num2);

    }



}
