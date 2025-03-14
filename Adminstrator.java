import java.io.*;
import java.util.*;

class Administrator {
    private String username;

    public Administrator(String username) {
        this.username = username;
    }

    // Assign a teacher to a course
    public void assignTeacherToCourse(String teacherName, String courseName) {
        String assignment = teacherName + " -> " + courseName;
        FileManager.writeToFile("assignments.txt", assignment);
        System.out.println("Assigned " + teacherName + " to " + courseName);
    }

    // Organize training for a teacher
    public void organizeTraining(String teacherName, String courseName, int requiredSessions) {
        String trainingData = teacherName + "," + courseName + "," + requiredSessions + "
                Pending";
        FileManager.writeToFile("training_sessions.txt", trainingData);
        System.out.println("Training organized for " + teacherName + " on " + courseName);
    }

    // Update training details after sessions
    public void updateTraining(String teacherName, String courseName, int completedSessions) {
        List<String> trainingList = FileManager.readFromFile("training_sessions.txt");
        List<String> updatedList = new ArrayList<>();
        boolean found = false;

        for (String training : trainingList) {
            String[] data = training.split(",");
            if (data[0].equals(teacherName) && data[1].equals(courseName)) {
                int requiredSessions = Integer.parseInt(data[2]);
                if (completedSessions >= requiredSessions) {
                    updatedList.add(teacherName + "," + courseName + "," + requiredSessions + ",Completed");
                } else {
                    updatedList.add(teacherName + "," + courseName + "," + requiredSessions + ",In Progress");
                }
                found = true;
            } else {
                updatedList.add(training);
            }
        }

        if (found) {
            FileManager.writeToFileOverwrite("training_sessions.txt", updatedList);
            System.out.println("Updated training status for " + teacherName + " on " + courseName);
        } else {
            System.out.println("Training record not found.");
        }
    }

    // View pending training requests
    public void viewPendingRequests() {
        List<String> trainingList = FileManager.readFromFile("training_sessions.txt");
        System.out.println("Pending Training Requests:");
        for (String training : trainingList) {
            if (training.contains("Pending") || training.contains("In Progress")) {
                System.out.println(training);
            }
        }
    }
}
