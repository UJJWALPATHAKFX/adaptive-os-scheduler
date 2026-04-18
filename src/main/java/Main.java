public class Main {

  public static void main(String[] args) {

      // Define your processes here — feel free to change these values
      int[][] data = {
          // {pid, arrivalTime, burstTime}
          {1, 0, 5},
          {2, 1, 3},
          {3, 2, 8},
          {4, 3, 6},
          {5, 4, 2}
      };

      int quantum = 2; // for Round Robin

      System.out.println("===========================================");
      System.out.println("   ADAPTIVE OS SCHEDULER — COMPARISON");
      System.out.println("===========================================");

      // Run FCFS
      Process[] p1 = createProcesses(data);
      FCFS.schedule(p1);
      printResults(p1, "FCFS — First Come First Serve");

      // Run SRTF
      Process[] p2 = createProcesses(data);
      SRTF.schedule(p2);
      printResults(p2, "SRTF — Shortest Remaining Time First");

      // Run Round Robin
      Process[] p3 = createProcesses(data);
      RoundRobin.schedule(p3, quantum);
      printResults(p3, "Round Robin — Quantum = " + quantum);

      // Print summary comparison
      printSummary(p1, p2, p3);
  }

  // Creates a fresh array of processes from raw data
  static Process[] createProcesses(int[][] data) {
      Process[] processes = new Process[data.length];
      for (int i = 0; i < data.length; i++) {
          processes[i] = new Process(data[i][0], data[i][1], data[i][2]);
      }
      return processes;
  }

  // Prints detailed table for one algorithm
  static void printResults(Process[] processes, String algoName) {
      System.out.println("\n--- " + algoName + " ---");
      System.out.printf("%-6s %-10s %-10s %-12s %-12s %-12s%n",
              "PID", "Arrival", "Burst", "Completion", "Turnaround", "Waiting");
      System.out.println("------------------------------------------------------------");

      for (Process p : processes) {
          System.out.printf("%-6d %-10d %-10d %-12d %-12d %-12d%n",
                  p.pid, p.arrivalTime, p.burstTime,
                  p.completionTime, p.turnaroundTime, p.waitingTime);
      }

      // Calculate averages
      double avgTAT = 0, avgWT = 0;
      for (Process p : processes) {
          avgTAT += p.turnaroundTime;
          avgWT  += p.waitingTime;
      }
      avgTAT /= processes.length;
      avgWT  /= processes.length;

      System.out.printf("%nAvg Turnaround Time : %.2f%n", avgTAT);
      System.out.printf("Avg Waiting Time    : %.2f%n", avgWT);
  }

  // Prints side by side summary of all three algorithms
  static void printSummary(Process[] fcfs, Process[] srtf, Process[] rr) {
      System.out.println("\n===========================================");
      System.out.println("   SUMMARY COMPARISON");
      System.out.println("===========================================");
      System.out.printf("%-35s %-15s %-15s%n", "Algorithm", "Avg TAT", "Avg WT");
      System.out.println("-------------------------------------------");

      System.out.printf("%-35s %-15.2f %-15.2f%n",
              "FCFS", avgTAT(fcfs), avgWT(fcfs));
      System.out.printf("%-35s %-15.2f %-15.2f%n",
              "SRTF", avgTAT(srtf), avgWT(srtf));
      System.out.printf("%-35s %-15.2f %-15.2f%n",
              "Round Robin (q=2)", avgTAT(rr), avgWT(rr));

      System.out.println("===========================================");
  }

  static double avgTAT(Process[] p) {
      double sum = 0;
      for (Process x : p) sum += x.turnaroundTime;
      return sum / p.length;
  }

  static double avgWT(Process[] p) {
      double sum = 0;
      for (Process x : p) sum += x.waitingTime;
      return sum / p.length;
  }
}