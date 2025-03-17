import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private int id;
    private String name;
    private String password;
    private int availableSlots;
    private List<String> coursesCanTeach;

    public Teacher(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.availableSlots = 0;
        this.coursesCanTeach = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int slots) {
        if (slots < 0) {
            System.out.println("Available slots cannot be negative. Setting to 0.");
            this.availableSlots = 0;
        } else {
            this.availableSlots = slots;
        }
    }

    public List<String> getCoursesCanTeach() {
        return coursesCanTeach;
    }

    public void setCoursesCanTeach(List<String> courses) {
        if (courses != null) {
            this.coursesCanTeach = courses;
        }
    }

    public void viewAvailableCourses() {
        System.out.println("Courses you can teach:");
        if (coursesCanTeach.isEmpty()) {
            System.out.println(" - None (no courses set)");
        } else {
            for (String course : coursesCanTeach) {
                System.out.println(" - " + course);
            }
        }
    }

    public void selectCourse(int courseId) {
        System.out.println("Selected course with ID: " + courseId);
    }

    /**
     * Teachers submit a new training application, defaulting to "Pending" status.
     * Format: TeacherName,CourseName,RequestedSessions,Pending
     */
    public void submitTrainingApplication(String courseName, int requestedSessions) {
        String application = this.name + "," + courseName + "," + requestedSessions + ",Pending";
        FileManager.writeToFile("training_sessions.txt", application);
        System.out.println("Training application submitted for course " + courseName + " [Pending]");
    }

    /**
     * Displays all lines from "training_sessions.txt" that match this teacher's name.
     * Shows any status: Pending, In Training, Completed, etc.
     */
    public void viewTrainingStatus() {
        System.out.println("Training Status for " + name + ":");
        List<String> lines = FileManager.readFromFile("training_sessions.txt");
        boolean found = false;
        for (String line : lines) {
            if (line.startsWith(this.name + ",")) {
                System.out.println(line);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No training requests found for " + name);
        }
    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}
