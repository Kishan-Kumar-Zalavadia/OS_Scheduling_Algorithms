import java.io.*;
import java.util.*;

public class BatchSchedulerRunner {

    public static void main(String[] args) {
        // Directory where the generated schedule files are stored
        String directoryPath = "data/";

        // List of algorithms to run
        String[] algorithms = {"FCFS", "SJF", "PriorityScheduling", "RR", "PriorityWithRR"};

        // Get all the schedule files in the directory
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        System.out.println("Number  of files: "+listOfFiles.length);

        if (listOfFiles == null || listOfFiles.length == 0) {
            System.out.println("No schedule files found in the directory: " + directoryPath);
            return;
        }

        // Loop through each schedule file
        for (File scheduleFile : listOfFiles) {
            System.out.println("\nRunning algorithms for file: " + scheduleFile.getName());

            // Loop through each algorithm for the current file
            for (String algorithm : algorithms) {
                System.out.println("\n--- Running " + algorithm + " on " + scheduleFile.getName() + " ---");
                runAlgorithm(algorithm, scheduleFile.getPath());
            }
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