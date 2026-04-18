public class Process {
  int pid;             // Process ID
  int arrivalTime;     // When process arrives in the ready queue
  int burstTime;       // Total CPU time the process needs
  int remainingTime;   // Used by SRTF — decreases as process executes
  int completionTime;  // When the process finishes
  int waitingTime;     // Time spent waiting in the queue
  int turnaroundTime;  // completionTime - arrivalTime

  // Constructor
  public Process(int pid, int arrivalTime, int burstTime) {
      this.pid = pid;
      this.arrivalTime = arrivalTime;
      this.burstTime = burstTime;
      this.remainingTime = burstTime; // starts equal to burst time
  }
}