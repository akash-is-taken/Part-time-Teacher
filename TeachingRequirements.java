import java.util.ArrayList;
import java.util.List;

public class TeachingRequirements {
    private List<Course> courses;
    private int slotsRequired;

    public TeachingRequirements() {
        this.courses = new ArrayList<>();
        this.slotsRequired = 0;
    }

    public void addCourse(Course course, int slotsRequired) {
        this.courses.add(course);
        this.slotsRequired = slotsRequired;
        System.out.println("Course added: " + course.getName() + ", Slots Required: " + slotsRequired);
    }

    public void viewRequirements() {
        System.out.println("Teaching Requirements:");
        for (Course course : courses) {
            System.out.println(course.toString() + ", Slots Required: " + slotsRequired);
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public int getSlotsRequired() {
        return slotsRequired;
    }

    public void saveRequirementsToFile(FileManager fileManager, String filepath) {
        // For demonstration, we build a string with course information.
        StringBuilder content = new StringBuilder();
        content.append("Teaching Requirements:\n");
        for (Course course : courses) {
            content.append(course.toString())
                   .append(", Slots Required: ")
                   .append(slotsRequired)
                   .append("\n");
        }
        FileManager.writeToFile(filepath, content.toString());
    }
}
