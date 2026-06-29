package utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

// JAVE TO JSON COVERSION ---> OBJECT MAPPER
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static void writeJson(
            Map<String, List<String>> dropdownMap)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        
        //  PRETTY PRINTER USED TO FORMATTING THE JSON, DOESN'T CHANGE THE DATA , ONLY FORMAT WILL CHANGE 
        mapper.writerWithDefaultPrettyPrinter()
              .writeValue(new File("src/test/resources/DropdownOptions.json"),dropdownMap);
    }
}