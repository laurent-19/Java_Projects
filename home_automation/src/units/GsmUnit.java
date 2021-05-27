package units;

import java.io.FileWriter;
import java.io.IOException;

public class GsmUnit {

    protected void callOwner() throws IOException {
        System.out.println("Call owner!");
        write(filePath,"Call owner!");
    }
    String filePath = "C:\\Development\\Java Projects\\home_automation\\src\\home\\system_logs.txt";

    private static void write(String filePath, String msg) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write(msg);
        writer.close();
    }
}
