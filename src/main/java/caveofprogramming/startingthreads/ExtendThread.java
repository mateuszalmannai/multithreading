package caveofprogramming.startingthreads;


class Runner extends Thread {
  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("Hello " + i);

      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}


public class ExtendThread {
  public static void main(String[] args) {
    final Runner runner1 = new Runner();
    runner1.start();

    final Runner runner2 = new Runner();
    runner2.start();
  }
}
