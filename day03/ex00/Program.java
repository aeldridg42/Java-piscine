public class Program {
    public static void main(String[] args) {
        if (args.length != 1 && !args[0].startsWith("--count=")) {
            System.err.println("usage: java Program --count=<number>");
            System.exit(-1);
        }

        int count = 0;
        try {
            count = Integer.parseInt(args[0].substring(8));
        } catch (NumberFormatException e) {
            System.err.println("usage: java Program --count=<number>");
            System.exit(-1);
        }

        Thread Egg = new Thread(new Runner("Egg", count));
        Thread Hen = new Thread(new Runner("Hen", count));

        Egg.start();
        Hen.start();

        for (int i = 0; i < count; i++)
            System.out.println("Human");
        
        try {
            Egg.join();
            Hen.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
