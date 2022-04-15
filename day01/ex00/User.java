public class User {
    private static Integer userCount = 1;
    private Integer id;
    private String name;
    private Integer balance;

    public User(String name, Integer balance) {
        id = userCount++;
        this.name = name;
        this.balance = balance >= 0 ? balance : 0;
    }

    public String getName() { return this.name; }

    private Integer getBalance() { return this.balance; }

    private Integer getId() { return this.id; }

    public void printInfo() {
        System.out.printf("username: %s, id: %d, balance: %d\n", name, id, balance);
    }
}
