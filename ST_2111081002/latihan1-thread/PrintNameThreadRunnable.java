class PrintNameThreadRunnable implements Runnable {
	Thread thread;
	PrintNameThreadRunnable(String name) {
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
		PrintNameThreadRunnable pnt1 = new PrintNameThreadRunnable("A");
		PrintNameThreadRunnable pnt2 = new PrintNameThreadRunnable("B");
		PrintNameThreadRunnable pnt3 = new PrintNameThreadRunnable("C");
		PrintNameThreadRunnable pnt4 = new PrintNameThreadRunnable("D");
		System.out.println("Running threads...");
		try {
			pnt1.thread.join();
			pnt2.thread.join();
			pnt3.thread.join();
			pnt4.thread.join();
		} catch (InterruptedException ie) {
		}
		System.out.println("Threads killed."); //dicetak terakhir
	}
}