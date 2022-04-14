import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long weeksStorage = 0;
        int weekOrder = 0;
        String string;

        while (!(string = scanner.nextLine()).equals("42")) {
            if (!string.equals("Week " + ++weekOrder))
                System.exit(illegalArgument());
            long grades = 0;
            for (int i = 0; i < 5; i++)
                grades += scanner.nextInt();
            scanner.nextLine();
            grades /= 5;
            for (int i = 0; i < weekOrder - 1; i++)
                grades *= 10;
            weeksStorage += grades;
        }

        long tmp = weeksStorage;

        for (int i = 1; tmp > 0; tmp /= 10, i++)
            showStat(countStat(weeksStorage, (int) (tmp % 10)), i);

    }

    private static void showStat(int count, int week) {
        System.out.print("Week " + week + " ");
        for (int i = -2; i < count; i++)
            System.out.print("=");
        System.out.println(">");
    }

    private static int countStat(long storage, int value) {
        int count = 0;
        while (storage > 0) {
            if (storage % 10 < value)
                count++;
            storage /= 10;
        }
        return count;
    }

    private static int illegalArgument() {
        System.err.println("Illegal Argument");
        return -1;
    }
}