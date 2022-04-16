public class Program {
	public static void main(String[] args) {
		boolean mode = false;
		if (args.length == 1 && args[0].equals("--profile=dev"))
			mode = true;
		Menu menu = new Menu(mode);
		menu.launch();
	}
}
