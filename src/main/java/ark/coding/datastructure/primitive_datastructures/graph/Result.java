import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'roadsAndLibraries' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n - cities
     *  2. INTEGER c_lib
     *  3. INTEGER c_road
     *  4. 2D_INTEGER_ARRAY cities
     */
     
     static Map<Integer, Boolean> visited;

    public static long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities) {
    // Write your code here
        int cost = 0;
        
        // if cost of building library is less than or equal to building road, build library in each city
        if (c_lib <= c_road) {
            cost = n * c_lib;
            return cost;
        }
        
        // cost of building library is more than building roads
        //1. Identify disconnected subgraphs
        //2. Each island will get a library and set of roads
        //   Cost of building island in each library is
        //   - 1 library and k-1 roads, where k is no of roads
        Set<Integer> cityNodes = getCities(n);
        Set<Set<Integer>> subgraphs = getSubgraphs(cityNodes, cities);
        
        for(Set<Integer> subgraph : subgraphs) {
            int cityCount = 0;
            for (Integer city : subgraph) {
                cityCount++;
            }
            cost = cost + libraryCost(cityCount, c_lib, c_road);
        }
        
        return cost;
    }
    
    /**
     *   Get the set of disconnected subgraphs from the given graph
    **/
    static Set<Set<Integer>> getSubgraphs(
            Set<Integer> cityNodes, 
            List<List<Integer>> cities) {
        Set<Set<Integer>> subgraphs = new HashSet<>();
        
        //Create Adjacency List from Edge List
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
        for (Integer city : cityNodes) {
            Set<Integer> adjacentCities = new HashSet<>();
            for(List<Integer> edge : cities) {
                // Find adjacent city and add it to the adjacency set
                if(edge.get(0) == city)
                adjacentCities.add(edge.get(1));
                if(edge.get(1) == city)
                adjacentCities.add(edge.get(0));
            }
            adjacencyList.put(city, adjacentCities);
        }
        
        for (Integer city : cityNodes) {
            if (!visited.get(city)) {
                Set<Integer> subgraph = new HashSet<>();
                dfs(city, adjacencyList, subgraph);
                subgraphs.add(subgraph);
            }
        }
        
        return subgraphs;
    }
    
    static void dfs(Integer city, 
        Map<Integer, Set<Integer>> adjacencyList, 
        Set<Integer> subgraph) {
        if (visited.get(city)) {
            return;
        }
        
        visited.put(city, true);
        subgraph.add(city);
        for(Integer adjacentCity : adjacencyList.get(city)) {
            dfs(adjacentCity, adjacencyList, subgraph);
        }
    }
    
    static int libraryCost(int cityCount, int c_lib, int c_road) {
        return c_lib + ((cityCount-1) * c_road);
    }
    
    static Set<Integer> getCities(int n) {
        Set<Integer> cityNodes = new HashSet<>();
        visited = new HashMap<>();
        for(int i = 1; i <= n; i++) {
            cityNodes.add(i);
            visited.put(i, false);   
        }
        
        return cityNodes;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                int c_lib = Integer.parseInt(firstMultipleInput[2]);

                int c_road = Integer.parseInt(firstMultipleInput[3]);

                List<List<Integer>> cities = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        cities.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                long result = Result.roadsAndLibraries(n, c_lib, c_road, cities);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
