package caveofprogramming.synchronisation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

  private Random random = new Random();


  // It's good practice to declare separate lock objects
  private Object lock1 = new Object();
  private Object lock2 = new Object();

  private List<Integer> list1 = new ArrayList<>();
  private List<Integer> list2 = new ArrayList<>();

  public void stageOne() {

    synchronized (lock1) {
      try {
        // Slow the program down a bit to simulate it getting some information from somewhere
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      list1.add(random.nextInt());
    }
  }

  public void stageTwo() {

    synchronized (lock2) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      list2.add(random.nextInt());
    }

  }

  public void process() {
    for (int i = 0; i < 1000; i++) {
      stageOne();
      stageTwo();
    }
  }

  public void main() {
    System.out.println("Starting ...");
    final long start = System.currentTimeMillis();

    Thread t1 = new Thread(() -> {
      process();
    });

    t1.start();

    Thread t2 = new Thread(() -> {
      process();
    });

    t2.start();

    // wait for thread to finish before we can expect meaningful results about processing
    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    final long end = System.currentTimeMillis();
    System.out.println("Time taken: " + (end - start) + "ms");
    System.out.println("List1: " + list1.size());
    System.out.println("List2: " + list2.size());
  }
}
