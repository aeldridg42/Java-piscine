import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    public static Map<String, String> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        try {
            FillSignatures.fillMap();
        } catch (IOException e) {
            System.err.println("Please add \"signutures.txt\" file");
            System.exit(-1);
        }
        Scanner scanner = new Scanner(System.in);

        String input = "";
        FileOutputStream resultF = new FileOutputStream("result.txt");
        while (!(input = scanner.nextLine()).equals("42")) {
            File file = new File(input);
            if (file.exists() && !file.isDirectory()) {
                FileInputStream fis = new FileInputStream(file);
                String result = "";
                for (int i = 0; i < 8 && fis.available() > 0; i++)
                    result += toHex(fis.read());
                for (Map.Entry<String, String> m : map.entrySet()) {
                    if (m.getValue().equals(result)) {
                        System.out.println("PROCESSED");
                        resultF.write(m.getKey().getBytes(StandardCharsets.UTF_8));
                    }
                }
                System.out.println("UNDEFINED"); // ?
            }
        }
    }

    private static String toHex(Integer value) {
        String result = "";
        int tmp = value / 16;
        if (tmp % 16 > 9)
            result += (char)(55 + tmp % 16);
        else
            result += (char)(48 + tmp % 16);
        if (value % 16 > 9)
            result += (char)(55 + value % 16);
        else
            result += (char)(48 + value % 16);
        return result;
    }
}
