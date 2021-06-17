package course1.week2.stringsfirstassignments;
import  edu.duke.URLResource;
public class Part4 {
    public void findLinks(String url) {
        URLResource urlResource = new URLResource(url);

        for (String line : urlResource.lines()) {
            int index = line.toLowerCase().indexOf("youtube.com");
            //System.out.println(line);
            if (index != -1) {

                int startIndex = line.lastIndexOf("\"", index);
                int lastIndex = line.indexOf("\"", index);

                System.out.println("youtube link: " + line.substring(startIndex + 1, lastIndex));
            }
        }
    }

    public void test() {
        String url = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        findLinks(url);
    }
    public static void main(String[] args) {
        Part4 p4=new Part4();
        p4.test();
    }

}

