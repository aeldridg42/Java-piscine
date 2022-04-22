import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.*;

public class Runner implements Runnable {

	public Runner() {
	}

	@Override
	public void run() {
		String url = Program.queue.poll();
		String threadName = Thread.currentThread().getName();
		int number = Program.map.get(url);
		int threadNumber = Integer.parseInt(threadName.substring(threadName.lastIndexOf("-") + 1));
		Path file = Paths.get("file" + number + url.substring(url.lastIndexOf(".")));
		System.out.printf("Thread-%d start download file number %d\n", threadNumber, number);
		if (Files.exists(file)) {
			System.out.printf("file number %d already exists\n", number);
			return;
		}
		try (InputStream in = URI.create(url).toURL().openStream()) {
			Files.copy(in, file);
		} catch (IOException e) {
			System.err.printf("File number %d: download fail\n", number);
			return;
		}

		System.out.printf("Thread-%d finish download file number %d\n", threadNumber, number);
	}
}
