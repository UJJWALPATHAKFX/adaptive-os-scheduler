import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin {

    public static void schedule(Process[] processes, int quantum) {

        int n = processes.length;
        int currentTime = 0;
        int completed = 0;

        // Reset remaining times
        for (Process p : processes) {
            p.remainingTime = p.burstTime;
        }

        // Queue holds the order of processes waiting to run
        Queue<Process> queue = new LinkedList<>();

        // Sort by arrival time first
        java.util.Arrays.sort(processes, (a, b) -> a.arrivalTime - b.arrivalTime);

        // Add first process to queue
        queue.add(processes[0]);
        int i = 1; // index to track which processes have been added

        while (completed != n) {

            // If queue is empty but processes still remain, jump time forward
            if (queue.isEmpty()) {
                currentTime = processes[i].arrivalTime;
                queue.add(processes[i]);
                i++;
            }

            // Pick next process from front of queue
            Process p = queue.poll();

            // Run for quantum or remaining time, whichever is smaller
            int runTime = Math.min(quantum, p.remainingTime);
            p.remainingTime -= runTime;
            currentTime += runTime;

            // Add any newly arrived processes to the queue
            while (i < n && processes[i].arrivalTime <= currentTime) {
                queue.add(processes[i]);
                i++;
            }

            // If process still has work left, put it back at end of queue
            if (p.remainingTime > 0) {
                queue.add(p);
            } else {
                // Process finished
                p.completionTime  = currentTime;
                p.turnaroundTime  = p.completionTime - p.arrivalTime;
                p.waitingTime     = p.turnaroundTime - p.burstTime;
                completed++;
            }
        }
    }
}