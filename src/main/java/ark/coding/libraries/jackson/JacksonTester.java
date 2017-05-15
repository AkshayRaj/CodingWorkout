package ark.coding.libraries.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.*;
import java.util.*;

public class JacksonTester {

    @JsonProperty
    HashMap<String, BISAppProcess> mBISAppProcesses = new HashMap<>();

    public static void main(String args[]){
        try {

            InputStream is = new FileInputStream("/Users/Akshayraj/IdeaProjects/CodingWorkout/src/main/java/ark/coding/libraries/jackson/file.json");
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            String jsonString = sb.toString();

            JacksonTester jacksonTester = new JacksonTester();
            jacksonTester.parseJsonUsingJackson(jsonString);

            jacksonTester.javaToJson();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void javaToJson(){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(mBISAppProcesses);
            System.out.println("\n\njavaToJson: " + json);
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
    }

    /**
     * Parses a json string and loads into corresponding BISAppProcess Objects
     * @param json
     */
    private void parseJsonUsingJackson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(json);
        } catch (IOException ioException) {
            System.out.println("Error reading Json string" + ioException.getMessage());
            return;
        }


        ObjectMapper objectMapper = new ObjectMapper();
        mBISAppProcesses = objectMapper.readValue(json, HashMap.class);


//
//        Iterator<Map.Entry<String,JsonNode>> procIterator = rootNode.fields();
//        while (procIterator.hasNext()) {
//
//            Map.Entry<String,JsonNode> field = procIterator.next();
//            String processName = field.getKey();
//
//            System.out.println(processName);
//
//            BISAppProcess bisAppProcess = new BISAppProcess(processName);
//
//            JsonNode procData = field.getValue();
//
//
//
//            JsonNode r_procTimeStampsArray = procData.get("r");
//            if(r_procTimeStampsArray != null) {
//                String jsonArray = r_procTimeStampsArray.toString();
//
//                if (r_procTimeStampsArray.isArray()) {
//                    try {
//                        ObjectMapper mapper1 = new ObjectMapper();
//                        CollectionType collectionType = mapper1.getTypeFactory().constructCollectionType(ArrayList.class, BISAppProcess.ProcTimeStamp.class);
//                        bisAppProcess.r = mapper1.readValue(jsonArray, collectionType);
//                    } catch (IOException ioException) {
//                        ioException.printStackTrace();
//                    }
//                }
//
//                if (!bisAppProcess.r.isEmpty()) {
//                    for(BISAppProcess.ProcTimeStamp procTimeStamp : bisAppProcess.r) {
//                        System.out.println("b: " + procTimeStamp.b);
//                        System.out.println("e: " + procTimeStamp.e);
//                    }
//                }
//            }
//
//            mBISAppProcesses.put(processName, bisAppProcess);
//        }
    }

    /**
     * Data structure to contain info about a BISProcess
     * This Data structure contains the processName and array of start and end times of the process
     */
    static class BISAppProcess{
        String iProcessName;

        @JsonProperty
        ArrayList<ProcTimeStamp> r;
        ArrayList<ProcTimeStamp> f;

        BISAppProcess(String processName){
            iProcessName = processName;
            r = new ArrayList<>();
        }


        static class ProcTimeStamp {

            @JsonProperty
            long b = -1;
            @JsonProperty
            long e = -1;

            //need default constructor for jackson
            public ProcTimeStamp(){

            }

            public ProcTimeStamp(long b, long e){
                this.b = b;
                this.e = e;
            }
        }
    }






}
