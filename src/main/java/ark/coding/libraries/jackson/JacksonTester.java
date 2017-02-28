package ark.coding.libraries.jackson;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class JacksonTester {

    public void parse(String json)  {
        JsonFactory factory = new JsonFactory();

        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(json);
        } catch (IOException ioExcpetion) {
            BISLog.e(TAG, "Error reading Json string", ioExcpetion);
            return;
        }

        Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {

            Map.Entry<String,JsonNode> field = fieldsIterator.next();
            System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
        }
    }
}
