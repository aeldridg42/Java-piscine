public class Program {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--count:")) {
            System.err.println("usage: java Program --count:<number>");
            System.exit(-1);
        }
        int count = Integer.parseInt(args[0].substring(8));
        Runner runner = new Runner(count);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.eggRun();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.henRun();
            }
        });
        t1.start();
        t2.start();
    }
}
