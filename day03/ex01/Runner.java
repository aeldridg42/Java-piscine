public class Runner {
	private int count;
	private boolean flag;

	public Runner(int count) {
		this.count = count;
	}

	public synchronized void eggRun() {
		for (int i = 0; i < count; ++i) {
			while (flag) {
				try {
					wait();
				} catch (InterruptedException e) { e.printStackTrace(); }
			}
			System.out.println("Egg");
			flag = true;
			notify();
		}
	}

	public synchronized void henRun() {
		for (int i = 0; i < count; ++i) {
			while (!flag) {
				try {
					wait();
				} catch (InterruptedException e) { e.printStackTrace(); }
			}
			System.out.println("Hen");
			flag = false;
			notify();
		}
	}
}
