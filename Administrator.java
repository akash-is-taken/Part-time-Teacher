import java.util.ArrayList;
import java.util.List;

public class Administrator {
    private String username;
    private String password;

    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    /**
     * Assigns a teacher to a course (unrelated to training status).
     */
    public void assignTeacherToCourse(String teacherName, String courseName) {
        String assignment = teacherName + " -> " + courseName;
        FileManager.writeToFile("assignments.txt", assignment);
        System.out.println("Assigned " + teacherName + " to " + courseName);
    }

    /**
     * "Organizes" training by finding a "Pending" record for teacherName/courseName
     * and updating it to "In Training". If not found, we create a new record with "In Training".
     */
    public void organizeTraining(String teacherName, String courseName, int requiredSessions) {
        List<String> trainingList = FileManager.readFromFile("training_sessions.txt");
        List<String> updatedList = new ArrayList<>();
        boolean foundPending = false;

        for (String line : trainingList) {
            String[] data = line.split(",");
            if (data.length == 4
                && data[0].equals(teacherName)
                && data[1].equals(courseName)
                && data[3].equals("Pending")) {
                // Found a matching "Pending" request, update to "In Training"
                updatedList.add(teacherName + "," + courseName + "," + requiredSessions + ",In Training");
                foundPending = true;
            } else {
                updatedList.add(line);
            }
        }

        if (!foundPending) {
            // If no "Pending" record found, create a new one
            updatedList.add(teacherName + "," + courseName + "," + requiredSessions + ",In Training");
        }

        FileManager.writeToFileOverwrite("training_sessions.txt", updatedList);
        System.out.println("Training organized for " + teacherName + " on " + courseName + " [In Training]");
    }

    /**
     * Updates training by setting "Completed" if completedSessions >= requiredSessions;
     * otherwise it remains "In Training".
     */
    public void updateTraining(String teacherName, String courseName, int completedSessions) {
        List<String> trainingList = FileManager.readFromFile("training_sessions.txt");
        List<String> updatedList = new ArrayList<>();
        boolean found = false;

        for (String training : trainingList) {
            String[] data = training.split(",");
            if (data.length == 4
                && data[0].equals(teacherName)
                && data[1].equals(courseName)) {
                int requiredSessions = Integer.parseInt(data[2]);
                if (completedSessions >= requiredSessions) {
                    updatedList.add(teacherName + "," + courseName + "," + requiredSessions + ",Completed");
                } else {
                    updatedList.add(teacherName + "," + courseName + "," + requiredSessions + ",In Training");
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
            System.out.println("Training record not found for " + teacherName + " on " + courseName);
        }
    }

    /**
     * Shows any line in "training_sessions.txt" that contains "Pending" or "In Training".
     * (If you also want to see "Completed", you can add it to the condition or create a separate view.)
     */
    public void viewPendingRequests() {
        List<String> trainingList = FileManager.readFromFile("training_sessions.txt");
        System.out.println("Pending or In-Training Requests:");
        for (String training : trainingList) {
            if (training.contains("Pending") || training.contains("In Training")) {
                System.out.println(training);
            }
        }
    }
}
