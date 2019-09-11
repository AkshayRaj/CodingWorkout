/**
 * Created by Akshayraj
 */
package ark.coding.interviews.statementcampus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatementCampus {

    private static final String TEST_STRING = "23511011501782351112179911801562340161171141148";
    private static final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args){
        try {
            String[] titles = getMovieTitles("Spiderman");
//            Arrays.sort(titles);
        for(int i = 0; i < titles.length ; i++){
            System.out.println("TITLES-" + i + " :" + titles[i]);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Movie {

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            this.Title = title;
        }

        @JsonProperty
        String Title = "";

        @JsonIgnore
        String Poster;

        @JsonIgnore
        String Type;

        @JsonIgnore
        String Year;

        @JsonIgnore
        String imdbID;

        public Movie(){
            Title = new String("");
        }

        public Movie(String Poster, String Title, String Type, String Year, String imdbID){
            this.Title = Title;
        }
    }

    static String[] getMovieTitles(String substr) throws IOException {
        String url = "https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr;

        URL obj = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        httpURLConnection.setRequestMethod("GET");

        //add request header
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = httpURLConnection.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();

        //print result
        System.out.println(response.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseNode = objectMapper.readTree(response.toString());

        int currentPage = responseNode.get("page").asInt();
        int per_page = responseNode.get("per_page").asInt();
        int total = responseNode.get("total").asInt();
        int total_pages = responseNode.get("total_pages").asInt();
        JsonNode dataNode = responseNode.get("data");
        String[] titles = new String[total];
        if (dataNode.isArray()) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Movie.class);
            List<Movie> myObjects = mapper.readValue(dataNode.toString(), collectionType);
            int titleIndex = 0;
            for (Movie myClass : myObjects) {
                titles[titleIndex] = myClass.getTitle();
                System.out.println(titles[titleIndex]);
                titleIndex++;
            }
        }

        for(int page = 2; page <= total_pages; page++){
            String pageUrl = "https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr + "&page=" + page;
            obj = new URL(pageUrl);
            httpURLConnection = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            httpURLConnection.setRequestMethod("GET");

            //add request header
            httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

            responseCode = httpURLConnection.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            inputLine = "";
            response = new StringBuffer();

            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();

            //print result
            System.out.println(response.toString());


            objectMapper = new ObjectMapper();
            responseNode = objectMapper.readTree(response.toString());
            currentPage = responseNode.get("page").asInt();
            dataNode = responseNode.get("data");
            if (dataNode.isArray()) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Movie.class);
                List<Movie> myObjects = mapper.readValue(dataNode.toString(), collectionType);
                int titleIndex = ((page-1)*per_page);
                for (Movie myClass : myObjects) {
                    titles[titleIndex] = myClass.getTitle();
                    System.out.println(titles[titleIndex]);
                    titleIndex++;
                }
            }

        }

        Arrays.sort(titles);
        return titles;
    }






















    /*

        String decodeString = decode(TEST_STRING);
        System.out.println("-----------OUTPUT--------------");
        System.out.println(decodeString);
     */


    static String decode(String encoded) {
        int length = encoded.length();
        String forwardString = "";
        for(int index = length-1; index >=0 ; index --){
            forwardString = forwardString + encoded.charAt(index);
        }
        System.out.println(forwardString);
        String[] decodedASCII = new String[length];
        int decodedIndex = 0;
        for(int index = 0; index <= length-1; index++){
            if(forwardString.charAt(index) == '6' || forwardString.charAt(index) == '7' ||
                    forwardString.charAt(index) == '8' ||forwardString.charAt(index) == '9' ||
                    forwardString.charAt(index) == '3'){
               decodedASCII[decodedIndex] = forwardString.substring(index, index+2);
                System.out.println(decodedASCII[decodedIndex]);
                index ++;
                decodedIndex++;
            } else if(forwardString.charAt(index) == '1'){
                decodedASCII[decodedIndex] = forwardString.substring(index, index+3);
                System.out.println(decodedASCII[decodedIndex]);
                index++;
                index++;
                decodedIndex++;
            }
        }

        String decodedString = "";
        for(int index = 0; index <= length-1; index++){
            if (decodedASCII[index] != null) {
                System.out.println(decodedASCII[index]);
                int i = Integer.valueOf(decodedASCII[index]);
                decodedString = decodedString + Character.toString((char) i);
            }
        }
        return decodedString;
    }
}
