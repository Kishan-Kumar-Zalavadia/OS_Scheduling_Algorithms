import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ScheduleFileGenerator {
    // Constants
    static final int MIN_PRIORITY = 1;
    static final int MAX_PRIORITY = 15;
    static final int MIN_BURST_TIME = 5;  // in milliseconds
    static final int MAX_BURST_TIME = 50; // in milliseconds
    static final String OUTPUT_FOLDER = "data"; // Folder where files will be saved

    // Method to generate random test cases and write them to files
    public static void generateScheduleFiles(int numProcesses, int numFiles) {
        Random random = new Random();
        File folder = new File(OUTPUT_FOLDER);

        // Create the output folder if it doesn't exist
        if (!folder.exists()) {
            folder.mkdir();
        }

        for (int fileNum = 1; fileNum <= numFiles; fileNum++) {
            String fileName = OUTPUT_FOLDER + "/schedule_" + numProcesses + "_processes_" + fileNum + ".txt";

            try (FileWriter writer = new FileWriter(fileName)) {
                // Generate each process
                for (int processNum = 1; processNum <= numProcesses; processNum++) {
                    String taskName = "T" + processNum;
                    int priority = random.nextInt((MAX_PRIORITY - MIN_PRIORITY) + 1) + MIN_PRIORITY;
                    int cpuBurst = random.nextInt((MAX_BURST_TIME - MIN_BURST_TIME) + 1) + MIN_BURST_TIME;

                    // Write to file in the format: [task name] [priority] [CPU burst]
                    writer.write(taskName + ", " + priority + ", " + cpuBurst + "\n");
                }

                System.out.println("Generated file: " + fileName);

            } catch (IOException e) {
                System.err.println("Error writing to file: " + fileName);
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Generate 7 files for 5 processes
        generateScheduleFiles(5, 7);

        // Generate 7 files for 10 processes
        generateScheduleFiles(10, 7);

        // Generate 7 files for 15 processes
        generateScheduleFiles(15, 7);
    }
}
