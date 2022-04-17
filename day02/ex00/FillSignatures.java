import java.io.FileInputStream;
import java.io.IOException;

public class FillSignatures {
    public static void fillMap() throws IOException {
        FileInputStream fis = new FileInputStream("/home/home/IdeaProjects/d02ex00/src/signatures.txt");
        if (fis.available() < 0)
            System.exit(-1);
        byte[] text = new byte[fis.available()];
        fis.read(text);
        String result = "";
        for (int i = 0; i < text.length; i++)
            if (text[i] != 32)
                result += (char)text[i];
        String[] strings = result.split("\n");
        for (String s : strings)
            Program.map.put(s.substring(0, s.indexOf(",")), s.substring(s.indexOf(",") + 1));
    }
}
