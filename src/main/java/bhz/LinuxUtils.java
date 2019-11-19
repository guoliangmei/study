package bhz;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
public class LinuxUtils {
    public static void main(String[] args) {
        String cmd = "sort -t,  -k2n,2  -k1,1   aa";
        LinuxUtils.exec(cmd);
        List<String> commands = new ArrayList<>();
        commands.add("cd /home/whj/tmp/hello");
        commands.add("sort -t,  -k2n,2  -k1,1   aa");
        commands.add("sort -t,  -k2n,2  -k1,1   aa > vv");
        LinuxUtils.execFlow(commands);
    }

    public static Object execFlow(List<String> commands) {
        try {
            String cmds = "";
            for (String cmd : commands) {
                cmds += cmd + ";";
            }
            String[] cmdA = {"/bin/sh", "-c", cmds};
            Process process = Runtime.getRuntime().exec(cmdA);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(
                    process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object exec(String cmd) {
        try {
            String[] cmdA = {"/bin/sh", "-c", cmd};
            Process process = Runtime.getRuntime().exec(cmdA);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(
                    process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
