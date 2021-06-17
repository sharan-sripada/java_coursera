package course1.week3;
import edu.duke.*;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class ParsingWeatherData {
    private static String TEMP_COLUMN= "TemperatureF";
    private static String DATE_COLUMN = "DateUTC";
    private static String HUMIDITY_COLUMN = "Humidity";

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestRecord = null;

        for (CSVRecord record : parser) {
            double temp = Double.parseDouble(record.get(TEMP_COLUMN));

            if (coldestRecord == null && temp != -9999) {
                coldestRecord = record;
            } else {
                double coldestTemp = Double.parseDouble(coldestRecord.get(TEMP_COLUMN));

                if (temp < coldestTemp && temp != -9999) {
                    coldestRecord = record;
                }
            }
        }

        return coldestRecord;
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temp was " + coldestRecord.get(TEMP_COLUMN) + " at " + coldestRecord.get(DATE_COLUMN));
    }

    public File fileWithColdestTemp() {
        DirectoryResource dr = new DirectoryResource();
        File file = null;
        CSVRecord coldestRecord = null;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRecord = coldestHourInFile(fr.getCSVParser());

            if (coldestRecord == null) {
                coldestRecord = currentRecord;
                file = f;
            } else {
                double coldesttemp = Double.parseDouble(coldestRecord.get(TEMP_COLUMN));
                double currenttemp = Double.parseDouble(currentRecord.get(TEMP_COLUMN));

                if (currenttemp < coldesttemp) {
                    coldestRecord = currentRecord;
                    file = f;
                }
            }
        }

        return file;
    }

    public void printAllRecordsInFile(CSVParser parser) {
        for (CSVRecord record : parser) {
            System.out.println(record.get(DATE_COLUMN) + " " + record.get(TEMP_COLUMN));
        }
    }

    public void testFileWithColdestTemp() {
        File file = fileWithColdestTemp();
        System.out.println("Coldest day was in file " + file.getName());

        FileResource fr = new FileResource(file);
        String coldesttemp = coldestHourInFile(fr.getCSVParser()).get(TEMP_COLUMN);
        System.out.println("Coldest temp on that day was " + coldesttemp);

        System.out.println("All the temps on the coldest day were:");
        printAllRecordsInFile(fr.getCSVParser());
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidityRecord = null;

        for (CSVRecord record : parser) {
            String humidityStr = record.get(HUMIDITY_COLUMN);

            if (!humidityStr.equals("N/A")) {
                int humidity = Integer.parseInt(humidityStr);

                if (lowestHumidityRecord == null) {
                    lowestHumidityRecord = record;
                } else {
                    int lowestHumidity = Integer.parseInt(lowestHumidityRecord.get(HUMIDITY_COLUMN));

                    if (humidity < lowestHumidity) {
                        lowestHumidityRecord = record;
                    }
                }
            }
        }

        return lowestHumidityRecord;
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumidityRecord = lowestHumidityInFile(parser);

        System.out.println("Lowest Humidity was " + lowestHumidityRecord.get(HUMIDITY_COLUMN) + " at " + lowestHumidityRecord.get(DATE_COLUMN));
    }


    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidityRecord = null;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRecord = lowestHumidityInFile(fr.getCSVParser());

            if (lowestHumidityRecord == null) {
                lowestHumidityRecord = currentRecord;
            } else {
                int lowestHumidity = Integer.parseInt(lowestHumidityRecord.get(HUMIDITY_COLUMN));
                int currentHumidity = Integer.parseInt(currentRecord.get(HUMIDITY_COLUMN));

                if (currentHumidity < lowestHumidity) {
                    lowestHumidityRecord = currentRecord;
                }
            }
        }

        return lowestHumidityRecord;
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidityRecord = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestHumidityRecord.get(HUMIDITY_COLUMN) + " at " + lowestHumidityRecord.get(DATE_COLUMN));
    }


    public double averageTempInFile(CSVParser parser) {
        double totalTemp = 0;
        int count = 0;

        for (CSVRecord record : parser) {
            double currentTemp= Double.parseDouble(record.get(TEMP_COLUMN));

            if (currentTemp!= -9999) {
                totalTemp += currentTemp;
                count++;
            }

        }

        return totalTemp/ count;
    }


    public void testAverageTempInFile() {
        FileResource fr = new FileResource();
        double averagetemp = averageTempInFile(fr.getCSVParser());
        System.out.println("Average temp in file is " + averagetemp);
    }

    public double averageTempWithHighHumidityInFile(CSVParser parser, int value) {
        double totalTemp = 0;
        int recordCount = 0;

        for (CSVRecord record : parser) {
            int currentHumidity = Integer.parseInt(record.get(HUMIDITY_COLUMN));

            if (currentHumidity >= value) {
                double currentTemp = Double.parseDouble(record.get(TEMP_COLUMN));

                if (currentTemp != -9999) {
                    totalTemp += currentTemp;
                    recordCount++;
                }
            }
        }

        if (recordCount == 0) {
            return Double.NEGATIVE_INFINITY;
        } else {
            return totalTemp / recordCount;
        }
    }

    public void testAverageTempWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double averagetemp = averageTempWithHighHumidityInFile(fr.getCSVParser(), 80);

        if (averagetemp == Double.NEGATIVE_INFINITY) {
            System.out.println("No temp with that humidity");
        } else {
            System.out.println("Average temp when high humidity is " + averagetemp);
        }
    }
    public static void main(String[] args){
        ParsingWeatherData test=new ParsingWeatherData();
        test.testAverageTempInFile();
        test.testAverageTempWithHighHumidityInFile();
        test.testColdestHourInFile();
        test.testLowestHumidityInManyFiles();
        test.testFileWithColdestTemp();
    }
}
