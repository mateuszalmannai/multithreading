package caveofprogramming.callableandfuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class App {
  public static void main(String[] args) throws InterruptedException {
    final ExecutorService executor = Executors.newCachedThreadPool();
    final Future<Integer> future = executor.submit(() -> {
      final Random random = new Random();
      final int duration = random.nextInt(4000);

      if (duration > 2000) {
        throw new IOException("Sleeping for too long.");
      }

      System.out.println("Starting...");
      Thread.sleep(duration);
      System.out.println("Finished.");
      return duration;
    });

    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.DAYS);

    try {
      System.out.println("Result is: " + future.get());
    } catch (ExecutionException e) {
      IOException ex = (IOException) e.getCause();
      System.out.println(ex.getMessage());
    }
  }
}
