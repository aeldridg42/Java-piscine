import java.util.Random;

public class Program {
	public static int[] array;
	public static int result;
	public static int threadToHandle;
	public static int threadsCount;

	public static void main(String[] args) throws InterruptedException {
		if (args.length != 2 || !args[0].startsWith("--arraySize=")
			|| !args[1].startsWith("--threadsCount="))
			System.exit(exitMessage("usage: java Program --arraySize=<number> --threadsCount=<number>"));
		int arraySize = 0;
		try {
			arraySize = Integer.parseInt(args[0].substring("--arraySize=".length()));
			threadsCount = Integer.parseInt(args[1].substring("--threadsCount=".length()));
			if (arraySize < 0 || arraySize > 2_000_000 || threadsCount < 0 || threadsCount > 2_000_000)
				throw new NumberFormatException();
			if (threadsCount > arraySize)
				throw new Exception();
		} catch (NumberFormatException e) {
			System.exit(exitMessage("Please use numbers between 0 and 2000000"));
		} catch (Exception e) {
			System.exit(exitMessage("Threads count must be less or equal to array size"));
		}

		threadToHandle = arraySize / threadsCount;

		result = 0;

		array = new int[arraySize];
		Random random = new Random();
		int correctResult = 0;
		for (int i = 0; i < arraySize; i++) {
			array[i] = random.nextInt(1000);
			correctResult += array[i];
		}

		System.out.println("Sum: " + correctResult);

		Thread[] threadsArray = new Thread[threadsCount];
		for (int i = 0; i < threadsCount; i++)
			threadsArray[i] = new Thread(new Runner(i));

		for (Thread t : threadsArray)
			t.start();

		for (Thread t : threadsArray)
			t.join();

		System.out.println("Sum by threads: " + result);
	}

	public static Integer exitMessage(String message) {
		System.err.println(message);
		return -1;
	}

	static class Runner implements Runnable {
		private int numberOfThread;

		Runner(int i) {
			numberOfThread = i;
		}

		@Override
		public void run() {
			int sum = 0;
			int start = numberOfThread * threadToHandle;
			int end = start + threadToHandle;
			end = numberOfThread + 1 == threadsCount ? array.length : end;
			for (int j = start; j < end; j++)
				sum += array[j];
			System.out.printf("Thread %d: from %d to %d sum is %d\n",
					numberOfThread + 1, start, end - 1, sum);
			synchronized (this) {
				result += sum;
			}
		}
	}
}
