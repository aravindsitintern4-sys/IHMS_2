// package utils;

// import java.io.File;
// import java.io.IOException;
// import java.util.List;
// import java.util.Map;

// // JAVE TO JSON COVERSION ---> OBJECT MAPPER
// import com.fasterxml.jackson.databind.ObjectMapper;

// public class JsonUtil {

//     public static void writeJson(
//             Map<String, List<String>> dropdownMap)
//             throws IOException {

//         ObjectMapper mapper = new ObjectMapper();
        
//         //  PRETTY PRINTER USED TO FORMATTING THE JSON, DOESN'T CHANGE THE DATA , ONLY FORMAT WILL CHANGE 
//         mapper.writerWithDefaultPrettyPrinter()
//               .writeValue(new File("src/test/resources/DropdownOptions.json"),dropdownMap);
//     }
// }




package utils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {

    private static final String FILE_PATH = "src/test/resources/DropdownOptions.json";

    private static Map<String, List<String>> dropdownMap;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            dropdownMap = mapper.readValue(
                    new File(FILE_PATH),
                    new TypeReference<Map<String, List<String>>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // WRITE JSON
    public static void writeJson(Map<String, List<String>> dropdownMap) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        mapper.writeValue(new File(FILE_PATH), dropdownMap);

        // REFRESH IN MEMORY MAP AFTER WRITING 
        JsonUtil.dropdownMap = dropdownMap;
    }

    public static boolean containsOption(String label, String option) {
        List<String> options = getOptions(label);
        for (String value : options) {
            if (value.trim().equalsIgnoreCase(option.trim())) {
                return true;
            }
        }
        return false;
    }

    // READ JSON
    public static List<String> getOptions(String label) {
        return dropdownMap.getOrDefault(label, Collections.emptyList());
    }
}
