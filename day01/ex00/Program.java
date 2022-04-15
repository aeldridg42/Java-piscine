public class Program {
    public static void main(String[] args) {
        User jimmy = new User("Jimmy", 3000);
        User kim = new User("Kim", 2700);
        User mike = new User("Mike", 4900);
        User nacho = new User("Nacho", -120);

        jimmy.printInfo();
        kim.printInfo();
        mike.printInfo();
        nacho.printInfo();

        System.out.println();

        Transaction transaction1 = new Transaction(kim, jimmy, Category.DEBITS, -2000);
        Transaction transaction2 = new Transaction(kim, jimmy, Category.CREDITS, 1000);
        Transaction transaction3 = new Transaction(mike, nacho, Category.CREDITS, -100);
        Transaction transaction4 = new Transaction(mike, nacho, Category.DEBITS, 100);

        transaction1.printInfo();
        transaction2.printInfo();
        transaction3.printInfo();
        transaction4.printInfo();
    }
}
