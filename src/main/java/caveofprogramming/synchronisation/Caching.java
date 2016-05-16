package caveofprogramming.synchronisation;

import java.util.Scanner;

class Processor extends Thread {

  private volatile boolean running = true;

  @Override
  public void run() {
    while (running) {
      System.out.println("Hello");
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void shutdown() {
    running = false;
  }
}

public class Caching {

  public static void main(String[] args) {
    Processor proc1 = new Processor();
    proc1.start();

    // Pause execution of main thread until return key is hit in console
    System.out.println("Press return to stop ...");
    final Scanner scanner = new Scanner(System.in);
    scanner.nextLine();

    proc1.shutdown();

  }

}
