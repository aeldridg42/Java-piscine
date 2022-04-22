import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Program {
	public static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	public static Map<String, Integer> map = new HashMap<>();

	public static void main(String[] args) {
		int number = 0;

		if (args.length != 1 || !args[0].startsWith("--threadsCount=")) {
			System.exit(exitMessage("usage: java Program --threadsCount=<number>"));
		}

		try {
			number = Integer.parseInt(args[0].substring("--threadsCount=".length()));
		} catch (Exception e) {
			System.exit(exitMessage("usage: java Program --threadsCount=<number>"));
		}

		if (number < 1)
			System.exit(exitMessage("please use positive amount of threads"));

		try {
			FileParser.parse();
		} catch (IOException e) {
			System.exit(exitMessage("File parse error"));
		}

		ExecutorService executorService = Executors.newFixedThreadPool(number);

		while (queue.size() > 0) {
			executorService.submit(new Runner());
		}
		executorService.shutdown();
	}

	private static Integer exitMessage(String message) {
		System.err.println(message);
		return -1;
	}
}
