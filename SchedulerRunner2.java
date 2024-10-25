import java.io.*;

public class SchedulerRunner2 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java SchedulerRunner <schedule_file>");
            return;
        }

        String filename = args[0];

        System.out.println("\n--- Running FCFS ---");
        FCFS.main(new String[]{filename});

        System.out.println("\n--- Running SJF ---");
        SJF.main(new String[]{filename});

        System.out.println("\n--- Running Priority Scheduling ---");
        PriorityScheduling.main(new String[]{filename});

        System.out.println("\n--- Running RR ---");
        RR.main(new String[]{filename});

        System.out.println("\n--- Running Priority RR ---");
        PriorityWithRR.main(new String[]{filename});
    }
}
