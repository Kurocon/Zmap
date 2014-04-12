import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kevin on 4/12/14.
 */
public class check_zmap {
    public static void main(String[] args){
        // Tail storage file.
        check_zmap obj = new check_zmap();
        String inFile = "/data/zmap/result";
        String command = "tail " + inFile;
        String output = obj.executeCommand(command);

        // Get last 4 chars
        String chars = "";
        int exitcode = 0;
        if(output.length() >= 4) {
            chars = output.substring(output.length() - 4);
            chars = stringToIp(chars);
            exitcode = 0;
        }else{
            chars = "CRITICAL - File empty.";
            exitcode = 2;
        }

        System.out.println(chars);
        System.exit(exitcode);
    }

    static String stringToIp(String s){
        return (""+
                (int) s.charAt(0) + '.' +
                (int) s.charAt(1) + '.' +
                (int) s.charAt(2) + '.' +
                (int) s.charAt(3)
        );
    }

    private String executeCommand(String command) {
        StringBuffer output = new StringBuffer();
        try {
            Process process = Runtime.getRuntime().exec(command);
            try (InputStream inputStream = process.getInputStream()) {
                int c;
                while ((c = inputStream.read()) != -1) {
                    output.append((char) c);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Zmap.class.getName())
                    .log(Level.SEVERE, null, ex);
        }


        return output.toString();

    }
}
