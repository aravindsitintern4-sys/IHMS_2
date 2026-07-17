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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {

    private static final String filePath = "src/test/resources/DropdownOptions.json";

    // ALWAYS INITIALIZE TO AVOID NULL EXCEPTION
    private static Map<String, List<String>> dropdownMap = new LinkedHashMap<>();
    static {
        loadJson();
    }

    // LOAD JSON IF FILE EXISTS
    private static void loadJson() {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                ObjectMapper mapper = new ObjectMapper();
                // DROPDOWNMAP = { "key(label)" : ["value1","value2"]} JSON FORMAT
                dropdownMap = mapper.readValue(file,
                        new TypeReference<Map<String, List<String>>>() {
                        });
                System.out.println("Dropdown JSON loaded successfully.");
            } else {
                System.out.println("DropdownOptions.json not found. A new file will be created after capture.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            // KEEP EMPTY MAP 
            dropdownMap = new LinkedHashMap<>();
        }
    }

    // WRITE JSON
    public static void writeJson(Map<String, List<String>> dropdownMap) throws IOException {
        File file = new File(filePath);

        // CREATE NEW JSON FILE IF DOESN'T EXSITS
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        // OBJECT MAPPER CONVERTS JAVA OBJECTS INTO JSON and JSON TO JAVA OBJECTS
        ObjectMapper mapper = new ObjectMapper();
        // PRETTY PRINTING IN JSON FILE ---> JSON INDENTATION
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(file, dropdownMap);

        // REFRESH IN MEMORY MAP  ---> COPY OF THE PASSED MAP 
        JsonUtil.dropdownMap = new LinkedHashMap<>(dropdownMap);
        System.out.println("Dropdown JSON saved successfully.");
    }

    // CHECK OPTION EXISTS
    public static boolean containsOption(String label, String option) {
        List<String> options = getOptions(label);
        for (String value : options) {
            if (value.trim().equalsIgnoreCase(option.trim())) {
                return true;
            }
        }
        return false;
    }

    // GET OPTIONS BY LABEL
    public static List<String> getOptions(String label) {
        return dropdownMap.getOrDefault(label, Collections.emptyList());
    }

    // OPTIONAL: RETURN ENTIRE MAP
    public static Map<String, List<String>> getDropdownMap() {
        return dropdownMap;
    }

    // CLEAR THE JSON FILE BEFORE NEXT PATIENT
    public static void clearJson() {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), new HashMap<String, List<String>>());

            dropdownMap.clear();
            System.out.println("Dropdown JSON cleared.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}