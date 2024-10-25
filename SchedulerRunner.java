import java.io.*;
import java.util.*;

public class SchedulerRunner {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java SchedulerRunner <schedule_file>");
            return;
        }

        String filename = args[0];

        // List of algorithms to run
        String[] algorithms = {"FCFS", "SJF", "PriorityScheduling", "RR", "PriorityWithRR"};

        for (String algorithm : algorithms) {
            System.out.println("\n--- Running " + algorithm + " ---");
            runAlgorithm(algorithm, filename);
        }
    }

    private static void runAlgorithm(String algorithm, String filename) {
        try {
            // Build the command to run the respective algorithm
            ProcessBuilder pb = new ProcessBuilder("java", algorithm, filename);
            Process process = pb.start();

            // Read the output of the algorithm
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the algorithm to finish
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            System.err.println("Error running " + algorithm + ": " + e.getMessage());
        }
    }
}
