package ark.coding.tools;

import java.io.*;

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
        createLatLongFileFromReqResponseLog(fileLocation);
    }

    public static void createLatLongFileFromReqResponseLog(String ReqResponseFile){
        try {
            String currentLine;
            StringBuilder testResult = new StringBuilder();
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
                            testResult.append(TimeConverter.millisToDate(miniTokens[1]));//actual value at first index
                            break;
                        }
                    }
                    testResult.append(",");
                    for(int i = 0 ; i < tokens.length; i++){
                        if(tokens[i].contains(LATITUDE_KEY)){
                            String[] miniTokens = tokens[i].split(":");
                            testResult.append(miniTokens[1]);//actual value at first index
                            break;
                        }
                    }
                    testResult.append(",");
                    for(int i = 0 ; i < tokens.length; i++){
                        if(tokens[i].contains(LONGITUDE_KEY)){
                            String[] miniTokens = tokens[i].split(":");
                            testResult.append(miniTokens[1]);//actual value at first index
                            break;
                        }
                    }
                    testResult.append("\n");
                }
            }
            System.out.print(testResult.toString());
            File file = new File("/Users/Akshayraj/Downloads/zipLocationLog-13/LatLong.csv");
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(testResult.toString());
            bw.close();


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
