package startingthreads;

class AnotherRunner implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println(Thread.currentThread().getName() + " : " + i);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}

public class ImplementRunnable {
  public static void main(String[] args) {
    Thread t1 = new Thread(new AnotherRunner());
    Thread t2 = new Thread(new AnotherRunner());

    t1.start();
    t2.start();
  }
}


