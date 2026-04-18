public class SRTF {

  public static void schedule(Process[] processes) {

      int n = processes.length;
      int completed = 0;
      int currentTime = 0;
      int shortest = -1;
      int minRemaining = Integer.MAX_VALUE;
      boolean found = false;

      // Reset remaining times
      for (Process p : processes) {
          p.remainingTime = p.burstTime;
      }

      // Keep running until all processes are done
      while (completed != n) {

          // Find process with shortest remaining time at currentTime
          minRemaining = Integer.MAX_VALUE;
          found = false;

          for (int i = 0; i < n; i++) {
              Process p = processes[i];

              if (p.arrivalTime <= currentTime
                      && p.remainingTime > 0
                      && p.remainingTime < minRemaining) {
                  minRemaining = p.remainingTime;
                  shortest = i;
                  found = true;
              }
          }

          // No process available — CPU is idle, jump time forward
          if (!found) {
              currentTime++;
              continue;
          }

          // Run the shortest process for 1 unit of time
          processes[shortest].remainingTime--;
          currentTime++;

          // Check if this process just finished
          if (processes[shortest].remainingTime == 0) {
              completed++;
              Process p = processes[shortest];
              p.completionTime  = currentTime;
              p.turnaroundTime  = p.completionTime - p.arrivalTime;
              p.waitingTime     = p.turnaroundTime - p.burstTime;
          }
      }
  }
}