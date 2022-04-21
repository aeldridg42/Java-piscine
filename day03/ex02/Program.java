import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program {
    public static int[] array;
    public static int result;
    public static int threadToHandle;

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2 && !args[0].startsWith("--arraySize=")
            && !args[1].startsWith("--threadsCount=")) {
            System.err.println("usage: java Program --arraySize=<number> --threadsCount=<number>");
            System.exit(-1);
        }
        int arraySize = Integer.parseInt(args[0].substring("--arraySize=".length()));
        int threads = Integer.parseInt(args[1].substring("--threadsCount=".length()));

        threadToHandle = (int)Math.ceil(arraySize / (float)threads);

        result = 0;

        array = new int[arraySize];
        Random random = new Random();
        int correctResult = 0;
        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(10000);
            correctResult += array[i];
        }

        System.out.println("Sum: " + correctResult);

        Thread[] threadsArray = new Thread[threads];
        for (int i = 0; i < threads; i++)
            threadsArray[i] = new Thread(new Runner(i));

        for (Thread t : threadsArray)
            t.start();


        for (Thread t : threadsArray)
            t.join();

        System.out.println("Sum by threads: " + result);
    }

    static class Runner implements Runnable {
        private int i;

        Runner(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            int sum = 0;
            int start = i * threadToHandle;
            for (int j = start; j < array.length && j < start + 5; j++)
                sum += array[j];
            System.out.printf("Thread %d: from %d to %d sum is %d\n", i + 1, start,
                    start + 4 < array.length ? start + 4 : array.length - 1, sum);
            synchronized (this) {
                result += sum;
            }
        }
    }
}
