package course2.week3;
/**
 * Write a description of class Tester here.
 * 
 * @author (Sharan)
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("src/course2/week3/short-test_log");
        la.printAll();
    }
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("src/course2/week3/short-test_log");
        System.out.println("There are " +la.countUniqueIPs() +" unique IPs");
        la.uniqueIPVisitsOnDay("Sep 30");
        System.out.println("Unique ip's in range 200-299: "+la.countUniqueIPsInRange(200,299));
    }

    public  void countingWebsiteVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("src/course2/week3/short-test_log");
        System.out.println(la.countVisitsPerIP());
        System.out.println(la.iPsForDays());


    }
    public static void main(String[] args) {
        Tester t=new Tester();
        t.testLogAnalyzer();
        t.testUniqueIP();
        t.countingWebsiteVisits();
    }
}
