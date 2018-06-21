package objects;

import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EUtil {

    public static Object[] combineArrays(Object[] array1, Object[] array2) {
        List<Object> outArray = new ArrayList<>();

        for(Object o1 : array1) {
            outArray.add(o1);
        }

        for(Object o2 : array2) {
            outArray.add(o2);
        }

        return outArray.toArray();
    }

    public static Object[] combineArrays(Object[] array1, Object[] array2, Object[] array3) {
        List<Object> outArray = new ArrayList<>();

        for(Object o1 : array1) {
            outArray.add(o1);
        }

        for(Object o2 : array2) {
            outArray.add(o2);
        }

        for(Object o3 : array3) {
            outArray.add(o3);
        }

        return outArray.toArray();
    }

    public static Logger getLogger() {
        return Logger.getLogger("AndrariCharacterCreator");
    }

    public static void createFile(File file, String content) throws IOException {
        if(file.exists()) return;

        file.getParentFile().mkdirs();
        file.createNewFile(); // Make new file

        StringWriter stringWriter = new StringWriter();
        stringWriter.write(content); // Write content in string writer

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(stringWriter.toString()); // Transfer content from string writer to file writer

        stringWriter.flush();
        fileWriter.flush();

        stringWriter.close();
        fileWriter.close();
    }

    public static Object getJSONSafe(JSONObject jsonObject, String key) {
        if(jsonObject.has(key)) return jsonObject.get(key);
        return null;
    }

    public static String readTextFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        br.close();
        return sb.toString();
    }
}
