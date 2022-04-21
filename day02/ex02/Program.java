import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class Program {
	private static String currentPath;

	public static void main(String[] args) {
		if (args.length != 1 || !args[0].startsWith("--current-folder=")) {
			System.err.println("usage: Program --current-folder=FOLDERNAME");
			return;
		}
		currentPath = args[0].substring("--current-folder=".length());
		Path path = Paths.get(currentPath);
		if (!path.isAbsolute() || Files.notExists(path) || !Files.isDirectory(path)) {
			System.err.println("Wrong current folder");
			return;
		}
		System.out.println(currentPath);
		Scanner s = new Scanner(System.in);
		String command = "";
		while (!(command = s.nextLine()).equalsIgnoreCase("exit")) {
			if (!currentPath.endsWith("/"))
				currentPath += "/";
			String[] strings = command.split(" ");
			switch (strings[0]) {
				case "ls":
					functionLS(currentPath);
					break;
				case "mv":
					functionMV(strings);
					break;
				case "cd":
					functionCD(strings);
					break;
				default:
					System.err.println("you can use <ls>, <cd> or <mv> only");
			}
		}
	}

	private static void functionLS(String currentPath) {
		try (DirectoryStream<Path> entries = Files.newDirectoryStream(Paths.get(currentPath))) {
			for (Path path1 : entries) {
				if (!Files.isHidden(path1)) {
					System.out.print(path1.getFileName() + " ");
					System.out.println(Files.size(path1) + " KB");
				}
			}
		} catch (IOException e) {
			System.err.println("don't have access to this folder");
			System.exit(-1);
		}
	}

	private static void functionMV(String[] strings) {
		if (strings.length != 3) {
			System.err.println("usage: mv thingToMove destination");
			return;
		}
		Path toMove = Paths.get(strings[1]);
		if (toMove.isAbsolute()) {
			if (Files.notExists(toMove)) {
				System.err.println("mv: " + strings[1] + ": no such file or directory");
				return;
			}
		}
		else {
			if (Files.notExists(Paths.get(currentPath + strings[1]))) {
				System.err.println("mv: " + strings[1] + ": no such file or directory");
				return;
			}
			toMove = Paths.get(currentPath + strings[1]);
		}
		Path whereTo = Paths.get(currentPath + strings[2]);
		if (Files.exists(whereTo) && Files.isDirectory(whereTo) && whereTo.isAbsolute())
			whereTo = Paths.get(currentPath + strings[2] + "/" + toMove.getFileName());
		else if (Paths.get(strings[2]).isAbsolute())
			whereTo = Paths.get(strings[2] + "/" + toMove.getFileName());
		else
			whereTo = Paths.get(currentPath + "/" + strings[2]);
		try {
			Files.move(toMove, whereTo, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.err.println("mv: Permission denied");
		}
	}

	private static void functionCD(String[] strings) {
		if (strings.length != 2) {
			System.err.println("cd: usage: cd destination");
			return;
		}
		if (Paths.get(strings[1]).isAbsolute() && Files.isDirectory(Paths.get(strings[1]))
				&& Files.isReadable(Paths.get(strings[1])))
			currentPath = strings[1];
		else {
			if (Files.exists(Paths.get(currentPath + strings[1])) &&
					Files.isDirectory(Paths.get(currentPath + strings[1])) &&
					Files.isReadable(Paths.get(currentPath + strings[1])))
				currentPath += strings[1];
			else {
				System.err.println("cd: wrong directory: " + strings[1]);
				return;
			}
		}
		currentPath = Paths.get(currentPath).normalize().toString();
		System.out.println(currentPath);
	}
}
