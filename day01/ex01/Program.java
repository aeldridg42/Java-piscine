public class Program {

	public static void main(String[] args) {
		UserIdsGenerator uig1 = UserIdsGenerator.getInstance();
		UserIdsGenerator uig2 = UserIdsGenerator.getInstance();

		System.out.println(uig1 == uig2);
		System.out.println(uig1.generateId() + " " + uig2.generateId());

		User jimmy = new User("Jimmy", 3000);
		User kim = new User("Kim", 2700);
		User mike = new User("Mike", 4900);
		User nacho = new User("Nacho", -120);

		jimmy.printInfo();
		kim.printInfo();
		mike.printInfo();
		nacho.printInfo();
	}
}
