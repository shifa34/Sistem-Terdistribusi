class SharedData1 {
	int data;
	boolean valueSet = false;
	synchronized void set(int value) {
		if (valueSet) { //baru saja membangkitkan sebuah nilai
			try {
				wait();
			} catch (InterruptedException ie) {
			}
		}
		System.out.println("Generate " + value);
		data = value;
		valueSet = true;
		notify();
	}
	synchronized int get() {
		if (!valueSet) { //produsen belum men-set sebuah nilai 
			try {
				wait();
			} catch (InterruptedException ie) {
			}
		}
		System.out.println("Get " + data);
		valueSet = false;
		notify();
		return data;
	}
}

class Producer implements Runnable {
	SharedData1 sd;
	Producer(SharedData1 sd) {
		this.sd = sd;
		new Thread(this, "Producer").start();
	}
	public void run() {
		for (int i = 0; i < 10; i++) {
			sd.set((int)(Math.random()*100));
		}
	}
}

class Consumer implements Runnable {
	SharedData1 sd;
	Consumer(SharedData1 sd) {
		this.sd = sd;
		new Thread(this, "Consumer").start();
	}
	public void run() {
		for (int i = 0; i < 10 ; i++) {
			sd.get();
		}
	}
}

class TestProducerConsumer {
	public static void main(String args[]) throws Exception {
		SharedData1 sd = new SharedData1();
		new Producer(sd);
		new Consumer(sd);
	}
}

		