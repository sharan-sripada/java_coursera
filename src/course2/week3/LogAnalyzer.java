package course2.week3;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (Sharan)
 * @version (19-06-21)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();

     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr=new FileResource(filename);
         for(String line :fr.lines()){

             records.add(WebLogParser.parseEntry(line));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
    public int countUniqueIPs() {
        ArrayList<String> uniqueIP = new ArrayList<String>();
        for(LogEntry le: records) {
            String ip = le.getIpAddress();
            if(!uniqueIP.contains(ip)) {
                uniqueIP.add(ip);
            }
        }
        return uniqueIP.size();
    }
    public void printAllHigherThanNum(int Num) {

        for(LogEntry le: records) {
            int statusCode = le.getStatusCode();
            if(statusCode > Num) {
                System.out.println("StatusCode greater than "+Num+": "+le);
            }
        }

    }
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> myIp = new ArrayList<String>();
        String myString = null;
        for(LogEntry le: records) {
            Date d = le.getAccessTime();
            String ip = le.getIpAddress();
            myString = d.toString();
            if ((myString.contains(someday)) && (!myIp.contains(ip))) {
                myIp.add(ip);
            }
        }

        for(int i =0; i < myIp.size();i++){
            System.out.println(myIp.get(i)+"  ");
        }
        System.out.println("Total: "+myIp.size());
        return myIp;
    }
    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIP = new ArrayList<String>();
        for(LogEntry New: records) {
            int statusCode = New.getStatusCode();
            String ip = New.getIpAddress();
            if((statusCode >= low) && (statusCode <= high)) {
                if(!uniqueIP.contains(ip)) {
                    uniqueIP.add(ip);
                }
            }
        }
        return uniqueIP.size();
    }

    public HashMap<String,Integer> countVisitsPerIP() {
        HashMap<String,Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le: records) {
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip,1);
            }
            else {
                counts.put(ip,counts.get(ip) + 1);
            }
        }
        return counts;
    }
    public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
        int max = 0;
        for (String s: counts.keySet()){
            int currentNum = counts.get(s);
            if (currentNum > max) {
                max = currentNum;
            }
        }
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> addressNumberTime){
        ArrayList<String> maxIp = new ArrayList<String>();
        int max;
        max = mostNumberVisitsByIP(addressNumberTime);
        for (String s: addressNumberTime.keySet()) {
            if (addressNumberTime.get(s) == max) {
                maxIp.add(s);
            }
        }

        return maxIp;
    }
    public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>> dayIpThatDay = new HashMap<String,ArrayList<String>>();
        ArrayList<String> myIPs = new ArrayList<String>();
        String date = null;
        for (LogEntry le: records) {
            Date d = le.getAccessTime();
            String ip = le.getIpAddress();
            date = d.toString();
            myIPs = uniqueIPVisitsOnDay(date);
            dayIpThatDay.put(date,myIPs);
        }
        return dayIpThatDay;
    }
    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> dayIPs){
        String date="";
        HashMap<Integer,String> days=new HashMap<>();
        int max= 0;
        for (String s : dayIPs.keySet()) {
            ArrayList<String> list=dayIPs.get(s);
            if(max<list.size()){
                max=list.size();
            }
        }

        for(String s:dayIPs.keySet()){
            if(max==dayIPs.get(s).size()){
                System.out.print(s+" ");
                date=s;
            }
        }
        System.out.println();
        return date;
    }
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> dayandIPs, String aDay){
        ArrayList<String> mostIPs = new ArrayList<String>();
        mostIPs = uniqueIPVisitsOnDay(aDay);
        HashMap<String,Integer> counts = new HashMap<String,Integer>();

        for (int k=0;k<mostIPs.size();k++) {
            String s = mostIPs.get(k) ;
            if (!counts.containsKey(s)) {
                counts.put(s,1);
            }
            else {
                int freq = counts.get(s);
                counts.put(s,freq+1);
            }
        }
        return iPsMostVisits(counts);
    }





}
