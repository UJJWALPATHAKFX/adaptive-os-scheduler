import java.util.Arrays;

public class FCFS {

    public static void schedule(Process[] processes) {

        // Step 1 — Sort processes by arrival time
        Arrays.sort(processes, (a, b) -> a.arrivalTime - b.arrivalTime);

        int currentTime = 0;

        for (int i = 0; i < processes.length; i++) {
            Process p = processes[i];

            // If CPU is free and process hasn't arrived yet, jump to its arrival
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }

            // Process runs from currentTime until it finishes
            p.completionTime = currentTime + p.burstTime;
            p.turnaroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime    = p.turnaroundTime - p.burstTime;

            // Move time forward
            currentTime = p.completionTime;
        }
    }
}