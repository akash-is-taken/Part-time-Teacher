import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Teacher in the system.
 * 
 * Responsibilities:
 *  - Store teacher data (ID, name, password, available slots).
 *  - Manage courses that the teacher can teach.
 *  - Provide methods to view/select courses and training status.
 */
public class Teacher {

    // Attributes
    private int id;
    private String name;
    private String password;
    private int availableSlots;
    private List<String> coursesCanTeach;

    /**
     * Constructs a Teacher with the given ID, name, and password.
     * 
     * @param id        The teacher's unique ID.
     * @param name      The teacher's name.
     * @param password  The teacher's password (hashed in a real system).
     */
    public Teacher(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.availableSlots = 0;
        this.coursesCanTeach = new ArrayList<>();
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the teacher's available weekly teaching slots.
     */
    public int getAvailableSlots() {
        return availableSlots;
    }

    /**
     * Updates the teacher's available weekly teaching slots.
     * 
     * @param slots The number of weekly slots the teacher can commit to.
     */
    public void setAvailableSlots(int slots) {
        if (slots < 0) {
            System.out.println("Available slots cannot be negative. Setting to 0.");
            this.availableSlots = 0;
        } else {
            this.availableSlots = slots;
        }
    }

    /**
     * Returns a list of course names that this teacher can teach.
     */
    public List<String> getCoursesCanTeach() {
        return coursesCanTeach;
    }

    /**
     * Sets the list of courses this teacher can teach.
     * 
     * @param courses A list of course names or IDs that the teacher is eligible for.
     */
    public void setCoursesCanTeach(List<String> courses) {
        if (courses != null) {
            this.coursesCanTeach = courses;
        }
    }

    // Methods from the class diagram

    /**
     * Allows the teacher to view all courses they are currently eligible to teach.
     * In a full system, this might retrieve data from a CourseManager or Database.
     */
    public void view_available_courses() {
        System.out.println("Courses you can teach:");
        if (coursesCanTeach.isEmpty()) {
            System.out.println(" - None (no courses set)");
        } else {
            for (String course : coursesCanTeach) {
                System.out.println(" - " + course);
            }
        }
    }

    /**
     * Selects a course for teaching by ID or name.
     * In a real system, you'd validate the course ID or name and update the schedule.
     * 
     * @param courseId The identifier of the course to teach.
     */
    public void select_course(int courseId) {
        // In a real system, you'd check if courseId is valid and in coursesCanTeach.
        // For now, we simply print a message for demonstration.
        System.out.println("Selected course with ID: " + courseId);
        // Potentially update availability or schedule here.
    }

    /**
     * Views the teacher's current training status.
     * In a real system, you might retrieve data from a TrainingManager or TrainingDetail object.
     */
    public void view_training_status() {
        // Stub: In a complete system, retrieve actual training info.
        System.out.println("Training Status: [Demo] No training data available.");
    }

    /**
     * Marks a particular training as completed.
     * 
     * @param trainingId The identifier of the completed training.
     */
    public void training_completed(int trainingId) {
        // Stub: In a real system, you'd update the teacher's training record.
        System.out.println("Training with ID " + trainingId + " marked as completed.");
    }

    /**
     * Basic authentication check.
     * In a real system, password should be hashed/salted, 
     * and checked via a UserManager or similar.
     * 
     * @param inputPassword The password to check.
     * @return True if the password matches, false otherwise.
     */
    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}

