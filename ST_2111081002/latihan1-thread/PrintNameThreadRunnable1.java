class PrintNameThreadRunnable1 implements Runnable {
	Thread thread;
	PrintNameThreadRunnable1(String name) {
		thread = new Thread(this, name);
		thread.start();
	}
	public void run() {
		String name = thread.getName();
		for (int i = 0; i < 100; i++) {
			System.out.print(name);
		}
	}
}

class TestThread {
	public static void main(String args[]) {
		new PrintNameThreadRunnable1("A");
		new PrintNameThreadRunnable1("B"); 
		new PrintNameThreadRunnable1("C");
		new PrintNameThreadRunnable1("D");
	}
}
