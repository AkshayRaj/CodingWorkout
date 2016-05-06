package ark.coding.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akshayraj on 3/9/16.
 */
public class Parser {
    public static String fileLocation = "/Users/Akshayraj/Downloads/zipLocationLog-13/gravyFile__req_resp.txt";
    private static final String LOCATION_ENDPOINT = "/location?";
    private static final String LATITUDE_KEY = "\"latitude\"";
    private static final String LONGITUDE_KEY = "\"longitude\"";
    private static final String TIMESTAMP_KEY = "\"timestamp\"";

    public static void main(String args[]){
        calculateAttendExitAverageTime("/Users/Akshayraj/Documents/zipLocationLogs/zipLocationLog_OldRLTP/gravyFile__req_resp.txt");
        //parseGravyReqResponse(fileLocation);
    }

    public static File parseGravyReqResponse(String ReqResponseFile){
        File file = null;
        try {
            String currentLine;
            StringBuilder parseResult = new StringBuilder();
            FileInputStream fileInputStream  = new FileInputStream(ReqResponseFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((currentLine = bufferedReader.readLine()) != null) {
                if(currentLine.contains(LOCATION_ENDPOINT) && currentLine.contains(LATITUDE_KEY)){
                    //contains latitude; therefore, longitude is also present
                    String[] tokens = currentLine.split(",");

                    for(int i = 0 ; i < tokens.length; i++){
                        if(tokens[i].contains(TIMESTAMP_KEY)){
                            String[] miniTokens = tokens[i].split(":");
                            parseResult.append(miniTokens[1] + ",");
                            parseResult.append(TimeConverter.millisToDate(miniTokens[1]));//actual value at first index
                            break;
                        }
                    }
                    parseResult.append(",");
                    for(int i = 0 ; i < tokens.length; i++){
                        if(tokens[i].contains(LATITUDE_KEY)){
                            String[] miniTokens = tokens[i].split(":");
                            parseResult.append(miniTokens[1]);//actual value at first index
                            break;
                        }
                    }
                    parseResult.append(",");
                    for(int i = 0 ; i < tokens.length; i++){
                        if(tokens[i].contains(LONGITUDE_KEY)){
                            String[] miniTokens = tokens[i].split(":");
                            parseResult.append(miniTokens[1]);//actual value at first index
                            break;
                        }
                    }
                    parseResult.append("\n");
                }
            }
            //write parsed data to File
            file = new File("/Users/Akshayraj/Downloads/zipLocationLog-13/LatLong_"
                    + TimeConverter.millisToDate(String.valueOf(System.currentTimeMillis())) + ".csv");
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(parseResult.toString());
            bufferedWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return file;
    }


    public static void filterProbableOffIntervals(String latLongFile){
        File file = null;
        try {
            String currentLine;
            String previousLine;
            StringBuilder parseResult = new StringBuilder();
            FileInputStream fileInputStream = new FileInputStream(latLongFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            long t2;
            long t1;
            previousLine = bufferedReader.readLine();
            String[] previousTokens = previousLine.split(",");
            while ((currentLine = bufferedReader.readLine()) != null) {
                t1 = Long.parseLong(previousTokens[0]);
                String[] tokens = currentLine.split(",");
                t2 = Long.valueOf(tokens[0]);
                if (t2 - t1 > 10 * 60 * 1000l) {
                    parseResult.append(previousTokens[1])
                            .append(",")
                            .append(tokens[1])
                            .append(",")
                            .append("PROBABLE OFF");
                }
            }

        }catch (Exception exception){

        }
    }

    public static void calculateAttendExitAverageTime(String gravyLogFile){
        try {
            String currentLine;
            List<Long> attendTimeList = new ArrayList<>();
            List<Long> exitTimeList = new ArrayList<>();
            Double averageAttendTime = 0.00;
            Double averageExitTime = 0.00;

            FileInputStream fileInputStream = new FileInputStream(new File(gravyLogFile));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((currentLine = bufferedReader.readLine()) != null){
                String ATTEND = "ATTEND: ";
                if(currentLine.contains(ATTEND)){
                    String[] tokens = currentLine.split("\t");
                    for(int index = 0; index < tokens.length; index++){
                        if(tokens[index].startsWith(ATTEND)) {
                            attendTimeList.add(Long.parseLong(tokens[index].replaceFirst(ATTEND ,"")));
                        }
                    }
                }
                String EXIT = "EXIT";
                if(currentLine.contains(EXIT)){
                    String[] tokens = currentLine.split("\t");
                    for(int index = 0; index < tokens.length; index++){
                        if(tokens[index].startsWith(EXIT)) {
                            exitTimeList.add(Long.parseLong(tokens[index].replaceFirst(EXIT, "")));
                        }
                    }
                }
            }
            for(Long number : attendTimeList){
                averageAttendTime = averageAttendTime + number;
            }
            if(attendTimeList.size() > 0) {
                averageAttendTime = averageAttendTime / attendTimeList.size();
                System.out.println("averageAttendTime: " + averageAttendTime);
            } else {
                System.out.println("averageAttendTime not available");
            }


            for(Long number : exitTimeList){
                averageExitTime = averageExitTime + number;
            }
            if(exitTimeList.size() > 0) {
                averageExitTime = averageExitTime / exitTimeList.size();
                System.out.println("averageExitTime: " + averageExitTime);
            } else {
                System.out.println("averageExitTime not available");
            }
        }catch (Exception exception){

        }
    }


}
