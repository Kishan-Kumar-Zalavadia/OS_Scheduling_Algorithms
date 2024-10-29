# Scheduling Algorithms

This project implements several CPU scheduling algorithms. The scheduler assigns a predefined set of tasks, each with a priority and CPU burst time, and schedules them according to the selected algorithm.

## Project Overview

The project includes implementations of the following scheduling algorithms:
1. **First-Come, First-Served (FCFS)** – Schedules tasks in the order they request the CPU.
2. **Shortest-Job-First (SJF)** – Schedules tasks based on the shortest CPU burst.
3. **Priority Scheduling** – Schedules tasks based on their priority.
4. **Round-Robin (RR)** – Assigns each task a time quantum of 10 milliseconds, cycling through tasks.
5. **Priority with Round-Robin** – Schedules tasks by priority and applies round-robin for tasks with equal priority.

### Project Details

- **Priorities**: Tasks have priority values from 1 to 15, where a higher number means a higher priority.
- **Time Quantum**: For round-robin scheduling, each task is given 10 milliseconds per CPU cycle.

## File Format and Inputs

Each algorithm takes a schedule file as input. The schedule file contains tasks in the following format:

# Project Execution Instructions

## Step 1: Compile all the algorithms
Use the following command to complete all the Java programs:

•	javac Task.java FCFS.java SJF.java PriorityScheduling.java RR.java PriorityWithRR.java


## Step 2: (Optional) Execute individual algorithms for a single file.

Use the following commands to run each algorithm for a particular file.

•	java SJF data/schedule.txt

•	java FCFS data/schedule.txt

•	java RR data/schedule.txt

•	java PriorityScheduling data/schedule.txt

•	java PriorityWithRR data/schedule.txt

Assumption: the file that has the process is named ‘schedule.txt’ and is located in the folder named ‘data.’

## Step 3: Create test cases
	
generateScheduleFiles.java has the code to generate test cases.

To execute the file run the following commands:

•	javac ScheduleFileGenerator.java

•	java ScheduleFileGenerator

## Step 4: (Optional) Run all the algorithms for a single file

To run all five algorithms for a single file, run the following commands:

•	javac SchedulerRunner.java

•	java SchedulerRunner data/schedule.txt

Assumption: the file that has the process is named ‘schedule.txt’ and is located in the folder named ‘data.’

## Step 5: Run all the algorithms for all the files

To run and analyze all the algorithms for all the files present in the ‘data’ folder use the following commands.

•	javac AnalyzeScheduler.java

•	java AnalyzeScheduler

## Step 6: (Optional) Visualize the result.

To visualize the results, execute the Python code “plot_scheduling_results” in Google Collab.

# Visual Output:

<img width="511" alt="image" src="https://github.com/user-attachments/assets/3211018a-8f93-427e-96d3-8dc07e6c98f7">

<img width="513" alt="image" src="https://github.com/user-attachments/assets/a75b3e9c-8869-496c-bce2-ed840aced087">

<img width="503" alt="image" src="https://github.com/user-attachments/assets/21f780f6-71f3-40fe-98eb-5058621130bd">





