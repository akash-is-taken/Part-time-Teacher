import java.util.ArrayList;
import java.util.List;


public class Teacher {

    // Attributes
    private int id;
    private String name;
    private String password;
    private int availableSlots;
    private List<String> coursesCanTeach;

    // Constructs a Teacher with the given ID, name, and password.
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

    // Returns the teacher's available weekly teaching slots.
    public int getAvailableSlots() {
        return availableSlots;
    }

    //Updates the teacher's available weekly teaching slots.
    public void setAvailableSlots(int slots) {
        if (slots < 0) {
            System.out.println("Available slots cannot be negative. Setting to 0.");
            this.availableSlots = 0;
        } else {
            this.availableSlots = slots;
        }
    }

    //Returns a list of course names that this teacher can teach.
    public List<String> getCoursesCanTeach() {
        return coursesCanTeach;
    }

    //Sets the list of courses this teacher can teach.
    public void setCoursesCanTeach(List<String> courses) {
        if (courses != null) {
            this.coursesCanTeach = courses;
        }
    }


    //Allows the teacher to view all courses they are currently eligible to teach.
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


    //Selects a course for teaching by ID or name.
    public void select_course(int courseId) {
        System.out.println("Selected course with ID: " + courseId);
    }


    //Views the teacher's current training status.
    public void view_training_status() {
        System.out.println("Training Status: [Demo] No training data available.");
    }


    //Marks a particular training as completed.
    public void training_completed(int trainingId) {
        System.out.println("Training with ID " + trainingId + " marked as completed.");
    }


    //Authentication check.
    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}

