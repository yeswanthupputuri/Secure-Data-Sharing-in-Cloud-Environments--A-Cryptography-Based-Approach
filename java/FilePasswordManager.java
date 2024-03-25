import java.util.HashMap;
import java.util.Map;

public class FilePasswordManager {
    private static final Map<String, String> filePasswordMap = new HashMap<String,String>();

    public static void addFilePassword(String filename, String password) {
        filePasswordMap.put(filename, password);
        
    }

    public static String getPassword(String filename) {
        return filePasswordMap.get(filename);
    }

    public static boolean isPasswordCorrect(String filename, String password) {
        return password.equals(filePasswordMap.get(filename));
    }

    // Utility method to check if the file exists in the manager
    public static boolean containsFile(String filename) {
        return filePasswordMap.containsKey(filename);
    }
    public static void main(String[] args){
    	System.out.println(filePasswordMap);
    }
}
