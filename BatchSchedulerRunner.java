//import java.io.*;
//import java.util.*;
//
//public class BatchSchedulerRunner {
//
//    public static void main(String[] args) {
//        // Directory where the generated schedule files are stored
//        String directoryPath = "data/";
//
//        // List of algorithms to run
//        String[] algorithms = {"FCFS", "SJF", "PriorityScheduling", "RR", "PriorityWithRR"};
//
//        // Get all the schedule files in the directory
//        File folder = new File(directoryPath);
//        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".txt"));
//        System.out.println("Number  of files: "+listOfFiles.length);
//
//        if (listOfFiles == null || listOfFiles.length == 0) {
//            System.out.println("No schedule files found in the directory: " + directoryPath);
//            return;
//        }
//
//        // Loop through each schedule file
//        for (File scheduleFile : listOfFiles) {
//            System.out.println("\nRunning algorithms for file: " + scheduleFile.getName());
//
//            // Loop through each algorithm for the current file
//            for (String algorithm : algorithms) {
//                System.out.println("\n--- Running " + algorithm + " on " + scheduleFile.getName() + " ---");
//                runAlgorithm(algorithm, scheduleFile.getPath());
//            }
//        }
//    }
//
//    private static void runAlgorithm(String algorithm, String filename) {
//        try {
//            // Build the command to run the respective algorithm
//            ProcessBuilder pb = new ProcessBuilder("java", algorithm, filename);
//            Process process = pb.start();
//
//            // Read the output of the algorithm
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            // Wait for the algorithm to finish
//            process.waitFor();
//
//        } catch (IOException | InterruptedException e) {
//            System.err.println("Error running " + algorithm + ": " + e.getMessage());
//        }
//    }
//}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BatchSchedulerRunner {

    public static void main(String[] args) {
        String directoryPath = "data/";
        String[] algorithms = {"FCFS", "SJF", "PriorityScheduling", "RR", "PriorityWithRR"};
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        if (listOfFiles == null || listOfFiles.length == 0) {
            System.out.println("No schedule files found in the directory: " + directoryPath);
            return;
        }

        Map<String, Map<String, ResultMetrics>> allResults = new HashMap<>();

        // Loop through each schedule file
        for (File scheduleFile : listOfFiles) {
            System.out.println("-------------------------------------------------------------");
            System.out.println("\nRunning algorithms for file: " + scheduleFile.getName());

            // Create a map to store results for this file
            Map<String, ResultMetrics> fileResults = new HashMap<>();

            // Loop through each algorithm for the current file
            for (String algorithm : algorithms) {
                System.out.println("\n--- Running " + algorithm + " on " + scheduleFile.getName() + " ---");
                ResultMetrics metrics = runAlgorithm(algorithm, scheduleFile.getPath());
                fileResults.put(algorithm, metrics);
            }

            // Store the results of this file in allResults
            allResults.put(scheduleFile.getName(), fileResults);
        }

        // Save all results to text file
        System.out.println("-------------------------------------------------------------");
        saveResultsToTxt(allResults, "all_results.txt");
    }

    private static ResultMetrics runAlgorithm(String algorithm, String filename) {
        try {
            ProcessBuilder pb = new ProcessBuilder("java", algorithm, filename);
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            ResultMetrics metrics = new ResultMetrics();
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("Average Waiting Time")) {
                    metrics.waitingTime = Double.parseDouble(line.split(":")[1].trim());
                } else if (line.contains("Average Turnaround Time")) {
                    metrics.turnaroundTime = Double.parseDouble(line.split(":")[1].trim());
                }
            }

            process.waitFor();
            return metrics;

        } catch (IOException | InterruptedException e) {
            System.err.println("Error running " + algorithm + ": " + e.getMessage());
            return null;
        }
    }

    private static void saveResultsToTxt(Map<String, Map<String, ResultMetrics>> allResults, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Map.Entry<String, Map<String, ResultMetrics>> fileEntry : allResults.entrySet()) {
                writer.write("Results for: " + fileEntry.getKey() + "\n");
                for (Map.Entry<String, ResultMetrics> algorithmEntry : fileEntry.getValue().entrySet()) {
                    ResultMetrics metrics = algorithmEntry.getValue();
                    writer.write("Algorithm: " + algorithmEntry.getKey() + "\n");
                    writer.write("  Average Waiting Time: " + metrics.waitingTime + "\n");
                    writer.write("  Average Turnaround Time: " + metrics.turnaroundTime + "\n");
                    writer.write("\n");
                }
                writer.write("---------------------------------------------------\n");
            }
            System.out.println("All results saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to text file: " + e.getMessage());
        }
    }
}

//class ResultMetrics {
//    double waitingTime;
//    double turnaroundTime;
//}
