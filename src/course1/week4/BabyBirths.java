package course1.week4;

import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class BabyBirths {
	public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}

	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
			}
			else {
				totalGirls += numBorn;
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("female girls = " + totalGirls);
		System.out.println("male boys = " + totalBoys);
	}

	public void testTotalBirths () {
		//FileResource fr = new FileResource();
		FileResource fr = new FileResource("/home/sharas/IdeaProjects/java_coursera/src/course1/week4/data/yob2014.csv");
		totalBirths(fr);
	}
	public  static  void main(String[] args){
		BabyBirths bb=new BabyBirths();
		bb.testTotalBirths();
		String name = "Mason";
		String gender = "M";
		int year=2012;
		System.out.println("Mason" + " rank in 2012 is " + bb.getRank(2012,name,gender));
		System.out.println("Name of  rank  2 in 2012 is " + bb.getName(2012,2,gender));
		bb.whatIsNameInYear("Isabella", 2012, 2014, "F");

		System.out.println("Average rank for " + name + " is " + bb.getAverageRank(name, gender));
		System.out.println("Total births higher than " + name + " is " + bb.getTotalBirthsRankedHigher(year, name, gender));
		System.out.println("Mason"+ " most popular year is " + bb.yearOfHighestRank(name,gender));
	}
	public int getRank(int year,String name,String gender){
		FileResource fr=new FileResource("/home/sharas/IdeaProjects/java_coursera/src/course1/week4/data/yob"+year+".csv");
		int rank=0;
		for(CSVRecord record:fr.getCSVParser()){
			String recordGender=record.get(1);
			if(recordGender.equals(gender)){
				rank++;
				if(record.get(0).equals(name)){
					return rank;
				}
			}
		}
		return -1;
	}
	String getName(int year,int rank,String gender){
		FileResource fr=new FileResource("/home/sharas/IdeaProjects/java_coursera/src/course1/week4/data/yob"+year+".csv");

		for(CSVRecord record:fr.getCSVParser()){
			String recordGender=record.get(1);
			if(recordGender.equals(gender)){
				rank--;
				if(rank==0){
					return record.get(0);
				}
			}
		}
		return "NO NAME";
	}
	void whatIsNameInYear(String name,int year,int newYear,String gender){
		//FileResource fr1=new FileResource("/home/sharas/IdeaProjects/java_coursera/src/course1/week4/data/yob"+year+".csv");
		int rank=getRank(year,name,gender);
		//fr2=new FileResource("/home/sharas/IdeaProjects/java_coursera/src/course1/week4/data/yob"+newYear+".csv");
		System.out.println(getName(year,rank,gender)+" born in "+year+" would be "+getName(newYear,rank,gender)+" in "+newYear);
	}
	int yearOfHighestRank(String name,String gender){
		int rank=Integer.MAX_VALUE;
		int year=Integer.MIN_VALUE;
		DirectoryResource dr=new DirectoryResource();
		for(File f:dr.selectedFiles()){
			int currYear=Integer.parseInt(f.getName().substring(3,7));
			int currRank=getRank(currYear,name,gender);
			//System.out.println(currRank);
			if(currRank!=-1&&currRank<rank){
				rank=currRank;
				year=currYear;
			}
		}
		return  year==Integer.MIN_VALUE?-1:year;
	}
	double getAverageRank(String name ,String gender){
		int toatalRank=0;
		int count=0;
		DirectoryResource dr=new DirectoryResource();
		for(File f:dr.selectedFiles()){
			int currYear=Integer.parseInt(f.getName().substring(3,7));
			int currRank=getRank(currYear,name,gender);
			if(currRank!=-1){
				toatalRank+=currRank;
				count++;
			}
		}
		return  count==0?-1.0:(double)toatalRank/count;

	}
	int getTotalBirthsRankedHigher(int year ,String name,String gender )
	{
		FileResource fr=new FileResource("/home/sharas/IdeaProjects/java_coursera/src/course1/week4/data/yob"+year+".csv");
		int totalBirths=0;
		//int rank=(getRank(year,name,gender));
		for(CSVRecord record:fr.getCSVParser()){
			if(record.get(1).equals(gender)){
				if(record.get(0).equals(name)){
					return totalBirths;
				}
				totalBirths+=Integer.parseInt(record.get(2));
			}
		}
		return totalBirths;

	}

}
